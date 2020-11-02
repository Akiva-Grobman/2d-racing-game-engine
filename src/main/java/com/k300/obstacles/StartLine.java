package com.k300.obstacles;

import com.k300.cars.player_car.PlayerCar;
import com.k300.cars.player_car.MOVEMENT_DIRECTION;
import com.k300.utils.Point;

/*
*       Purpose:
*           this represents the starting line, it's used to calculate the rounds
*       Contains:
*           the positions of the top and bottom of the line,
*           a flag representing if the round is legal,
*           and a flag representing if the car has passed the line.
*       Quick explanation:
*           the way we determine if when the car is over the line it's actually completing a full round is as follows.
*           once the front of the car passes the line we set the legal round flag to true,
*           and once the back of the car passes the line (fully i.e. the whole car is on the right side of the line) we set the ready for new round to true.
*           if the car passes the line and both flags are true we know we can increment the rounds otherwise we can't.
*/

public class StartLine {

    // the upper position of the line
    private final Point upperStartLinePoint;
    // the lower position of the line
    private final Point lowerStartLinePoint;
    // legal round flag
    private boolean isLegalRound;
    // ready for legal round flag
    private boolean readyForNewRound;

    // only initialization option (setting the location of the line)
    public StartLine(Point upperStartLinePoint, Point lowerStartLinePoint) {
        // set positions to constructor arguments
        this.upperStartLinePoint = upperStartLinePoint;
        this.lowerStartLinePoint = lowerStartLinePoint;
        // set both flags to false
        this.isLegalRound = false;
        this.readyForNewRound = false;
    }

    // will return if the car has crossed the line legally(i.e. finished a full round) (to it can increment the round count)
    public boolean hasLegalCrossStartLine(PlayerCar car, MOVEMENT_DIRECTION driveDirection) {
        // we set a flag representing if the round was competed in a legal manner
        // by default we assume the car hasn't crossed the line legally
        boolean hasLegalCrossStartLine = false;
        // if the front of the car has passed the line
        if (carIsOnStartLineLegally(car, driveDirection)) {
            // if both flags are true
            if (isLegalRound && readyForNewRound) {
                // reset ready for legal round flag to false
                readyForNewRound = false;
                // has legally crossed the line
                hasLegalCrossStartLine = true;
                // is crossing the line, yet not after a full round
            } else {
                // set the ready for legal round flag to false
                readyForNewRound = false;
                // set the legal round flag to true
                isLegalRound = true;
            }
            // if is on the starting line yet not in the proper way
        } else if (carIsOnStartLineIllegally(car, driveDirection)) {
            // it isn't in the middle of a legal round (because it's at the start of one) we set the legal round flag to false
            isLegalRound = false;
        } else if (wholeCarPassedTheStartLine(car, driveDirection)) {
            // if the whole car has passed the line (driving forwards and on the right side of the starting line)
            readyForNewRound = true;
        }
        // return the flag containing if the round was legal
        return hasLegalCrossStartLine;
    }

    // will return true if the front corners of the car is on the right side of the line
    private boolean frontOfTheCarIsAfterStartLine(PlayerCar car) {
        // store the car front corners
        Point frontLeftCorner = car.playerCarCorners.getFrontLeftCorner();
        Point frontRightCorner = car.playerCarCorners.getFrontRightCorner();
        // check if one of the corners are on the right(in front) of the line
        return cornerIsAfterStartLine(frontLeftCorner) || cornerIsAfterStartLine(frontRightCorner);
    }

    // will return true if the front corners of the car are to the left of the line
    private boolean frontOfTheCarIsBeforeStartLine(PlayerCar car) {
        // store the car front corners
        Point frontLeftCorner = car.playerCarCorners.getFrontLeftCorner();
        Point frontRightCorner = car.playerCarCorners.getFrontRightCorner();
        // check if one of the corners are to the left (before) of the line
        return cornerIsBeforeStartLine(frontLeftCorner) || cornerIsBeforeStartLine(frontRightCorner);
    }

    // will return true if the back of the car is on the left side of the line
    private boolean rearOfTheCarIsAfterStartLine(PlayerCar car) {
        // store the car back corners
        Point rearLeftCorner = car.playerCarCorners.getRearLeftCorner();
        Point rearRightCorner = car.playerCarCorners.getRearRightCorner();
        // check if one of the corners are to the left (behind) of the line
        return cornerIsAfterStartLine(rearLeftCorner) || cornerIsAfterStartLine(rearRightCorner);
    }

    // will return true if the back of the car is on the right side of the line
    private boolean rearOfTheCarIsBeforeStartLine(PlayerCar car) {
        // store the car back corners
        Point rearLeftCorner = car.playerCarCorners.getRearLeftCorner();
        Point rearRightCorner = car.playerCarCorners.getRearRightCorner();
        // check if one of the corners are to the right (before) of the line
        return cornerIsBeforeStartLine(rearLeftCorner) || cornerIsBeforeStartLine(rearRightCorner);
    }

    // checks if a point is to the right of the line
    private boolean cornerIsAfterStartLine(Point corner) {
        return corner.x > upperStartLinePoint.x && yCornerIsInBoundsStartLine(corner.y);
    }

    // checks if a point is to the left of the line
    private boolean cornerIsBeforeStartLine(Point corner) {
        return corner.x < upperStartLinePoint.x && yCornerIsInBoundsStartLine(corner.y);
    }

    // checks if the y coordinate is in the bounds of the line
    private boolean yCornerIsInBoundsStartLine(double y) {
        return y < upperStartLinePoint.y && y > lowerStartLinePoint.y;
    }

    // will check if the car is legally on the line
    private boolean carIsOnStartLineLegally(PlayerCar car, MOVEMENT_DIRECTION direction) {
        // if the car is moving forwards
        if(direction == MOVEMENT_DIRECTION.FORWARDS) {
            // check if the car is on the line facing right (front on right side of line)
            return isOnStartLineFacingTheRight(car);
            // if the car is moving backwards
        } else {
            // check if the car is on the line facing left (front on left side of line)
            return isOnStartLineFacingTheLeft(car);
        }
    }

    // will check if the car is legally on the line
    private boolean carIsOnStartLineIllegally(PlayerCar car, MOVEMENT_DIRECTION direction) {
        // if the car is moving backwards
        if(direction == MOVEMENT_DIRECTION.BACKWARDS) {
            // check if the car is on the line facing right (front on right side of line)
            return isOnStartLineFacingTheRight(car);
        } else {
            // check if the car is on the line facing left (front on left side of line)
            return isOnStartLineFacingTheLeft(car);
        }
    }

    // will return true if the car is on the line facing the right
    private boolean isOnStartLineFacingTheRight(PlayerCar car) {
        return frontOfTheCarIsAfterStartLine(car) && rearOfTheCarIsBeforeStartLine(car);
    }

    // will return true if the car is on the line facing the left
    private boolean isOnStartLineFacingTheLeft(PlayerCar car) {
        return frontOfTheCarIsBeforeStartLine(car) && rearOfTheCarIsAfterStartLine(car);
    }

    // will return true if the line is behind the car
    private boolean wholeCarPassedTheStartLine(PlayerCar car, MOVEMENT_DIRECTION direction) {
        // if the car is driving forwards
        if(direction == MOVEMENT_DIRECTION.FORWARDS) {
            // check if all of the car corners are to the right of the line (putting it behind the car)
            return frontOfTheCarIsAfterStartLine(car) && rearOfTheCarIsAfterStartLine(car);
            // if the car is driving backwards
        } else {
            // check if all of the car corners are to the left of the line (putting the line behind the car)
            return frontOfTheCarIsBeforeStartLine(car) && rearOfTheCarIsBeforeStartLine(car);
        }
    }

}
