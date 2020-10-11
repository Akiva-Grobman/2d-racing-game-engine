package com.k300.tracks;

import com.k300.utils.Point;

import java.awt.*;

public class Collisions {

    private final Rectangle startingLine;
    private final Margins margins;
    private final ObstacleManager obstacleManager;

    public Collisions(Margins margins, ObstacleManager obstacleManager) {
        this.margins = margins;
        this.obstacleManager = obstacleManager;
        startingLine = new Rectangle(margins.getStartingLineBounds().x - margins.getStartingLineBounds().width / 2,
                margins.getStartingLineBounds().y,
                5,
                margins.getStartingLineBounds().height);
    }

    public boolean onTheTrack(Point position) {
        return margins.onTheTrack(position) && !obstacleManager.isCollidingWithObstacle(position);
    }

    public boolean isOnStartingLine(Point position) {
        return startingLine.contains(position.x, position.y);
    }

}
