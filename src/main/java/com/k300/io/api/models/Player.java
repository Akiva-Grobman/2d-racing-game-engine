package com.k300.io.api.models;

import java.util.Objects;

/*
 *       Purpose:
 *           represent a Json Player object in a POJO
 *       Contains:
 *           a player id, x,y position of car, angle of car, car color, and a flag if it finished the game.
 */

@SuppressWarnings("unused")
public class Player {

    // id field
    private String id;
    // x field
    private double x;
    // y field
    private double y;
    // angle field
    private double angle;
    // game finished flag
    private boolean finished;
    // car color field
    private String color;

    // player id accessor
    public String getId() {
        return id;
    }

    // player id modifier
    public void setId(String id) {
        this.id = id;
    }

    // x position accessor
    public double getX() {
        return x;
    }

    // x position modifier
    public void setX(double x) {
        this.x = x;
    }

    // y position accessor
    public double getY() {
        return y;
    }

    // y position modifier
    public void setY(double y) {
        this.y = y;
    }

    // angle accessor
    public double getAngle() {
        return angle;
    }

    // angle modifier
    public void setAngle(double angle) {
        this.angle = angle;
    }

    // finished flag accessor
    public boolean isFinished() {
        return finished;
    }

    // finished flag modifier
    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    // color accessor
    public String getColor() {
        return color;
    }

    // color modifier
    public void setColor(String color) {
        this.color = color;
    }

    // over ride the equals method to return true if both players contain th same color
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(id, player.id);
    }

    // over ride the hash method to hash only the id
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // over ride to string method
    @Override
    public String toString() {
        return "Player{" +
                "id='" + id + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", angle=" + angle +
                ", finished=" + finished +
                ", color='" + color + '\'' +
                '}';
    }
}
