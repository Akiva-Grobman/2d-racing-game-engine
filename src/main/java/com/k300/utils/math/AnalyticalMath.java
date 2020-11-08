package com.k300.utils.math;

import com.k300.cars.player_car.MOVEMENT_DIRECTION;
import com.k300.utils.Point;
import static java.lang.Math.*;

/*
 *       Purpose:
 *           This class is used to return new points in the axis in terms of angle, distance and "direction" (the Direction param is relative to "human looking" and isn't to axis direction).
 *           This class is used for the car calculations.
 *       How:
 *           The calculations of new points will use the mathematical distance formula: d²=(x1-x2)² + (y1-y2)².
 *           But in our case, we need to extract x2 and y2 because we are looking for new (x,y) coordinates in given a distance and an old (x,y) coordinates.
 *
 *           So the new formulas for the new x are:
 *           For axis forwards -  newX = oldX + ( distance * (1 / sqrt(1+m²)) )
 *           For axis backwards -  newX = oldX - ( distance * (1 / sqrt(1+m²)) )
 *
 *           And the new formulas for the new y are:
 *           For axis forwards -  newY = oldY + m( distance * (1 / sqrt(1+m²)) )
 *           For axis backwards -  newY = oldY - m( distance * (1 / sqrt(1+m²)) )
 *
 *           The param "m" is used for the tan(angle) of our car calculations.
 */

public class AnalyticalMath {

    // Checks if an angle is in bounds of a field
    private static boolean angleIsInBoundsOf(double angle, int lowerBound, int upperBound) {
        return angle >= lowerBound && angle <= upperBound;
    }

    // This is the main function in this class.
    // This function gets an old point and distance from this point, angle of the line and direction(forwards/backwards).
    // The function would return new point in terms of the params.
    public static Point getNewPointByDistanceAndAngle(Point point, double distance, double angle, MOVEMENT_DIRECTION direction) {
        // The Point that would return
        Point newPoint = new Point();

        // First we check if we need the forward point or the backward forward from the original point position.
        // The Direction is used for "human looking" so we check if we need to do the positive formula or the negative one,
        // in terms of the angle and the direction

        // If we need to find forwards new point in "human looking"
        if(direction == MOVEMENT_DIRECTION.FORWARDS) {
            // If the angle is in the left of the angle "circle"
            if (angleIsInBoundsOf(angle, 90, 270)) {
                //Then we need to reduce the x because the car is turning to the left of the screen and we want forwards.
                newPoint.x = point.x - getXDistanceFactor(distance, angle);
                // The y will be relative to the x(positive/negative).
                newPoint.y = point.y + getYDistanceFactor(distance, angle);
            // If the angle is in the right of the angle "circle", that mean we need to redound the x because the car is turning to the right of the screen and we want forwards.
            // The y will be relative to the x(positive/negative).
            } else /*if(angleIsInBoundsOf(angle, 0, 90) || if(angleIsInBoundsOf(angle, 270, 360))*/ {
                newPoint.x = point.x + getXDistanceFactor(distance, angle);
                newPoint.y = point.y - getYDistanceFactor(distance, angle);
            }
        // If we need to find backwards new point in "human looking"
        } else {
            // If the angle is in the left of the angle "circle"
            if (angleIsInBoundsOf(angle, 90, 270)) {
                // Then we need to reduce the x because the car is turning to the left of the screen and we want backwards.
                newPoint.x = point.x + getXDistanceFactor(distance, angle);
                // The y will be relative to the x(positive/negative).
                newPoint.y = point.y - getYDistanceFactor(distance, angle);
            // If the angle is in the right of the angle "circle"
            } else /*if(isInBoundsOf(car.angle, 0, 90) || if(isInBoundsOf(car.angle, 270, 360))*/ {
                // Then we need to redound the x because the car is turning to the right of the screen and we want backwards.
                newPoint.x = point.x - getXDistanceFactor(distance, angle);
                // The y will be relative to the x(positive/negative).
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

    // This is most of the distance formula (with the y extracted instead of the standard distance extracted).
    // Meaning y +- distance() the -/+ will be determined by the angle and direction (in the forwards and backwards methods)
    private static double getYDistanceFactor(double distance, double angle) {
        return getTanOfAngle(angle) * getXDistanceFactor(distance, angle);
    }

    // Return sqrt(1 + tan(angle)²)
    private static double sqrtOfSquaredAnglePlusOne(double angle) {
        return sqrt(1 + getTanOfAngle(angle) * getTanOfAngle(angle));
    }

    // Return tan(angle)
    private static double getTanOfAngle(double angle) {
        return tan(toRadians(angle));
    }

}