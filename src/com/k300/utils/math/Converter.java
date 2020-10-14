package com.k300.utils.math;

import java.awt.*;

public class Converter {

    public static final int FHD_SCREEN_WIDTH = 1920;
    public static final int FHD_SCREEN_HEIGHT = 1080;

    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static final double SCREEN_WIDTH = screenSize.getWidth();
    public static final double SCREEN_HEIGHT = screenSize.getHeight();


    public static double getProportionalNumber(double number) {
        return number * (SCREEN_WIDTH / FHD_SCREEN_WIDTH);
    }

    public static double getAxisX(double x){
        return x - (FHD_SCREEN_WIDTH / 2.0);
    }

    public static double getAxisY(double y){
        return  - (y - (FHD_SCREEN_HEIGHT / 2.0));
    }

    public static double getFrameX(double x) {
        return (FHD_SCREEN_WIDTH / 2.0) + x;
    }

    public static double getFrameY(double y) {
        return (FHD_SCREEN_HEIGHT / 2.0) - y;
    }
}
