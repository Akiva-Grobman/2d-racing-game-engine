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

    public static void drawImageInCenter(double x, double y, double width, double height, Graphics graphics, BufferedImage image) {
        int newX = (int) ((x + width / 2) - image.getWidth() / 2);
        int newY = (int) ((y + height / 2) - image.getHeight() / 2);
        graphics.drawImage(image, newX, newY, null);
    }

}
