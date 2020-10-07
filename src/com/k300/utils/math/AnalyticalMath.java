package com.k300.utils.math;

import static java.lang.Math.*;

public class AnalyticalMath {

    public static boolean isInBoundsOf(double angle, int lowerBound, int upperBound) {
        return angle >= lowerBound && angle <= upperBound;
    }

    // This is most of the distance formula (with the x extracted instead of the standard distance extracted).
    // Meaning x +- distance() the -/+ will be determined by the angle and direction (in the forwards and backwards methods)
    // and the y will use this as well for it's distance calculation.
    public static double getXDistanceFactor(double speed, double angle) {
        return speed / sqrtOfSquaredAnglePlusOne(angle);
    }

    public static double getYMovementFactor(double speed, double angle) {
        return getTanOfAngle(angle) * getXDistanceFactor(speed, angle);
    }

    private static double sqrtOfSquaredAnglePlusOne(double angle) {
        return sqrt(1 + getTanOfAngle(angle) * getTanOfAngle(angle));
    }

    private static double getTanOfAngle(double angle) {
        return tan(toRadians(angle));
    }

}