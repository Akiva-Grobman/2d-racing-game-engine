package com.akivaGrobman.cars;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public abstract class Car {

    protected float x;
    protected float y;
    protected float angle;
    protected BufferedImage carImage;

    public abstract void tick();

    public final void render(Graphics2D graphics) {
        AffineTransform carAngle = AffineTransform.getTranslateInstance(x, y);
        carAngle.rotate(Math.toDegrees(angle), carImage.getWidth() / 2f, carImage.getHeight() / 2f);
        graphics.drawImage(carImage, carAngle, null);
    }

}
