package com.akivaGrobman.tracks;

import com.akivaGrobman.cars.Car;
import com.akivaGrobman.cars.PlayerCar;
import java.awt.*;

public class Track {

    private final Car[] cars;

    public Track() {
        cars = new Car[1];
        cars[0] = new PlayerCar();
    }

    public void tick() {
        for (Car car: cars) {
            car.tick();
        }
    }

    public void render(Graphics graphics) {
        // todo
//        int width = getWindowWidth();
//        int height = getWindowHeight();
//        graphics.drawImage(Assets.getImage(Assets.TRACK_KEY), 0, 0, width, height, null);
        for (Car car: cars) {
            car.render((Graphics2D) graphics);
        }
    }

}
