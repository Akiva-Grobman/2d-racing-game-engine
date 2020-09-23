package com.k300.cars;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public abstract class Car {

    protected double x;
    protected double y;
    protected double angle;
    protected BufferedImage carImage;

    public abstract void tick();

    public final void render(Graphics2D graphics) {
        if(carImage == null) {
            return;
        }
        AffineTransform carAngle = AffineTransform.getTranslateInstance(x, y);
        carAngle.rotate(Math.toDegrees(angle), carImage.getWidth() / 2f, carImage.getHeight() / 2f);
        graphics.drawImage(carImage, carAngle, null);
    }

}
