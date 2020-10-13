package com.k300.cars.player_car;

import com.k300.cars.Car;
import com.k300.io.PlayerKeyListener;
import com.k300.tracks.Collisions;
import com.k300.utils.Point;

import java.awt.event.KeyListener;
import java.util.ArrayList;

import static com.k300.utils.math.AnalyticalMath.angleIsInBoundsOf;

public class PlayerCar extends Car {

    private final PlayerCarMover mover;
    private final PlayerCarCorners playerCarCorners;
    private final double SPEED_INCREMENT;
    private final double SPEED_DECREMENT;
    private final double COLLISION_SPEED_DECREMENT;
    private final PlayerKeyListener keyListener;
    private final Collisions collisions;
    private int roundCount;
    private int keyReleased;
    private boolean frontalCollision;
    private boolean rearCollision;
    private boolean isLegalRound;
    private boolean wasOnStartingLine;

    public PlayerCar(String carColor, Point startingPosition, Collisions collisions) {
        super(carColor, startingPosition);
        this.collisions = collisions;
        SPEED_INCREMENT = 0.2;
        SPEED_DECREMENT = 0.3;
        COLLISION_SPEED_DECREMENT = 1;
        roundCount = 1;
        isLegalRound = true;
        wasOnStartingLine = false;
        keyListener = new PlayerKeyListener();
        mover = new PlayerCarMover(this);
        playerCarCorners = new PlayerCarCorners(this);
    }

    @Override
    public void tick() {
        resetIsLegalRound();
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
            if(isLegalRound && (angleIsInBoundsOf(angle, 270,360) || angleIsInBoundsOf(angle, 0, 90))) {
                if (!wasOnStartingLine && isOnStartingLine(playerCarCorners.getFrontRightCorner(), playerCarCorners.getFrontLeftCorner())) {
                    isLegalRound = false;
                    wasOnStartingLine = true;
                    roundCount++;
                } else if(wasOnStartingLine) {
                    if(!isOnStartingLine(playerCarCorners.getRearRightCorner(), playerCarCorners.getRearLeftCorner())) {
                        wasOnStartingLine = isOnStartingLine(position);
                    }
                }
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

    private void resetIsLegalRound() {
        if(isOnStartingLine(playerCarCorners.getFrontRightCorner(), playerCarCorners.getFrontLeftCorner())) {
            // if wrong side of moving backwards this will equal false
            isLegalRound = !angleIsInBoundsOf(angle, 90, 270) &&
                    !keyListener.getKeyIsPressed(PlayerKeyListener.DOWN_ARROW);
        }
    }

    public int getRoundCount() {
        return roundCount;
    }

    private boolean isOnStartingLine(Point position) {
        return collisions.isOnStartingLine(position);
    }

    private boolean isOnStartingLine(Point rightCorner, Point leftCorner) {
        return collisions.isOnStartingLine(rightCorner, leftCorner);
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
