package com.k300.cars;

import com.k300.input.PlayerKeyListener;

public class PlayerCar extends Car {

    private PlayerKeyListener keyListener;

    public PlayerCar(String carColor, int x, int y) {
        super(carColor, x, y);
        keyListener = new PlayerKeyListener();
    }

    @Override
    public void tick() {
        //todo update x,y,angle
    }

}
