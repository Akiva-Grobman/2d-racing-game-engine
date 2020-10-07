package com.k300.io;

//import com.google.gson.Gson;
import com.k300.cars.Car;

public class EnemyInput {

//    private final Gson gson;
    private volatile double x;
    private volatile double y;
    private volatile double angle;

    public EnemyInput() {
//        gson = new Gson();
    }

    public void tick() {
        //todo receive json string from server
        String json = "";
//        Car car = gson.fromJson(json, Car.class);
//        x = car.getX();
//        y = car.getY();
//        angle = car.getAngle();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getAngle() {
        return angle;
    }
}
