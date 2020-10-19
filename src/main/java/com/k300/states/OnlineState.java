package com.k300.states;

import com.k300.Launcher;
import com.k300.graphics.MenuBackground;
import com.k300.ui.UIManager;
import com.k300.ui.buttons.UIBackButton;
import com.k300.ui.buttons.UIFourPlayersButton;
import com.k300.ui.buttons.UIThreePlayersButton;
import com.k300.ui.buttons.UITwoPlayersButton;
import com.k300.ui.listeners.ClickListener;
import com.k300.utils.math.Converter;

import java.awt.*;

public class OnlineState extends State {

    private final UIManager uiManager;
    private final MenuBackground background;
    private final int buttonWidth;
    private final int buttonHeight;

    private final int buttonsXLeft;
    private final int buttonsXRight;

    private final int buttonsYUp;
    private final int buttonsYDown;

    public OnlineState(Launcher launcher) {
        super(launcher);
        uiManager = new UIManager();
        background = new MenuBackground();
        launcher.getMouseListener().setUiManager(uiManager);
        buttonWidth = Converter.FHD_SCREEN_WIDTH / 3;
        buttonHeight = Converter.FHD_SCREEN_HEIGHT / 4;

        buttonsXLeft = buttonWidth / 3;
        buttonsXRight = (Converter.FHD_SCREEN_WIDTH -  buttonWidth) -  buttonWidth/2;
        buttonsYUp = buttonHeight / 3;
        buttonsYDown = (Converter.FHD_SCREEN_HEIGHT -  buttonHeight) -  buttonHeight/2;

        UITwoPlayersButton twoPlayersButton = get2PlayersButton(launcher);
        UIThreePlayersButton threePlayersButton = get3PlayersButton(launcher);
        UIFourPlayersButton fourPlayersButton = get4PlayersButton(launcher);
        UIBackButton backButton = getBackButton(launcher);
        uiManager.addUIObject(twoPlayersButton);
        uiManager.addUIObject(threePlayersButton);
        uiManager.addUIObject(fourPlayersButton);
        uiManager.addUIObject(backButton);
    }

    private UITwoPlayersButton get2PlayersButton(Launcher launcher) {
        ClickListener listener = () -> launcher.startOnlineGame(2);
        return new UITwoPlayersButton(buttonsXLeft, buttonsYUp, buttonWidth, buttonHeight, listener);
    }

    private UIThreePlayersButton get3PlayersButton(Launcher launcher) {
        ClickListener listener = () -> launcher.startOnlineGame(3);
        return new UIThreePlayersButton(buttonsXRight, buttonsYUp, buttonWidth, buttonHeight, listener);
    }

    private UIFourPlayersButton get4PlayersButton(Launcher launcher) {
        ClickListener listener = () -> launcher.startOnlineGame(4);
        return new UIFourPlayersButton(buttonsXLeft, buttonsYDown, buttonWidth, buttonHeight, listener);
    }

    private UIBackButton getBackButton(Launcher launcher) {
        return new UIBackButton(buttonsXRight, buttonsYDown, buttonWidth, buttonHeight, launcher);
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