package com.k300.ui;

import com.k300.graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIPlayButtonButton extends UIObject {

    private final ClickListener clickListener;
    private final BufferedImage image;

    public UIPlayButtonButton(float x, float y, int width, int height, ClickListener clickListener) {
        super(x, y, width, height);
        image = Assets.getImage(Assets.PLAY_BUTTON_KEY);
        this.clickListener = clickListener;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics graphics) {
        //todo add another image for hovering
        graphics.drawImage(image, (int)x, (int)y, width, height, null);
    }

    @Override
    public void onClick() {
        clickListener.onClick();
    }

}