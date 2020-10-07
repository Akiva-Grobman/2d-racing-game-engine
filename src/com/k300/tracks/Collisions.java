package com.k300.tracks;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Collisions {

    private final Margins margins;
    private final ArrayList<Obstacle> obstacles;

    public Collisions(Margins margins) {
        this.margins = margins;
        obstacles = new ArrayList<>();
    }

    public void addObstacle(double x, double y, double size) {
        obstacles.add(new Obstacle(x, y, size));
    }

    public boolean onTheTrack(double carX, double carY) {
        AtomicBoolean isOnTrack = new AtomicBoolean(true);
        if (!margins.onTheTrack(carX, carY)) {
            return false;
        }
        for (Obstacle obstacle : obstacles) {
            if (!obstacle.onTheTrack(carX, carY)) {
                isOnTrack.set(false);
            }
        }
        return isOnTrack.get();
    }

    //Testing
    public void render(Graphics graphics) {
        for (Obstacle obstacle : obstacles) {
            obstacle.render(graphics);
        }
    }
}
