package com.k300.cars;


import com.k300.utils.Point;

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
        if(isInBoundsOf(rotatedSlantAngle,0, 90)) {
            topRightCorner.x = car.x + getXDistanceFactor(rotatedSlantAngle, slant);
            topRightCorner.y = car.y - getYDistanceFactor(rotatedSlantAngle, slant);
        } else if(isInBoundsOf(rotatedSlantAngle,90, 180)) {
            topRightCorner.x = car.x - getXDistanceFactor(rotatedSlantAngle, slant);
            topRightCorner.y = car.y + getYDistanceFactor(rotatedSlantAngle, slant);
        } else if(isInBoundsOf(rotatedSlantAngle,180, 270)) {
            topRightCorner.x = car.x - getXDistanceFactor(rotatedSlantAngle, slant);
            topRightCorner.y = car.y + getYDistanceFactor(slant, rotatedSlantAngle);
        } else /*if(angle >= 270 && angle <= 360)*/ {
            topRightCorner.x = car.x + getXDistanceFactor(rotatedSlantAngle, slant);
            topRightCorner.y = car.y - getYDistanceFactor(rotatedSlantAngle, slant);
        }
        return topRightCorner;
    }

    public Point getBottomRightCorner() {
        double rotatedSlantAngle = car.angle - slantAngle;

        if(isInBoundsOf(rotatedSlantAngle,0, 90)) {
            bottomRightCorner.x = car.x + getXDistanceFactor(rotatedSlantAngle, slant);
            bottomRightCorner.y = car.y - getYDistanceFactor(rotatedSlantAngle, slant);
        } else if(isInBoundsOf(rotatedSlantAngle,90, 180)) {
            bottomRightCorner.x = car.x - getXDistanceFactor(rotatedSlantAngle, slant);
            bottomRightCorner.y = car.y + getYDistanceFactor(rotatedSlantAngle, slant);
        } else if(isInBoundsOf(rotatedSlantAngle,180, 270)) {
            bottomRightCorner.x = car.x - getXDistanceFactor(rotatedSlantAngle, slant);
            bottomRightCorner.y = car.y + getYDistanceFactor(rotatedSlantAngle, slant);
        } else /*if(angle >= 270 && angle <= 360)*/ {
            bottomRightCorner.x = car.x + getXDistanceFactor(rotatedSlantAngle, slant);
            bottomRightCorner.y = car.y - getYDistanceFactor(rotatedSlantAngle, slant);
        }
        return bottomRightCorner;
    }

    public Point getTopLeftCorner() {
        double rotatedSlantAngle = car.angle - slantAngle;

        if(isInBoundsOf(rotatedSlantAngle,0, 90)) {
            topLeftCorner.x = car.x - getXDistanceFactor(rotatedSlantAngle, slant);
            topLeftCorner.y = car.y + getYDistanceFactor(rotatedSlantAngle, slant);
        } else if(isInBoundsOf(rotatedSlantAngle,90, 180)) {
            topLeftCorner.x = car.x + getXDistanceFactor(rotatedSlantAngle, slant);
            topLeftCorner.y = car.y - getYDistanceFactor(rotatedSlantAngle, slant);
        } else if(isInBoundsOf(rotatedSlantAngle,180, 270)) {
            topLeftCorner.x = car.x + getXDistanceFactor(rotatedSlantAngle, slant);
            topLeftCorner.y = car.y - getYDistanceFactor(rotatedSlantAngle, slant);
        } else /*if(angle >= 270 && angle <= 360)*/ {
            topLeftCorner.x = car.x - getXDistanceFactor(rotatedSlantAngle, slant);
            topLeftCorner.y = car.y + getYDistanceFactor(rotatedSlantAngle, slant);
        }
        return topLeftCorner;
    }

    public Point getBottomLeftCorner() {
        double rotatedSlantAngle = car.angle + slantAngle;

        if(isInBoundsOf(rotatedSlantAngle,0, 90)) {
            bottomLeftCorner.x = car.x - getXDistanceFactor(rotatedSlantAngle, slant);
            bottomLeftCorner.y = car.y + getYDistanceFactor(rotatedSlantAngle, slant);
        } else if(isInBoundsOf(rotatedSlantAngle,90, 180)) {
            bottomLeftCorner.x = car.x + getXDistanceFactor(rotatedSlantAngle, slant);
            bottomLeftCorner.y = car.y - getYDistanceFactor(rotatedSlantAngle, slant);
        } else if(isInBoundsOf(rotatedSlantAngle,180, 270)) {
            bottomLeftCorner.x = car.x + getXDistanceFactor(rotatedSlantAngle, slant);
            bottomLeftCorner.y = car.y - getYDistanceFactor(rotatedSlantAngle, slant);
        } else /*if(angle >= 270 && angle <= 360)*/ {
            bottomLeftCorner.x = car.x - getXDistanceFactor(rotatedSlantAngle, slant);
            bottomLeftCorner.y = car.y + getYDistanceFactor(rotatedSlantAngle, slant);
        }
        return bottomLeftCorner;
    }

}
