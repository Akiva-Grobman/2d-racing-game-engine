package com.k300.io;

//import com.google.gson.Gson;
import com.k300.cars.Car;

public class EnemyOutput {

//    private final Gson gson;
    private final Car car;

    public EnemyOutput(/*Gson gson, */Car car) {
//        this.gson = gson;
        this.car = car;
    }

    public void send() {
//        String json = gson.toJson(car);
        //todo send to server
    }

}
