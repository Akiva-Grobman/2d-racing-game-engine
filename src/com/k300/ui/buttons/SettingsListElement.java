package com.k300.ui.buttons;

import com.k300.utils.SETTING_LIST_ELEMENTS;

import java.awt.*;

public class SettingsListElement extends UIButton {

    private boolean isChecked;
    private final SETTING_LIST_ELEMENTS elementsType;

    public SettingsListElement(float x, float y, int width, int height, SETTING_LIST_ELEMENTS elementsType) {
        super(x, y, width, height);
        this.elementsType = elementsType;
        isChecked = elementsType.getVariableValue().getAsBoolean();
    }

    @Override
    public void render(Graphics graphics) {
        Color original = graphics.getColor();
        if(isChecked) {
            graphics.setColor(Color.green);
            graphics.fillRect((int) x, (int) y, width, height);
        } else {
            graphics.setColor(Color.white);
            graphics.drawRect((int) x, (int) y, width, height);
        }
        if(isHovering) {
            graphics.setColor(Color.yellow);
        } else {
            graphics.setColor(Color.white);
        }
        graphics.drawString(elementsType.getMessage(), (int) (x + width * 1.5), (int) (y + height - graphics.getFontMetrics().getAscent()));
        graphics.setColor(original);
    }

    @Override
    public void onClick() {
        isChecked = !isChecked;
        elementsType.getVariableSetter().accept(isChecked);
    }

}
