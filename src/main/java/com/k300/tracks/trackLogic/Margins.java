package com.k300.tracks.trackLogic;

import com.k300.graphics.Assets;
import com.k300.utils.Point;
import com.k300.utils.math.Converter;

import java.awt.geom.Point2D;

/*
 *       Purpose:
 *           This represents a part of the track the car can't drive on (obviously with a visual representation on the track).
 *           This part represents the margins of the road(There are only 2 margins: inside margin and outside margin)
 *       Contains:
 *           |a|, |b|, and |c| for each margin. (The margins are ellipses)
 *       How:
 *           First of all, we build the ellipses by using images parts of the track,
 *           from there we pull our width and height of the margins.
 *           The width and height of the margin are actually our |a| and |b| of our margins.
 *           |a| represents the width and |b| represents the height.
 *
 *           After we have |a| and |b| we need to find |c| of the ellipse.
 *           By |c| we can check if a point is inside the ellipse or outside the ellipse.
 *
 *           To check if a point is inside/outside the road we first need to find the distances between the point to the |c|'s,
 *           the negative c and the positive c. We will mark them as distance1 and distance2.
 *           The formula distance1+distance2=2a represents all points on the ellipse.
 *           It means there will be equality only if the point on the ellipse.
 *           If the point is outside the ellipse, then distance1+distance2 > 2a will be true.
 *           If the point is inside the ellipse, then distance1+distance2 < 2a will be true.
 *
 *           To find |c| we need to use the formula c²=a²-b².
 */


public class Margins {

    // the a of the inside ellipse
    private final double smallA;
    // the b of the inside ellipse
    private final double smallB;
    // the b of the outside ellipse
    private final double bigA;
    // the b of the outside ellipse
    private final double bigB;
    // the positive c of the inside ellipse
    private final double positiveSmallC;
    // the negative c of the inside ellipse
    private final double negativeSmallC;
    // the positive c of the outside ellipse
    private final double positiveBigC;
    // the negative c of the outside ellipse
    private final double negativeBigC;

    public Margins() {
        // getting the width and height of the both margins
        double middleWidth = Assets.getImage(Assets.TRACK_MIDDLE_STROKE_KEY).getWidth();
        double middleHeight = Assets.getImage(Assets.TRACK_MIDDLE_STROKE_KEY).getHeight();
        double roadWidth = Assets.getImage(Assets.ROAD_KEY).getWidth();
        double roadHeight = Assets.getImage(Assets.ROAD_KEY).getHeight();

        // initialize object variables.
        // (We divide by 2 because the width and height represents |a| and |b| start from first the hinges,
        // and goes to each side from there)
        smallA = middleWidth / 2;
        smallB = middleHeight / 2;
        bigA = roadWidth / 2;
        bigB = roadHeight / 2;

        positiveSmallC = getPositiveC(smallA, smallB);
        negativeSmallC = getNegativeC(smallA, smallB);

        positiveBigC = getPositiveC(bigA, bigB);
        negativeBigC = getNegativeC(bigA, bigB);
    }

    // return the distance between 2 points
    private double getDistance(double x1, @SuppressWarnings("SameParameterValue") double y1, double x2, double y2) {
        return Point2D.distance(x1, y1, x2, y2);
    }

    // return a positive c by the formula c²=a²-b²
    private double getPositiveC(double a, double b) {
        return Math.sqrt( (Math.pow(a, 2) - Math.pow(b, 2)) );
    }

    // return a negative c by the formula c²=a²-b²
    private double getNegativeC(double a, double b) {
        return -( Math.sqrt( (Math.pow(a, 2) - Math.pow(b, 2)) ) );
    }

    // checks if the point inside the ellipse
    public boolean onTheTrack(Point position) {
        // declare auxiliary variables
        double axisX;
        double axisY;
        double smallDistance1;
        double smallDistance2;
        double bigDistance1;
        double bigDistance2;

        // because the points comes from the frame coordinate system,
        // we need to convert them to classic coordinate system(the ellipse is conical and in the center of the screen)
        axisX = Converter.getAxisX(position.x);
        axisY = Converter.getAxisY(position.y);

        // getting the distances between the point to both c points of the inside margin
        smallDistance1 = getDistance(positiveSmallC, 0, axisX, axisY);
        smallDistance2 = getDistance(negativeSmallC, 0, axisX, axisY);

        // getting the distances between the point to both c points of the outside margin
        bigDistance1 = getDistance(positiveBigC, 0, axisX, axisY);
        bigDistance2 = getDistance(negativeBigC, 0, axisX, axisY);

        // calculate distance1+distance2
        double smallDistance = smallDistance1 + smallDistance2;
        double bigDistance = bigDistance1 + bigDistance2;

        // return if the point inside the big ellipse(outside margin) AND outside the small ellipse(inside margin)
        return (smallDistance > (2 * smallA) && bigDistance < (2 * bigA));
    }

    // because we converted the coordinates to be on "virtual" classic coordinate system,
    // there are some cases that we want to get the physical coordinates on the frame.
    // This function will return the negative b of the inside margin, because we use this function only for the start line coordinates,
    // and the start line is located on the bottom of the axis(in the negative y).
    public Point getFrameSmallBPoint() {
        double x = Converter.getFrameX(0);
        double y = Converter.getFrameY(-smallB); //need negative B because the start line is on the lower part of the screen
        return new Point(x, y);
    }

    // because we converted the coordinates to be on "virtual" classic coordinate system,
    // there are some cases that we want to get the physical coordinates on the frame.
    // This function will return the negative b of the outside margin, because we use this function only for the start line coordinates,
    // and the start line is located on the bottom of the axis(in the negative y).
    public Point getFrameBigBPoint() {
        double x = Converter.getFrameX(0);
        double y = Converter.getFrameY(-bigB); //need negative B because the start line is on the lower part of the screen
        return new Point(x, y);
    }

}
