package com.k300.cars.player_car;

import com.k300.utils.Point;

import static com.k300.utils.math.AnalyticalMath.*;

public class PlayerCarMover {

    private final double CAR_TURNING_ANGLE;
    private final PlayerCar car;

    public PlayerCarMover(PlayerCar car) {
        this.car = car;
        CAR_TURNING_ANGLE = 4;
    }

    public void driveForwards() {
        drive(MOVEMENT_DIRECTION.FORWARDS);
    }

    public void driveBackwards() {
        drive(MOVEMENT_DIRECTION.BACKWARDS);
    }

    private void drive(MOVEMENT_DIRECTION drivingDirection) {
        Point newPosition = getNewPointByDistanceAndAngle(car.position, car.speed, car.angle, drivingDirection);
        setNewPosition(newPosition);
    }

    private void setNewPosition(Point position) {
        // rounding is for display prepossess
        // 5 numbers after the decimal point
        car.position.x = Math.round(position.x * 10000) / 10000.0;
        car.position.y = Math.round(position.y * 10000) / 10000.0;
    }

    public void turnRight() {
        turn(TURNING_DIRECTION.RIGHT);
    }

    public void turnLeft() {
        turn(TURNING_DIRECTION.LEFT);
    }

    private void turn(TURNING_DIRECTION direction) {
        car.angle += CAR_TURNING_ANGLE * direction.getRotationMultiplier();
        if(car.angle == 90) {
            car.angle += CAR_TURNING_ANGLE * direction.getRotationMultiplier();
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
