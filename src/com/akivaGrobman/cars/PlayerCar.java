package com.akivaGrobman.cars;

import com.akivaGrobman.input.PlayerKeyListener;

import java.awt.*;

public class PlayerCar extends Car {

    private PlayerKeyListener keyListener;

    public PlayerCar() {
        keyListener = new PlayerKeyListener();
    }

    @Override
    public void tick() {
//        carAngle = AffineTransform.getTranslateInstance(x, y);
//        carAngle.rotate(Math.toDegrees(angle), width, height);
    }

    @Override
    public void render(Graphics2D graphics) {
        graphics.drawImage(carImage, carAngle, null);
    }

}
