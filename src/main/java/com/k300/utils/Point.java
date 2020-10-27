package com.k300.utils;

/*
*   Purpose:
*       store coordinates with double values
*/

public class Point {

    // contains the x value
    public double x;
    // contains the y value
    public double y;

    // default initialization with no starting values
    public Point() {

    }

    // custom initialization with both values as parameters
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

}
