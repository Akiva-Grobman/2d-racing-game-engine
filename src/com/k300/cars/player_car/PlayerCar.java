package com.k300.cars.player_car;

import com.k300.cars.Car;
import com.k300.io.PlayerKeyListener;
import com.k300.tracks.Collisions;
import com.k300.utils.Point;

import java.awt.event.KeyListener;

public class PlayerCar extends Car {

    private final PlayerCarMover mover;
    private final double START_SPEED_INCREMENT;
    private final double START_SPEED_DECREMENT;
    private final double COLLISION_SPEED_DECREMENT;

    private final PlayerKeyListener keyListener;
    private final Collisions collisions;
    private int keyReleased;
    private boolean speedFadeForwards;
    private boolean speedFadeBackwards;


    public PlayerCar(String carColor, Point startingPosition, Collisions collisions) {
        super(carColor, startingPosition);
        this.collisions = collisions;
        START_SPEED_INCREMENT = 0.2;
        START_SPEED_DECREMENT = 0.3;
        COLLISION_SPEED_DECREMENT = 1;
        keyListener = new PlayerKeyListener();
        mover = new PlayerCarMover(this);
    }


    @Override
    public void tick() {
        if(speedFadeForwards || speedFadeBackwards) {
            speedEffect();
        } else if (keyListener.getKeyIsPressed(PlayerKeyListener.UP_ARROW)) {
            if(keyReleased != PlayerKeyListener.UP_ARROW) {
                resetSpeed();
            }
            mover.driveForwards();
            increaseSpeed(START_SPEED_INCREMENT);
            if(isOffTrack()) {
                mover.driveBackwards();
                speedFadeBackwards = true;
            }
            keyReleased = PlayerKeyListener.UP_ARROW;
        } else if (keyListener.getKeyIsPressed(PlayerKeyListener.DOWN_ARROW)) {
            if(keyReleased != PlayerKeyListener.DOWN_ARROW) {
                resetSpeed();
            }
            mover.driveBackwards();
            increaseSpeed(START_SPEED_INCREMENT);
            if(isOffTrack()) {
                mover.driveForwards();
                speedFadeForwards = true;
            }
            keyReleased = PlayerKeyListener.DOWN_ARROW;
        } else if (keyReleased == PlayerKeyListener.UP_ARROW){
            decreaseSpeed(START_SPEED_DECREMENT);
            mover.driveForwards();
            if(isOffTrack()) {
                mover.driveBackwards();
            }
        } else if (keyReleased == PlayerKeyListener.DOWN_ARROW){
            decreaseSpeed(START_SPEED_DECREMENT);
            mover.driveBackwards();
            if(isOffTrack()) {
                mover.driveForwards();
            }
        }

        if(keyListener.getKeyIsPressed(PlayerKeyListener.RIGHT_ARROW)) {
            mover.turnRight();
        }
        if(keyListener.getKeyIsPressed(PlayerKeyListener.LEFT_ARROW)) {
            mover.turnLeft();
        }
    }


    private boolean isOffTrack() {
        return !collisions.onTheTrack(x, y);
    }

    private void resetSpeed() {
        speed = 0;
    }

    private void increaseSpeed(double increment) {
        if(speed < maxSpeed) {
            speed = speed + increment;
        } else {
            speed = maxSpeed;
        }
    }

    private void decreaseSpeed(double decrement) {
        if(speed > 0) {
            speed = speed - decrement;
        } else {
            speed = 0;
            speedFadeForwards = false;
            speedFadeBackwards = false;
        }
    }

    private void speedEffect() {
        if (speedFadeForwards) {
            mover.driveForwards();
            if(isOffTrack()) {
                mover.driveBackwards();
                speedFadeForwards = false;
                speedFadeBackwards = true;
            }
            decreaseSpeed(COLLISION_SPEED_DECREMENT);
        } else if (speedFadeBackwards) {
            mover.driveBackwards();
            if(isOffTrack()) {
                mover.driveForwards();
                speedFadeForwards = true;
                speedFadeBackwards = false;
            }
            decreaseSpeed(COLLISION_SPEED_DECREMENT);
        }
    }

    public KeyListener getKeyListener() {
        return keyListener;
    }

}
