package com.k300.display;

import com.k300.graphics.Assets;
import com.k300.graphics.ZoomInCamera;
import com.k300.utils.math.Converter;

import java.awt.*;
import java.awt.image.BufferedImage;

import static com.k300.graphics.Zoom.getZoomedImage;
import static com.k300.utils.Utils.*;

public class ZoomExample {

    private final int leftEdge;

    public ZoomExample(int xPositionOfEndOfDevModeText) {
        leftEdge = xPositionOfEndOfDevModeText;
    }

    public void render(Graphics graphics) {
        BufferedImage zoomExample = getZoomExample();
        drawImageInCenter(leftEdge,
                0,
                Converter.FHD_SCREEN_WIDTH - leftEdge,
                Converter.FHD_SCREEN_HEIGHT + Converter.FHD_SCREEN_HEIGHT / 20,
                graphics,
                zoomExample);
    }

    private BufferedImage getZoomExample() {
        BufferedImage track = clone(Assets.getImage(Assets.TRACK_KEY));
        BufferedImage car = clone(Assets.getImage(Assets.BLUE_CAR_KEY));
        Graphics trackGraphics = track.getGraphics();
        int heightClamp = track.getHeight() / 4 * 3 + track.getHeight() / 20;
        drawImageInCenter(0, heightClamp, track.getWidth(), track.getHeight() - heightClamp, trackGraphics, car);
        BufferedImage zoomExample = getZoomedImage(track.getWidth() / 2f,
                (track.getHeight() + heightClamp) / 2f,
                ZoomInCamera.WIDTH,
                ZoomInCamera.HEIGHT,
                track);
        return resizeImage(zoomExample, Converter.FHD_SCREEN_WIDTH / 2, Converter.FHD_SCREEN_HEIGHT / 2);
    }

    private BufferedImage clone(BufferedImage originalImage) {
        BufferedImage clone = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), originalImage.getType());
        clone.getGraphics().drawImage(originalImage, 0, 0, originalImage.getWidth(), originalImage.getHeight(), null);
        return clone;
    }

}
