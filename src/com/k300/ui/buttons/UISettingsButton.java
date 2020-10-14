package com.k300.ui.buttons;

import com.k300.Launcher;
import com.k300.graphics.Assets;
import com.k300.states.SettingsState;
import com.k300.states.StateManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UISettingsButton extends UIButton {

    private final Launcher launcher;

    public UISettingsButton(float x, float y, int width, int height, Launcher launcher) {
        super(x, y, width, height);
        this.launcher = launcher;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics graphics) {
        BufferedImage image;
        if(isHovering) {
            image = Assets.getImage(Assets.SETTINGS_BUTTON_HOVER_KEY);
        } else {
            image = Assets.getImage(Assets.SETTINGS_BUTTON_KEY);
        }
        graphics.drawImage(image, (int) x, (int) y, width, height, null);
    }

    @Override
    public void onClick() {
        StateManager.setCurrentState(new SettingsState(launcher));
    }

}
