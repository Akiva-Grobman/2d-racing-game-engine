package com.k300.utils.math;

import com.k300.cars.player_car.DIRECTION;
import com.k300.utils.Point;
import static java.lang.Math.*;

public class AnalyticalMath {

    public static boolean angleIsInBoundsOf(double angle, int lowerBound, int upperBound) {
        return angle >= lowerBound && angle <= upperBound;
    }

    public static Point getNewPointByDistanceAndAngle(Point point, double distance, double angle, DIRECTION direction) {
        Point newPoint = new Point();
        if(direction == DIRECTION.FORWARDS) {
            if (angleIsInBoundsOf(angle, 90, 270)) {
                newPoint.x = point.x - getXDistanceFactor(distance, angle);
                newPoint.y = point.y + getYDistanceFactor(distance, angle);
            } else /*if(isInBoundsOf(car.angle, 0, 90) || if(isInBoundsOf(car.angle, 270, 360))*/ {
                newPoint.x = point.x + getXDistanceFactor(distance, angle);
                newPoint.y = point.y - getYDistanceFactor(distance, angle);
            }
        } else {
            if (angleIsInBoundsOf(angle, 90, 270)) {
                newPoint.x = point.x + getXDistanceFactor(distance, angle);
                newPoint.y = point.y - getYDistanceFactor(distance, angle);
            } else /*if(isInBoundsOf(car.angle, 0, 90) || if(isInBoundsOf(car.angle, 270, 360))*/ {
                newPoint.x = point.x - getXDistanceFactor(distance, angle);
                newPoint.y = point.y + getYDistanceFactor(distance, angle);
            }
        }
        return newPoint;
    }

    // This is most of the distance formula (with the x extracted instead of the standard distance extracted).
    // Meaning x +- distance() the -/+ will be determined by the angle and direction (in the forwards and backwards methods)
    // and the y will use this as well for it's distance calculation.
    private static double getXDistanceFactor(double distance, double angle) {
        return distance / sqrtOfSquaredAnglePlusOne(angle);
    }

    private static double getYDistanceFactor(double distance, double angle) {
        return getTanOfAngle(angle) * getXDistanceFactor(distance, angle);
    }

    private static double sqrtOfSquaredAnglePlusOne(double angle) {
        return sqrt(1 + getTanOfAngle(angle) * getTanOfAngle(angle));
    }

    private static double getTanOfAngle(double angle) {
        return tan(toRadians(angle));
    }

}