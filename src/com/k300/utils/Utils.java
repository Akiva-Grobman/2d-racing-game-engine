package com.k300.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Utils {

    public static BufferedImage loadImage(String imageName, String type) {
        try {
            return ImageIO.read(Utils.class.getResource("/" + imageName + type));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public static BufferedImage resizeImage(BufferedImage originalImage, int newWidth, int newHeight) {
        BufferedImage newImage = new BufferedImage(newWidth, newHeight, originalImage.getType());
        Graphics graphics = newImage.getGraphics();
        graphics.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
        return newImage;
    }

    public static boolean isInBoundsOf(double angle, int lowerBound, int upperBound) {
        return angle >= lowerBound && angle <= upperBound;
    }

}
