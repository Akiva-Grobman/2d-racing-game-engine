package com.k300.tracks;

import com.k300.cars.Car;
import com.k300.graphics.Assets;
import com.k300.obstacles.Obstacle;
import com.k300.obstacles.ObstacleManager;
import com.k300.states.gameStates.GameState;
import com.k300.utils.configarations.Config;
import com.k300.utils.math.Converter;

import java.awt.*;

/*
*       Purpose:
*           this is an abstract representation of what a track should be.
*       Contains:
 *           an obstacle manager and all of the cars in the game.
*       Functionality:
*           will update an render all cars, as well as the dev mode monitor
*/

public abstract class Track {

    // will handle all of the obstacles
    protected final ObstacleManager obstacleManager;
    // a reference to the game state
    protected final GameState gameState;
    // all of the cars in the game
    protected final Car[] cars;

    // only initialization option
    public Track(GameState gameState, Car[] cars) {
        // set the cars array
        this.cars = cars;
        // set the game state reference
        this.gameState = gameState;
        // initialize an obstacleManager
        obstacleManager = new ObstacleManager();
        // add obstacles to the obstacle manager
        obstacleManager.addObstacle(new Obstacle(1300, 550, 500));
        obstacleManager.addObstacle(new Obstacle(1070, -150, 700));
        obstacleManager.addObstacle(new Obstacle(-350, 550, 1100));
        obstacleManager.addObstacle(new Obstacle(650, 480, 500));
        obstacleManager.addObstacle(new Obstacle(300, 1250, 900));
    }

    // update all cars
    public void tick() {
        for (Car car: cars) {
            car.tick();
        }
    }

    // render the track, obstacles, and cars
    public void render(Graphics graphics) {
        // default width and height
        int width = Converter.FHD_SCREEN_WIDTH;
        int height = Converter.FHD_SCREEN_HEIGHT;
        // if the user is playing online and wants to use the zoom in option
        if(this instanceof OnlineTrack && Config.isUsingZoom()) {
            ((OnlineTrack) this).renderWithZoom(graphics);
            // draw on full screen
        } else {
            render(graphics, width, height);
        }
        // render developer info
        if(Config.isInDevMode()) {
            renderDevMonitor(graphics);
        }
    }

    // will render the track and cars on the full screen
    private void render(Graphics graphics, int width, int height) {
        // render track
        graphics.drawImage(Assets.getImage(Assets.TRACK_KEY), 0, 0, width, height, null);
        // render cars
        for (Car car : cars) {
            car.render((Graphics2D) graphics);
        }
    }

    // all tracks must render a dev monitor
    protected abstract void renderDevMonitor(Graphics graphics);

}
