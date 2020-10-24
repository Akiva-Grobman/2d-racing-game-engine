package com.k300.ui.buttons;

import com.k300.graphics.FontLoader;
import com.k300.utils.SETTING_LIST_ELEMENTS;

import java.awt.*;

public class SettingsListElement extends UIButton {

    private boolean isChecked;
    private final SETTING_LIST_ELEMENTS elementsType;

    public SettingsListElement(float x, float y, int width, int height, SETTING_LIST_ELEMENTS elementsType) {
        super(x, y, width, height, elementsType.getMessage(), 50);
        this.elementsType = elementsType;
        isChecked = elementsType.getVariableValue().getAsBoolean();
    }

    @Override
    public void render(Graphics graphics) {
        Color original = graphics.getColor();
        if(isChecked) {
            graphics.setColor(Color.green);
            graphics.fillRect((int) x, (int) y, width, height);
        }
        if(isHovering) {
            graphics.setColor(Color.red);
        } else {
            graphics.setColor(Color.white);
        }
        graphics.drawRect((int) x, (int) y, width, height);

        Font currentFont = graphics.getFont();
        graphics.setFont(FontLoader.loadFont(currentFont.getFontName(), fontSize));
        graphics.drawString(text, (int) (x + width * 1.5), (int) (y + height - graphics.getFontMetrics().getAscent()));
        graphics.setColor(original);
    }

    @Override
    public void onClick() {
        isChecked = !isChecked;
        elementsType.getVariableSetter().accept(isChecked);
    }

}
