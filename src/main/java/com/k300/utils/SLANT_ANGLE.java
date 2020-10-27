package com.k300.utils;

/*
*       Necessary Info:
*           the calculations of car movement will use the mathematical straight line function(y = ax + b)
*       Purpose:
*           this is supposed to be a cleaner solution to represent the slant(angle) of a car straight line function
*       Contains:
*           all possible instances of car angles.
*           each angel instance contains:
*               an integer value (1/-1) that will represent the slant of the car.
*/
public enum SLANT_ANGLE {

    // initialize negative slope
    POSITIVE(1),
    // initialize positive slope
    NEGATIVE(-1);

    // slant angle
    private final int value;

    // initialize slope angle when instantiated
    SLANT_ANGLE(int value) {
        this.value = value;
    }

    // slant accessor
    public int getValue() {
        return value;
    }

}
