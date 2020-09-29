package com.k300.cars;

import com.k300.io.PlayerKeyListener;
import com.k300.utils.Point;

import java.awt.*;
import java.awt.event.KeyListener;

public class PlayerCar extends Car {

    private final double CAR_TURNING_ANGLE;
    private final PlayerKeyListener keyListener;

    public PlayerCar(String carColor, Point startingPosition) {
        super(carColor, startingPosition);
        CAR_TURNING_ANGLE = 4;
        keyListener = new PlayerKeyListener();
    }

    @Override
    public void tick() {
        if(keyListener.getKeyIsPressed(PlayerKeyListener.UP_ARROW)) {
            moveForward();
        }
        if(keyListener.getKeyIsPressed(PlayerKeyListener.DOWN_ARROW)) {
            moveBackwards();
        }
        if(keyListener.getKeyIsPressed(PlayerKeyListener.RIGHT_ARROW)) {
            moveRight();
        }
        if(keyListener.getKeyIsPressed(PlayerKeyListener.LEFT_ARROW)) {
            moveLeft();
        }
    }

    private void moveForward() {
        double newX;
        double newY;
        if(isInBoundsOf(0, 90)) {
            newX = x + distance();
            newY = y - yMovement();
        } else if(isInBoundsOf(90, 180)) {
            newX = x - distance();
            newY = y + yMovement();
        } else if(isInBoundsOf(180, 270)) {
            newX = x - distance();
            newY = y + yMovement();
        } else /*if(angle >= 270 && angle <= 360)*/ {
            newX = x + distance();
            newY = y - yMovement();
        }
        setNewXY(newX, newY);
    }

    private void moveBackwards() {
        double newX;
        double newY;
        if(isInBoundsOf(0, 90)) {
            newX = x - distance();
            newY = y + yMovement();
        } else if(isInBoundsOf(90, 180)) {
            newX = x + distance();
            newY = y - yMovement();
        } else if(isInBoundsOf(180, 270)) {
            newX = x + distance();
            newY = y - yMovement();
        } else /*if(angle >= 270 && angle <= 360)*/ {
            newX = x - distance();
            newY = y + yMovement();
        }
        setNewXY(newX, newY);
    }

    private boolean isInBoundsOf(int lowerBound, int upperBound) {
        return angle >= lowerBound && angle <= upperBound;
    }

    // this is most of the distance formula (with the x extracted instead of the standard distance extracted)
    // meaning x +- distance() the -/+ will be determined by the angle and direction (in the forwards and backwards methods)
    // and the y will use this as well for it's distance calculation.
    private double distance() {
        return (speed / sqrtOfAnglePlusOne() * tanOfAngle());
    }

    private double yMovement() {
        return tanOfAngle() * distance();
    }

    private double sqrtOfAnglePlusOne() {
        return Math.sqrt(1 + tanOfAngle());
    }

    private double tanOfAngle() {
        return Math.tan(Math.toRadians(angle));
    }

    private void setNewXY(double newX, double newY) {
        // rounding is for display prepossess
        // 5 numbers after the decimal point
        x = Math.round(newX * 10000) / 10000.0;
        y = Math.round(newY * 10000) / 10000.0;
    }

    private void moveRight() {
        turn(-1);
    }

    private void moveLeft() {
        turn(1);
    }

    private void turn(int multiplier) {
        angle += CAR_TURNING_ANGLE * multiplier;
        if(angle == 90) {
            angle += CAR_TURNING_ANGLE * multiplier;
        }
        resetAngle();
    }

    private void resetAngle() {
        if (angle > 360) {
            angle = 0;
        } else if (angle < 0) {
            angle = 360;
        }
    }

    public KeyListener getKeyListener() {
        return keyListener;
    }

}
