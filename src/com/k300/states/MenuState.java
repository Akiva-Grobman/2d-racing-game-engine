package com.k300.states;

import com.k300.Launcher;

import java.awt.*;

import com.k300.graphics.MenuBackground;
import com.k300.ui.*;
import com.k300.utils.math.Converter;

public class MenuState extends State {

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
        int width = Converter.FHD_SCREEN_WIDTH / 2;
        int height = Converter.FHD_SCREEN_HEIGHT / 4;
        int x = (Converter.FHD_SCREEN_WIDTH - width) / 2;
        int y = (Converter.FHD_SCREEN_HEIGHT) / 2;
        ClickListener listener = launcher::startGame;
        return new UIPlayButtonButton(x, y, width, height, listener);
    }

    private UIObject getExitButton(UIObject playButton) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = Converter.FHD_SCREEN_WIDTH / 5;
        int height = (int) ((Converter.FHD_SCREEN_HEIGHT - (playButton.getY() + playButton.getHeight())) / 3);
        int x = (Converter.FHD_SCREEN_WIDTH - width) / 2;
        int y = (int) ((Converter.FHD_SCREEN_HEIGHT + playButton.getHeight() + playButton.getY() - height) / 2);
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