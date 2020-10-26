package com.k300.states;

import com.k300.Launcher;

import java.awt.*;

import com.k300.display.MenuBackground;
import com.k300.ui.*;
import com.k300.ui.buttons.*;
import com.k300.ui.listeners.ClickListener;
import com.k300.utils.math.Converter;

public class MenuState extends State {

    private final UIManager uiManager;
    private final MenuBackground background;

    private final double bigButtonWidth;
    private final double bigButtonHeight;
    private final double xBigButton;
    private final double startingYBigButton;
    private final double bigButtonMargin;


    private final double smallButtonWidth;
    private final double smallButtonHeight;
    private final double smallButtonMargin;

    private final int bigButtonFontSize;
    private final int smallButtonFontSize;


    public MenuState(Launcher launcher) {
        super(launcher);
        uiManager = new UIManager();
        background = new MenuBackground();
        launcher.getMouseListener().setUiManager(uiManager);

        bigButtonWidth = Converter.FHD_SCREEN_WIDTH / 2.5f;
        bigButtonHeight = Converter.FHD_SCREEN_HEIGHT / 4.5f;

        xBigButton = (Converter.FHD_SCREEN_WIDTH - bigButtonWidth) / 2;
        startingYBigButton = (Converter.FHD_SCREEN_HEIGHT) / 8.5f;
        bigButtonMargin = bigButtonHeight * 1.4;

        smallButtonMargin = 40;
        smallButtonWidth = (bigButtonWidth - smallButtonMargin) / 2f;
        smallButtonHeight = bigButtonHeight / 1.8;

        bigButtonFontSize = 100;
        smallButtonFontSize = 40;

        UIMenuButton playButton = getPlayButton(launcher);
        UIMenuButton onlineButton = getOnlineButton(launcher, playButton);
        UIMenuButton settingsButton = getSettingsButton(onlineButton);
        uiManager.addUIObject(playButton);
        uiManager.addUIObject(onlineButton);
        uiManager.addUIObject(settingsButton);
        uiManager.addUIObject(getExitButton(settingsButton));
    }

    private UIMenuButton getPlayButton(Launcher launcher) {
        ClickListener listener = launcher::startOfflineGame;
        return new UIMenuButton((int) xBigButton, (int) startingYBigButton, (int) bigButtonWidth, (int) bigButtonHeight, "PLAY", bigButtonFontSize, listener);
    }

    private UIMenuButton getOnlineButton(Launcher launcher, UIMenuButton playButton) {
        ClickListener listener = () -> StateManager.setCurrentState(new OnlineState(launcher));
        return new UIMenuButton((int) xBigButton, (int) (playButton.getY() + bigButtonMargin), (int) bigButtonWidth, (int) bigButtonHeight,"ONLINE", bigButtonFontSize, listener);
    }

    private UIMenuButton getSettingsButton(UIMenuButton multiplayerButton) {
        ClickListener listener = () -> StateManager.setCurrentState(new SettingsState(launcher));
        return new UIMenuButton((int) xBigButton, (int) (multiplayerButton.getY() + bigButtonMargin), (int) smallButtonWidth, (int) smallButtonHeight,"SETTINGS", smallButtonFontSize, listener);
    }

    private UIMenuButton getExitButton(UIMenuButton settingsButton) {
        ClickListener listener = Launcher::stop;
        return new UIMenuButton((int) (xBigButton + smallButtonWidth + smallButtonMargin), (int) settingsButton.getY(), (int) smallButtonWidth, (int) smallButtonHeight,"EXIT", smallButtonFontSize, listener);
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