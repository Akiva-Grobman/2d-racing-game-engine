package com.k300.states;

import com.k300.Launcher;

import java.awt.*;

import com.k300.graphics.Assets;
import com.k300.graphics.MenuBackground;
import com.k300.ui.ClickListener;
import com.k300.ui.UIPlayButtonButton;
import com.k300.ui.UIManager;

public class MenuState extends State{

    private final UIManager uiManager;
    private final MenuBackground background;

    public MenuState(Launcher launcher) {
        super(launcher);
        uiManager = new UIManager();
        background = new MenuBackground();
        launcher.getMouseListener().setUiManager(uiManager);
        uiManager.addUIObject(getPlayButton(launcher));
    }

    private UIPlayButtonButton getPlayButton(Launcher launcher) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width / 3 * 2;
        int height = screenSize.height / 7 * 3;
        int x = (screenSize.width - width) / 2;
        int y = (screenSize.height - height) / 2;
        ClickListener listener = launcher::startGame;
        return new UIPlayButtonButton(x, y, width, height, listener);
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