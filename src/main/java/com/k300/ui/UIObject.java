package com.k300.ui;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class UIObject {

    protected float x;
    protected float y;
    protected int width;
    protected int height;
    protected boolean isHovering;
    private final Rectangle bounds;

    public UIObject(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        isHovering = false;
        bounds = new Rectangle((int)x, (int)y, width, height);
    }

    public abstract void tick();

    public abstract void render(Graphics graphics);

    public abstract void onClick();

    public void onMouseMove(MouseEvent e) {
        isHovering = bounds.contains(e.getX(), e.getY());
    }

    public void onMouseRelease(MouseEvent e) {
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
