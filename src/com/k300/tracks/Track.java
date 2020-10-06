package com.k300.tracks;

import com.k300.cars.Car;
import com.k300.cars.PlayerCar;
import com.k300.graphics.Assets;
import com.k300.states.GameState;
import com.k300.states.State;
import com.k300.utils.Converter;
import com.k300.utils.Point;

import java.awt.*;

public class Track {

    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static final double SCREEN_WIDTH = screenSize.getWidth();
    private static final double SCREEN_HEIGHT = screenSize.getHeight();
    private final Car[] cars;
    private final State gameState;
    private final Collisions collisions;

    private final double a;
    private final double b;

    public Track(State gameState) {
        this.gameState = gameState;


        //Margins
        a = 750;
        b = 410;
        Margins margins = new Margins(a, b);
        collisions = new Collisions(margins);
        collisions.addObstacle(900, 800, 200);
        collisions.addObstacle(900, 250, 100);


        cars = new Car[1];
        // testing this should be information given from the server
        cars[0] = new PlayerCar(Assets.BLUE_CAR_KEY, new Point(700, 700), collisions);
        ((GameState)gameState).setKeyListener(((PlayerCar)cars[0]).getKeyListener());
    }

    public void tick() {
        for (Car car: cars) {
            car.tick();
        }
    }

    public void render(Graphics graphics) {


        int width = gameState.getWindowWidth();
        int height = gameState.getWindowHeight();
        graphics.drawImage(Assets.getImage(Assets.TRACK_KEY), 0, 0, width, height, null);

        collisions.render(graphics); //Testing

        graphics.drawImage(Assets.getImage(Assets.INSIDE_MARGIN_KEY), 0, 0, width, height, null);

        for (Car car: cars) {
            car.render((Graphics2D) graphics);
        }

        //Testing
//        double xLocation = SCREEN_WIDTH/2;
//        double yLocation = SCREEN_HEIGHT/2;
//        double bigA = a * 1.85;
//        double bigB = b * 1.7;
//
//        graphics.drawOval((int)(xLocation-a/2), (int)(yLocation-b/2), (int) a, (int) b);
//        graphics.drawOval((int)(xLocation-bigA/2), (int)(yLocation-bigB/2), (int) bigA, (int) bigB);



    }

}
