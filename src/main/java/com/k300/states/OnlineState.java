package com.k300.states;

import com.k300.Launcher;
import com.k300.graphics.MenuBackground;
import com.k300.ui.UIManager;
import com.k300.ui.buttons.UIMenuButton;
import com.k300.ui.listeners.ClickListener;
import com.k300.utils.math.Converter;

import java.awt.*;

public class OnlineState extends State {

    private final UIManager uiManager;
    private final MenuBackground background;
    private final double buttonWidth;
    private final double buttonHeight;

    private final double buttonHeightMargin;
    private final double buttonWidthMargin;

    private final double buttonsXLeft;
    private final double buttonsXRight;

    private final double buttonsYUp;
    private final double buttonsYDown;

    public OnlineState(Launcher launcher) {
        super(launcher);
        uiManager = new UIManager();
        background = new MenuBackground();
        launcher.getMouseListener().setUiManager(uiManager);

        buttonHeightMargin = 150;
        buttonWidthMargin = 250;

        buttonWidth = Converter.FHD_SCREEN_WIDTH / 3f;
        buttonHeight = Converter.FHD_SCREEN_HEIGHT / 4f;

        buttonsXLeft = buttonWidthMargin;
        buttonsXRight = (Converter.FHD_SCREEN_WIDTH -  buttonWidth) -  buttonWidthMargin;
        buttonsYUp = buttonHeightMargin;
        buttonsYDown = (Converter.FHD_SCREEN_HEIGHT -  buttonHeight) -  buttonHeightMargin;

        UIMenuButton twoPlayersButton = get2PlayersButton(launcher);
        UIMenuButton threePlayersButton = get3PlayersButton(launcher);
        UIMenuButton fourPlayersButton = get4PlayersButton(launcher);
        UIMenuButton backButton = getBackButton(launcher);
        uiManager.addUIObject(twoPlayersButton);
        uiManager.addUIObject(threePlayersButton);
        uiManager.addUIObject(fourPlayersButton);
        uiManager.addUIObject(backButton);
    }

    private UIMenuButton get2PlayersButton(Launcher launcher) {
        ClickListener listener = () -> launcher.startOnlineGame(2);
        return new UIMenuButton((int) buttonsXLeft, (int) buttonsYUp, (int) buttonWidth, (int) buttonHeight,"2 PLAYERS", listener);
    }

    private UIMenuButton get3PlayersButton(Launcher launcher) {
        ClickListener listener = () -> launcher.startOnlineGame(3);
        return new UIMenuButton((int) buttonsXRight, (int) buttonsYUp, (int) buttonWidth, (int) buttonHeight, "3 PLAYERS", listener);
    }

    private UIMenuButton get4PlayersButton(Launcher launcher) {
        ClickListener listener = () -> launcher.startOnlineGame(4);
        return new UIMenuButton((int) buttonsXLeft, (int) buttonsYDown, (int) buttonWidth, (int) buttonHeight, "4 PLAYERS", listener);
    }

    private UIMenuButton getBackButton(Launcher launcher) {
        ClickListener listener = () -> StateManager.setCurrentState(new MenuState(launcher));
        return new UIMenuButton((int) buttonsXRight, (int) buttonsYDown, (int) buttonWidth, (int) buttonHeight, "BACK", listener);
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