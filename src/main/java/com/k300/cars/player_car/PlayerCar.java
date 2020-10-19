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
    public final StartLine startLine;
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
        //COLLISIONS
        if(frontalCollision || rearCollision) {
            collisionEffect();
        //DRIVING
        } else if (keyListener.getKeyIsPressed(PlayerKeyListener.UP_ARROW)) {
            drive(MOVEMENT_DIRECTION.getDriveDirectionFrom(PlayerKeyListener.UP_ARROW));
        } else if (keyListener.getKeyIsPressed(PlayerKeyListener.DOWN_ARROW)) {
            drive(MOVEMENT_DIRECTION.getDriveDirectionFrom(PlayerKeyListener.DOWN_ARROW));
        } else if (keyReleased == PlayerKeyListener.UP_ARROW){
            slowDown(MOVEMENT_DIRECTION.getDriveDirectionFrom(PlayerKeyListener.UP_ARROW));
        } else if (keyReleased == PlayerKeyListener.DOWN_ARROW){
            slowDown(MOVEMENT_DIRECTION.getDriveDirectionFrom(PlayerKeyListener.DOWN_ARROW));
        }
        // TURNING
        if(keyListener.getKeyIsPressed(PlayerKeyListener.RIGHT_ARROW)) {
            turn(TURNING_DIRECTION.getDirectionFromValue(PlayerKeyListener.RIGHT_ARROW));
        }
        if(keyListener.getKeyIsPressed(PlayerKeyListener.LEFT_ARROW)) {
            turn(TURNING_DIRECTION.getDirectionFromValue(PlayerKeyListener.LEFT_ARROW));
        }
    }

    private void drive(MOVEMENT_DIRECTION drivingDirection) {
        resetSpeed(drivingDirection.getValue());
        drivingDirection.getDrivingDirection(mover).run();
        increaseSpeed(SPEED_INCREMENT);
        if(isOffTrack()) {
            drivingDirection.getReverseDirection(mover).run();
            if(drivingDirection == MOVEMENT_DIRECTION.FORWARDS) {
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

    private void slowDown(MOVEMENT_DIRECTION drivingDirection) {
        decreaseSpeed(SPEED_DECREMENT);
        drivingDirection.getDrivingDirection(mover).run();
        if(isOffTrack()) {
            drivingDirection.getReverseDirection(mover).run();
            if(drivingDirection == MOVEMENT_DIRECTION.FORWARDS) {
                frontalCollision = true;
            } else {
                rearCollision = true;
            }
        }

        decreaseSpeed(COLLISION_SPEED_DECREMENT);

        if(startLine.hasLegalCrossStartLine(this, drivingDirection)) {
            rounds++;
        }
    }

    private void turn(TURNING_DIRECTION direction) {
        direction.getTurningDirection(mover).run();
        if(isOffTrack()) {
            direction.getReverseTurningDirection(mover).run();
        }
    }

    private void resetSpeed(int currentKey) { //todo change the name
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
            slowDown(MOVEMENT_DIRECTION.FORWARDS);
        } else if (frontalCollision) {
            slowDown(MOVEMENT_DIRECTION.BACKWARDS);
        }
    }

    public KeyListener getKeyListener() {
        return keyListener;
    }

}
