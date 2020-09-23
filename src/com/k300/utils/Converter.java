package com.k300.utils;

import java.awt.*;

public class Converter {

    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static final double SCREEN_WIDTH = screenSize.getWidth();
    private static final double SCREEN_HEIGHT = screenSize.getHeight();
    private double width = SCREEN_WIDTH;
    private double height = SCREEN_HEIGHT;



    public double getProportionalNumber(double number) {
        return number * (1920 / SCREEN_WIDTH);
    }

    public double getAxisX(double x){
        return x - (width / 2.0);
    }

    public double getAxisY(double y){
        return  - (y - (height / 2.0));
    }

    public double getFrameX(double x) {
        return (width / 2.0) + x;
    }

    public double getFrameY(double y) {
        return (height / 2.0) - y;
    }

    public double getDistance(int x1, int y1, int x2, int y2) {
        return Math.sqrt( (Math.pow((y2-y1), 2) + Math.pow((x2-x1), 2)) );
    }

    public double getPositiveC(int a, int b) {
        return (int) Math.sqrt( (Math.pow(a, 2) - Math.pow(b, 2)) );
    }

    public double getNegativeC(int a, int b) {
        return - (int) Math.sqrt( (Math.pow(a, 2) - Math.pow(b, 2)) );
    }

    public double getFrameXPoint(int xPosition) {
        return 1920/2 + xPosition;
    }

    public double getFrameYPoint(int yPosition) {
        return 1080/2 - yPosition;
    }

    public double getAxisXPoint(int xPosition) {
        return xPosition - 1920/2;
    }

    public double getAxisYPoint(int yPosition) {
        return -(yPosition - 1080/2);
    }
}
