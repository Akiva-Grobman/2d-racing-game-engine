package com.k300.tracks;

import com.k300.utils.Converter;

import java.awt.*;
import java.awt.geom.Point2D;

public class Obstacle {
    private final double a;
    private final double b;
    private final double r;

    public Obstacle(double x, double y, double size) {
        a = x;
        b = y;
        r = size/2;
    }

    private double getDistance(double x1, double y1, double x2,  double y2) {
        return Point2D.distance(x1, y1, x2, y2);
    }

    public boolean onTheTrack(double carX, double carY) {
        double distance = getDistance(a, b, carX, carY);
        return distance > r;
    }

    //Testing
    public void render(Graphics graphics) {
        Color green = new Color(100, 155, 54); // Color green
        graphics.setColor(green);
        graphics.fillOval((int) (a - r), (int) (b - r), (int) r * 2, (int) r * 2);
    }
}
