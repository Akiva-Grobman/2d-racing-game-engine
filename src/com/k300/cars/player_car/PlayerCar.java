package com.k300.cars.player_car;

import com.k300.cars.Car;
import com.k300.io.PlayerKeyListener;
import com.k300.tracks.Collisions;
import com.k300.tracks.Obstacle;
import com.k300.tracks.StartLine;
import com.k300.utils.Point;

import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class PlayerCar extends Car {

    public final PlayerCarCorners playerCarCorners;
    private final PlayerCarMover mover;
    private final double SPEED_INCREMENT;
    private final double SPEED_DECREMENT;
    private final double COLLISION_SPEED_DECREMENT;
    private final int FORWARDS;
    private final int BACKWARDS;

    private final PlayerKeyListener keyListener;
    private final Collisions collisions;
    private int keyReleased;
    private boolean frontalCollision;
    private boolean rearCollision;
    public StartLine startLine;


    public PlayerCar(String carColor, Point startingPosition, Collisions collisions, StartLine startLine) {
        super(carColor, startingPosition);
        this.collisions = collisions;
        SPEED_INCREMENT = 0.2;
        SPEED_DECREMENT = 0.3;
        COLLISION_SPEED_DECREMENT = 1;
        FORWARDS = 1;
        BACKWARDS = -1;
        keyListener = new PlayerKeyListener();
        mover = new PlayerCarMover(this);
        playerCarCorners = new PlayerCarCorners(this);
        this.startLine = startLine;
    }

    @Override
    public void tick() {
        if(frontalCollision || rearCollision) {
            collisionEffect();
        } else if (keyListener.getKeyIsPressed(PlayerKeyListener.UP_ARROW)) {
            if(hasChangedDriveDirection(PlayerKeyListener.UP_ARROW)) {
                resetSpeed();
            }
            mover.driveForwards();
            increaseSpeed(SPEED_INCREMENT);
            if(isOffTrack()) {
                mover.driveBackwards();
                frontalCollision = true;
            }

            if (startLine.hasLegalCrossStartLine(this, FORWARDS)) {
                this.rounds++;
            }

            keyReleased = PlayerKeyListener.UP_ARROW;
        } else if (keyListener.getKeyIsPressed(PlayerKeyListener.DOWN_ARROW)) {
            if(hasChangedDriveDirection(PlayerKeyListener.DOWN_ARROW)) {
                resetSpeed();
            }
            mover.driveBackwards();
            increaseSpeed(SPEED_INCREMENT);
            if(isOffTrack()) {
                mover.driveForwards();
                rearCollision = true;
            }

            if (startLine.hasLegalCrossStartLine(this, BACKWARDS)) {
                this.rounds++;
            }

            keyReleased = PlayerKeyListener.DOWN_ARROW;
        } else if (keyReleased == PlayerKeyListener.UP_ARROW){
            decreaseSpeed(SPEED_DECREMENT);
            mover.driveForwards();
            if(isOffTrack()) {
                mover.driveBackwards();
            }
        } else if (keyReleased == PlayerKeyListener.DOWN_ARROW){
            decreaseSpeed(SPEED_DECREMENT);
            mover.driveBackwards();
            if(isOffTrack()) {
                mover.driveForwards();
            }
        }

        if(keyListener.getKeyIsPressed(PlayerKeyListener.RIGHT_ARROW)) {
            mover.turnRight();
            if(isOffTrack()) {
                mover.turnLeft();
            }
        }
        if(keyListener.getKeyIsPressed(PlayerKeyListener.LEFT_ARROW)) {
            mover.turnLeft();
            if(isOffTrack()) {
                mover.turnRight();
            }
        }
    }

    private boolean isOffTrack() {
        ArrayList<Point> fourCarCorners = playerCarCorners.getFourCarCorners();
        for (Point corner : fourCarCorners) {
            if(!collisions.onTheTrack(corner)) {
                return true;
            }
        }
        return false;
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
            frontalCollision = false;
            rearCollision = false;
        }
    }

    private boolean hasChangedDriveDirection(int currentKey) {
        return keyReleased != currentKey;
    }

    private void collisionEffect() {
        if (rearCollision) {
            mover.driveForwards();
            if(isOffTrack()) {
                mover.driveBackwards();
                rearCollision = false;
                frontalCollision = true;
            }
        } else if (frontalCollision) {
            mover.driveBackwards();
            if(isOffTrack()) {
                mover.driveForwards();
                rearCollision = true;
                frontalCollision = false;
            }
        }
        decreaseSpeed(COLLISION_SPEED_DECREMENT);
    }

    public KeyListener getKeyListener() {
        return keyListener;
    }

}
