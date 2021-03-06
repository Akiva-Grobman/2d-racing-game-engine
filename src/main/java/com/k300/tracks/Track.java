package com.k300.tracks;

import com.k300.cars.Car;
import com.k300.cars.player_car.PlayerCar;
import com.k300.graphics.Assets;
import com.k300.graphics.FontLoader;
import com.k300.states.SettingsState;
import com.k300.states.StateManager;
import com.k300.tracks.trackLogic.obstacles.Obstacle;
import com.k300.tracks.trackLogic.obstacles.ObstacleManager;
import com.k300.tracks.trackLogic.obstacles.StartLine;
import com.k300.states.gameStates.GameState;
import com.k300.tracks.trackLogic.Collisions;
import com.k300.utils.configarations.Config;
import com.k300.utils.math.Converter;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*
*       Purpose:
*           this is an abstract representation of what a track should be.
*       Contains:
 *           an obstacle manager and all of the cars in the game.
*       Functionality:
*           will update an render all cars, as well as the dev mode monitor
*/

import static com.k300.utils.Utils.drawStringInCenter;

public abstract class Track {

    // will handle all of the obstacles
    protected final ObstacleManager obstacleManager;
    // a reference to the game state
    protected final GameState gameState;
    // all of the cars in the game
    protected final Car[] cars;

    public boolean gameStarted;
    private int secondsForEachRotation;
    private String introCounter;
    private Long startTime;

    // only initialization option
    public Track(GameState gameState, Car[] cars) {
        secondsForEachRotation = 3;
        gameStarted = false;
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

        this.gameState.getLauncher().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    StateManager.setCurrentState(new SettingsState(gameState.getLauncher(), gameState));
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    // update all cars
    public void tick() {
        if(!gameStarted) {
            if (startTime == null) {
                startTime = System.currentTimeMillis();
            }
            long difference = System.currentTimeMillis() - startTime;
            int duration = 1000;
            if(difference >= duration /*duration*/) {
                startTime = System.currentTimeMillis();
                secondsForEachRotation--;
            }

            if(secondsForEachRotation <= 0) {
                introCounter = "GO!";
            } else {
                introCounter = String.valueOf(secondsForEachRotation);
            }

            if(secondsForEachRotation == -1) {
                gameStarted = true;
            }
        }

        if(gameStarted) {
            for (Car car : cars) {
                car.tick();
            }
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

        if(!gameStarted) {
            Graphics2D graphics2D = (Graphics2D) graphics.create();
            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 0.9));
            graphics2D.setColor(new Color(180, 19, 19));
            graphics2D.fillRect(0, 0, width, height);
            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
            graphics.setColor(Color.white);
            graphics.setFont(new Font("Ariel", Font.BOLD, 600));
            drawStringInCenter(0, 0, width, height, graphics, introCounter);
        }

    }

    // initialize a local car
    protected PlayerCar initLocalCar(Collisions playerCollisionLogic, StartLine startLine, int carIndex) {
        // the first element in the array will always be the local player
        assert cars[carIndex] instanceof PlayerCar;
        PlayerCar localPlayer = (PlayerCar) cars[carIndex];
        // set the collisions for the local player (see more in the Collisions and PlayerCar classes)
        localPlayer.setCollisions(playerCollisionLogic);
        // set the start line for the local player (see more in the StartLine and PlayerCar classes)
        localPlayer.setStartLine(startLine);
        // add the key listener for the local car to the game state (witch will add it to the display object)
        gameState.getLauncher().addKeyListener(localPlayer.getKeyListener());
        // return the initialized car
        return localPlayer;
    }

    // will render the track and cars on the full screen
    private void render(Graphics graphics, int width, int height) {
        // render track
        graphics.drawImage(Assets.getImage(Assets.TRACK_KEY), 0, 0, width, height, null);
        Font currentFont = graphics.getFont();
        Color currentColor = graphics.getColor();
        int heightMargin = 60;

        graphics.setColor(Color.black);
        graphics.setFont(FontLoader.loadFont("Minecraft", 60));
        int size = 600;
        int scoreFontSize = 50;

        graphics.fillOval(Converter.FHD_SCREEN_WIDTH - size / 2, -size / 2, size, size + heightMargin);

        graphics.setColor(Color.white);
        drawStringInCenter(Converter.FHD_SCREEN_WIDTH - size / 2f, 0, size/2 + 30, 100, graphics,"SCORE");


        graphics.setColor(currentColor);

        if(cars.length  > 2) {
            heightMargin = 40;
            scoreFontSize = 30;
        }
        // render cars
        for (int i = 0; i < cars.length; i++) {
            Car car = cars[i];
            car.render((Graphics2D) graphics);

            graphics.setFont(FontLoader.loadFont("Minecraft", scoreFontSize));
            if(car.carColor.contains("red")) {
                graphics.setColor(Color.red);
                drawStringInCenter(Converter.FHD_SCREEN_WIDTH - size / 2f, (i + 1) * heightMargin + 10, size / 2 + 30, 100, graphics, "Red: " + car.rounds);
            } else if(car.carColor.contains("blue")) {
                graphics.setColor(Color.blue);
                drawStringInCenter(Converter.FHD_SCREEN_WIDTH - size / 2f, (i + 1) * heightMargin + 10, size / 2 + 30, 100, graphics, "Blue: " + car.rounds);
            } else if(car.carColor.contains("yellow")) {
                graphics.setColor(Color.yellow);
                drawStringInCenter(Converter.FHD_SCREEN_WIDTH - size / 2f, (i + 1) * heightMargin + 10, size / 2 + 30, 100, graphics, "Yellow: " + car.rounds);
            }
        }

        graphics.setFont(new Font(currentFont.getName(), Font.PLAIN, 40));

    }

    // all tracks must render a dev monitor (for dev mode)
    protected abstract void renderDevMonitor(Graphics graphics);

}
