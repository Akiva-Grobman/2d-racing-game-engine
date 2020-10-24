package com.k300.graphics;

import com.k300.utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Zoom {

    public static BufferedImage getZoomedImage(double x, double y, double width, double height ,BufferedImage image) {
        double statingZoomX = x - (width / 2);
        double startingZoomY = y - (height / 2);
        statingZoomX = clamp(statingZoomX, width, image.getWidth());
        startingZoomY = clamp(startingZoomY, height, image.getHeight());

        BufferedImage croppedImage = image.getSubimage((int) statingZoomX, (int) startingZoomY, (int)width, (int)height);

        return Utils.resizeImage(croppedImage, image.getWidth(), image.getHeight());
    }

    private static double clamp(double coordinate, double zoomDimension, int screenDimension) {
        if(zoomDimension + coordinate >= screenDimension) {
            return screenDimension - zoomDimension;
        } else if(coordinate < 0){
            return 0;
        }
        return coordinate;
    }

}
