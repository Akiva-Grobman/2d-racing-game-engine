package com.k300.graphics;

import com.k300.utils.Utils;
import com.k300.utils.configarations.Config;

import java.awt.image.BufferedImage;

public class Zoom {

    public static BufferedImage getZoomedImage(double x, double y, double widthFactor, double heightFactor ,BufferedImage image) {
        double widthFactorFromConfig = widthFactor + Config.getZoomInWidthFactor();
        double heightFactorFromConfig = heightFactor + Config.getZoomInHeightFactor();
        double statingZoomX = x - (widthFactorFromConfig / 2);
        double startingZoomY = y - (heightFactorFromConfig / 2);
        statingZoomX = clamp(statingZoomX, widthFactorFromConfig, image.getWidth());
        startingZoomY = clamp(startingZoomY, heightFactorFromConfig, image.getHeight());
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
