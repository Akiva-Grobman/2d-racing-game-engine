package com.k300.utils;

public enum ROTATION {

    RIGHT(-1),
    LEFT(1);

    private final int value;

    ROTATION(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
