package com.k300.ui.buttons;

import com.k300.utils.math.Converter;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class UIButton {

    protected final float x;
    protected final float y;
    protected final int width;
    protected final int height;
    protected final String text;
    protected final int fontSize;
    protected boolean isHovering;
    private final Rectangle bounds;

    public UIButton(float x, float y, int width, int height, String text, int fontSize) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
        this.fontSize = fontSize;
        isHovering = false;

        // Because we are centering the final image to be in the center of the screen,
        // also we need to to do the opposite for the bounds and add to their y the centeredY
        double relativeHeight = Converter.getProportionalNumber(Converter.FHD_SCREEN_HEIGHT);
        double fullOriginalHeight = Converter.SCREEN_HEIGHT;
        double centeredY = (fullOriginalHeight - relativeHeight) / 2;

        // The images are drawn relative to FHD screen,
        // so we need to convert the bounds of the mouse hovering to be relative to the current screen size
        bounds = new Rectangle((int) Converter.getProportionalNumber(x), (int)(Converter.getProportionalNumber(y) + centeredY),
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

    public float getY() {
        return y;
    }

    public float getX() {
        return x;
    }

    public int getWidth() {
        return width;
    }
}
