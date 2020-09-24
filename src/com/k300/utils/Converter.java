package com.k300.utils;

import java.awt.*;

public class Converter {

    private static final double SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private static final double SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private static final int TRACK_ORIGINAL_WIDTH = 1920;
    private static final int TRACK_ORIGINAL_HEIGHT = 1080;

    public static double getProportionalNumber(double number) {
        return number * (TRACK_ORIGINAL_WIDTH / SCREEN_WIDTH);
    }

    public static double getAxisX(double x){
        return x - (SCREEN_WIDTH / 2f);
    }

    public static double getAxisY(double y){
        return  - (y - (SCREEN_HEIGHT / 2f));
    }

    public static double getFrameX(double x) {
        return (SCREEN_WIDTH / 2f) + x;
    }

    public static double getFrameY(double y) {
        return (SCREEN_HEIGHT / 2f) - y;
    }

    public static double getDistance(int x1, int y1, int x2, int y2) {
        return Math.sqrt( (Math.pow((y2 - y1), 2) + Math.pow((x2 - x1), 2)) );
    }

    public static double getFrameXPoint(int xPosition) {
        return TRACK_ORIGINAL_WIDTH / 2f + xPosition;
    }

    public static double getFrameYPoint(int yPosition) {
        return TRACK_ORIGINAL_HEIGHT / 2f - yPosition;
    }

    public static double getAxisXPoint(int xPosition) {
        return xPosition - TRACK_ORIGINAL_WIDTH / 2f;
    }

    public static double getAxisYPoint(int yPosition) {
        return -(yPosition - TRACK_ORIGINAL_HEIGHT / 2f);
    }

}
