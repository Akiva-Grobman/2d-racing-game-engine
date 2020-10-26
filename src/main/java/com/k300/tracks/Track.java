package com.k300.tracks;

import com.k300.cars.Car;
import com.k300.graphics.Assets;
import com.k300.obstacles.Obstacle;
import com.k300.obstacles.ObstacleManager;
import com.k300.states.gameStates.GameState;
import com.k300.utils.configarations.Config;
import com.k300.utils.math.Converter;

import java.awt.*;

public abstract class Track {

    protected final ObstacleManager obstacleManager;
    protected final GameState gameState;
    protected final Car[] cars;

    public Track(GameState gameState, Car[] cars) {
        this.cars = cars;
        this.gameState = gameState;
        obstacleManager = new ObstacleManager();
        obstacleManager.addObstacle(new Obstacle(1300, 550, 500));
        obstacleManager.addObstacle(new Obstacle(1070, -150, 700));
        obstacleManager.addObstacle(new Obstacle(-350, 550, 1100));
        obstacleManager.addObstacle(new Obstacle(650, 480, 500));
        obstacleManager.addObstacle(new Obstacle(300, 1250, 900));
    }

    public void tick() {
        for (Car car: cars) {
            car.tick();
        }
    }

    public void render(Graphics graphics) {
        int width = Converter.FHD_SCREEN_WIDTH;
        int height = Converter.FHD_SCREEN_HEIGHT;



        // won't allow zoom with local player (because there are 2 players)
        if(this instanceof OnlineTrack && Config.isUsingZoom()) {
            ((OnlineTrack) this).renderWithZoom(graphics);
        } else {
            render(graphics, width, height);
        }

        if(Config.isInDevMode()) {
            renderDevMonitor(graphics);
        }
    }

    private void render(Graphics graphics, int width, int height) {
        graphics.drawImage(Assets.getImage(Assets.TRACK_KEY), 0, 0, width, height, null);
        for (Car car : cars) {
            car.render((Graphics2D) graphics);
        }
    }

    protected abstract void renderDevMonitor(Graphics graphics);

}
