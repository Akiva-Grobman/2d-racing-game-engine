package com.k300.cars.player_car;


import com.k300.cars.Car;
import com.k300.utils.Point;

import static com.k300.utils.Utils.isInBoundsOf;
import static com.k300.utils.math.AnalyticalMath.*;

public class PlayerCarCorners {
    private double slant;
    private final double slantAngle;
    private final PlayerCar car;
    private final Point topRightCorner;
    private final Point bottomRightCorner;
    private final Point topLeftCorner;
    private final Point bottomLeftCorner;

    public PlayerCarCorners(Car car) {
        this.car = (PlayerCar) car;
        topRightCorner = new Point();
        bottomRightCorner = new Point();
        topLeftCorner = new Point();
        bottomLeftCorner = new Point();

        double widthFactor = car.carImage.getWidth() / 2f;
        double heightFactor = car.carImage.getHeight() / 2f;
        slant = Math.sqrt(widthFactor*widthFactor + heightFactor*heightFactor);
        slant = slant * 0.85;
        slantAngle = Math.toDegrees(Math.atan(heightFactor / widthFactor));
    }
    
    public Point getTopRightCorner() {
        double rotatedSlantAngle = car.angle + slantAngle;
        if(isInBoundsOf(rotatedSlantAngle,90, 270)) {
            topRightCorner.x = car.position.x - getXDistanceFactor(slant, rotatedSlantAngle);
            topRightCorner.y = car.position.y + getYDistanceFactor(slant, rotatedSlantAngle);
        } else /*if(isInBoundsOf(car.angle, 0, 90) || if(isInBoundsOf(car.angle, 270, 360))*/{
            topRightCorner.x = car.position.x + getXDistanceFactor(slant, rotatedSlantAngle);
            topRightCorner.y = car.position.y - getYDistanceFactor(slant, rotatedSlantAngle);
        }
        return topRightCorner;
    }

    public Point getBottomRightCorner() {
        double rotatedSlantAngle = car.angle - slantAngle;

        if(isInBoundsOf(rotatedSlantAngle,90, 270)) {
            bottomRightCorner.x = car.position.x - getXDistanceFactor(slant, rotatedSlantAngle);
            bottomRightCorner.y = car.position.y + getYDistanceFactor(slant, rotatedSlantAngle);
        } else /*if(isInBoundsOf(car.angle, 0, 90) || if(isInBoundsOf(car.angle, 270, 360))*/{
            bottomRightCorner.x = car.position.x + getXDistanceFactor(slant, rotatedSlantAngle);
            bottomRightCorner.y = car.position.y - getYDistanceFactor(slant, rotatedSlantAngle);
        }
        return bottomRightCorner;
    }

    public Point getTopLeftCorner() {
        double rotatedSlantAngle = car.angle - slantAngle;

        if(isInBoundsOf(rotatedSlantAngle,90, 270)) {
            topLeftCorner.x = car.position.x + getXDistanceFactor(slant, rotatedSlantAngle);
            topLeftCorner.y = car.position.y - getYDistanceFactor(slant, rotatedSlantAngle);
        } else /*if(isInBoundsOf(car.angle, 0, 90) || if(isInBoundsOf(car.angle, 270, 360))*/{
            topLeftCorner.x = car.position.x - getXDistanceFactor(slant, rotatedSlantAngle);
            topLeftCorner.y = car.position.y + getYDistanceFactor(slant, rotatedSlantAngle);
        }
        return topLeftCorner;
    }

    public Point getBottomLeftCorner() {
        double rotatedSlantAngle = car.angle + slantAngle;

        if(isInBoundsOf(rotatedSlantAngle,90, 270)) {
            bottomLeftCorner.x = car.position.x + getXDistanceFactor(slant, rotatedSlantAngle);
            bottomLeftCorner.y = car.position.y - getYDistanceFactor(slant, rotatedSlantAngle);
        } else /*if(isInBoundsOf(car.angle, 0, 90) || if(isInBoundsOf(car.angle, 270, 360))*/{
            bottomLeftCorner.x = car.position.x - getXDistanceFactor(slant, rotatedSlantAngle);
            bottomLeftCorner.y = car.position.y + getYDistanceFactor(slant, rotatedSlantAngle);
        }
        return bottomLeftCorner;
    }

}
