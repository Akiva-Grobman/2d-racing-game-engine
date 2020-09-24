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
        if(keyListener.getKeyIsPressed(PlayerKeyListener.UP_ARROW)) {
            forward();
        }
        if(keyListener.getKeyIsPressed(PlayerKeyListener.DOWN_ARROW)) {
            backwards();
        }
        if(keyListener.getKeyIsPressed(PlayerKeyListener.RIGHT_ARROW)) {
            right();
        }
        if(keyListener.getKeyIsPressed(PlayerKeyListener.LEFT_ARROW)) {
            left();
        }
    }


    private void forward() {
        y -= velocity;
    }

    private void backwards() {
        y += velocity;
    }

    private void right() {
        x += velocity;
    }

    private void left() {
        x -= velocity;
    }


    public KeyListener getKeyListener() {
        return keyListener;
    }

}
