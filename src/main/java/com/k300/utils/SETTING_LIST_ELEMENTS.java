package com.k300.utils;

import com.k300.utils.configarations.Config;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

/*
*     Purpose:
*          represent the types of items on the settings window, as well as the functionality of said items.
*          these items are representing values of preferences stored in the database(Config).
*          each item has an accessor an modifier for the value it represents.
*     Contains:
*          all possible instances of the settings item.
*          each element contains:
*                the message to display on screen, accessor and modifier for the value the item represents.
*/

public enum SETTING_LIST_ELEMENTS {

    // the zoom item instance
    ZOOM("Zoom In On Car"),
    // the dev mode instance
    DEV_MODE("Dev Mode");

    // this will store the value of the item
    private final String message;

    // this will initialize the message when a specific instance is instantiated
    SETTING_LIST_ELEMENTS(String message) {
        this.message = message;
    }

    // message accessor
    public String getMessage() {
        return message;
    }

    // this will return the modifier for the variable this item represents
    public Consumer<Boolean> getVariableSetter() {
        if(this == ZOOM) {
            return Config::setUsingZoom;
        } else {
            return Config::setInDevMode;
        }
    }

    // this will return the accessor for the variable this item represents
    public BooleanSupplier getVariableValue() {
        if(this == ZOOM)  {
            return Config::isUsingZoom;
        } else {
            return Config::isInDevMode;
        }
    }
}
