package com.k300.cars.player_car;

import com.k300.io.PlayerKeyListener;

public enum MOVEMENT_DIRECTION {

    FORWARDS(PlayerKeyListener.UP_ARROW),
    BACKWARDS(PlayerKeyListener.DOWN_ARROW);

    private final int value;

    MOVEMENT_DIRECTION(int value) {
        this.value = value;
    }

    public Runnable getDrivingDirection(PlayerCarMover mover) {
        if(this == FORWARDS) {
            return mover::driveForwards;
        } else {
            return mover::driveBackwards;
        }
    }

    public Runnable getReverseDirection(PlayerCarMover mover) {
        if(this == FORWARDS) {
            return mover::driveBackwards;
        } else {
            return mover::driveForwards;
        }
    }

    public static MOVEMENT_DIRECTION getDriveDirectionFrom(int value) {
        if(value == FORWARDS.value) {
            return FORWARDS;
        } else {
            return BACKWARDS;
        }
    }

    public int getValue() {
        return value;
    }

}