package com.k300.tracks;

import com.k300.cars.Car;
import com.k300.cars.player_car.PlayerCar;
import com.k300.graphics.Assets;
import com.k300.states.GameState;
import com.k300.states.State;
import com.k300.utils.Point;
import com.k300.utils.math.Converter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Track {

    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static final double SCREEN_WIDTH = screenSize.getWidth();
    private static final double SCREEN_HEIGHT = screenSize.getHeight();
    private final Car[] cars;
    private final State gameState;

    public Track(State gameState) {
        this.gameState = gameState;
        ObstacleManager obstacleManager = new ObstacleManager();

        Margins margins = new Margins();
        Collisions collisions = new Collisions(margins, obstacleManager);
        obstacleManager.addObstacle(new Obstacle(1300, 550, 500));
        obstacleManager.addObstacle(new Obstacle(1070, -150, 700));
        obstacleManager.addObstacle(new Obstacle(-350, 550, 1100));
        obstacleManager.addObstacle(new Obstacle(650, 480, 500));
        obstacleManager.addObstacle(new Obstacle(300, 1250, 900));
        //set cars
        cars = new Car[1];
        // testing this should be information given from the server
        cars[0] = new PlayerCar(Assets.BLUE_CAR_KEY, new Point(500, 700), collisions);
        ((GameState)gameState).setKeyListener(((PlayerCar)cars[0]).getKeyListener());
    }

    public void tick() {
        for (Car car: cars) {
            car.tick();
        }
    }

    public void render(Graphics graphics) {
        int width = Converter.DEFAULT_SCREEN_WIDTH;
        int height = Converter.DEFAULT_SCREEN_HEIGHT;
        graphics.drawImage(Assets.getImage(Assets.TRACK_KEY), 0, 0, width, height, null);
        for (Car car: cars) {
            car.render((Graphics2D) graphics);
        }
    //Testing
        double xLocation = width/2;
        double yLocation = height/2;
        double middleWidth = Assets.getImage(Assets.TRACK_MIDDLE_KEY).getWidth();
        double middleHeight = Assets.getImage(Assets.TRACK_MIDDLE_KEY).getHeight();
        double roadWidth = Assets.getImage(Assets.ROAD_KEY).getWidth();
        double roadHeight = Assets.getImage(Assets.ROAD_KEY).getHeight();

        graphics.drawOval((int)(xLocation-middleWidth/2), (int)(yLocation-middleHeight/2), (int) middleWidth, (int) middleHeight);
        graphics.drawOval((int)(xLocation-roadWidth/2), (int)(yLocation-roadHeight/2), (int) roadWidth, (int) roadHeight);
    }

}
