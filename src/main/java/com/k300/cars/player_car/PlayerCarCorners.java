package com.k300.cars.player_car;

import com.k300.utils.Point;
import com.k300.utils.SLANT_ANGLE;

import java.util.ArrayList;
import static com.k300.utils.math.AnalyticalMath.*;

/*
 *       Purpose:
 *           Calculate the position of the car corners for a collision test
 *       Contains:
 *           length of the slant of the car (there are 2 slants, but the length of both is the same).
 *           angle of the slant of the car - there are 2 angles, first is carAngle+currentAngle and the second carAngle-currentAngle, for each slant.
 *           the car.
 *       How:
 *           The calculation is done just like the calculation of the movement of the car (see explanation in PlayerCarMover and AnalyticalMath classes).
 *           the different is the distance (in our case it's the slant length), and the angle - depends on the each slant.
 */

public class PlayerCarCorners {

    private final double slantAngle;
    private final PlayerCar car;
    private double slant;

    public PlayerCarCorners(PlayerCar car) {
        this.car = car;
        // getting the width of the car
        double widthFactor = car.carImage.getWidth() / 2f;
        // getting the height of the car
        double heightFactor = car.carImage.getHeight() / 2f;
        // calculating the slant length by using Pythagoras formula
        slant = Math.sqrt(widthFactor*widthFactor + heightFactor*heightFactor);
        // shorten the slant slightly so that it looks like it fits a drop inside the margin/obstacle
        slant = slant * 0.85;
        // calculating the slant angle by using Trigonometry Tan formula
        slantAngle = Math.toDegrees(Math.atan(heightFactor / widthFactor));
    }

    // return an array that contains all corners of the car
    public ArrayList<Point> getFourCarCorners() {
        ArrayList<Point> fourCarCorners = new ArrayList<>();
        fourCarCorners.add(getFrontLeftCorner());
        fourCarCorners.add(getFrontRightCorner());
        fourCarCorners.add(getRearLeftCorner());
        fourCarCorners.add(getRearRightCorner());
        return fourCarCorners;
    }

    // return the front left corner point by using getCorner() function
    public Point getFrontLeftCorner() {
        return getCorner(SLANT_ANGLE.POSITIVE.getValue(), MOVEMENT_DIRECTION.FORWARDS);
    }

    // return the front right corner point by using getCorner() function
    public Point getFrontRightCorner() {
        return getCorner(SLANT_ANGLE.NEGATIVE.getValue(), MOVEMENT_DIRECTION.FORWARDS);
    }

    // return the rear left corner point by using getCorner() function
    public Point getRearLeftCorner() {
        return getCorner(SLANT_ANGLE.NEGATIVE.getValue(), MOVEMENT_DIRECTION.BACKWARDS);
    }

    // return the rear right corner point by using getCorner() function
    public Point getRearRightCorner() {
        return getCorner(SLANT_ANGLE.POSITIVE.getValue(), MOVEMENT_DIRECTION.BACKWARDS);
    }

    // a generic function to get a corner point. gets slantDirection(1/-1) and MOVEMENT_DIRECTION(FORWARDS/BACKWARDS).
    // if the MOVEMENT_DIRECTION equals to FORWARDS it will return one of the front corners, depends on the slantDirection.
    // if the MOVEMENT_DIRECTION equals to BACKWARDS it will return one of the rear corners, depends on the slantDirection.
    private Point getCorner(int slantDirection, MOVEMENT_DIRECTION direction) {
        // calculating the rotated slant angle, depending on the slantDirection
        double rotatedSlantAngle = car.angle + (slantDirection * slantAngle);
        // calculating a corner, by using getNewPointByDistanceAndAngle() function
        return getNewPointByDistanceAndAngle(car.position, slant, rotatedSlantAngle, direction);
    }

}
