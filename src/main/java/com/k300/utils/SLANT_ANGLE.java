package com.k300.utils;

public enum SLANT_ANGLE {

    POSITIVE(1),
    NEGATIVE(-1);

    private final int value;

    SLANT_ANGLE(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
