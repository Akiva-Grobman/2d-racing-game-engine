package com.k300.cars.player_car;

import com.k300.io.PlayerKeyListener;

public enum TURNING_DIRECTION {

    RIGHT(PlayerKeyListener.RIGHT_ARROW),
    LEFT(PlayerKeyListener.LEFT_ARROW);

    private final int keyValue;

    TURNING_DIRECTION(int keyValue) {
        this.keyValue = keyValue;
    }

    public static TURNING_DIRECTION getDirectionFromValue(int keyValue) {
        if(keyValue == RIGHT.keyValue) {
            return RIGHT;
        } else if(keyValue == LEFT.keyValue) {
            return LEFT;
        }
        throw new Error(keyValue + " is not a valid turing value");
    }

    public Runnable getTurningDirection(PlayerCarMover mover) {
        if(this == RIGHT) {
            return mover::turnRight;
        } else {
            return mover::turnLeft;
        }
    }

    public Runnable getReversTurningDirection(PlayerCarMover mover) {
        if(this == RIGHT) {
            return mover::turnLeft;
        } else {
            return mover::turnRight;
        }
    }

}
