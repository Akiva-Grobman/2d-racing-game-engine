package com.k300.ui.buttons;

import com.k300.graphics.FontLoader;
import com.k300.utils.SETTING_LIST_ELEMENTS;

import java.awt.*;

/*
*       Quick explanation:
*           an element is a visual representation of a boolean value in Config, true == marked, false == unmarked.
*           each element contains a box showing if it's marked, and text describing what it represents.
*       Purpose:
*           represents an element in the settings state.
*       Contains:
*           a SETTINGS_LIST_ELEMENT enum determining what kind of element it is, and a boolean tracking if it is marked or not.
*/

public class SettingsListElement extends UIButton {

    // tracker for if it's marked or not
    private boolean isChecked;
    // determine the type of element it is(see further explanation in the SETTING_LIST_ELEMENTS enum)
    private final SETTING_LIST_ELEMENTS elementsType;

    // only initialization option
    public SettingsListElement(float x, float y, int width, int height, SETTING_LIST_ELEMENTS elementsType) {
        // initialize the abstract button
        super(x, y, width, height, elementsType.getMessage(), 50);
        // set element type
        this.elementsType = elementsType;
        // get current value of the variable this element represents
        isChecked = elementsType.getVariableValue().getAsBoolean();
    }

    // render
    @Override
    public void render(Graphics graphics) {
        // store the current color value
        Color original = graphics.getColor();
        // if the element is chosen
        if(isChecked) {
            // draw on screen a green rectangle
            graphics.setColor(Color.green);
            graphics.fillRect((int) x, (int) y, width, height);
        }
        // if the mouse is over the will highlight the marking rectangle with red (other wise it will be white)
        if(isHovering) {
            graphics.setColor(Color.red);
        } else {
            graphics.setColor(Color.white);
        }
        // draw marking rectangle outline
        graphics.drawRect((int) x, (int) y, width, height);

        Font currentFont = graphics.getFont();
        graphics.setFont(FontLoader.loadFont(currentFont.getFontName(), fontSize));
        // draw elements bound variable type
        graphics.drawString(text, (int) (x + width * 1.5), (int) (y + height - graphics.getFontMetrics().getAscent()));
        // reset color to the it's value when this method was called
        graphics.setColor(original);
    }

    // get element message
    public String getMessage() {
        return elementsType.getMessage();
    }

    // handle button click
    @Override
    public void onClick() {
        // switch marked value to opposite
        isChecked = !isChecked;
        // update variable value in Config
        elementsType.getVariableSetter().accept(isChecked);
    }

}
