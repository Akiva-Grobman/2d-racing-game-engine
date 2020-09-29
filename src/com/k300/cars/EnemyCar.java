package com.k300.cars;

import com.k300.io.EnemyInput;
import com.k300.utils.Point;

import java.awt.*;

public class EnemyCar extends Car {

    private final EnemyInput input;

    public EnemyCar(String carColor, Point startingPosition) {
        super(carColor, startingPosition);
        input = new EnemyInput();
    }

    @Override
    public void tick() {
        //todo
        input.tick();
        x = input.getX();
        y = input.getY();
        angle = input.getAngle();
    }

}
