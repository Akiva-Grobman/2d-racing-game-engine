package com.k300.cars;

import com.k300.graphics.Assets;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public abstract class Car {

    protected int speed;
    protected double x;
    protected double y;
    protected double angle;
    protected BufferedImage carImage;

    public Car(String carColor, Point startingPosition) {
        carImage = Assets.getImage(carColor);
        speed = 10;
        x = startingPosition.x;
        y = startingPosition.y;
        angle = 0;
    }

    public abstract void tick();

    public final void render(Graphics2D graphics) {
        if(carImage == null) {
            return;
        }
        AffineTransform carAngle = AffineTransform.getTranslateInstance(x - carImage.getWidth() / 2f, y -  carImage.getHeight() / 2f);
        carAngle.rotate(Math.toRadians(-angle), carImage.getWidth() / 2f, carImage.getHeight() / 2f); //angle needs Minus because Java is multiplier minus
        graphics.drawImage(carImage, carAngle, null);
        // testing code
        graphics.setColor(Color.red);
        graphics.setFont(new Font("TimesRoman", Font.BOLD, 60));
        graphics.drawString("Angle: " + angle, 600, 350);
        graphics.drawString("X: " + x, 600, 450);
        graphics.drawString("Y: " + y, 600, 550);
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
