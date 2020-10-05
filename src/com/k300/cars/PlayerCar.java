package com.k300.cars;

import com.k300.io.PlayerKeyListener;
import com.k300.tracks.Collisions;
import com.k300.utils.Point;

import java.awt.event.KeyListener;

public class PlayerCar extends Car {

    private final double CAR_TURNING_ANGLE;
    private final int RIGHT_ROTATION;
    private final int LEFT_ROTATION;
    private final PlayerKeyListener keyListener;
    private final Collisions collisions;
    private int keyReleased;


    public PlayerCar(String carColor, Point startingPosition, Collisions collisions) {
        super(carColor, startingPosition);
        this.collisions = collisions;
        CAR_TURNING_ANGLE = 4;
        RIGHT_ROTATION = -1;
        LEFT_ROTATION = 1;
        keyListener = new PlayerKeyListener();
    }


    @Override
    public void tick() {
        if(keyListener.getKeyIsPressed(PlayerKeyListener.UP_ARROW)) {
            if(keyReleased != PlayerKeyListener.UP_ARROW) {
                resetSpeed();
            }
            moveForward();
            increaseSpeed();
            if(isOffTrack()) {
                moveBackwards();
                resetSpeed();
            }
            keyReleased = PlayerKeyListener.UP_ARROW;
        } else if(keyListener.getKeyIsPressed(PlayerKeyListener.DOWN_ARROW)) {
            if(keyReleased != PlayerKeyListener.DOWN_ARROW) {
                resetSpeed();
            }
            moveBackwards();
            increaseSpeed();
            if(isOffTrack()) {
                moveForward();
                resetSpeed();
            }
            keyReleased = PlayerKeyListener.DOWN_ARROW;
        } else if (keyReleased == PlayerKeyListener.UP_ARROW){
            decreaseSpeed();
            moveForward();
            if(isOffTrack()) {
                moveBackwards();
            }
        } else if (keyReleased == PlayerKeyListener.DOWN_ARROW){
            decreaseSpeed();
            moveBackwards();
            if(isOffTrack()) {
                moveForward();
            }
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
            newX = x + xDistance();
            newY = y - yDistance();
        } else if(isInBoundsOf(90, 180)) {
            newX = x - xDistance();
            newY = y + yDistance();
        } else if(isInBoundsOf(180, 270)) {
            newX = x - xDistance();
            newY = y + yDistance();
        } else /*if(angle >= 270 && angle <= 360)*/ {
            newX = x + xDistance();
            newY = y - yDistance();
        }
        setNewXY(newX, newY);
    }

    private void moveBackwards() {
        double newX;
        double newY;
        if(isInBoundsOf(0, 90)) {
            newX = x - xDistance();
            newY = y + yDistance();
        } else if(isInBoundsOf(90, 180)) {
            newX = x + xDistance();
            newY = y - yDistance();
        } else if(isInBoundsOf(180, 270)) {
            newX = x + xDistance();
            newY = y - yDistance();
        } else /*if(angle >= 270 && angle <= 360)*/ {
            newX = x - xDistance();
            newY = y + yDistance();
        }
        setNewXY(newX, newY);
    }

    private boolean isInBoundsOf(int lowerBound, int upperBound) {
        return angle >= lowerBound && angle <= upperBound;
    }

    // This is most of the distance formula (with the x extracted instead of the standard distance extracted).
    // Meaning x +- distance() the -/+ will be determined by the angle and direction (in the forwards and backwards methods)
    // and the y will use this as well for it's distance calculation.
    private double xDistance() {
        return (speed / sqrtOfSquareAnglePlusOne());
    }

    private double yDistance() {
        return tanOfAngle() * xDistance();
    }

    private double sqrtOfSquareAnglePlusOne() {
        return Math.sqrt(1 + tanOfAngle()*tanOfAngle());
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
        turn(RIGHT_ROTATION);
    }

    private void moveLeft() {
        turn(LEFT_ROTATION);
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

    private boolean isOffTrack() {
        return !collisions.onTheTrack(x, y);
    }

    private void increaseSpeed() {
        if(speed < maxSpeed) {
            speed = speed + 0.2;
        } else {
            speed = maxSpeed;
        }
    }

    private void resetSpeed() {
        speed = 0;
    }

    private void decreaseSpeed() {
        if(speed > 0) {
            speed = speed - 0.3;
        } else {
            speed = 0;
        }
    }

    public KeyListener getKeyListener() {
        return keyListener;
    }

}
