package com.k300.cars.player_car;

import com.k300.utils.Point;
import com.k300.utils.SLANT_ANGLE;

import java.util.ArrayList;
import static com.k300.utils.math.AnalyticalMath.*;

public class PlayerCarCorners {

    private final double slantAngle;
    private final PlayerCar car;
    private double slant;

    public PlayerCarCorners(PlayerCar car) {
        this.car = car;
        double widthFactor = car.carImage.getWidth() / 2f;
        double heightFactor = car.carImage.getHeight() / 2f;
        slant = Math.sqrt(widthFactor*widthFactor + heightFactor*heightFactor);
        slant = slant * 0.85;
        slantAngle = Math.toDegrees(Math.atan(heightFactor / widthFactor));
    }

    public ArrayList<Point> getFourCarCorners() {
        ArrayList<Point> fourCarCorners = new ArrayList<>();
        fourCarCorners.add(getFrontLeftCorner());
        fourCarCorners.add(getFrontRightCorner());
        fourCarCorners.add(getRearLeftCorner());
        fourCarCorners.add(getRearRightCorner());
        return fourCarCorners;
    }

    public Point getFrontLeftCorner() {
        return getCorner(SLANT_ANGLE.POSITIVE.getValue(), DIRECTION.FORWARDS);
    }

    public Point getFrontRightCorner() {
        return getCorner(SLANT_ANGLE.NEGATIVE.getValue(), DIRECTION.FORWARDS);
    }

    public Point getRearLeftCorner() {
        return getCorner(SLANT_ANGLE.NEGATIVE.getValue(), DIRECTION.BACKWARDS);
    }

    public Point getRearRightCorner() {
        return getCorner(SLANT_ANGLE.POSITIVE.getValue(), DIRECTION.BACKWARDS);
    }

    private Point getCorner(int slantDirection, DIRECTION direction) {
        double rotatedSlantAngle = car.angle + (slantDirection * slantAngle);
        return getNewPointByDistanceAndAngle(car.position, slant, rotatedSlantAngle, direction);
    }

}
