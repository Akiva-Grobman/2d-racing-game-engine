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
        buttonHeight = Converter.FHD_SCREEN_HEIGHT / 5;
        buttonsX = (Converter.FHD_SCREEN_WIDTH -  buttonWidth) / 2;
        UIMenuButton playButton = getPlayButton(launcher);
        UIMenuButton onlineButton = getOnlineButton(launcher, playButton);
        UIMenuButton settingsButton = getSettingsButton(onlineButton);
        uiManager.addUIObject(playButton);
        uiManager.addUIObject(onlineButton);
        uiManager.addUIObject(settingsButton);
        uiManager.addUIObject(getExitButton(settingsButton));
    }

    private UIMenuButton getPlayButton(Launcher launcher) {
        int y = (Converter.FHD_SCREEN_HEIGHT) / 8;
        ClickListener listener = launcher::startGame;
        return new UIMenuButton(buttonsX, y, buttonWidth, buttonHeight, "PLAY", listener);
    }

    private UIMenuButton getOnlineButton(Launcher launcher, UIMenuButton playButton) {
        int y = (int) (playButton.getY() + (Converter.FHD_SCREEN_HEIGHT) / 3.5);
        ClickListener listener = () -> StateManager.setCurrentState(new OnlineState(launcher));
        return new UIMenuButton(buttonsX, y, buttonWidth, buttonHeight,"ONLINE", listener);
    }

    private UIMenuButton getSettingsButton(UIMenuButton multiplayerButton) {
        int y = (int) (Converter.FHD_SCREEN_HEIGHT - (Converter.FHD_SCREEN_HEIGHT) / 3.5);
        ClickListener listener = () -> StateManager.setCurrentState(new SettingsState(launcher));
        return new UIMenuButton(buttonsX, y, buttonWidth/2, buttonHeight/2,"SETTINGS", 30, listener);
    }

    private UIMenuButton getExitButton(UIMenuButton settingsButton) {
        int y = (int) settingsButton.getY();
        ClickListener listener = Launcher::stop;
        return new UIMenuButton(buttonsX + buttonWidth/2f, y, buttonWidth/2, buttonHeight/2,"EXIT", 30, listener);
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