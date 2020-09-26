package com.k300.tracks;

import com.k300.utils.Converter;

import java.awt.geom.Point2D;

public class Margins {

    private final double smallA;
    private final double bigA;
    private final double positiveSmallC;
    private final double negativeSmallC;
    private final double positiveBigC;
    private final double negativeBigC;

    public Margins(int a, int b) {
        smallA = a / 2f;
        bigA = (a * 1.7) / 2f;
        final double bigB = (b * 1.7) / 2f;
        final double smallB = b / 2f;

        positiveSmallC = getPositiveC(smallA, smallB);
        negativeSmallC = getNegativeC(smallA, smallB);

        positiveBigC = getPositiveC(bigA, bigB);
        negativeBigC = getNegativeC(bigA, bigB);
    }

    public boolean isOnTheTrack(double carX, double carY) {
        double axisX;
        double axisY;
        double smallDistance1;
        double smallDistance2;
        double bigDistance1;
        double bigDistance2;

        axisX = Converter.getAxisX(carX);
        axisY = Converter.getAxisY(carY);

        smallDistance1 = getDistance(positiveSmallC, axisX, 0, axisY);
        smallDistance2 = getDistance(negativeSmallC, axisX, 0, axisY);

        bigDistance1 = getDistance(positiveBigC, axisX, 0, axisY);
        bigDistance2 = getDistance(negativeBigC, axisX, 0, axisY);

        double smallDistance = smallDistance1 + smallDistance2;
        double bigDistance = bigDistance1 + bigDistance2;
        return (smallDistance > (2 * smallA) && bigDistance < (2 * bigA));
    }

    private double getDistance(double x1, double x2, double y1, double y2) {
        return Point2D.distance(x1, y1, x2, y2);
    }

    private double getPositiveC(double a, double b) {
        return Math.sqrt( (Math.pow(a, 2) - Math.pow(b, 2)) );
    }

    private double getNegativeC(double a, double b) {
        return -( Math.sqrt( (Math.pow(a, 2) - Math.pow(b, 2)) ) );
    }
}
