package com.k300.graphics;

import com.k300.utils.Utils;
import java.awt.image.BufferedImage;

public class Zoom {

    public static BufferedImage getZoomedImage(double x, double y, double widthFactor, double heightFactor ,BufferedImage image) {
        double statingZoomX = x - (widthFactor / 2);
        double startingZoomY = y - (heightFactor / 2);
        statingZoomX = clamp(statingZoomX, widthFactor, image.getWidth());
        startingZoomY = clamp(startingZoomY, heightFactor, image.getHeight());
        BufferedImage croppedImage = image.getSubimage((int) statingZoomX, (int) startingZoomY, (int) widthFactor, (int) heightFactor);
        return Utils.resizeImage(croppedImage, croppedImage.getWidth(), croppedImage.getHeight());
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
