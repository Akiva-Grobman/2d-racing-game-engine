package com.akivaGrobman.tracks;

import com.akivaGrobman.Launcher;
import com.akivaGrobman.cars.Car;
import com.akivaGrobman.cars.PlayerCar;
import com.akivaGrobman.graphics.Assets;

import java.awt.*;

public class Track {

    private Car[] cars;
    private Launcher launcher;

    public Track(Launcher launcher) {
        this.launcher = launcher;
        cars = new Car[1];
        cars[0] = new PlayerCar();
    }

    public void tick() {
        for (Car car: cars) {
            car.tick();
        }
    }

    public void render(Graphics graphics) {
        graphics.drawImage(Assets.getImage(Assets.TRACK_KEY), 0, 0, launcher.getWindow().getFrameWidth(), launcher.getWindow().getFrameHeight(), null);
        for (Car car: cars) {
            car.render((Graphics2D) graphics);
        }
    }

}
