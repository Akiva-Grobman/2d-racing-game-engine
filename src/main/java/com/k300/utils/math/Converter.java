package com.k300.utils.math;

import java.awt.*;

/*
*       Purpose:
*           this class is used to make sure all rendering is relative to screen size.
*       How:
*           /todo
*           all rendering will be done relative to a full HD screen (1900,1080) on to a temporary image.
*           than the image is scaled to fit the current machine screen size.
*       Contains:
*           the full HD screen dimensions, the current machine screen dimensions.
*       Methods:
*           this class contains converter methods from x and y axis (like on a graph) to the x and y of the frame(meaning (0,0) is the top left corner x increases as it moves to the right and y increases as in moves down).
*
*/

public abstract class Converter {

    // Full HD dimensions
    public static final int FHD_SCREEN_WIDTH = 1920;
    public static final int FHD_SCREEN_HEIGHT = 1080;
    // current machine screen dimensions
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static final double SCREEN_WIDTH = screenSize.getWidth();
    public static final double SCREEN_HEIGHT = screenSize.getHeight();

    // this will convert a point(number) in the full HD dimension to the machine screen size dimension
    public static double getProportionalNumber(double number) {
        return number * (SCREEN_WIDTH / FHD_SCREEN_WIDTH);
    }

    // will return a x frame coordinate as a x axis coordinate
    public static double getAxisX(double x){
        return x - (FHD_SCREEN_WIDTH / 2.0);
    }

    // will return a y frame coordinate as a y axis coordinate
    public static double getAxisY(double y){
        return  - (y - (FHD_SCREEN_HEIGHT / 2.0));
    }

    // will return a x axis coordinate as a x frame coordinate
    public static double getFrameX(double x) {
        return (FHD_SCREEN_WIDTH / 2.0) + x;
    }

    // will return a y axis coordinate as a y frame coordinate
    public static double getFrameY(double y) {
        return (FHD_SCREEN_HEIGHT / 2.0) - y;
    }
}
