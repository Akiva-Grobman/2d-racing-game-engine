package com.k300.tracks;

import com.k300.Launcher;
import com.k300.cars.Car;
import com.k300.cars.PlayerCar;
import com.k300.graphics.Assets;
import com.k300.states.State;

import java.awt.*;

public class Track {

    private final Car[] cars;
    private State gameState;

    public Track(State gameState) {
        this.gameState = gameState;
        cars = new Car[1];
        cars[0] = new PlayerCar();
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
//        for (Car car: cars) {
//            car.render((Graphics2D) graphics);
//        }
    }

}
