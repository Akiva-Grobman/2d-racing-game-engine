package com.k300.tracks;

import com.k300.graphics.Assets;

import java.awt.*;
import java.awt.geom.Point2D;

public class Obstacle {

    private final double a;
    private final double b;
    private final double r;

    public Obstacle(double x, double y, double size) {
        a = x;
        b = y;
        r = size / 2;
    }

    private double getDistance(double x1, double y1, double x2,  double y2) {
        return Point2D.distance(x1, y1, x2, y2);
    }

    public boolean isOnTrack(double carX, double carY) {
        double distance = getDistance(a, b, carX, carY);
        return distance > r;
    }

    public void render(Graphics graphics) {
        graphics.drawImage(Assets.getImage(Assets.OBSTACLE_KEY), (int) (a - r), (int) (b - r), (int) r * 2, (int) r * 2, null);
    }
}
