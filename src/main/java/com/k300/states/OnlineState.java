package com.k300.states;

import com.k300.Launcher;
import com.k300.display.MenuBackground;
import com.k300.ui.UIManager;
import com.k300.ui.buttons.UIMenuButton;
import com.k300.ui.listeners.ClickListener;
import com.k300.utils.math.Converter;

import java.awt.*;

/*
*       Purpose:
*           this will allow the user to start an online game with 2/3/4 players.
*       Contains:
*           four button for the 2/3/4 player game and a button that will return to the menu, a UIManager that will manage all of the buttons,
*           as well as a MenuBackground to be displayed in the background
*/

public class OnlineState extends State {

    // this will manage all of the buttons (update, render, onclick)
    private final UIManager uiManager;
    // this will be displayed in the background
    private final MenuBackground background;
    // button dimensions
    private final double buttonWidth;
    private final double buttonHeight;

    // only initialization option
    public OnlineState(Launcher launcher) {
        // initialize abstract state
        super(launcher);
        // initialize the manager
        uiManager = new UIManager();
        // initialize the background
        background = new MenuBackground();
        // set the manager as mouse listener
        launcher.getMouseListener().setUiManager(uiManager);
        // set button margins
        double buttonHeightMargin = 150;
        double buttonWidthMargin = 250;
        // determine button dimensions
        buttonWidth = Converter.FHD_SCREEN_WIDTH / 3f;
        buttonHeight = Converter.FHD_SCREEN_HEIGHT / 4f;
        // initialize the two player game button, location: top left
        UIMenuButton twoPlayersButton = getPlayerButton(2,
                buttonWidthMargin,
                buttonHeightMargin);
        // initialize the three player game button, location: top right
        UIMenuButton threePlayersButton = getPlayerButton(3,
                (Converter.FHD_SCREEN_WIDTH -  buttonWidth) - buttonWidthMargin,
                buttonHeightMargin);
        // initialize the four player game button, location: bottom left
        UIMenuButton fourPlayersButton = getPlayerButton(4,
                buttonWidthMargin,
                (Converter.FHD_SCREEN_HEIGHT -  buttonHeight) - buttonHeightMargin);
        // initialize the back player game button, location: bottom right
        UIMenuButton backButton = getButton((Converter.FHD_SCREEN_WIDTH -  buttonWidth) - buttonWidthMargin,
                (Converter.FHD_SCREEN_HEIGHT -  buttonHeight) - buttonHeightMargin,
                "BACK",
                () -> StateManager.setCurrentState(new MenuState(launcher)));
        // add the buttons to the manager
        uiManager.addUIObject(twoPlayersButton);
        uiManager.addUIObject(threePlayersButton);
        uiManager.addUIObject(fourPlayersButton);
        uiManager.addUIObject(backButton);
    }

    // initialize a player button (this will determine how many players will be in the game)
    private UIMenuButton getPlayerButton(int sumOfPlayers, double x, double y) {
        String text = sumOfPlayers + " PLAYERS";
        return getButton(x, y, text, () -> launcher.startOnlineGame(sumOfPlayers));
    }

    // initialize a button
    private UIMenuButton getButton(double x, double y, String text, ClickListener listener) {
        return new UIMenuButton((int) x, (int) y, (int) buttonWidth, (int) buttonHeight, text, listener);
    }

    // this will update the background (the background contains moving components)
    @Override
    public void tick() {
        // update the background
        background.tick();
    }

    // render the background and the buttons
    @Override
    public void render(Graphics graphics) {
        // render the background
        background.render(graphics);
        // render the buttons
        uiManager.render(graphics);
    }

}