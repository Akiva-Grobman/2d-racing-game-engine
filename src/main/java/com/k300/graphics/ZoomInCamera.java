package com.k300.graphics;

import com.k300.cars.Car;
import com.k300.cars.player_car.PlayerCar;
import com.k300.utils.configarations.Config;
import com.k300.utils.math.Converter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import static com.k300.graphics.Zoom.getZoomedImage;
import static com.k300.utils.Utils.drawImageInCenter;

public class ZoomInCamera {

    private final Car[] cars;
    private final Graphics2D zoomGraphics;
    private final Graphics windowGraphics;
    private final BufferedImage zoomWindow;
    private int playerXPosition;
    private int playerYPosition;

    public ZoomInCamera(Graphics graphics, Car[] cars) {
        windowGraphics = graphics;
        this.cars = cars;
        zoomWindow = new BufferedImage(Converter.FHD_SCREEN_WIDTH,
                Converter.FHD_SCREEN_HEIGHT,
                Assets.getImage(Assets.TRACK_KEY).getType());
        zoomGraphics = zoomWindow.createGraphics();
        setCarCoordinates();
    }

    public void render() {
        drawFullTrackInBackground();
        drawZoomedView();
        drawRoundsOverZoomWindow();
    }

    private void drawFullTrackInBackground() {
        drawFullTrackWithCars(windowGraphics, getShadowedTrackImage());
    }

    private BufferedImage getShadowedTrackImage() {
        BufferedImage originalTrack = Assets.getImage(Assets.TRACK_KEY);
        BufferedImage trackCopy = new BufferedImage(originalTrack.getWidth(),
                originalTrack.getHeight(),
                originalTrack.getType());
        Graphics2D trackGraphics = trackCopy.createGraphics();
        trackGraphics.drawImage(originalTrack,
                0,
                0,
                originalTrack.getWidth(),
                originalTrack.getHeight(),
                null);
        float alpha = 0.8f;
        trackGraphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        trackGraphics.drawImage(Assets.getImage(Assets.INIT_IMAGE_KEY),
                0,
                0,
                trackCopy.getWidth(),
                trackCopy.getHeight(),
                null);
        return trackCopy;
    }

    private void drawFullTrackWithCars(Graphics graphics, BufferedImage trackImage) {
        graphics.drawImage(trackImage, 0, 0,null);
        for (Car car: cars) {
            car.render((Graphics2D) graphics);
        }
    }

    private void drawZoomedView() {
        drawFullTrackWithCars(zoomGraphics, Assets.getImage(Assets.TRACK_KEY));
        BufferedImage zoomedImage = getZoomedImage(
                playerXPosition,
                playerYPosition,
                Config.getZoomInFactor(),
                zoomWindow
        );
        drawImageInCenter(0, 0, Converter.FHD_SCREEN_WIDTH, Converter.FHD_SCREEN_HEIGHT, windowGraphics, zoomedImage);
    }

    private void drawRoundsOverZoomWindow() {
        windowGraphics.setFont(new Font("Minecraft", Font.BOLD, 120));
        windowGraphics.setColor(Color.white);
        String roundsMsg = "ROUNDS: " + getPlayersCar().rounds;
        int msgWidth = windowGraphics.getFontMetrics().stringWidth(roundsMsg);
        int msgHeight = windowGraphics.getFontMetrics().getAscent();
        windowGraphics.drawString(roundsMsg, (Converter.FHD_SCREEN_WIDTH - msgWidth) / 2, (Converter.FHD_SCREEN_HEIGHT - zoomWindow.getHeight() / 2) / 2 - msgHeight);
    }

    private void setCarCoordinates() {
        PlayerCar car = getPlayersCar();
        playerXPosition = (int) car.getX();
        playerYPosition = (int) car.getY();
    }

    private PlayerCar getPlayersCar() {
        for (Car car: cars) {
            if(car instanceof PlayerCar) {
                return (PlayerCar) car;
            }
        }
        throw new Error("No PlayerCar found in: " + Arrays.toString(cars));
    }

}
