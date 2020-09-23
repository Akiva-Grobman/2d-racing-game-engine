package com.akivaGrobman.cars;

import com.akivaGrobman.input.PlayerKeyListener;

public class PlayerCar extends Car {

    private PlayerKeyListener keyListener;

    public PlayerCar() {
        keyListener = new PlayerKeyListener();
    }

    @Override
    public void tick() {
        //todo update x,y,angle
    }

}
