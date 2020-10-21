package com.k300.cars.player_car;

import com.k300.io.PlayerKeyListener;

public enum TURNING_DIRECTION {

    RIGHT(PlayerKeyListener.RIGHT_ARROW, -1),
    LEFT(PlayerKeyListener.LEFT_ARROW, 1);

    private final int keyValue;
    private final int multiplier;

    TURNING_DIRECTION(int keyValue, int multiplier) {
        this.keyValue = keyValue;
        this.multiplier = multiplier;
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

    public Runnable getReverseTurningDirection(PlayerCarMover mover) {
        if(this == RIGHT) {
            return mover::turnLeft;
        } else {
            return mover::turnRight;
        }
    }

    public int getRotationMultiplier() {
        return multiplier;
    }

}
