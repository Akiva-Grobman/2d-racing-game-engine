package com.k300.tracks.trackLogic;

import com.k300.tracks.trackLogic.obstacles.ObstacleManager;
import com.k300.utils.Point;

/*
 *       Purpose:
 *           An object that contains the Margin object and the ObstacleManager object and check if a car is on the track.
 *       Contains:
 *           Margin, ObstacleManager of the current track.
 */

public class Collisions {

    // the margins of the track
    private final Margins margins;
    // the obstacles of the track
    private final ObstacleManager obstacleManager;

    public Collisions(Margins margins, ObstacleManager obstacleManager) {
        this.margins = margins;
        this.obstacleManager = obstacleManager;
    }

    // check if a car on the track by getting his position,
    // and using the margins,.onTheTrack and obstacleManager.isCollidingWithObstacle
    // to check if the car isn't crossing the margins and an obstacle.
    public boolean onTheTrack(Point position) {
        return margins.onTheTrack(position) && !obstacleManager.isCollidingWithObstacle(position);
    }

}
