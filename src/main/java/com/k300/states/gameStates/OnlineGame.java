package com.k300.states.gameStates;

import com.k300.Launcher;
import com.k300.cars.Car;
import com.k300.tracks.Collisions;
import com.k300.tracks.StartLine;

public class OnlineGame extends GameState {

    public OnlineGame(Launcher launcher, int players) {
        super(launcher);
    }

    @Override
    public Car[] getCars(Collisions playerCollisionLogic, StartLine startLine) {
        return new Car[0];
    }

}
