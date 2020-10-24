package com.k300.obstacles;

import com.k300.graphics.Assets;
import com.k300.utils.Point;
import com.k300.utils.math.Converter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ObstacleManager {

    private final List<Obstacle> obstacles;

    public ObstacleManager() {
        obstacles = new ArrayList<>();
    }

    public boolean isCollidingWithObstacle(Point position) {
        AtomicBoolean isOnTrack = new AtomicBoolean(true);
        for (Obstacle obstacle : obstacles) {
            if (!obstacle.isOnTrack(position.x, position.y)) {
                isOnTrack.set(false);
            }
        }
        return !isOnTrack.get();
    }

    public void addObstacle(Obstacle obstacle) {
        obstacles.add(obstacle);
        addObstacleToTrackImage(obstacle);
        resetTrackBackground();
    }


    private void resetTrackBackground() {
        BufferedImage track = Assets.getImage(Assets.TRACK_KEY);
        Graphics trackGraphics = track.getGraphics();

        BufferedImage trackMiddle = Assets.getImage(Assets.TRACK_MIDDLE_KEY);
        trackGraphics.drawImage(trackMiddle, (1920 - trackMiddle.getWidth()) / 2, (1080 - trackMiddle.getHeight()) / 2, trackMiddle.getWidth(), trackMiddle.getHeight(), null);

        trackGraphics.drawImage(Assets.getImage(Assets.TRACK_OUTSIDE_KEY), 0, 0, track.getWidth(), track.getHeight(), null);

        trackGraphics.drawImage(Assets.getImage(Assets.FILTER_KEY), 0, 0, Converter.FHD_SCREEN_WIDTH, Converter.FHD_SCREEN_HEIGHT, null);

    }

    public void addObstacleToTrackImage(Obstacle obstacle) {
        Graphics trackGraphics = Assets.getImage(Assets.TRACK_KEY).getGraphics();
        obstacle.render(trackGraphics);
    }

}
