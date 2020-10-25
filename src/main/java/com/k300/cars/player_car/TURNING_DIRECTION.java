package com.k300.cars.player_car;

public enum TURNING_DIRECTION {

    RIGHT(-1),
    LEFT(1);

    private final int multiplier;

    TURNING_DIRECTION(int multiplier) {
        this.multiplier = multiplier;
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
