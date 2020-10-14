package com.k300.utils;

import com.k300.utils.configarations.Config;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

public enum SETTING_LIST_ELEMENTS {

    ZOOM("Zoom In On Car"),
    DEV_MODE("Dev Mode");

    private final String message;

    SETTING_LIST_ELEMENTS(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Consumer<Boolean> getVariableSetter() {
        if(this == ZOOM) {
            return Config::setIsUsingZoom;
        } else {
            return Config::setIsInDevMode;
        }
    }

    public BooleanSupplier getVariableValue() {
        if(this == ZOOM)  {
            return Config::getIsUsingZoom;
        } else {
            return Config::getIsInDevMode;
        }
    }
}
