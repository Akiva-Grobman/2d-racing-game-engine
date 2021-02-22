package com.k300.ui.buttons;

import com.k300.utils.math.Converter;

import java.awt.*;
import java.awt.event.MouseEvent;

/*
*       Purpose:
*           this is an abstract representation of a button.
*           we don't use the built in java JButton for two reasons:
*               1. you can't add it to a canvas(we use that in the display object)
*               2. it's easier to customize the way it looks with the graphics display object.
*       Contains:
*           the x,y position for the on screen location, the width,height dimensions of the desired size,
*           the text describing the buttons purpose, a boolean that represents whether the mouse is over the button or not,
*           and a Rectangle object with the the dimension and location of the button to calculate if the mouse is over the button.
*/

public abstract class UIButton {

    // on screen x position
    protected final float x;
    // on screen y position
    protected final float y;
    // button width
    protected final int width;
    // button height
    protected final int height;
    // button description
    protected final String text;
    // button text size
    protected final int fontSize;
    // is the mouse over the button
    protected boolean isHovering;
    // button dimensions and location
    private final Rectangle bounds;

    // only initialization option
    public UIButton(float x, float y, int width, int height, String text, int fontSize) {
        // set variables from constructor parameters
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
        this.fontSize = fontSize;
        // set to default
        isHovering = false;

        // Because we are centering the final image to be in the center of the machine screen,
        // we need to do the opposite for the bounds and add to their y the centeredY so the mouse coordinates are in sync with the on screen display.
        double relativeHeight = Converter.getProportionalNumber(Converter.FHD_SCREEN_HEIGHT);
        double fullOriginalHeight = Converter.SCREEN_HEIGHT;
        double centeredY = (fullOriginalHeight - relativeHeight) / 2;

        // The images are drawn relative to FHD screen,
        // so we need to convert the bounds of the mouse hovering to be relative to the current screen size
        bounds = new Rectangle((int) Converter.getProportionalNumber(x), (int)(Converter.getProportionalNumber(y) + centeredY),
                (int)Converter.getProportionalNumber(width), (int)Converter.getProportionalNumber(height));
    }

    // all buttons must have to ability to render
    public abstract void render(Graphics graphics);

    // all buttons must have to ability to be clicked
    public abstract void onClick();

    // if the mouse constants are in the rectangle called bounds the mouse is currently hovering over the button
    public void onMouseMove(MouseEvent e) {
        System.out.println("moving");
        isHovering = bounds.contains(e.getX(), e.getY());
    }

    // this will determine if the button was clicked
    public void onMouseRelease() {
        // if the mouse was released while over the button that means it was clicked
        if(isHovering) {
            // handle the click
            onClick();
        }
    }

    // y accessor
    public float getY() {
        return y;
    }

    // x accessor
    public float getX() {
        return x;
    }

    // width accessor
    public int getWidth() {
        return width;
    }

    // height accessor
    public int getHeight() {
        return height;
    }

}
