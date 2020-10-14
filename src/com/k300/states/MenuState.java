package com.k300.states;

import com.k300.Launcher;

import java.awt.*;

import com.k300.graphics.MenuBackground;
import com.k300.ui.*;
import com.k300.utils.math.Converter;

public class MenuState extends State {

    private final UIManager uiManager;
    private final MenuBackground background;

    public MenuState(Launcher launcher) {
        super(launcher);
        uiManager = new UIManager();
        background = new MenuBackground();
        launcher.getMouseListener().setUiManager(uiManager);
        UIPlayButton playButton = getPlayButton(launcher);
        UISettingsButton settingsButton = getSettingsButton(playButton);
        uiManager.addUIObject(playButton);
        uiManager.addUIObject(settingsButton);
        uiManager.addUIObject(getExitButton(settingsButton));
    }

    private UIPlayButton getPlayButton(Launcher launcher) {
        int width = Converter.FHD_SCREEN_WIDTH / 2;
        int height = Converter.FHD_SCREEN_HEIGHT / 4;
        int x = (Converter.FHD_SCREEN_WIDTH - width) / 2;
        int y = (Converter.FHD_SCREEN_HEIGHT) / 8;
        ClickListener listener = launcher::startGame;
        return new UIPlayButton(x, y, width, height, listener);
    }

    private UISettingsButton getSettingsButton(UIObject playButton) {
        int width = Converter.FHD_SCREEN_WIDTH / 2;
        int height = Converter.FHD_SCREEN_HEIGHT / 4;
        int x = (Converter.FHD_SCREEN_WIDTH - width) / 2;
        int y = (int) (playButton.getY() + (Converter.FHD_SCREEN_HEIGHT) / 3.5);
        return new UISettingsButton(x, y, width, height);
    }

    private UIObject getExitButton(UIObject settingsButton) {
        int width = Converter.FHD_SCREEN_WIDTH / 2;
        int height = Converter.FHD_SCREEN_HEIGHT / 4;
        int x = (Converter.FHD_SCREEN_WIDTH - width) / 2;
        int y = (int) (settingsButton.getY() + (Converter.FHD_SCREEN_HEIGHT) / 3.5);
        return new UIExitButton(x, y, width, height);
    }

    @Override
    public void tick() {
        background.tick();
        uiManager.tick();
    }

    @Override
    public void render(Graphics graphics) {
        background.render(graphics);
        uiManager.render(graphics);
    }

}