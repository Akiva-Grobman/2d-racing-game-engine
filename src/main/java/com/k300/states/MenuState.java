package com.k300.states;

import com.k300.Launcher;

import java.awt.*;

import com.k300.graphics.MenuBackground;
import com.k300.ui.*;
import com.k300.ui.buttons.*;
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
        UIButton playButton = getPlayButton(launcher);
        UIButton multiplayerButton = getOnlineButton(launcher, playButton);
        UIButton settingsButton = getSettingsButton(playButton);
        uiManager.addUIObject(playButton);
        uiManager.addUIObject(multiplayerButton);
        uiManager.addUIObject(settingsButton);
        uiManager.addUIObject(getExitButton(settingsButton));
    }

    private UIButton getPlayButton(Launcher launcher) {
        int y = (Converter.FHD_SCREEN_HEIGHT) / 8;
        ClickListener listener = launcher::startGame;
        return new UIButton(buttonsX, y, buttonWidth, buttonHeight, "PLAY", listener);
    }

    private UIButton getOnlineButton(Launcher launcher, UIButton playButton) {
        int y = (int) (playButton.getY() + (Converter.FHD_SCREEN_HEIGHT) / 3.5);
        ClickListener listener = () -> StateManager.setCurrentState(new OnlineState(launcher));
        return new UIButton(buttonsX, y, buttonWidth, buttonHeight,"ONLINE", listener);
    }

    private UIButton getSettingsButton(UIButton multiplayerButton) {
        int y = (int) (Converter.FHD_SCREEN_HEIGHT - (Converter.FHD_SCREEN_HEIGHT) / 3.5);
        ClickListener listener = () -> StateManager.setCurrentState(new SettingsState(launcher));
        return new UIButton(buttonsX, y, buttonWidth, buttonHeight,"SETTINGS", listener);
    }

    private UIButton getExitButton(UIButton settingsButton) {
        int y = (int) settingsButton.getY();
        ClickListener listener = Launcher::stop;
        return new UIButton(buttonsX, y, buttonWidth, buttonHeight,"EXIT", listener);
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