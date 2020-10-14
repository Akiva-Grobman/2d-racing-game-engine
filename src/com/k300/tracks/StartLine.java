package com.k300.tracks;

import com.k300.cars.player_car.PlayerCar;
import com.k300.cars.player_car.DIRECTION;
import com.k300.utils.Point;

public class StartLine {

    private final Point upperStartLinePoint;
    private final Point lowerStartLinePoint;
    private boolean isLegalRound;
    private boolean readyToNewRound;

    public StartLine(Point upperStartLinePoint, Point lowerStartLinePoint) {
        this.upperStartLinePoint = upperStartLinePoint;
        this.lowerStartLinePoint = lowerStartLinePoint;
        this.isLegalRound = false;
        this.readyToNewRound = false;
    }

    public boolean hasLegalCrossStartLine(PlayerCar car, DIRECTION driveDirection) {
        boolean hasLegalCrossStartLine = false;
        if (carIsOnStartLineLegally(car, driveDirection)) {
            if (isLegalRound && readyToNewRound) {
                readyToNewRound = false;
                hasLegalCrossStartLine = true;
            } else {
                readyToNewRound = false;
                isLegalRound = true;
            }
        } else if (carIsOnStartLineIllegally(car, driveDirection)) {
            isLegalRound = false;
        } else if (wholeCarIsPassedTheStartLine(car, driveDirection)) {
            readyToNewRound = true;
        }

        return hasLegalCrossStartLine;
    }

    private boolean frontOfTheCarIsAfterStartLine(PlayerCar car) {
        Point frontLeftCorner = car.playerCarCorners.getFrontLeftCorner();
        Point frontRightCorner = car.playerCarCorners.getFrontRightCorner();
        return cornerIsAfterStartLine(frontLeftCorner) || cornerIsAfterStartLine(frontRightCorner);
    }

    private boolean frontOfTheCarIsBeforeStartLine(PlayerCar car) {
        Point frontLeftCorner = car.playerCarCorners.getFrontLeftCorner();
        Point frontRightCorner = car.playerCarCorners.getFrontRightCorner();
        return cornerIsBeforeStartLine(frontLeftCorner) || cornerIsBeforeStartLine(frontRightCorner);
    }

    private boolean rearOfTheCarIsAfterStartLine(PlayerCar car) {
        Point rearLeftCorner = car.playerCarCorners.getRearLeftCorner();
        Point rearRightCorner = car.playerCarCorners.getRearRightCorner();
        return cornerIsAfterStartLine(rearLeftCorner) || cornerIsAfterStartLine(rearRightCorner);
    }

    private boolean rearOfTheCarIsBeforeStartLine(PlayerCar car) {
        Point rearLeftCorner = car.playerCarCorners.getRearLeftCorner();
        Point rearRightCorner = car.playerCarCorners.getRearRightCorner();
        return cornerIsBeforeStartLine(rearLeftCorner) || cornerIsBeforeStartLine(rearRightCorner);
    }

    private boolean cornerIsAfterStartLine(Point corner) {
        return corner.x > upperStartLinePoint.x && yCornerIsInBoundsStartLine(corner.y);
    }

    private boolean cornerIsBeforeStartLine(Point corner) {
        return corner.x < upperStartLinePoint.x && yCornerIsInBoundsStartLine(corner.y);
    }

    private boolean yCornerIsInBoundsStartLine(double y) {
        return y < upperStartLinePoint.y && y > lowerStartLinePoint.y;
    }

    private boolean carIsOnStartLineLegally(PlayerCar car, DIRECTION direction) {
        if(direction == DIRECTION.FORWARDS) {
            return frontOfTheCarIsAfterStartLine(car) && rearOfTheCarIsBeforeStartLine(car);
        } else {
            return frontOfTheCarIsBeforeStartLine(car) && rearOfTheCarIsAfterStartLine(car);
        }
    }

    private boolean carIsOnStartLineIllegally(PlayerCar car, DIRECTION direction) {
        if(direction == DIRECTION.BACKWARDS) {
            return frontOfTheCarIsAfterStartLine(car) && rearOfTheCarIsBeforeStartLine(car);
        } else {
            return frontOfTheCarIsBeforeStartLine(car) && rearOfTheCarIsAfterStartLine(car);
        }
    }

    private boolean wholeCarIsPassedTheStartLine(PlayerCar car, DIRECTION direction) {
        if(direction == DIRECTION.FORWARDS) {
            return frontOfTheCarIsAfterStartLine(car) && rearOfTheCarIsAfterStartLine(car);
        } else {
            return frontOfTheCarIsBeforeStartLine(car) && rearOfTheCarIsBeforeStartLine(car);
        }
    }

}
