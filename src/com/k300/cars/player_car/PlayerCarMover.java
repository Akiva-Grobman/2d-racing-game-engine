package com.k300.cars.player_car;

import com.k300.utils.Point;

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
        Point newPoint = getNewPointByDistanceAndAngle(car.position, car.speed, car.angle, true);
        setNewPosition(newPoint.x, newPoint.y);
    }

    public void driveBackwards() {
        Point newPoint = getNewPointByDistanceAndAngle(car.position, car.speed, car.angle, false);
        setNewPosition(newPoint.x, newPoint.y);
    }

    private void setNewPosition(double newX, double newY) {
        // rounding is for display prepossess
        // 5 numbers after the decimal point
        car.position.x = Math.round(newX * 10000) / 10000.0;
        car.position.y = Math.round(newY * 10000) / 10000.0;
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
