package com.k300.ui;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class UIImageButton extends UIObject {

    private final BufferedImage image;
    private final ClickListener clickListener;

    public UIImageButton(float x, float y, int width, int height, BufferedImage image, ClickListener clickListener) {
        super(x, y, width, height);
        this.image = image;
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