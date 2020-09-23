package com.k300.cars;

import com.k300.input.EnemyInput;

public class EnemyCar extends Car {

    private EnemyInput input;

    public EnemyCar() {
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
