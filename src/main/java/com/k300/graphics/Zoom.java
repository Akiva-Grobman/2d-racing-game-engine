package com.k300.graphics;

import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;

import static com.k300.utils.Utils.resizeImage;

public class Zoom {

    public static BufferedImage getZoomedImage(double x, double y, double zoomFactor, BufferedImage image) {
        double zoomedWidth = image.getWidth() / zoomFactor;
        double zoomedHeight = image.getHeight() / zoomFactor;
        double statingZoomX = x - (zoomedWidth / 2);
        double startingZoomY = y - (zoomedHeight / 2);
        statingZoomX = clamp(statingZoomX, zoomedWidth, image.getWidth());
        startingZoomY = clamp(startingZoomY, zoomedHeight, image.getHeight());

        try {
            BufferedImage croppedImage = image.getSubimage(
                    (int) statingZoomX,
                    (int) startingZoomY,
                    (int) zoomedWidth,
                    (int) zoomedHeight
            );
            return resizeImage(croppedImage, croppedImage.getWidth(), croppedImage.getHeight());
        } catch (RasterFormatException err) {
            System.out.println("Too much zoom factor " + zoomFactor);
        }
        return resizeImage(image, image.getWidth(), image.getHeight());
    }

    private static double clamp(double coordinate, double zoomDimension, int maxDimension) {
        if(zoomDimension + coordinate >= maxDimension) {
            return maxDimension - zoomDimension;
        } else if(coordinate < 0){
            return 0;
        }
        return coordinate;
    }

}
