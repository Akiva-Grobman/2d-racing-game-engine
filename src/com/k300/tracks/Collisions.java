package com.k300.tracks;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Collisions {
    private Margins margins;
    private ArrayList<Obstacle> obstacles;

    public Collisions(Margins margins) {
        this.margins = margins;
        obstacles = new ArrayList<>();
    }

    public void addObstacle(double x, double y, double size) {
        obstacles.add(new Obstacle(x, y, size));
    }

    public boolean onTheTrack(double carX, double carY) {
        AtomicBoolean onTheTrack = new AtomicBoolean(true);
        if (!margins.onTheTrack(carX, carY)) {
            return false;
        }
        obstacles.forEach((obstacle) -> {
                    if (!obstacle.onTheTrack(carX, carY)) {
                        onTheTrack.set(false);
                    }
                }
        );
        return onTheTrack.get();
    }

    //Testing
    public void render(Graphics graphics) {
        obstacles.forEach((obstacle) -> obstacle.render(graphics));
    }
}
