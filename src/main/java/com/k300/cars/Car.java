package com.k300.cars;

import com.k300.cars.player_car.PlayerCar;
import com.k300.cars.player_car.PlayerCarCorners;
import com.k300.graphics.Assets;
import com.k300.utils.Point;
import com.k300.utils.configarations.Config;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public abstract class Car {

    public final Point position;
    public BufferedImage carImage;
    public String carColor;
    public double angle;
    public int rounds;

    public Car(String carColor, Point startingPosition) {
        carImage = Assets.getImage(carColor);
        position = startingPosition;
        angle = 0;
        this.carColor = carColor;
    }

    public abstract void tick();

    public final void render(Graphics2D graphics) {
        if(carImage == null) {
            if(carColor != null) {
                updateColor(carColor);
            }
            return;
        }
        AffineTransform carAngle = AffineTransform.getTranslateInstance(position.x - carImage.getWidth() / 2f, position.y -  carImage.getHeight() / 2f);
        carAngle.rotate(Math.toRadians(-angle), carImage.getWidth() / 2f, carImage.getHeight() / 2f); //need Minus because Java is multiplier minus
        graphics.drawImage(carImage, carAngle, null);
        if(!Config.isUsingZoom() && this instanceof PlayerCar) {
            graphics.setColor(Color.white);
            graphics.setFont(new Font("Minecraft", Font.BOLD, 120));
            graphics.drawString("ROUNDS: " + rounds, 625, 570);
            if(Config.isInDevMode()) {
                graphics.drawString("Angle: " + angle, 800, 500);
                graphics.drawString("X: " + position.x, 800, 600);
                graphics.drawString("Y: " + position.y, 800, 700);
                graphics.fillRect(960, 0, 10, 2000); //start line
                graphics.fillOval((int) (position.x - 25 / 2), (int) (position.y - 25 / 2), 25, 25);
                final PlayerCarCorners corners = ((PlayerCar) this).playerCarCorners;
                Point TopRightCorner = corners.getFrontLeftCorner();
                Point BottomRightCorner = corners.getFrontRightCorner();
                Point TopLeftCorner = corners.getRearLeftCorner();
                Point BottomLeftCorner = corners.getRearRightCorner();
                graphics.fillOval((int) (TopRightCorner.x - 10 / 2), (int) (TopRightCorner.y - 10 / 2), 10, 10);
                graphics.fillOval((int) (BottomRightCorner.x - 10 / 2), (int) (BottomRightCorner.y - 10 / 2), 10, 10);
                graphics.fillOval((int) (TopLeftCorner.x - 10 / 2), (int) (TopLeftCorner.y - 10 / 2), 10, 10);
                graphics.fillOval((int) (BottomLeftCorner.x - 10 / 2), (int) (BottomLeftCorner.y - 10 / 2), 10, 10);
            }
        }
    }

    public void updateColor(String color) {
        if(!color.contains("_")) {
            carColor = getColor(color);
        } else {
            carColor = color;
        }
        carImage = Assets.getImage(carColor);
    }

    private String getColor(String color) {
        color = color.toLowerCase();
        if(color.contains("blue")) {
            return Assets.BLUE_CAR_KEY;
        } else if(color.contains("red")) {
            return Assets.RED_CAR_KEY;
        }
        return Assets.YELLOW_CAR_KEY;
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
