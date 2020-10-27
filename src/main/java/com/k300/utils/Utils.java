package com.k300.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/*
*      Purpose:
*           this is a class that will contain methods that are used multiple times throughout the program,
*           yet they don't fit in any existing classes.
*      Method list:
*           loadImage(imageName, type)
*                -> will load the specific image for the resources directory, will exit the program with code 1 if is unuseful in loading image
*           resizeImage(originalImage, newWidth, newHeight)
*                -> will return the original image in the dimensions of newWidth and newHeight
*           drawImageInCenter(x, y, width, height, graphics, image)
*                -> will draw image in the center of a rectangle that starts at position (x,y) and is of size width, height using the graphics objects.
*           drawStringInCenter(x, y, width, height, graphics, string)
*                -> will draw string in the center of a rectangle that starts at position (x,y) and is of size width, height using the graphics object.
*/

public abstract class Utils {

    // will load an image from resources
    public static BufferedImage loadImage(String imageName, String type) {
        try {
            // attempt to load image
            return ImageIO.read(Utils.class.getResource("/" + imageName + type));
        } catch (IOException e) {
            // print where and why the error happened
            e.printStackTrace();
            // will exit the program with code 1 (error)
            System.exit(1);
        }
        return null;
    }

    // will resize an image to desired width and height
    public static BufferedImage resizeImage(BufferedImage originalImage, int newWidth, int newHeight) {
        // create a new image with dimensions defined as the target size
        BufferedImage newImage = new BufferedImage(newWidth, newHeight, originalImage.getType());
        Graphics graphics = newImage.getGraphics();
        // using the new image graphics we draw the old one on it covering the whole area(assuming the original was bigger)
        graphics.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
        return newImage;
    }

    // will draw an image in the center of a given rectangle
    public static void drawImageInCenter(int x, int y, int width, int height, Graphics graphics, BufferedImage image) {
        // if the image is bigger than the given rectangle we draw the image in the size of the rectangle in the rectangle position
        if(image.getWidth() > width || image.getHeight() > height) {
            graphics.drawImage(image, x, y, width, height, null);
        } else {
            /*
            *   Necessary Info:
            *       when drawing an image we will tell the graphics object where to put the top left corner of our image.
            */
            // (width / 2) will give us x of the middle of the rectangle.
            // changing it to (x + width / 2) change it to be relative to the graphics display.
            // (image.getWidth / 2) will give us the x position of the middle of the image.
            // when we remove the x that represents the middle of the image from the x that represents the middle of the rectangle (relative to the graphics display),
            // we're finding the x position that will center (horizontally) the image.
            int newX = (x + width / 2) - image.getWidth() / 2;
            // the same logic in the x coordinate applies to the y coordinate
            // and that's how we find the y position that will center (vertically) the image.
            int newY = (y + height / 2) - image.getHeight() / 2;
            // draw the image based on new x,y.
            graphics.drawImage(image, newX, newY, null);
        }
    }

    // will draw a string in the center of a given rectangle
    public static void drawStringInCenter(float x, float y, int width, int height, Graphics graphics, String string) {
        // this will represent the width(in pixels) of a specific string using the current font and font size.
        int stringWidth = graphics.getFontMetrics().stringWidth(string);
        // this will represent the height(in pixels) of the font size (based on current font).
        int stringHeight = graphics.getFontMetrics().getDescent();
        // if the string is bigger than the given rectangle we draw it using the x,y of the rectangle
        if(stringWidth > width || stringHeight > height) {
            graphics.drawString(string, (int) x, (int) y);
            return;
        }
        /*
         *   Necessary Info:
         *       when drawing a string we will tell the graphics object where to put the top left corner of our image.
         */
        // (width / 2) will give us the x of the rectangle center (relative to the rectangle).
        // stringWidth / 2 will give us the x of the string center (relative to the string).
        // center(x) of rectangle minus center(x) of string plus x will give us the x coordinate that will center the string(relative to the graphics display).
        int startingX = (int) (width / 2 - stringWidth / 2 + x);
        // (height / 2) will give us the y of the rectangle center(relative to the rectangle).
        // (stringHeight * 1.5) will give us the y of the string center (relative to the string).
        // center(y) of the rectangle minus the center(y) of the string plus the y will give us the y coordinate that will center the string(relative to the graphics display).
        int startingY = (int) (height / 2 + stringHeight * 1.5 + y);
        // draw the sting based on the new x,y.
        graphics.drawString(string, startingX, startingY);
    }

}
