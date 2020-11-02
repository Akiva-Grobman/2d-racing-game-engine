package com.k300.obstacles;

import com.k300.graphics.Assets;

import java.awt.*;
import java.awt.geom.Point2D;

/*
*       Purpose:
*           this represents a part of the track the car can't drive on (obviously with a visual representation on the track)
*       Contains:
*           the a, b, and radius of the obstacle. (all obstacles are circles)
*/

public class Obstacle {

    // the a of the circle
    private final double a;
    // the b of the circle
    private final double b;
    // the radius of the circle
    private final double r;

    // only initialization option (with all of the circle dimensions)
    public Obstacle(double x, double y, double size) {
        // initialize object variables
        a = x;
        b = y;
        r = size / 2;
    }

    // get the distance between two points
    private double getDistance(double x1, double y1, double x2,  double y2) {
        // this uses the distance formula (D = sqrt((x1 - x2)^2 + (y1 - y2)^2)
        return Point2D.distance(x1, y1, x2, y2);
    }

    // will return false if the point is on the obstacle
    public boolean isOffObstacle(double carX, double carY) {
        // we store the distance from our point and the circle center
        double distance = getDistance(a, b, carX, carY);
        // if the distance is smaller than r the point given is on the obstacle
        return distance > r;
    }

    // render the obstacle onto the track image (this will only happen once because this becomes a part of the track)
    public void render(Graphics graphics) {
        graphics.drawImage(Assets.getImage(Assets.OBSTACLE_KEY), (int) (a - r), (int) (b - r), (int) r * 2, (int) r * 2, null);
    }

}
