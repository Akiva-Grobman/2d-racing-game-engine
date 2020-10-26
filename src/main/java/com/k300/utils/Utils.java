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

    public static void drawImageInCenter(int x, int y, int width, int height, Graphics graphics, BufferedImage image) {
        if(image.getWidth() > width || image.getHeight() > height) {
            graphics.drawImage(image, x, y, width, height, null);
        } else {
            int newX = (x + width / 2) - image.getWidth() / 2;
            int newY = (y + height / 2) - image.getHeight() / 2;
            graphics.drawImage(image, newX, newY, null);
        }
    }

    public static void drawStringInCenter(float x, float y, int width, int height, Graphics graphics, String string) {
        int stringWidth = graphics.getFontMetrics().stringWidth(string);
        int stringHeight = graphics.getFontMetrics().getDescent();
        if(stringWidth > width || stringHeight > height) {
            graphics.drawString(string, (int) x, (int) y);
            return;
        }
        int startingX = (int) (width / 2 - stringWidth / 2 + x);
        int startingY = (int) (height / 2 + stringHeight * 1.5 + y);
        graphics.drawString(string, startingX, startingY);
    }

}
