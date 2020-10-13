package com.k300.tracks;

import com.k300.cars.player_car.PlayerCarCorners;
import com.k300.utils.Point;

public class Collisions {

    private final Margins margins;
    private final ObstacleManager obstacleManager;

    public Collisions(Margins margins, ObstacleManager obstacleManager) {
        this.margins = margins;
        this.obstacleManager = obstacleManager;
    }

    public boolean onTheTrack(Point position) {
        return margins.onTheTrack(position) && !obstacleManager.isCollidingWithObstacle(position);
    }

    public boolean isOnStartingLine(Point rightCorner, Point leftCorner) {
        return margins.isOnStartingLine(rightCorner, leftCorner);
    }

    public boolean isOnStartingLine(Point position) {
        return margins.isOnStartingLine(position);
    }
}
