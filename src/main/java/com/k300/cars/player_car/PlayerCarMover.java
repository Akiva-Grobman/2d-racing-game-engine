package com.k300.cars.player_car;

import com.k300.utils.Point;

import static com.k300.utils.math.AnalyticalMath.*;

/*
 *       Purpose:
 *           This class is responsible to move a car from the the current position to the next position,
 *           depends on the driving direction, car angle, turning angle and speed.
 *       How:
 *           this class is used AnalyticalMath class to get new positions that depends on the variables.
 */


public class PlayerCarMover {

    // the turning angle of the car
    private final double CAR_TURNING_ANGLE;
    // the car object
    private final PlayerCar car;

    public PlayerCarMover(PlayerCar car) {
        this.car = car;
        CAR_TURNING_ANGLE = 4;
    }

    // moves the car forwards by using drive() function
    public void driveForwards() {
        drive(MOVEMENT_DIRECTION.FORWARDS);
    }

    // moves the car backwards by using drive() function
    public void driveBackwards() {
        drive(MOVEMENT_DIRECTION.BACKWARDS);
    }

    // moves the car depends on the drivingDirection(forwards/backwards)
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

    // turns the car right by using turn() function
    public void turnRight() {
        turn(TURNING_DIRECTION.RIGHT);
    }

    // turns the car left by using turn() function
    public void turnLeft() {
        turn(TURNING_DIRECTION.LEFT);
    }

    // turns the car depends on the direction(right/left) and by using getNewPointByDistanceAndAngle to get the new point
    private void turn(TURNING_DIRECTION direction) {
        // direction.getRotationMultiplier() can be 1 or -1.
        // if the direction is right, it return -1.
        // if the direction is right, it return 1.

        // adding the angle factor depends on the rotation direction.
        car.angle += CAR_TURNING_ANGLE * direction.getRotationMultiplier();

        // this function cares to have angle only between 0 - 360
        resetAngle();
    }

    // cares to have angle only between 0 - 360, because the angle is cyclic and we put it inside tan().
    private void resetAngle() {
        if (car.angle > 360) {
            car.angle = 0;
        } else if (car.angle < 0) {
            car.angle = 360;
        }
    }

}
