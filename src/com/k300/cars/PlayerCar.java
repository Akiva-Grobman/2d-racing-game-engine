package com.k300.cars;

import com.k300.io.PlayerKeyListener;
import com.k300.tracks.Collisions;
import com.k300.utils.Point;

import java.awt.event.KeyListener;

public class PlayerCar extends Car {

    private final PlayerKeyListener keyListener;
    private final double carTurnAngle;
    private Collisions collisions;


    public PlayerCar(String carColor, Point startingPosition, Collisions collisions) {
        super(carColor, startingPosition);
        this.collisions = collisions;
        carTurnAngle = 4;
        keyListener = new PlayerKeyListener();
    }


    @Override
    public void tick() {
        if(keyListener.getKeyIsPressed(PlayerKeyListener.UP_ARROW)) {
            forward();
            if(isOffTrack()) {
                backwards();
            }
        }
        if(keyListener.getKeyIsPressed(PlayerKeyListener.DOWN_ARROW)) {
            backwards();
            if(isOffTrack()) {
                forward();
            }
        }
        if(keyListener.getKeyIsPressed(PlayerKeyListener.RIGHT_ARROW)) {
            right();
        }
        if(keyListener.getKeyIsPressed(PlayerKeyListener.LEFT_ARROW)) {
            left();
        }
    }

    private void forward() {
        double newX;
        double newY;
        if(angle >= 0 && angle <= 90) {
            newX = x + (speed / Math.sqrt(1 + Math.tan(Math.toRadians(angle)) * Math.tan(Math.toRadians(angle))));
            newY = y - Math.tan(Math.toRadians(angle)) * (speed / Math.sqrt(1 + Math.tan(Math.toRadians(angle)) * Math.tan(Math.toRadians(angle))));
        } else if(angle >= 90 && angle <= 180) {
            newX = x - (speed / Math.sqrt(1 + Math.tan(Math.toRadians(angle)) * Math.tan(Math.toRadians(angle))));
            newY = y + Math.tan(Math.toRadians(angle)) * (speed / Math.sqrt(1 + Math.tan(Math.toRadians(angle)) * Math.tan(Math.toRadians(angle))));
        } else if(angle >= 180 && angle <= 270) {
            newX = x - (speed / Math.sqrt(1 + Math.tan(Math.toRadians(angle)) * Math.tan(Math.toRadians(angle))));
            newY = y + Math.tan(Math.toRadians(angle)) * (speed / Math.sqrt(1 + Math.tan(Math.toRadians(angle)) * Math.tan(Math.toRadians(angle))));
        } else /*if(angle >= 270 && angle <= 360)*/ {
            newX = x + (speed / Math.sqrt(1 + Math.tan(Math.toRadians(angle)) * Math.tan(Math.toRadians(angle))));
            newY = y - Math.tan(Math.toRadians(angle)) * (speed / Math.sqrt(1 + Math.tan(Math.toRadians(angle)) * Math.tan(Math.toRadians(angle))));
        }
        x = newX;
        x = Math.round(x*10000)/10000.0; //5 numbers after the decimal point
        y = newY;
        y = Math.round(y*10000)/10000.0; //5 numbers after the decimal point
    }

    private void backwards() {
        double newX;
        double newY;
        if(angle >= 0 && angle <= 90) {
            newX = x - (speed / Math.sqrt(1 + Math.tan(Math.toRadians(angle)) * Math.tan(Math.toRadians(angle))));
            newY = y + Math.tan(Math.toRadians(angle)) * (speed / Math.sqrt(1 + Math.tan(Math.toRadians(angle)) * Math.tan(Math.toRadians(angle))));
        } else if(angle >= 90 && angle <= 180) {
            newX = x + (speed / Math.sqrt(1 + Math.tan(Math.toRadians(angle)) * Math.tan(Math.toRadians(angle))));
            newY = y - Math.tan(Math.toRadians(angle)) * (speed / Math.sqrt(1 + Math.tan(Math.toRadians(angle)) * Math.tan(Math.toRadians(angle))));
        } else if(angle >= 180 && angle <= 270) {
            newX = x + (speed / Math.sqrt(1 + Math.tan(Math.toRadians(angle)) * Math.tan(Math.toRadians(angle))));
            newY = y - Math.tan(Math.toRadians(angle)) * (speed / Math.sqrt(1 + Math.tan(Math.toRadians(angle)) * Math.tan(Math.toRadians(angle))));
        } else /*if(angle >= 270 && angle <= 360)*/ {
            newX = x - (speed / Math.sqrt(1 + Math.tan(Math.toRadians(angle)) * Math.tan(Math.toRadians(angle))));
            newY = y + Math.tan(Math.toRadians(angle)) * (speed / Math.sqrt(1 + Math.tan(Math.toRadians(angle)) * Math.tan(Math.toRadians(angle))));
        }
        x = newX;
        x = Math.round(x*10000)/10000.0; //5 numbers after the decimal point
        y = newY;
        y = Math.round(y*10000)/10000.0; //5 numbers after the decimal point
    }

    private void right() {
        angle -= carTurnAngle;
        if (angle == 90) {
            angle -= carTurnAngle;
        }

        if (angle > 360) {
            angle = 0;
        } else if (angle < 0) {
            angle = 360;
        }
    }

    private void left() {
        angle += carTurnAngle;
        if (angle == 90) {
            angle += carTurnAngle;
        }

        if (angle > 360) {
            angle = 0;
        } else if (angle < 0) {
            angle = 360;
        }

    }

    private boolean isOffTrack() {
        return !collisions.onTheTrack(x, y);
    }

    public KeyListener getKeyListener() {
        return keyListener;
    }

}
