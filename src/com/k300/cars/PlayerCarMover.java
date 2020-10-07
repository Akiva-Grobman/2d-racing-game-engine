package com.k300.cars;

import static com.k300.utils.math.AnalyticalMath.*;

public class PlayerCarMover {

    private final double CAR_TURNING_ANGLE;
    private final int RIGHT_ROTATION;
    private final int LEFT_ROTATION;
    private final PlayerCar car;

    public PlayerCarMover(PlayerCar car) {
        this.car = car;

        CAR_TURNING_ANGLE = 4;
        RIGHT_ROTATION = -1;
        LEFT_ROTATION = 1;
    }

    public void driveForwards() {
        double newX;
        double newY;
        if(isInBoundsOf(car.angle, 0, 90)) {
            newX = car.x + getXDistanceFactor(car.speed, car.angle);
            newY = car.y - getYDistanceFactor(car.speed, car.angle);
        } else if(isInBoundsOf(car.angle, 90, 180)) {
            newX = car.x - getXDistanceFactor(car.speed, car.angle);
            newY = car.y + getYDistanceFactor(car.speed, car.angle);
        } else if(isInBoundsOf(car.angle, 180, 270)) {
            newX = car.x - getXDistanceFactor(car.speed, car.angle);
            newY = car.y + getYDistanceFactor(car.speed, car.angle);
        } else /*if(angle >= 270 && angle <= 360)*/ {
            newX = car.x + getXDistanceFactor(car.speed, car.angle);
            newY = car.y - getYDistanceFactor(car.speed, car.angle);
        }
        setNewPosition(newX, newY);
    }

    public void driveBackwards() {
        double newX;
        double newY;
        if(isInBoundsOf(car.angle, 0, 90)) {
            newX = car.x - getXDistanceFactor(car.speed, car.angle);
            newY = car.y + getYDistanceFactor(car.speed, car.angle);
        } else if(isInBoundsOf(car.angle, 90, 180)) {
            newX = car.x + getXDistanceFactor(car.speed, car.angle);
            newY = car.y - getYDistanceFactor(car.speed, car.angle);
        } else if(isInBoundsOf(car.angle, 180, 270)) {
            newX = car.x + getXDistanceFactor(car.speed, car.angle);
            newY = car.y - getYDistanceFactor(car.speed, car.angle);
        } else /*if(angle >= 270 && angle <= 360)*/ {
            newX = car.x - getXDistanceFactor(car.speed, car.angle);
            newY = car.y + getYDistanceFactor(car.speed, car.angle);
        }
        setNewPosition(newX, newY);
    }

    private void setNewPosition(double newX, double newY) {
        // rounding is for display prepossess
        // 5 numbers after the decimal point
        car.x = Math.round(newX * 10000) / 10000.0;
        car.y = Math.round(newY * 10000) / 10000.0;
    }

    public void turnRight() {
        turn(RIGHT_ROTATION);
    }

    public void turnLeft() {
        turn(LEFT_ROTATION);
    }

    private void turn(int multiplier) {
        car.angle += CAR_TURNING_ANGLE * multiplier;
        if(car.angle == 90) {
            car.angle += CAR_TURNING_ANGLE * multiplier;
        }
        resetAngle();
    }

    private void resetAngle() {
        if (car.angle > 360) {
            car.angle = 0;
        } else if (car.angle < 0) {
            car.angle = 360;
        }
    }

}
