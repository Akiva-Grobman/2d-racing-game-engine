package com.k300.states.gameStates;

import com.k300.Launcher;
import com.k300.cars.Car;
import com.k300.graphics.Assets;
import com.k300.tracks.Collisions;
import com.k300.tracks.StartLine;
import com.k300.utils.math.Converter;

import java.awt.*;

import static com.k300.utils.Utils.drawStringInCenter;

public class OnlineGame extends GameState {

    public static int player;
    private boolean hasAllCars;
    private String dots;
    private int timeFromLastUpdate;

    public OnlineGame(Launcher launcher) {
        super(launcher);
        hasAllCars = false;
        dots = "";
        timeFromLastUpdate = 0;
    }

    @Override
    public void tick() {
        if(hasAllCars()) {
            super.tick();
        } else {
            //todo handle loading
        }
    }

    @Override
    public void render(Graphics graphics) {
        if(hasAllCars()) {
            super.render(graphics);
        } else {
            //todo handle loading
            drawStringInCenter(0,
                    0,
                    Converter.FHD_SCREEN_WIDTH,
                    Converter.FHD_SCREEN_HEIGHT,
                    graphics,
                    "Loading" + getDot());
        }
    }

    private String getDot() {
        timeFromLastUpdate++;
        if (timeFromLastUpdate >= 30) {
            timeFromLastUpdate = 0;
            if(dots.length() == 3){
                dots = "";
            } else {
                dots += ".";
            }
        }
        return dots;
    }

    private boolean hasAllCars() {
        if (!hasAllCars) {
            for (Car car : track.cars) {
                if (car == null) {
                    return false;
                }
            }
        }
        hasAllCars = true;
        return true;
    }

    @Override
    public Car[] getCars(Collisions playerCollisionLogic, StartLine startLine) {
        Car[] cars = new Car[player];
        cars[0] = getLocalPlayer(Assets.BLUE_CAR_KEY, playerCollisionLogic, startLine);
        return cars;
    }

}
