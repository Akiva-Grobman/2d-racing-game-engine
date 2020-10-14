package com.k300.cars;

import com.k300.cars.player_car.PlayerCar;
import com.k300.graphics.Assets;
import com.k300.utils.Point;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public abstract class Car {

    public Point position;
    public double angle;
    public BufferedImage carImage;

    public Car(String carColor, Point startingPosition) {
        carImage = Assets.getImage(carColor);
        position = startingPosition;
        angle = 0;
    }

    public abstract void tick();

    public final void render(Graphics2D graphics) {
        if(carImage == null) {
            return;
        }
        AffineTransform carAngle = AffineTransform.getTranslateInstance(position.x - carImage.getWidth() / 2f, position.y -  carImage.getHeight() / 2f);
        carAngle.rotate(Math.toRadians(-angle), carImage.getWidth() / 2f, carImage.getHeight() / 2f); //need Minus because Java is multiplier minus
        graphics.setColor(Color.red);
        graphics.drawImage(carImage, carAngle, null);
        if(this instanceof PlayerCar) {

            graphics.setFont(new Font("TimesRoman", Font.BOLD, 60));
            graphics.drawString("Rounds: " + ((PlayerCar) this).rounds, 800, 400);
        }
    }

    public double getX() {
        return position.x;
    }

    public double getY() {
        return position.y;
    }

    public double getAngle() {
        return angle;
    }

}
