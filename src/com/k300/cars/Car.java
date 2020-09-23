package com.k300.cars;

import com.k300.graphics.Assets;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public abstract class Car {

    protected static final int velocity = 5;
    protected double x;
    protected double y;
    protected double angle;
    protected BufferedImage carImage;

    public Car(String carColor, int x, int y) {
        carImage = Assets.getImage(carColor);
        this.x = x;
        this.y = y;
        angle = 0;
    }

    public abstract void tick();

    public final void render(Graphics2D graphics) {
        if(carImage == null) {
            return;
        }
        AffineTransform carAngle = AffineTransform.getTranslateInstance(x - carImage.getWidth() / 2f, y -  carImage.getHeight() / 2f);
        carAngle.rotate(Math.toDegrees(angle), carImage.getWidth() / 2f, carImage.getHeight() / 2f);
        graphics.setColor(Color.red);
        graphics.drawImage(carImage, carAngle, null);
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
