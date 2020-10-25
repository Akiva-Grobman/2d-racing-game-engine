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


    public final static int WIDTH = Converter.FHD_SCREEN_WIDTH / 2;
    public final static int HEIGHT = Converter.FHD_SCREEN_HEIGHT / 2;
    private final Car[] cars;
    private final Graphics2D zoomGraphics;
    private final AlphaComposite originalComposite;
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
        originalComposite = (AlphaComposite) zoomGraphics.getComposite();
        setCarCoordinates();
    }

    public void render() {
        drawFullTrackInBackground();
        drawZoomedView();
        drawRoundsOverZoomWindow();
    }

    private void drawFullTrackInBackground() {
        changeImageAlpha();
        drawFullTrackWithCars(windowGraphics);
        ((Graphics2D) windowGraphics).setComposite(originalComposite);
    }

    private void drawFullTrackWithCars(Graphics graphics) {
        graphics.drawImage(Assets.getImage(Assets.TRACK_KEY), 0, 0,null);
        for (Car car: cars) {
            car.render((Graphics2D) graphics);
        }
    }

    private void drawZoomedView() {
        drawFullTrackWithCars(zoomGraphics);
        BufferedImage zoomedImage = getZoomedImage(playerXPosition,
                playerYPosition,
                WIDTH,
                HEIGHT,
                zoomWindow);
        drawImageInCenter(0, 0, Converter.FHD_SCREEN_WIDTH, Converter.FHD_SCREEN_HEIGHT, windowGraphics, zoomedImage);
    }

    private void drawRoundsOverZoomWindow() {
        windowGraphics.setColor(Color.white);
        String roundsMsg = "ROUNDS: " + getPlayersCar().rounds;
        int msgWidth = windowGraphics.getFontMetrics().stringWidth(roundsMsg);
        int msgHeight = windowGraphics.getFontMetrics().getAscent();
        windowGraphics.drawString(roundsMsg, (Converter.FHD_SCREEN_WIDTH - msgWidth) / 2, (Converter.FHD_SCREEN_HEIGHT - zoomWindow.getHeight()) / 2 - msgHeight);
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

    private void changeImageAlpha() {
        float alpha = 0.2f;
        ((Graphics2D) windowGraphics).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
    }

}
