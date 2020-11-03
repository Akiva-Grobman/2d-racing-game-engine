package com.k300.tracks.trackLogic.obstacles;

import com.k300.graphics.Assets;
import com.k300.utils.Point;
import com.k300.utils.math.Converter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/*
*       Purpose:
*           handle all all obstacles (check collision, fix track after rendering one)
*       Contains:
*           a list of obstacles
*/

public class ObstacleManager {

    // list of all on track obstacles
    private final List<Obstacle> obstacles;

    // only initialization option
    public ObstacleManager() {
        // set obstacle list to empty array
        obstacles = new ArrayList<>();
    }

    // will check if a position is colliding with any of the obstacles
    public boolean isCollidingWithObstacle(Point position) {
        // this will track if the point is colliding with any of the obstacles
        AtomicBoolean isOnTrack = new AtomicBoolean(true);
        for (Obstacle obstacle : obstacles) {
            // if it's colliding
            if (!obstacle.isOffObstacle(position.x, position.y)) {
                isOnTrack.set(false);
                break;
            }
        }
        // return isColliding
        return !isOnTrack.get();
    }

    // add obstacle
    public void addObstacle(Obstacle obstacle) {
        // add to list
        obstacles.add(obstacle);
        // draw obstacle on track image
        addObstacleToTrackImage(obstacle);
        // fixes track image
        resetTrackBackground();
    }

    // draw an obstacle on the track image
    public void addObstacleToTrackImage(Obstacle obstacle) {
        // get track graphics
        Graphics trackGraphics = Assets.getImage(Assets.TRACK_KEY).getGraphics();
        // render the obstacle to the image
        obstacle.render(trackGraphics);
    }

    // fix track image
    private void resetTrackBackground() {
        // this contains the "problematic" track image (with the obstacle)
        BufferedImage track = Assets.getImage(Assets.TRACK_KEY);
        Graphics trackGraphics = track.getGraphics();
        // add to the track the middle area
        BufferedImage trackMiddle = Assets.getImage(Assets.TRACK_MIDDLE_KEY);
        trackGraphics.drawImage(trackMiddle, (1920 - trackMiddle.getWidth()) / 2, (1080 - trackMiddle.getHeight()) / 2, trackMiddle.getWidth(), trackMiddle.getHeight(), null);
        // add to the track the outsize area
        trackGraphics.drawImage(Assets.getImage(Assets.TRACK_OUTSIDE_KEY), 0, 0, track.getWidth(), track.getHeight(), null);
        // add to the track the filter
        trackGraphics.drawImage(Assets.getImage(Assets.FILTER_KEY), 0, 0, Converter.FHD_SCREEN_WIDTH, Converter.FHD_SCREEN_HEIGHT, null);

    }

}
