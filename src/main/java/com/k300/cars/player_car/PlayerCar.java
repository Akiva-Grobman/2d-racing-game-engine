package com.k300.cars.player_car;

import com.k300.cars.Car;
import com.k300.io.KEY_MOVEMENT_TYPE;
import com.k300.io.PlayerKeyListener;
import com.k300.tracks.Collisions;
import com.k300.obstacles.StartLine;
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
    private Collisions collisions;
    private int keyReleased;
    private boolean frontalCollision;
    private boolean rearCollision;
    public StartLine startLine;
    double speed;

    public PlayerCar(String carColor, Point startingPosition) {
        this(carColor, startingPosition, new PlayerKeyListener(KEY_MOVEMENT_TYPE.ARROWS));
    }

    public PlayerCar(String carColor, Point startingPosition, PlayerKeyListener listener) {
        super(carColor, startingPosition);
        SPEED_INCREMENT = 0.2;
        SPEED_DECREMENT = 0.3;
        COLLISION_SPEED_DECREMENT = 1;
        MAX_SPEED = 15;
        speed = 0;
        rounds = 0;
        keyListener = listener;
        mover = new PlayerCarMover(this);
        playerCarCorners = new PlayerCarCorners(this);
    }

    @Override
    public void tick() {
        assert collisions != null && startLine != null;
        //COLLISIONS
        if(frontalCollision || rearCollision) {
            collisionEffect();
        //DRIVING
        } else if (keyListener.getKeyIsPressed(keyListener.getForwardsMovementKey())) {
            drive(MOVEMENT_DIRECTION.FORWARDS);
        } else if (keyListener.getKeyIsPressed(keyListener.getBackwardsMovementKey())) {
            drive(MOVEMENT_DIRECTION.BACKWARDS);
        } else if (keyReleased == keyListener.getForwardsMovementKey()){
            slowDown(MOVEMENT_DIRECTION.FORWARDS);
        } else if (keyReleased == keyListener.getBackwardsMovementKey()){
            slowDown(MOVEMENT_DIRECTION.BACKWARDS);
        }
        // TURNING
        if(keyListener.getKeyIsPressed(keyListener.getRightMovementKey())) {
            turn(TURNING_DIRECTION.RIGHT);
        }
        if(keyListener.getKeyIsPressed(keyListener.getLeftMovementKey())) {
            turn(TURNING_DIRECTION.LEFT);
        }
    }

    public void setCollisions(Collisions collisions) {
        this.collisions = collisions;
    }

    public void setStartLine(StartLine startLine) {
        this.startLine = startLine;
    }

    private void drive(MOVEMENT_DIRECTION drivingDirection) {
        resetSpeed(keyListener.getKeyCodeByDirection(drivingDirection));
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
        keyReleased = keyListener.getKeyCodeByDirection(drivingDirection);
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
