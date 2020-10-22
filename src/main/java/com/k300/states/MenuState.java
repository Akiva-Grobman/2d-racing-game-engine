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
    private final double bigButtonWidth;
    private final double bigButtonHeight;
    private final double buttonWidth;
    private final double buttonHeight;
    private final double xBigButton;
    private final double startingYBigButton;
    private final double bigButtonMargin;
    private final double buttonMargin;

    public MenuState(Launcher launcher) {
        super(launcher);
        uiManager = new UIManager();
        background = new MenuBackground();
        launcher.getMouseListener().setUiManager(uiManager);

        bigButtonWidth = Converter.FHD_SCREEN_WIDTH / 2.5f;
        bigButtonHeight = Converter.FHD_SCREEN_HEIGHT / 4.5f;

        xBigButton = (Converter.FHD_SCREEN_WIDTH - bigButtonWidth) / 2;
        startingYBigButton = (Converter.FHD_SCREEN_HEIGHT) / 9f;
        bigButtonMargin = bigButtonHeight * 1.4;

        buttonMargin = 40;
        buttonWidth = (bigButtonWidth - buttonMargin) / 2f;
        buttonHeight = bigButtonHeight / 1.5;

        UIMenuButton playButton = getPlayButton(launcher);
        UIMenuButton onlineButton = getOnlineButton(launcher, playButton);
        UIMenuButton settingsButton = getSettingsButton(onlineButton);
        uiManager.addUIObject(playButton);
        uiManager.addUIObject(onlineButton);
        uiManager.addUIObject(settingsButton);
        uiManager.addUIObject(getExitButton(settingsButton));
    }

    private UIMenuButton getPlayButton(Launcher launcher) {
        ClickListener listener = launcher::startGame;
        return new UIMenuButton((int) xBigButton, (int) startingYBigButton, (int) bigButtonWidth, (int) bigButtonHeight, "PLAY", listener);
    }

    private UIMenuButton getOnlineButton(Launcher launcher, UIMenuButton playButton) {
        ClickListener listener = () -> StateManager.setCurrentState(new OnlineState(launcher));
        return new UIMenuButton((int) xBigButton, (int) (playButton.getY() + bigButtonMargin), (int) bigButtonWidth, (int) bigButtonHeight,"ONLINE", listener);
    }

    private UIMenuButton getSettingsButton(UIMenuButton multiplayerButton) {
        ClickListener listener = () -> StateManager.setCurrentState(new SettingsState(launcher));
        return new UIMenuButton((int) xBigButton, (int) (multiplayerButton.getY() + bigButtonMargin), (int) buttonWidth, (int) buttonHeight,"SETTINGS", 30, listener);
    }

    private UIMenuButton getExitButton(UIMenuButton settingsButton) {
        int y = (int) settingsButton.getY();
        ClickListener listener = Launcher::stop;
        return new UIMenuButton((int) (xBigButton + buttonWidth + buttonMargin), y, (int) buttonWidth, (int) buttonHeight,"EXIT", 30, listener);
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