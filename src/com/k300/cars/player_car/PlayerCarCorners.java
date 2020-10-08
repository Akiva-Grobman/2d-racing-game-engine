package com.k300.cars.player_car;


import com.k300.cars.Car;
import com.k300.utils.Point;

import static com.k300.utils.Utils.isInBoundsOf;
import static com.k300.utils.math.AnalyticalMath.*;

public class PlayerCarCorners {
    private double slant;
    private final double slantAngle;
    private final PlayerCar car;
    private final Point frontLeftCorner;
    private final Point frontRightCorner;
    private final Point rearLeftCorner;
    private final Point rearRightCorner;

    public PlayerCarCorners(Car car) {
        this.car = (PlayerCar) car;
        frontLeftCorner = new Point();
        frontRightCorner = new Point();
        rearLeftCorner = new Point();
        rearRightCorner = new Point();

        double widthFactor = car.carImage.getWidth() / 2f;
        double heightFactor = car.carImage.getHeight() / 2f;
        slant = Math.sqrt(widthFactor*widthFactor + heightFactor*heightFactor);
        slant = slant * 0.85;
        slantAngle = Math.toDegrees(Math.atan(heightFactor / widthFactor));
    }
    
    public Point getFrontLeftCorner() {
        double rotatedSlantAngle = car.angle + slantAngle;
        return getNewPointByDistanceAndAngle(car.position, slant, rotatedSlantAngle, true);
    }

    public Point getFrontRightCorner() {
        double rotatedSlantAngle = car.angle - slantAngle;
        return getNewPointByDistanceAndAngle(car.position, slant, rotatedSlantAngle, true);
    }

    public Point getRearLeftCorner() {
        double rotatedSlantAngle = car.angle - slantAngle;
        return getNewPointByDistanceAndAngle(car.position, slant, rotatedSlantAngle, false);
    }

    public Point getRearRightCorner() {
        double rotatedSlantAngle = car.angle + slantAngle;
        return getNewPointByDistanceAndAngle(car.position, slant, rotatedSlantAngle, false);
    }

}
