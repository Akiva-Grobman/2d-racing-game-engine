package com.k300.cars;

import com.k300.utils.Point;

import java.util.function.BiConsumer;
import java.util.function.BooleanSupplier;

import static com.k300.utils.math.Math.getXMovementFactor;
import static com.k300.utils.math.Math.getYMovementFactor;

public class PlayerCarMover {


    private final double START_SPEED_INCREMENT;
    private final double START_SPEED_DECREMENT;
    private final double COLLISION_SPEED_DECREMENT;
    private final double CAR_TURNING_ANGLE;
    private final int RIGHT_ROTATION;
    private final int LEFT_ROTATION;
    private final PlayerCar car;
    private boolean speedFadeForward;
    private boolean speedFadeBackwards;

    public PlayerCarMover(PlayerCar car) {
        this.car = car;
        START_SPEED_INCREMENT = 0.2;
        START_SPEED_DECREMENT = 0.3;
        COLLISION_SPEED_DECREMENT = 1;
        CAR_TURNING_ANGLE = 4;
        RIGHT_ROTATION = -1;
        LEFT_ROTATION = 1;
        speedFadeForward = false;
        speedFadeBackwards = false;
    }

    boolean isFadeClick() {
        return speedFadeForward || speedFadeBackwards;
    }

    void fade() {
        if (speedFadeForward) {
            getFadeType().accept(
                    this::moveForwards,
                    this::moveBackwards
            );
        } else {
            getFadeType().accept(
                    this::moveBackwards,
                    this::moveForwards
            );
        }
    }

    void keyPressed(boolean isPressingUp) {
        if(isPressingUp) {
            getPressResponse(true).accept(this::moveForwards, this::moveBackwards);
        } else {
            getPressResponse(false).accept(this::moveBackwards, this::moveForwards);
        }
    }

    void keyReleased(boolean isReleasingUp) {
        if(isReleasingUp) {
            handleKeyReleased().accept(this::moveForwards, this::moveBackwards);
        } else {
            handleKeyReleased().accept(this::moveBackwards, this::moveForwards);
        }
    }

    void moveRight() {
        turn(RIGHT_ROTATION);
    }

    void moveLeft() {
        turn(LEFT_ROTATION);
    }

    void resetSpeed() {
        car.speed = 0;
    }

    private BiConsumer<Runnable, Runnable> getFadeType() {
        return (runnable, runnable2) -> {
            runnable.run();
            if(car.isOffTrack()) {
                runnable2.run();
                speedFadeForward = !speedFadeForward;
                speedFadeBackwards = !speedFadeBackwards;
            }
            decreaseSpeed(COLLISION_SPEED_DECREMENT);
        };
    }

    private BiConsumer<Runnable, Runnable> getPressResponse(boolean isPressingUp) {
        return ((runnable, runnable2) -> {
            runnable.run();
            increaseSpeed(START_SPEED_INCREMENT);
            if(car.isOffTrack()) {
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
            if(car.isOffTrack()) {
                runnable2.run();
            }
        });
    }

    private void moveForwards() {
        move(() -> isInBoundsOf(90, 270));
    }

    private void moveBackwards() {
        move(() -> isInBoundsOf(0, 90) || isInBoundsOf(270, 360));
    }

    private void turn(int multiplier) {
        car.angle += CAR_TURNING_ANGLE * multiplier;
        if(car.angle == 90) {
            car.angle += CAR_TURNING_ANGLE * multiplier;
        }
        resetAngle();
    }

    private void resetAngle() {
        if (car.angle > 360) {
            car.angle = 0;
        } else if (car.angle < 0) {
            car.angle = 360;
        }
    }

    private boolean isInBoundsOf(int lowerBound, int upperBound) {
        return car.angle >= lowerBound && car.angle <= upperBound;
    }

    // TODO: 07/10/2020 rename param
    private void move(BooleanSupplier boundsCondition) {
        Point newPosition;
        if(boundsCondition.getAsBoolean()) {
            newPosition = new Point(car.x - getXMovementFactor(car.speed, car.angle), car.y + getYMovementFactor(car.speed, car.angle));
        } else {
            newPosition = new Point(car.x + getXMovementFactor(car.speed, car.angle), car.y - getYMovementFactor(car.speed, car.angle));
        }
        setNewXY(newPosition);
    }

    private void setNewXY(Point newPosition) {
        // rounding is for display prepossess
        // 5 numbers after the decimal point
        car.x = Math.round(newPosition.x * 10000) / 10000.0;
        car.y = Math.round(newPosition.y * 10000) / 10000.0;
    }

    private void increaseSpeed(double increment) {
        if(car.speed < car.maxSpeed) {
            car.speed += increment;
        } else {
            car.speed = car.maxSpeed;
        }
    }

    private void decreaseSpeed(double decrement) {
        if(car.speed > 0) {
            car.speed -= decrement;
        } else {
            car.speed = 0;
            speedFadeForward = false;
            speedFadeBackwards = false;
        }
    }

}
