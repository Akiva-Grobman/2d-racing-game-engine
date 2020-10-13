package com.k300.tracks;

import com.k300.graphics.Assets;
import com.k300.utils.Point;
import com.k300.utils.math.Converter;

import java.awt.geom.Point2D;

public class Margins {

    private double smallA;
    private double bigA;
    private double smallB;
    private double bigB;
    private double positiveSmallC;
    private double negativeSmallC;
    private double positiveBigC;
    private double negativeBigC;

    public Margins() {
        double middleWidth = Assets.getImage(Assets.TRACK_MIDDLE_KEY).getWidth();
        double middleHeight = Assets.getImage(Assets.TRACK_MIDDLE_KEY).getHeight();
        double roadWidth = Assets.getImage(Assets.ROAD_KEY).getWidth();
        double roadHeight = Assets.getImage(Assets.ROAD_KEY).getHeight();

        smallA = middleWidth / 2;
        smallB = middleHeight / 2;
        bigA = roadWidth / 2;
        bigB = roadHeight / 2;

        positiveSmallC = getPositiveC(smallA, smallB);
        negativeSmallC = getNegativeC(smallA, smallB);

        positiveBigC = getPositiveC(bigA, bigB);
        negativeBigC = getNegativeC(bigA, bigB);
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

    public Point getFrameSmallBPoint() {
        double x = Converter.getFrameX(0);
        double y = Converter.getFrameY(-smallB); //need negative B because the start line is on the lower part of the screen
        return new Point(x, y);
    }

    public Point getFrameBigBPoint() {
        double x = Converter.getFrameX(0);
        double y = Converter.getFrameY(-bigB); //need negative B because the start line is on the lower part of the screen
        return new Point(x, y);
    }
}
