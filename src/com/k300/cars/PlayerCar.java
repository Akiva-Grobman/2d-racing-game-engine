package com.k300.cars;

import com.k300.io.PlayerKeyListener;
import com.k300.tracks.Collisions;
import com.k300.utils.Point;
import java.awt.event.KeyListener;
import java.util.function.BiConsumer;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

import static com.k300.utils.math.Math.getXMovementFactor;
import static com.k300.utils.math.Math.getYMovementFactor;

public class PlayerCar extends Car {

    private final double CAR_TURNING_ANGLE;
    private final int RIGHT_ROTATION;
    private final int LEFT_ROTATION;
    private final double START_SPEED_INCREMENT;
    private final double START_SPEED_DECREMENT;
    private final double COLLISION_SPEED_DECREMENT;
    private final PlayerKeyListener keyListener;
    private final Collisions collisions;
    private int keyReleased;
    private boolean speedFadeForward;
    private boolean speedFadeBackwards;

    public PlayerCar(String carColor, Point startingPosition, Collisions collisions) {
        super(carColor, startingPosition);
        this.collisions = collisions;
        CAR_TURNING_ANGLE = 4;
        RIGHT_ROTATION = -1;
        LEFT_ROTATION = 1;
        speedFadeForward = false;
        speedFadeBackwards = false;
        START_SPEED_INCREMENT = 0.2;
        START_SPEED_DECREMENT = 0.3;
        COLLISION_SPEED_DECREMENT = 1;
        keyListener = new PlayerKeyListener();
    }

    @Override
    public void tick() {
        if (speedFadeForward) {
            fade().accept(
                    this::moveForward,
                    this::moveBackwards
            );
        } else if (speedFadeBackwards) {
            fade().accept(
                    this::moveBackwards,
                    this::moveForward
            );
        } else if (keyListener.getKeyIsPressed(PlayerKeyListener.UP_ARROW)) {
            if(keyReleased != PlayerKeyListener.UP_ARROW) {
                resetSpeed();
            }
            handleKeyPressed(true).accept(this::moveForward, this::moveBackwards);
            keyReleased = PlayerKeyListener.UP_ARROW;
        } else if (keyListener.getKeyIsPressed(PlayerKeyListener.DOWN_ARROW)) {
            if(keyReleased != PlayerKeyListener.DOWN_ARROW) {
                resetSpeed();
            }
            handleKeyPressed(false).accept(this::moveBackwards, this::moveForward);
            keyReleased = PlayerKeyListener.DOWN_ARROW;
        } else if (keyReleased == PlayerKeyListener.UP_ARROW){
            handleKeyReleased().accept(this::moveForward, this::moveBackwards);
        } else if (keyReleased == PlayerKeyListener.DOWN_ARROW){
            handleKeyReleased().accept(this::moveBackwards, this::moveForward);
        }

        if(keyListener.getKeyIsPressed(PlayerKeyListener.RIGHT_ARROW)) {
            moveRight();
        }
        if(keyListener.getKeyIsPressed(PlayerKeyListener.LEFT_ARROW)) {
            moveLeft();
        }
    }

    private BiConsumer<Runnable, Runnable> fade() {
        return (runnable, runnable2) -> {
            runnable.run();
            if(isOffTrack()) {
                runnable2.run();
                speedFadeForward = !speedFadeForward;
                speedFadeBackwards = !speedFadeBackwards;
            }
            decreaseSpeed(COLLISION_SPEED_DECREMENT);
        };
    }

    private BiConsumer<Runnable, Runnable> handleKeyPressed(boolean isPressingUp) {
        return ((runnable, runnable2) -> {
            runnable.run();
            increaseSpeed(START_SPEED_INCREMENT);
            if(isOffTrack()) {
                runnable2.run();
                if(isPressingUp) {
                    speedFadeBackwards = true;
                } else {
                    speedFadeForward = true;
                }
            }
        });
    }

    private BiConsumer<Runnable, Runnable> handleKeyReleased() {
        return ((runnable, runnable2) ->  {
            decreaseSpeed(START_SPEED_DECREMENT);
            runnable.run();
            if(isOffTrack()) {
                runnable2.run();
            }
        });
    }

    private void moveForward() {
        move(() -> isInBoundsOf(90, 270));
    }

    private void moveBackwards() {
        move(() -> isInBoundsOf(0, 90) || isInBoundsOf(270, 360));
    }

    // TODO: 07/10/2020 rename param
    private void move(BooleanSupplier boundsCondition) {
        Point newPosition;
        if(boundsCondition.getAsBoolean()) {
            newPosition = new Point(x - getXMovementFactor(speed, angle), y + getYMovementFactor(speed, angle));
        } else {
            newPosition = new Point(x + getXMovementFactor(speed, angle), y - getYMovementFactor(speed, angle));
        }
        setNewXY(newPosition);
    }

    private boolean isInBoundsOf(int lowerBound, int upperBound) {
        return angle >= lowerBound && angle <= upperBound;
    }

    private void setNewXY(Point newPosition) {
        // rounding is for display prepossess
        // 5 numbers after the decimal point
        x = Math.round(newPosition.x * 10000) / 10000.0;
        y = Math.round(newPosition.y * 10000) / 10000.0;
    }

    private void moveRight() {
        turn(RIGHT_ROTATION);
    }

    private void moveLeft() {
        turn(LEFT_ROTATION);
    }

    private void turn(int multiplier) {
        angle += CAR_TURNING_ANGLE * multiplier;
        if(angle == 90) {
            angle += CAR_TURNING_ANGLE * multiplier;
        }
        resetAngle();
    }

    private void resetAngle() {
        if (angle > 360) {
            angle = 0;
        } else if (angle < 0) {
            angle = 360;
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
            speedFadeForward = false;
            speedFadeBackwards = false;
        }
    }

    public KeyListener getKeyListener() {
        return keyListener;
    }

}
