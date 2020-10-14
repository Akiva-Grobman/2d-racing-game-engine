package com.k300.cars.player_car;

import com.k300.cars.Car;
import com.k300.io.PlayerKeyListener;
import com.k300.tracks.Collisions;
import com.k300.tracks.StartLine;
import com.k300.utils.Point;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class PlayerCar extends Car {

    public final PlayerCarCorners playerCarCorners;
    private final PlayerCarMover mover;
    private final double SPEED_INCREMENT;
    private final double SPEED_DECREMENT;
    private final double COLLISION_SPEED_DECREMENT;
    private final int MAX_SPEED;
    private final PlayerKeyListener keyListener;
    private final Collisions collisions;
    private int keyReleased;
    private boolean frontalCollision;
    private boolean rearCollision;
    public int rounds;
    public StartLine startLine;
    double speed;

    public PlayerCar(String carColor, Point startingPosition, Collisions collisions, StartLine startLine) {
        super(carColor, startingPosition);
        this.collisions = collisions;
        SPEED_INCREMENT = 0.2;
        SPEED_DECREMENT = 0.3;
        COLLISION_SPEED_DECREMENT = 1;
        MAX_SPEED = 15;
        speed = 0;
        rounds = 0;
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
            drive(DIRECTION.getDriveDirectionFrom(PlayerKeyListener.UP_ARROW));
        } else if (keyListener.getKeyIsPressed(PlayerKeyListener.DOWN_ARROW)) {
            drive(DIRECTION.getDriveDirectionFrom(PlayerKeyListener.DOWN_ARROW));
        } else if (keyReleased == PlayerKeyListener.UP_ARROW){
            slowDown(DIRECTION.getDriveDirectionFrom(PlayerKeyListener.UP_ARROW));
        } else if (keyReleased == PlayerKeyListener.DOWN_ARROW){
            slowDown(DIRECTION.getDriveDirectionFrom(PlayerKeyListener.DOWN_ARROW));
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

    private void drive(DIRECTION drivingDirection) {
        resetSpeed(drivingDirection.getValue());
        drivingDirection.getDrivingDirection(mover).run();
        increaseSpeed(SPEED_INCREMENT);
        if(isOffTrack()) {
            drivingDirection.getReverseDirection(mover).run();
            if(drivingDirection == DIRECTION.FORWARDS) {
                frontalCollision = true;
            } else {
                rearCollision = true;
            }
        }
        if(startLine.hasLegalCrossStartLine(this, drivingDirection)) {
            rounds++;
        }
        keyReleased = drivingDirection.getValue();
    }

    private void slowDown(DIRECTION drivingDirection) {
        decreaseSpeed(SPEED_DECREMENT);
        drivingDirection.getDrivingDirection(mover).run();
        if(isOffTrack()) {
            drivingDirection.getReverseDirection(mover).run();
        }
    }

    private void resetSpeed(int currentKey) {
        if(hasChangedDriveDirection(currentKey)) {
            resetSpeed();
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
        if(speed < MAX_SPEED) {
            speed = speed + increment;
        } else {
            speed = MAX_SPEED;
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
