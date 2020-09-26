package com.k300.ui;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIImageButton extends UIObject {

    private BufferedImage[] images;
    private ClickListener clickListener;

    public UIImageButton(float x, float y, int width, int height, BufferedImage[] images, ClickListener clickListener) {
        super(x, y, width, height);
        this.images = images;
        this.clickListener = clickListener;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics graphics) {
        BufferedImage image;
        if(isHovering) {
            image = images[0];
        } else {
            image = images[1];
        }
        graphics.drawImage(image, (int)x, (int)y, width, height, null);
    }

    @Override
    public void onClick() {
        clickListener.onClick();
    }

}