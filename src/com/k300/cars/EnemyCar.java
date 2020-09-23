package com.k300.cars;

import com.k300.input.EnemyInput;

public class EnemyCar extends Car {

    private EnemyInput input;

    public EnemyCar(String carColor, int x, int y) {
        super(carColor, x, y);
        input = new EnemyInput();
    }

    @Override
    public void tick() {
        //todo
        x = input.getX();
        y = input.getY();
        angle = input.getAngle();
    }

}
