package com.k300.tracks;

import com.k300.graphics.Assets;
import com.k300.utils.Point;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ObstacleManager {

    private final List<Obstacle> obstacles;

    public ObstacleManager() {
        obstacles = new ArrayList<>();
    }

    public boolean isCollidingWithObstacle(Point position) {
        AtomicBoolean isOnTrack = new AtomicBoolean(false);
        for (Obstacle obstacle : obstacles) {
            if (!obstacle.isOnTrack(position.x, position.y)) {
                isOnTrack.set(false);
            }
        }
        return isOnTrack.get();
    }

    public void addObstacle(Obstacle obstacle) {
        obstacles.add(obstacle);
        addObstacleToTrackImage(obstacle);
    }

    public void addObstacleToTrackImage(Obstacle obstacle) {
        Graphics trackGraphics = Assets.getImage(Assets.TRACK_KEY).createGraphics();
        obstacle.render(trackGraphics);
    }

}
