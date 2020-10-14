package com.k300.ui.buttons;

import com.k300.Launcher;
import com.k300.graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIExitButton extends UIButton {

    public UIExitButton(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void render(Graphics graphics) {
        BufferedImage image;
        if(isHovering) {
            image = Assets.getImage(Assets.EXIT_BUTTON_HOVER_KEY);
        } else {
            image = Assets.getImage(Assets.EXIT_BUTTON_KEY);
        }
        graphics.drawImage(image, (int) x, (int) y, width, height, null);
    }

    @Override
    public void onClick() {
        Launcher.stop();
    }

}
