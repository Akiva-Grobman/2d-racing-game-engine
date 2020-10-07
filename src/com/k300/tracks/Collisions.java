package com.k300.tracks;

import com.k300.utils.Point;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Collisions {

    private final Margins margins;
    private final ObstacleManager obstacleManager;

    public Collisions(Margins margins, ObstacleManager obstacleManager) {
        this.margins = margins;
        this.obstacleManager = obstacleManager;
    }

    public boolean onTheTrack(Point position) {
        if (!margins.onTheTrack(position.x, position.y)) {
            return false;
        }
        return !obstacleManager.isCollidingWithObstacle(position);
    }

}
