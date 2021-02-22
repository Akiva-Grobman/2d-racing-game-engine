package com.k300.states.gameStates;

import com.k300.Launcher;
import com.k300.cars.Car;
import com.k300.cars.player_car.PlayerCar;
import com.k300.graphics.Assets;
import com.k300.io.KEY_MOVEMENT_TYPE;
import com.k300.io.PlayerKeyListener;
import com.k300.tracks.OfflineTrack;
import com.k300.utils.Point;

/*
*       Purpose:
*           manage the offline game
*/

public class OfflineGame extends GameState {

    // only initialization option
    public OfflineGame(Launcher launcher) {
        // initialize abstract game state
        super(launcher);
        System.out.println(startingPosition.x);
        // initialize track to an offline track with a list of two local players
        track = new OfflineTrack(this, getInitCars());
    }

    // initialize two local players
    public Car[] getInitCars() {
        // initialize empty cars array of size 2
        Car[] cars = new Car[2];
        // initialize the first car to a blue local player with the default starting position and a key listener that listens to the arrow keys
        cars[0] = new PlayerCar(Assets.BLUE_CAR_KEY,
                startingPosition,
                new PlayerKeyListener(KEY_MOVEMENT_TYPE.ARROWS));
        // initialize the second car to a red local player with the default starting position and a key listener that listens to the A S W D keys
        cars[1] = new PlayerCar(Assets.RED_CAR_KEY,
                new Point(startingPosition.x, startingPosition.y),
                new PlayerKeyListener(KEY_MOVEMENT_TYPE.A_S_W_D));
        // return initialized cars
        return cars;
    }

}
