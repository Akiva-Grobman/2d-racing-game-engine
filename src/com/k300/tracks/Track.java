package com.k300.tracks;

import com.k300.cars.Car;
import com.k300.cars.player_car.PlayerCar;
import com.k300.graphics.Assets;
import com.k300.graphics.Camera;
import com.k300.states.GameState;
import com.k300.states.State;
import com.k300.utils.Point;
import com.k300.utils.configarations.Config;
import com.k300.utils.math.Converter;

import java.awt.*;

public class Track {

    private final Car[] cars;

    public Track(State gameState) {
        ObstacleManager obstacleManager = new ObstacleManager();
        Margins margins = new Margins();
        StartLine startLine = new StartLine(margins.getFrameBigBPoint(), margins.getFrameSmallBPoint());
        Collisions collisions = new Collisions(margins, obstacleManager);
        obstacleManager.addObstacle(new Obstacle(1300, 550, 500));
        obstacleManager.addObstacle(new Obstacle(1070, -150, 700));
        obstacleManager.addObstacle(new Obstacle(-350, 550, 1100));
        obstacleManager.addObstacle(new Obstacle(650, 480, 500));
        obstacleManager.addObstacle(new Obstacle(300, 1250, 900));
        //set cars
        cars = new Car[1];
        // testing this should be information given from the server
        cars[0] = new PlayerCar(Assets.BLUE_CAR_KEY, new Point(800, 880), collisions, startLine);
        ((GameState)gameState).setKeyListener(((PlayerCar)cars[0]).getKeyListener());
    }

    public void tick() {
        for (Car car: cars) {
            car.tick();
        }
    }

    public void render(Graphics graphics) {
        int width = Converter.FHD_SCREEN_WIDTH;
        int height = Converter.FHD_SCREEN_HEIGHT;
        if(Config.IsUsingZoom()) {
            renderWithZoom(graphics);
        } else {
            renderWithoutZoom(graphics, width, height);
        }
    }

    private void renderWithoutZoom(Graphics graphics, int width, int height) {
        graphics.drawImage(Assets.getImage(Assets.TRACK_KEY), 0, 0, width, height, null);
        for (Car car : cars) {
            car.render((Graphics2D) graphics);
        }
    }

    private void renderWithZoom(Graphics graphics) {
        Camera zoomInCamera = new Camera(graphics, cars);
        zoomInCamera.render();
    }

}
