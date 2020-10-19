package com.k300.states.gameStates;

import com.k300.Launcher;
import com.k300.cars.Car;
import com.k300.graphics.Assets;
import com.k300.tracks.Collisions;
import com.k300.tracks.StartLine;

public class OfflineGame extends GameState {

    public OfflineGame(Launcher launcher) {
        super(launcher);
    }

    @Override
    public Car[] getCars(Collisions playerCollisionLogic, StartLine startLine) {
        //todo add second car
        Car[] cars = new Car[1];
        cars[0] = getLocalPlayer(Assets.BLUE_CAR_KEY, playerCollisionLogic, startLine);
        return cars;
    }

}
