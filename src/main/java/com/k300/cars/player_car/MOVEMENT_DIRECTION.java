package com.k300.cars.player_car;

public enum MOVEMENT_DIRECTION {

    FORWARDS,
    BACKWARDS;

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

}
