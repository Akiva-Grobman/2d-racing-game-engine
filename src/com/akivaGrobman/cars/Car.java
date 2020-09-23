package com.akivaGrobman.cars;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public abstract class Car {

    protected float x;
    protected float y;
    protected AffineTransform carAngle;
    protected BufferedImage carImage;

    public abstract void tick();

    public final void render(Graphics2D graphics) {
        graphics.drawImage(carImage, carAngle, null);
    }

}
