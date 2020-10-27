package com.k300.tracks;

import com.k300.cars.Car;
import com.k300.graphics.Assets;
import com.k300.obstacles.Obstacle;
import com.k300.obstacles.ObstacleManager;
import com.k300.states.gameStates.GameState;
import com.k300.utils.Utils;
import com.k300.utils.configarations.Config;
import com.k300.utils.math.Converter;

import java.awt.*;

import static com.k300.utils.Utils.drawStringInCenter;

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


        Font currentFont = graphics.getFont();
        Color currentColor = graphics.getColor();
        int heightMargin = 80;

        graphics.setColor(Color.black);
        graphics.setFont(new Font(currentFont.getName(), Font.PLAIN, 40));
        int size = 600;
        graphics.fillOval(Converter.FHD_SCREEN_WIDTH - size / 2, -size/2, size, size + heightMargin);

        graphics.setColor(Color.white);
        drawStringInCenter(Converter.FHD_SCREEN_WIDTH - size / 2f, 0, size/2 + 50, heightMargin, graphics,"SCORE");


        graphics.setColor(currentColor);


        for (int i = 0; i < cars.length; i++) {
            Car car = cars[i];
            car.render((Graphics2D) graphics);
            drawStringInCenter(Converter.FHD_SCREEN_WIDTH - size / 2f, (i + 1) * heightMargin, size / 2 + 50, 100, graphics, car.carColor + ": " + car.rounds);
        }

    }

    protected abstract void renderDevMonitor(Graphics graphics);

}
