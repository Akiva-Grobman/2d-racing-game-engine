package com.k300.utils.math;

import static java.lang.Math.*;

public class AnalyticalMath {

    // This is most of the distance formula (with the x extracted instead of the standard distance extracted).
    // Meaning x +- distance() the -/+ will be determined by the angle and direction (in the forwards and backwards methods)
    // and the y will use this as well for it's distance calculation.
    public static double getXDistanceFactor(double distance, double angle) {
        return distance / sqrtOfSquaredAnglePlusOne(angle);
    }

    public static double getYDistanceFactor(double distance, double angle) {
        return getTanOfAngle(angle) * getXDistanceFactor(distance, angle);
    }

    private static double sqrtOfSquaredAnglePlusOne(double angle) {
        return sqrt(1 + getTanOfAngle(angle) * getTanOfAngle(angle));
    }

    private static double getTanOfAngle(double angle) {
        return tan(toRadians(angle));
    }

}