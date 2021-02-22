package com.k300.ui.buttons;

import com.k300.graphics.Assets;
import com.k300.graphics.FontLoader;
import com.k300.ui.listeners.ClickListener;

import java.awt.*;
import java.awt.image.BufferedImage;

import static com.k300.utils.Utils.drawStringInCenter;

/*
*       Purpose:
*           all buttons on the menu state are instances of this class.
*       Contains:
*           an instance of the ClickListener interface that will contain the code to handle the onClick of the specific button.
*/

public class UIMenuButton extends UIButton {

    // the specific implementation for when the button is clicked
    protected final ClickListener clickListener;

    // initialize with default font size
    public UIMenuButton(float x, float y, int width, int height, String text, ClickListener clickListener) {
        this(x, y, width, height, text, 70, clickListener);
    }

    // initialize with font size as a parameter argument
    public UIMenuButton(float x, float y, int width, int height, String text, int fontSize, ClickListener clickListener) {
        // initialize abstract button
        super(x, y, width, height, text, fontSize);
        // initialize instance click listener
        this.clickListener = clickListener;
    }

    @Override
    public void render(Graphics graphics) {
        /*
        *     in resources we have to button images, one is used while the mouse isn't over the button and the other is while it is hovering over the button.
        */
        BufferedImage image;
        // if the mouse is over the button set hte button image to the corresponding image, otherwise set it to the regular button image
        if(isHovering) {
            image = Assets.getImage(Assets.BUTTON_HOVER_KEY);
        } else {
            image = Assets.getImage(Assets.BUTTON_KEY);
        }
        // draw the image
        graphics.drawImage(image, (int) x, (int) y, width, height, null);
        // get the current font
        Font currentFont = graphics.getFont();
        // create a copy of the current font with the font size for this button
        graphics.setFont(FontLoader.loadFont(currentFont.getFontName(), fontSize));
        // draw the button description over the button image(in the center)
        drawStringInCenter(x, y, width, height, graphics, text);
    }

    // handle button click
    @Override
    public void onClick() {
        clickListener.onClick();
    }
}
