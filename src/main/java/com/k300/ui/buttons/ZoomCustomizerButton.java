package com.k300.ui.buttons;

import java.awt.*;

import static com.k300.utils.Utils.drawStringInCenter;

public class ZoomCustomizerButton extends UIButton {

    public ZoomCustomizerButton(float x, float y, int width, int height, String text, int fontSize) {
        super(x, y, width, height, text, fontSize);
    }

    @Override
    public void render(Graphics graphics) {
        drawStringInCenter(x, y, width, height, graphics, text);
    }

    @Override
    public void onClick() {
        // TODO: 25/10/2020
    }

}
