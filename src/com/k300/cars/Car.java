package com.k300.cars;

import com.k300.cars.player_car.PlayerCar;
import com.k300.cars.player_car.PlayerCarCorners;
import com.k300.graphics.Assets;
import com.k300.utils.Point;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public abstract class Car {

    public double maxSpeed = 15;
    public double speed;
    public Point position;
    public double angle;
    public int rounds;
    public BufferedImage carImage;

    private final PlayerCarCorners playerCarCorners;

    public Car(String carColor, Point startingPosition) {
        carImage = Assets.getImage(carColor);
        position = startingPosition;
        angle = 0;
        speed = 0;

        playerCarCorners = new PlayerCarCorners(this);
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

        graphics.setFont(new Font("TimesRoman", Font.BOLD, 60));
        graphics.drawString("Angle: " + angle, 800, 450);
        graphics.drawString("X: " + position.x, 800, 550);
        graphics.drawString("Y: " + position.y, 800, 650);

        graphics.drawString("Rounds: " + this.rounds, 200, 200);

        graphics.fillRect(960,0, 10, 2000);

        //Testing
        graphics.fillOval((int)(position.x-25/2) , (int)(position.y-25/2), 25, 25);

        Point TopRightCorner = playerCarCorners.getFrontLeftCorner();
        Point BottomRightCorner = playerCarCorners.getFrontRightCorner();
        Point TopLeftCorner = playerCarCorners.getRearLeftCorner();
        Point BottomLeftCorner = playerCarCorners.getRearRightCorner();

        graphics.fillOval((int)(TopRightCorner.x-10/2) , (int)(TopRightCorner.y-10/2), 10, 10);
        graphics.fillOval((int)(BottomRightCorner.x-10/2) , (int)(BottomRightCorner.y-10/2), 10, 10);
        graphics.fillOval((int)(TopLeftCorner.x-10/2) , (int)(TopLeftCorner.y-10/2), 10, 10);
        graphics.fillOval((int)(BottomLeftCorner.x-10/2) , (int)(BottomLeftCorner.y-10/2), 10, 10);

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
