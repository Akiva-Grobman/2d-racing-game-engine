package com.k300.utils.math;

import java.awt.*;

public class Converter {

    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static final double SCREEN_WIDTH = screenSize.getWidth();
    private static final double SCREEN_HEIGHT = screenSize.getHeight();


    public static double getProportionalNumber(double number) {
        return number * (1920 / SCREEN_WIDTH);
    }

    public static double getAxisX(double x){
        return x - (SCREEN_WIDTH / 2.0);
    }

    public static double getAxisY(double y){
        return  - (y - (SCREEN_HEIGHT / 2.0));
    }

    public static double getFrameX(double x) {
        return (SCREEN_WIDTH / 2.0) + x;
    }

    public static double getFrameY(double y) {
        return (SCREEN_HEIGHT / 2.0) - y;
    }

    public static double getDistance(int x1, int y1, int x2, int y2) {
        return Math.sqrt( (Math.pow((y2-y1), 2) + Math.pow((x2-x1), 2)) );
    }

    public static double getFrameXPoint(int xPosition) {
        return 1920/2 + xPosition;
    }

    public static double getFrameYPoint(int yPosition) {
        return 1080/2 - yPosition;
    }

    public static double getAxisXPoint(int xPosition) {
        return xPosition - 1920/2;
    }

    public static double getAxisYPoint(int yPosition) {
        return -(yPosition - 1080/2);
    }
}
