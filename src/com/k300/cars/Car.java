package com.k300.cars;

import com.k300.graphics.Assets;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public abstract class Car {

    protected static final int speed = 10;
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
        carAngle.rotate(Math.toRadians(-angle), carImage.getWidth() / 2f, carImage.getHeight() / 2f); //need Minus because Java is multiplier minus
        graphics.setColor(Color.red);
        graphics.drawImage(carImage, carAngle, null);

        graphics.setFont(new Font("TimesRoman", Font.BOLD, 60));
        graphics.drawString("Angle: " + Double.toString(angle), 600, 350);
        graphics.drawString("X: " + Double.toString(x), 600, 450);
        graphics.drawString("Y: " + Double.toString(y), 600, 550);
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
