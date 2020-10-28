package com.k300.states;

import com.k300.Launcher;

import java.awt.*;

import com.k300.display.MenuBackground;
import com.k300.ui.*;
import com.k300.ui.buttons.*;
import com.k300.ui.listeners.ClickListener;
import com.k300.utils.math.Converter;

/*
*       Purpose:
*           this represents the menu state, the user will choose to start a game online/offline or enter the settings of exit the program.
*       Contains:
*           a UIManager for the buttons and a background
*/

public class MenuState extends State {

    // this will handle the buttons (update, render, on click)
    private final UIManager uiManager;
    // this will be displayed in the background
    private final MenuBackground background;

    private final double bigButtonWidth;
    private final double bigButtonHeight;
    private final double smallButtonWidth;
    private final double smallButtonHeight;
    private final int bigButtonFontSize;
    private final int smallButtonFontSize;

    // only initialization option
    public MenuState(Launcher launcher) {
        // initialize abstract state
        super(launcher);
        // initialize empty manager
        uiManager = new UIManager();
        // initialize the background
        background = new MenuBackground();
        // set manager as mouse listener
        launcher.getMouseListener().setUiManager(uiManager);
        // set big button dimensions
        bigButtonWidth = Converter.FHD_SCREEN_WIDTH / 2.5f;
        bigButtonHeight = Converter.FHD_SCREEN_HEIGHT / 4.5f;
        // an easy way to understand how the buttons are rendered, is to imagine a rectangle and all buttons are set in side this rectangle
        // set left edge (of our hypothetical rectangle)
        final double leftEdge = (Converter.FHD_SCREEN_WIDTH - bigButtonWidth) / 2;
        // set the top edge (of our hypothetical rectangle)
        final double topEdge = (Converter.FHD_SCREEN_HEIGHT) / 8.5f;
        // set margins
        final double bigButtonMargin = bigButtonHeight * 1.4;
        final double smallButtonMargin = 40;
        // set small button dimensions
        smallButtonWidth = (bigButtonWidth - smallButtonMargin) / 2f;
        smallButtonHeight = bigButtonHeight / 1.8;
        // set font sizes
        bigButtonFontSize = 100;
        smallButtonFontSize = 40;
        // initialize a button to start a game (offline)
        UIMenuButton playButton = initBigButton(leftEdge,
                topEdge,
                "PLAY",
                launcher::startOfflineGame);
        // initialize a button to start an online game
        UIMenuButton onlineButton = initBigButton(leftEdge,
                topEdge + bigButtonMargin,
                "ONLINE",
                () -> StateManager.setCurrentState(new OnlineState(launcher)));
        // initialize a button that will change to the settings menu
        UIMenuButton settingsButton = initSmallButton(leftEdge,
                topEdge + (2 * bigButtonMargin),
                "SETTINGS",
                        () -> StateManager.setCurrentState(new SettingsState(launcher)));
        // initialize an exit button that will close the program
        UIMenuButton exitButton = initSmallButton(leftEdge + smallButtonWidth + smallButtonMargin,
                topEdge + (2 * bigButtonMargin),
                "EXIT",
                Launcher::stop);
        // add the buttons to the manager
        uiManager.addUIObject(playButton);
        uiManager.addUIObject(onlineButton);
        uiManager.addUIObject(settingsButton);
        uiManager.addUIObject(exitButton);
    }

    // initialize a big button
    private UIMenuButton initSmallButton(double x, double y, String text, ClickListener clickListener) {
        return initButton(x, y, bigButtonWidth, bigButtonHeight, bigButtonFontSize, text, clickListener);
    }

    // initialize a small menu button
    private UIMenuButton initBigButton(double x, double y, String text, ClickListener clickListener) {
        return initButton(x, y, smallButtonWidth, smallButtonHeight, smallButtonFontSize, text, clickListener);
    }

    // initialize a menu button
    private UIMenuButton initButton(double x, double y, double width, double height, double fontSize, String text, ClickListener clickListener) {
        return new UIMenuButton((int) x, (int) y, (int) width, (int) height, text, (int) fontSize, clickListener);
    }

    // update the background (it contains moving parts)
    @Override
    public void tick() {
        background.tick();
    }

    // render background and buttons
    @Override
    public void render(Graphics graphics) {
        // render background
        background.render(graphics);
        // render buttons
        uiManager.render(graphics);
    }

}