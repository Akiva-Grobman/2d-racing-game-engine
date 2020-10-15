package com.k300.states;

import com.k300.Launcher;

import java.awt.*;

import com.k300.graphics.MenuBackground;
import com.k300.ui.*;
import com.k300.ui.buttons.UIExitButton;
import com.k300.ui.buttons.UIButton;
import com.k300.ui.buttons.UIPlayButton;
import com.k300.ui.buttons.UISettingsButton;
import com.k300.ui.listeners.ClickListener;
import com.k300.utils.math.Converter;

public class MenuState extends State {

    private final UIManager uiManager;
    private final MenuBackground background;
    private final int buttonWidth;
    private final int buttonHeight;
    private final int buttonsX;

    public MenuState(Launcher launcher) {
        super(launcher);
        uiManager = new UIManager();
        background = new MenuBackground();
        launcher.getMouseListener().setUiManager(uiManager);
        buttonWidth = Converter.FHD_SCREEN_WIDTH / 3;
        buttonHeight = Converter.FHD_SCREEN_HEIGHT / 4;
        buttonsX = (Converter.FHD_SCREEN_WIDTH -  buttonWidth) / 2;
        UIPlayButton playButton = getPlayButton(launcher);
        UISettingsButton settingsButton = getSettingsButton(playButton);
        uiManager.addUIObject(playButton);
        uiManager.addUIObject(settingsButton);
        uiManager.addUIObject(getExitButton(settingsButton));
    }

    private UIPlayButton getPlayButton(Launcher launcher) {
        int y = (Converter.FHD_SCREEN_HEIGHT) / 8;
        ClickListener listener = launcher::startGame;
        return new UIPlayButton(buttonsX, y, buttonWidth, buttonHeight, listener);
    }

    private UISettingsButton getSettingsButton(UIButton playButton) {
        int y = (int) (playButton.getY() + (Converter.FHD_SCREEN_HEIGHT) / 3.5);
        return new UISettingsButton(buttonsX, y, buttonWidth, buttonHeight, launcher);
    }

    private UIButton getExitButton(UIButton settingsButton) {
        int y = (int) (settingsButton.getY() + (Converter.FHD_SCREEN_HEIGHT) / 3.5);
        return new UIExitButton(buttonsX, y, buttonWidth, buttonHeight);
    }

    @Override
    public void tick() {
        background.tick();
    }

    @Override
    public void render(Graphics graphics) {
        background.render(graphics);
        uiManager.render(graphics);
    }

}