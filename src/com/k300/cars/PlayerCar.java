package com.k300.cars;

import com.k300.io.PlayerKeyListener;
import java.awt.event.KeyListener;

public class PlayerCar extends Car {

    private final PlayerKeyListener keyListener;

    public PlayerCar(String carColor, int x, int y) {
        super(carColor, x, y);
        keyListener = new PlayerKeyListener();
    }

    @Override
    public void tick() {
        //todo add calculations
        if(keyListener.getKeyIsPressed(PlayerKeyListener.RIGHT_ARROW)) {
            x += velocity;
        }
        if(keyListener.getKeyIsPressed(PlayerKeyListener.LEFT_ARROW)) {
            x -= velocity;
        }
        if(keyListener.getKeyIsPressed(PlayerKeyListener.UP_ARROW)) {
            y -= velocity;
        }
        if(keyListener.getKeyIsPressed(PlayerKeyListener.DOWN_ARROW)) {
            y += velocity;
        }
    }

    public KeyListener getKeyListener() {
        return keyListener;
    }
}
