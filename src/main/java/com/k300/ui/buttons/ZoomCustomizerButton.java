package com.k300.ui.buttons;

import com.k300.utils.configarations.Config;

import java.awt.*;

import static com.k300.utils.Utils.drawStringInCenter;

public class ZoomCustomizerButton extends UIButton {


    private enum BUTTON_TYPE {
        PLUS,
        MINUS;

        private static BUTTON_TYPE getButtonType(String type) {
            return switch (type.charAt(0)) {
                case '+' -> PLUS;
                case '-' -> MINUS;
                default -> throw new Error(type + " not supported");
            };
        }

        public double getOnClickChange() {
            if(this == PLUS) {
                return 2;
            } else {
                return -2;
            }
        }
    }

    private final BUTTON_TYPE buttonType;

    public ZoomCustomizerButton(float x, float y, int width, int height, String text) {
        super(x, y, width, height, text, 50);
        buttonType = BUTTON_TYPE.getButtonType(text);
    }

    @Override
    public void render(Graphics graphics) {
        if(isHovering) {
            graphics.setColor(Color.red);
        } else {
            graphics.setColor(Color.white);
        }
        drawStringInCenter(x, y, width, height, graphics, text);
    }

    @Override
    public void onClick() {
        double originalWidthFactor = Config.getZoomInWidthFactor();
        double originalHeightFactor = Config.getZoomInHeightFactor();
        double newWidthFactor = originalWidthFactor + buttonType.getOnClickChange();
        double newHeightFactor = originalHeightFactor + buttonType.getOnClickChange();
        Config.setZoomInFactor(newWidthFactor, newHeightFactor);
    }

}
