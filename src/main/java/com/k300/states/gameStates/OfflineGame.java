package com.k300.states.gameStates;

import com.k300.Launcher;
import com.k300.cars.Car;
import com.k300.cars.player_car.MOVEMENT_DIRECTION;
import com.k300.cars.player_car.PlayerCar;
import com.k300.graphics.Assets;
import com.k300.io.KEY_MOVEMENT_TYPE;
import com.k300.io.KeyListener;
import com.k300.io.PlayerKeyListener;
import com.k300.tracks.OfflineTrack;
import com.k300.utils.Point;

public class OfflineGame extends GameState {

    public OfflineGame(Launcher launcher) {
        super(launcher);
        track = new OfflineTrack(this, getInitCars());
    }

    public Car[] getInitCars() {
        Car[] cars = new Car[2];
        cars[0] = new PlayerCar(Assets.BLUE_CAR_KEY,
                startingPosition,
                new PlayerKeyListener(KEY_MOVEMENT_TYPE.ARROWS));
        cars[1] = new PlayerCar(Assets.RED_CAR_KEY,
                new Point(startingPosition.x - 50, startingPosition.y + 40),
                new PlayerKeyListener(KEY_MOVEMENT_TYPE.A_S_W_D));
        return cars;
    }

}
