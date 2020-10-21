package com.k300.tracks;

import com.k300.cars.Car;
import com.k300.cars.EnemyCar;
import com.k300.graphics.Assets;
import com.k300.graphics.ZoomInCamera;
import com.k300.io.api.models.Player;
import com.k300.states.State;
import com.k300.states.gameStates.GameState;
import com.k300.states.gameStates.OfflineGame;
import com.k300.states.gameStates.OnlineGame;
import com.k300.utils.Point;
import com.k300.utils.configarations.Config;
import com.k300.utils.math.Converter;

import java.awt.*;

public class Track {

    private final ObstacleManager obstacleManager;
    private final GameState gameState;
    private volatile Car[] cars;

    public Track(State gameState) {
        this.gameState = (GameState) gameState;
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
        if(Config.isUsingZoom()) {
            renderWithZoom(graphics);
        } else {
            renderWithoutZoom(graphics, width, height);
        }
    }

    public void setCars() {
        Margins margins = new Margins();
        StartLine startLine = new StartLine(margins.getFrameBigBPoint(), margins.getFrameSmallBPoint());
        Collisions collisions = new Collisions(margins, obstacleManager);
        cars = gameState.getCars(collisions, startLine, getSumOfPlayers());
    }

    public Car[] getCars() {
        Car[] cars = new Car[this.cars.length];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new EnemyCar();
            cars[i].position.x = this.cars[i].position.x;
            cars[i].position.y = this.cars[i].position.y;
            cars[i].angle = this.cars[i].angle;
            cars[i].carColor = this.cars[i].carColor;
        }
        return cars;
    }

    public void updateCars(Player[] newCars) {
        assert newCars.length + 1 == cars.length;
        int index = 1;
        for (Player updatedCar: newCars) {
            updateLocalCar(index, updatedCar);
            index++;
        }
    }

    private void updateLocalCar(int index, Player updatedCar) {
        cars[index].updateColor(updatedCar.getColor());
        cars[index].position.x = updatedCar.getX();
        cars[index].position.y = updatedCar.getY();
        cars[index].angle = updatedCar.getAngle();
    }

    private int getSumOfPlayers() {
        if(gameState instanceof OfflineGame) {
            return 1;
        }
        return ((OnlineGame) gameState).sumOfPlayers;
    }

    private void renderWithoutZoom(Graphics graphics, int width, int height) {
        graphics.drawImage(Assets.getImage(Assets.TRACK_KEY), 0, 0, width, height, null);
        for (Car car : cars) {
            car.render((Graphics2D) graphics);
        }
    }

    private void renderWithZoom(Graphics graphics) {
        ZoomInCamera zoomInCamera = new ZoomInCamera(graphics, cars);
        zoomInCamera.render();
    }

}
