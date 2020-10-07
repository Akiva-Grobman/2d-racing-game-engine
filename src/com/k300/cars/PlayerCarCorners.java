package com.k300.cars;


import com.k300.utils.Point;

import static com.k300.utils.math.AnalyticalMath.*;

public class PlayerCarCorners {
    private double slant;
    private final double slantAngle;
    private final PlayerCar car;
    private final Point TopRightCorner;
    private final Point BottomRightCorner;
    private final Point TopLeftCorner;
    private final Point BottomLeftCorner;

    public PlayerCarCorners(PlayerCar car) {
        this.car = car;
        TopRightCorner = new Point();
        BottomRightCorner = new Point();
        TopLeftCorner = new Point();
        BottomLeftCorner = new Point();

        double widthFactor = car.carImage.getWidth() / 2f;
        double heightFactor = car.carImage.getHeight() / 2f;
        slant = Math.sqrt(widthFactor*widthFactor + heightFactor*heightFactor);
        slant = slant * 0.85;
        slantAngle = Math.toDegrees(Math.atan(heightFactor / widthFactor));
    }
    
    private void getTopRightCorner() {
        double rotatedSlantAngle = car.angle + slantAngle;
        if(isInBoundsOf(rotatedSlantAngle,0, 90)) {
            TopRightCorner.x = car.x + getXDistanceFactor(rotatedSlantAngle, slant);
            TopRightCorner.y = car.y - getYDistanceFactor(rotatedSlantAngle, slant);
        } else if(isInBoundsOf(rotatedSlantAngle,90, 180)) {
            TopRightCorner.x = car.x - getXDistanceFactor(rotatedSlantAngle, slant);
            TopRightCorner.y = car.y + getYDistanceFactor(rotatedSlantAngle, slant);
        } else if(isInBoundsOf(rotatedSlantAngle,180, 270)) {
            TopRightCorner.x = car.x - getXDistanceFactor(rotatedSlantAngle, slant);
            TopRightCorner.y = car.y + getYDistanceFactor(slant, rotatedSlantAngle);
        } else /*if(angle >= 270 && angle <= 360)*/ {
            TopRightCorner.x = car.x + getXDistanceFactor(rotatedSlantAngle, slant);
            TopRightCorner.y = car.y - getYDistanceFactor(rotatedSlantAngle, slant);
        }
    }

    private void getBottomRightCorner() {
        double rotatedSlantAngle = car.angle - slantAngle;

        if(isInBoundsOf(rotatedSlantAngle,0, 90)) {
            BottomRightCorner.x = car.x + getXDistanceFactor(rotatedSlantAngle, slant);
            BottomRightCorner.y = car.y - getYDistanceFactor(rotatedSlantAngle, slant);
        } else if(isInBoundsOf(rotatedSlantAngle,90, 180)) {
            BottomRightCorner.x = car.x - getXDistanceFactor(rotatedSlantAngle, slant);
            BottomRightCorner.y = car.y + getYDistanceFactor(rotatedSlantAngle, slant);
        } else if(isInBoundsOf(rotatedSlantAngle,180, 270)) {
            BottomRightCorner.x = car.x - getXDistanceFactor(rotatedSlantAngle, slant);
            BottomRightCorner.y = car.y + getYDistanceFactor(rotatedSlantAngle, slant);
        } else /*if(angle >= 270 && angle <= 360)*/ {
            BottomRightCorner.x = car.x + getXDistanceFactor(rotatedSlantAngle, slant);
            BottomRightCorner.y = car.y - getYDistanceFactor(rotatedSlantAngle, slant);
        }
    }

    private void getTopLeftCorner() {
        double rotatedSlantAngle = car.angle - slantAngle;

        if(isInBoundsOf(rotatedSlantAngle,0, 90)) {
            TopLeftCorner.x = car.x - getXDistanceFactor(rotatedSlantAngle, slant);
            TopLeftCorner.y = car.y + getYDistanceFactor(rotatedSlantAngle, slant);
        } else if(isInBoundsOf(rotatedSlantAngle,90, 180)) {
            TopLeftCorner.x = car.x + getXDistanceFactor(rotatedSlantAngle, slant);
            TopLeftCorner.y = car.y - getYDistanceFactor(rotatedSlantAngle, slant);
        } else if(isInBoundsOf(rotatedSlantAngle,180, 270)) {
            TopLeftCorner.x = car.x + getXDistanceFactor(rotatedSlantAngle, slant);
            TopLeftCorner.y = car.y - getYDistanceFactor(rotatedSlantAngle, slant);
        } else /*if(angle >= 270 && angle <= 360)*/ {
            TopLeftCorner.x = car.x - getXDistanceFactor(rotatedSlantAngle, slant);
            TopLeftCorner.y = car.y + getYDistanceFactor(rotatedSlantAngle, slant);
        }
    }

    private void getBottomLeftCorner() {
        double rotatedSlantAngle = car.angle + slantAngle;

        if(isInBoundsOf(rotatedSlantAngle,0, 90)) {
            BottomLeftCorner.x = car.x - getXDistanceFactor(rotatedSlantAngle, slant);
            BottomLeftCorner.y = car.y + getYDistanceFactor(rotatedSlantAngle, slant);
        } else if(isInBoundsOf(rotatedSlantAngle,90, 180)) {
            BottomLeftCorner.x = car.x + getXDistanceFactor(rotatedSlantAngle, slant);
            BottomLeftCorner.y = car.y - getYDistanceFactor(rotatedSlantAngle, slant);
        } else if(isInBoundsOf(rotatedSlantAngle,180, 270)) {
            BottomLeftCorner.x = car.x + getXDistanceFactor(rotatedSlantAngle, slant);
            BottomLeftCorner.y = car.y - getYDistanceFactor(rotatedSlantAngle, slant);
        } else /*if(angle >= 270 && angle <= 360)*/ {
            BottomLeftCorner.x = car.x - getXDistanceFactor(rotatedSlantAngle, slant);
            BottomLeftCorner.y = car.y + getYDistanceFactor(rotatedSlantAngle, slant);
        }
    }

}
