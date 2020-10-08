package com.k300.cars;

import com.k300.cars.player_car.PlayerCarCorners;
import com.k300.graphics.Assets;
import com.k300.utils.Point;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public abstract class Car {

    public double maxSpeed = 15;
    public double speed;
    public double x;
    public double y;
    public double angle;
    public BufferedImage carImage;

    private final PlayerCarCorners playerCarCorners;

    public Car(String carColor, Point startingPosition) {
        carImage = Assets.getImage(carColor);
        x = startingPosition.x;
        y = startingPosition.y;
        angle = 0;
        speed = 0;

        playerCarCorners = new PlayerCarCorners(this);
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
        graphics.drawString("Angle: " + angle, 600, 350);
        graphics.drawString("X: " + x, 600, 450);
        graphics.drawString("Y: " + y, 600, 550);

        //Testing
        graphics.fillOval((int)(x-25/2) , (int)(y-25/2), 25, 25);

        Point TopRightCorner = playerCarCorners.getTopRightCorner();
        Point BottomRightCorner = playerCarCorners.getBottomRightCorner();
        Point TopLeftCorner = playerCarCorners.getTopLeftCorner();
        Point BottomLeftCorner = playerCarCorners.getBottomLeftCorner();

        graphics.fillOval((int)(TopRightCorner.x-10/2) , (int)(TopRightCorner.y-10/2), 10, 10);
        graphics.fillOval((int)(BottomRightCorner.x-10/2) , (int)(BottomRightCorner.y-10/2), 10, 10);
        graphics.fillOval((int)(TopLeftCorner.x-10/2) , (int)(TopLeftCorner.y-10/2), 10, 10);
        graphics.fillOval((int)(BottomLeftCorner.x-10/2) , (int)(BottomLeftCorner.y-10/2), 10, 10);

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
