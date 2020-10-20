package com.k300.cars;

import com.k300.utils.Point;

public class EnemyCar extends Car {

    public EnemyCar() {
        this("", new Point());
    }

    public EnemyCar(String carColor, Point position) {
        super(carColor, position);
    }

    @Override
    public void tick() {

    }

}
