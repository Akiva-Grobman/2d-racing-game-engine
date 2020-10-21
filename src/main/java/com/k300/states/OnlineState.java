package com.k300.states;

import com.k300.Launcher;
import com.k300.graphics.MenuBackground;
import com.k300.ui.UIManager;
import com.k300.ui.buttons.UIButton;
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

        UIButton twoPlayersButton = get2PlayersButton(launcher);
        UIButton threePlayersButton = get3PlayersButton(launcher);
        UIButton fourPlayersButton = get4PlayersButton(launcher);
        UIButton backButton = getBackButton(launcher);
        uiManager.addUIObject(twoPlayersButton);
        uiManager.addUIObject(threePlayersButton);
        uiManager.addUIObject(fourPlayersButton);
        uiManager.addUIObject(backButton);
    }

    private UIButton get2PlayersButton(Launcher launcher) {
        ClickListener listener = () -> launcher.startOnlineGame(2);
        return new UIButton(buttonsXLeft, buttonsYUp, buttonWidth, buttonHeight,"2 PLAYERS", listener);
    }

    private UIButton get3PlayersButton(Launcher launcher) {
        ClickListener listener = () -> launcher.startOnlineGame(3);
        return new UIButton(buttonsXRight, buttonsYUp, buttonWidth, buttonHeight, "3 PLAYERS", listener);
    }

    private UIButton get4PlayersButton(Launcher launcher) {
        ClickListener listener = () -> launcher.startOnlineGame(4);
        return new UIButton(buttonsXLeft, buttonsYDown, buttonWidth, buttonHeight, "4 PLAYERS", listener);
    }

    private UIButton getBackButton(Launcher launcher) {
        ClickListener listener = () -> StateManager.setCurrentState(new MenuState(launcher));
        return new UIButton(buttonsXRight, buttonsYDown, buttonWidth, buttonHeight, "BACK", listener);
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