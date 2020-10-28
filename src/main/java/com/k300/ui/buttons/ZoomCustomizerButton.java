package com.k300.ui.buttons;

import com.k300.utils.configarations.Config;

import java.awt.*;

import static com.k300.utils.Utils.drawStringInCenter;

/*
*       Purpose:
*           this represent a button used in the settings state to increase and decrease the zoom factor.
*       Contains:
*           the BUTTON_TYPE enum definition as well as an instance of the BUTTON_TYPE enum.
*/

public class ZoomCustomizerButton extends UIButton {

    /*
    *       Purpose:
    *           This enum is only relevant to this class.
    *       Contains:
    *           both instances, PLUS(zoom in) and MINUS(zoom out).
    *       Methods:
    *           (static) getButtonType(type)
    *               -> will return a BUTTON_TYPE instance base on input +/-
    *           getOnClickChange()
    *               -> will return a value that the original zoom factor will be multiplied by for PLUS it will increase the original value, for MINUS it will decrease the origial value.
    */
    private enum BUTTON_TYPE {

        // zoom in
        PLUS,
        // zoom out
        MINUS;

        // will return an instance of this enum base on input
        private static BUTTON_TYPE getButtonType(String type) {
            return switch (type.charAt(0)) {
                case '+' -> PLUS;
                case '-' -> MINUS;
                default -> throw new Error(type + " not supported");
            };
        }

        // will return a value to multiply the original zoom in factor
        public double getOnClickChange() {
            if(this == PLUS) {
                // increase value
                return 1.2;
            } else {
                // decrease value
                return 0.8;
            }
        }

    }


    // will contain the type of button for each object instance
    private final BUTTON_TYPE buttonType;

    // only initialization option
    public ZoomCustomizerButton(float x, float y, int width, int height, String text) {
        // initialize abstract button
        super(x, y, width, height, text, 50);
        // set the instance button type
        buttonType = BUTTON_TYPE.getButtonType(text);
    }

    // render the button
    @Override
    public void render(Graphics graphics) {
        // this is so the button color changes when the mouse is hovering over it
        if(isHovering) {
            graphics.setColor(Color.red);
        } else {
            graphics.setColor(Color.white);
        }
        // draw a representation of the button(+/-) so the user knows where the button is
        drawStringInCenter(x, y, width, height, graphics, text);
    }

    // handle clicks
    @Override
    public void onClick() {
        // retrieve the current zoom in factor
        double originalZoomFactor = Config.getZoomInFactor();
        // changed it based on the button changing factor
        double newZoomFactor = originalZoomFactor * buttonType.getOnClickChange();
        // minimum zoom in factor value
        final double MINIMUM_ZOOM_FACTOR = 1.5;
        // maximum zoom out factor value
        final int MAXIMUM_ZOOM_FACTOR = 6;
        // if zoomed out too much clamp to minimum
        if(newZoomFactor < MINIMUM_ZOOM_FACTOR) {
            newZoomFactor = MINIMUM_ZOOM_FACTOR;
            // if zoomed in too much clamp to maximum
        } else if(newZoomFactor > MAXIMUM_ZOOM_FACTOR){
                newZoomFactor = MAXIMUM_ZOOM_FACTOR;

        }
        // update zoom in factor
        Config.setZoomInFactor(newZoomFactor);
    }

}
