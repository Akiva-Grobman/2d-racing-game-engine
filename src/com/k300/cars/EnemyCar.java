package com.k300.cars;

import com.k300.io.EnemyInput;

public class EnemyCar extends Car {

    private EnemyInput input;

    public EnemyCar(String carColor, int x, int y) {
        super(carColor, x, y);
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
