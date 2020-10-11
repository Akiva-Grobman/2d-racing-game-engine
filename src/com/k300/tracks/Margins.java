package com.k300.tracks;

import com.k300.graphics.Assets;
import com.k300.utils.Point;
import com.k300.utils.math.Converter;

import java.awt.*;
import java.awt.geom.Point2D;

public class Margins {

    private final double smallA;
    private final double bigA;
    private final double smallB;
    private final double bigB;
    private final double positiveSmallC;
    private final double negativeSmallC;
    private final double positiveBigC;
    private final double negativeBigC;
    private final Rectangle startingLineBounds;

    public Margins(double a, double b) {
        smallA = a / 2;
        smallB = b / 2;
        bigA = (a * 1.98) / 2;
        bigB = (b * 1.97) / 2;

        positiveSmallC = getPositiveC(smallA, smallB);
        negativeSmallC = getNegativeC(smallA, smallB);

        positiveBigC = getPositiveC(bigA, bigB);
        negativeBigC = getNegativeC(bigA, bigB);


        int width = Assets.getImage(Assets.BLUE_CAR_KEY).getHeight() / 5 * 4;
        int height = getNegativeBigB() - getNegativeSmallB();
        int x = Converter.DEFAULT_SCREEN_WIDTH / 2 - width / 2;
        int y = getNegativeSmallB();
        startingLineBounds = new Rectangle(x, y, width, height);
    }

    public Rectangle getStartingLineBounds() {
        return startingLineBounds;
    }

    private int getNegativeSmallB() {
        return (int) Converter.getFrameY(-smallB);
    }

    private int getNegativeBigB() {
        return (int) Converter.getFrameY(-bigB);
    }

    private double getDistance(double x1, double y1, double x2,  double y2) {
        return Point2D.distance(x1, y1, x2, y2);
    }

    private double getPositiveC(double a, double b) {
        return Math.sqrt( (Math.pow(a, 2) - Math.pow(b, 2)) );
    }

    private double getNegativeC(double a, double b) {
        return -( Math.sqrt( (Math.pow(a, 2) - Math.pow(b, 2)) ) );
    }

    public boolean onTheTrack(Point position) {
        double axisX;
        double axisY;
        double smallDistance1;
        double smallDistance2;
        double bigDistance1;
        double bigDistance2;

        axisX = Converter.getAxisX(position.x);
        axisY = Converter.getAxisY(position.y);

        smallDistance1 = getDistance(positiveSmallC, 0, axisX, axisY);
        smallDistance2 = getDistance(negativeSmallC, 0, axisX, axisY);

        bigDistance1 = getDistance(positiveBigC, 0, axisX, axisY);
        bigDistance2 = getDistance(negativeBigC, 0, axisX, axisY);

        double smallDistance = smallDistance1 + smallDistance2;
        double bigDistance = bigDistance1 + bigDistance2;
        return (smallDistance > (2 * smallA) && bigDistance < (2 * bigA));
    }
}
