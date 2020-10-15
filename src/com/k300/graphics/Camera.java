package com.k300.graphics;

import com.k300.cars.Car;
import com.k300.cars.player_car.PlayerCar;
import com.k300.utils.math.Converter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class Camera {


    public final static int WIDTH = Converter.FHD_SCREEN_WIDTH / 2;
    public final static int HEIGHT = Converter.FHD_SCREEN_HEIGHT / 2;
    private int zoomX;
    private int zoomY;

    public static BufferedImage getZoomedView() {
        return new BufferedImage(Converter.FHD_SCREEN_WIDTH, Converter.FHD_SCREEN_HEIGHT, Assets.getImage(Assets.TRACK_KEY).getType());
    }

    // this image is used to get the car dimensions. all cars have the same dimensions so color doesn't matter
    private static final BufferedImage carImage = Assets.getImage(Assets.BLUE_CAR_KEY);
    private final Car[] cars;
    private BufferedImage zoomWindow;
    private final Graphics2D zoomGraphics;
    private final AlphaComposite originalComposite;
    private final Graphics windowGraphics;
    private int playerXPosition;
    private int playerYPosition;

    public Camera(Graphics graphics, Car[] cars) {
        windowGraphics = graphics;
        this.cars = cars;
        zoomWindow = new BufferedImage(Converter.FHD_SCREEN_WIDTH,
                Converter.FHD_SCREEN_HEIGHT,
                Assets.getImage(Assets.TRACK_KEY).getType());
        zoomGraphics = zoomWindow.createGraphics();
        originalComposite = (AlphaComposite) zoomGraphics.getComposite();
        setCarCoordinates();
        zoomX = (playerXPosition + carImage.getWidth() / 2) - (WIDTH / 2);
        zoomY = (playerYPosition + carImage.getHeight() / 2) - (HEIGHT / 2);
        zoomX = clamp(zoomX, WIDTH, Converter.FHD_SCREEN_WIDTH);
        zoomY = clamp(zoomY, HEIGHT, Converter.FHD_SCREEN_HEIGHT);
    }

    public void render() {
        drawFullTrackInBackground();
        drawTrackOnZoomWindow();
        drawZoomedView();
        addRoundsOverZoomDisplay();
    }

    private void addRoundsOverZoomDisplay() {
        windowGraphics.setColor(Color.white);
        windowGraphics.setFont(new Font("TimesRoman", Font.BOLD, 120));
        String roundsMsg = "ROUNDS: " + getPlayersCar().rounds;
        int msgWidth = windowGraphics.getFontMetrics().stringWidth(roundsMsg);
        int msgHeight = windowGraphics.getFontMetrics().getAscent();
        windowGraphics.drawString(roundsMsg, Converter.FHD_SCREEN_WIDTH / 2 - msgWidth / 2, (Converter.FHD_SCREEN_HEIGHT - zoomWindow.getHeight()) / 2 - msgHeight);
    }

    private void drawTrackOnZoomWindow() {
        drawFullTrack(zoomGraphics);
    }

    private void drawZoomedView() {
        zoomWindow = zoomWindow.getSubimage(zoomX, zoomY, Camera.WIDTH, Camera.HEIGHT);
        windowGraphics.drawImage(zoomWindow,
                (Converter.FHD_SCREEN_WIDTH - zoomWindow.getWidth()) / 2,
                (Converter.FHD_SCREEN_HEIGHT - zoomWindow.getHeight()) / 2,
                zoomWindow.getWidth(),
                zoomWindow.getHeight(),
                null);
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

    private void drawFullTrackInBackground() {
        changeImageAlpha();
        drawFullTrack(windowGraphics);
        ((Graphics2D) windowGraphics).setComposite(originalComposite);
    }

    private void drawFullTrack(Graphics graphics) {
        graphics.drawImage(Assets.getImage(Assets.TRACK_KEY), 0, 0, zoomWindow.getWidth(), zoomWindow.getHeight(), null);
        for (Car car: cars) {
            car.render((Graphics2D) graphics);
        }
    }

    private void changeImageAlpha() {
        float alpha = 0.2f;
        ((Graphics2D) windowGraphics).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
    }

    private int clamp(int coordinate, int zoomDimension, int screenDimension) {
        if(zoomDimension + coordinate >= screenDimension) {
            return screenDimension - zoomDimension;
        } else if(coordinate < 0){
            return 0;
        }
        return coordinate;
    }

}
