package com.k300.states;

import com.k300.Launcher;

import java.awt.*;

import com.k300.graphics.MenuBackground;
import com.k300.ui.*;

public class MenuState extends State{

    private final UIManager uiManager;
    private final MenuBackground background;

    public MenuState(Launcher launcher) {
        super(launcher);
        uiManager = new UIManager();
        background = new MenuBackground();
        launcher.getMouseListener().setUiManager(uiManager);
        UIPlayButtonButton playButton = getPlayButton(launcher);
        uiManager.addUIObject(playButton);
        uiManager.addUIObject(getExitButton(playButton));
    }

    private UIPlayButtonButton getPlayButton(Launcher launcher) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width / 3 * 2;
        int height = screenSize.height / 7 * 3;
        int x = (screenSize.width - width) / 2;
        // button in the center
        int y = (screenSize.height - height) / 2;
        // button on the bottom
//        int y = (screenSize.height - height) + screenSize.height / 20;
        ClickListener listener = launcher::startGame;
        return new UIPlayButtonButton(x, y, width, height, listener);
    }

    private UIObject getExitButton(UIObject playButton) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width / 3;
        int height = (int) ((screenSize.height - playButton.getY()) / 7 * 3);
        int x = (screenSize.width - width) / 2;
        int y = (int) ((screenSize.height + playButton.getHeight() + playButton.getY() - height) / 2);
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