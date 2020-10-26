package com.k300.display;

import com.k300.graphics.Assets;
import com.k300.states.gameStates.GameState;
import com.k300.utils.configarations.Config;
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
        drawShadowOverTrackImage(track, trackGraphics);
        trackGraphics.drawImage(
                car,
                (int) (GameState.startingPosition.x - car.getWidth() / 2f),
                (int) (GameState.startingPosition.y - car.getHeight() / 2f),
                null
        );
        BufferedImage zoomExample = getZoomedImage(
                GameState.startingPosition.x,
                GameState.startingPosition.y,
                Config.getZoomInFactor(),
                track
        );
        return resizeImage(zoomExample, Converter.FHD_SCREEN_WIDTH / 2, Converter.FHD_SCREEN_HEIGHT / 2);
    }

    private void drawShadowOverTrackImage(BufferedImage track, Graphics trackGraphics) {
        Composite originalComposite = ((Graphics2D) trackGraphics).getComposite();
        float alpha = 0.5f;
        ((Graphics2D) trackGraphics).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        trackGraphics.drawImage(Assets.getImage(Assets.INIT_IMAGE_KEY),
                0,
                0,
                track.getWidth(),
                track.getHeight(),
                null);
        ((Graphics2D) trackGraphics).setComposite(originalComposite);
    }

    private BufferedImage clone(BufferedImage originalImage) {
        BufferedImage clone = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), originalImage.getType());
        clone.getGraphics().drawImage(originalImage, 0, 0, originalImage.getWidth(), originalImage.getHeight(), null);
        return clone;
    }

}
