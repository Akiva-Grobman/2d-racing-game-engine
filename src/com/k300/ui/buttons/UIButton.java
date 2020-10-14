package com.k300.ui.buttons;

import com.k300.utils.math.Converter;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class UIButton {

    protected final float x;
    protected final float y;
    protected final int width;
    protected final int height;
    protected boolean isHovering;
    private final Rectangle bounds;

    public UIButton(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        isHovering = false;
        // The images drawn relative to FHD screen,
        // so we need to convert the bounds of the mouse hovering to be relative to the current screen size
        bounds = new Rectangle((int) Converter.getProportionalNumber(x), (int)Converter.getProportionalNumber(y),
                (int)Converter.getProportionalNumber(width), (int)Converter.getProportionalNumber(height));
    }

    public abstract void render(Graphics graphics);

    public abstract void onClick();

    public void onMouseMove(MouseEvent e) {
        isHovering = bounds.contains(e.getX(), e.getY());
    }

    public void onMouseRelease() {
        if(isHovering) {
            onClick();
        }
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }
}
