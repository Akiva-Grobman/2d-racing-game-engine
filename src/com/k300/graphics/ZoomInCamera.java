package com.k300.graphics;

import com.k300.cars.Car;
import com.k300.cars.player_car.PlayerCar;
import com.k300.utils.math.Converter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import static com.k300.utils.Utils.drawImageInCenter;
import static com.k300.utils.Utils.drawStringInCenter;

public class ZoomInCamera {


    public final static double WIDTH = Converter.FHD_SCREEN_WIDTH / 1.3;
    public final static double HEIGHT = Converter.FHD_SCREEN_HEIGHT / 1.3;
    private final Car[] cars;
    private final Graphics2D zoomGraphics;
    private final AlphaComposite originalComposite;
    private final Graphics windowGraphics;
    private double zoomX;
    private double zoomY;
    private BufferedImage zoomWindow;
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
        zoomX = playerXPosition - (WIDTH / 2);
        zoomY = playerYPosition - (HEIGHT / 2);
        zoomX = clamp(zoomX, WIDTH, Converter.FHD_SCREEN_WIDTH);
        zoomY = clamp(zoomY, HEIGHT, Converter.FHD_SCREEN_HEIGHT);
    }

    public void render() {
        drawTrackOnZoomWindow();
        drawZoomedView();
        drawImageInCenter(Converter.FHD_SCREEN_WIDTH / 10,
                Converter.FHD_SCREEN_HEIGHT / 3 * 2,
                zoomWindow.getWidth() / 4,
                zoomWindow.getHeight() / 4,
                windowGraphics,
                getMiniTrack());
    }

    private BufferedImage getMiniTrack() {
        BufferedImage track = Assets.getImage(Assets.TRACK_KEY);
        BufferedImage miniTrack = new BufferedImage(track.getWidth(), track.getHeight(), track.getType());
        Graphics miniGraphics = miniTrack.getGraphics();
        miniTrack.getGraphics().drawImage(track, 0, 0, null);
        for (Car car: cars) {
            int x = (int) car.position.x;
            int y = (int) car.position.y;
            int r = car.carImage.getHeight() / 2;
            Color color = getColor(car.carColor.split("_")[1]);
            miniGraphics.setColor(color);
            miniGraphics.fillOval(x, y, r * 2, r * 2);
        }
        miniGraphics.dispose();
        return miniTrack;
    }

    private Color getColor(String name) {
        if(name.equals("blue")) {
            return Color.blue;
        }
        //todo
        return Color.black;
    }

    private void drawTrackOnZoomWindow() {
        drawFullTrack(zoomGraphics);
        zoomGraphics.setColor(Color.white);
        zoomGraphics.setFont(new Font("TimesRoman", Font.BOLD, 120));
        String roundsMsg = "ROUNDS: " + getPlayersCar().rounds;
        drawStringInCenter(0, 0, Converter.FHD_SCREEN_WIDTH, Converter.FHD_SCREEN_HEIGHT, zoomGraphics, roundsMsg);
    }

    private void drawZoomedView() {
        zoomWindow = zoomWindow.getSubimage((int) zoomX, (int) zoomY, (int) ZoomInCamera.WIDTH, (int) ZoomInCamera.HEIGHT);
        windowGraphics.drawImage(zoomWindow,
                0,
                0,
                Converter.FHD_SCREEN_WIDTH,
                Converter.FHD_SCREEN_HEIGHT,
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

    private double clamp(double coordinate, double zoomDimension, int screenDimension) {
        if(zoomDimension + coordinate >= screenDimension) {
            return screenDimension - zoomDimension;
        } else if(coordinate < 0){
            return 0;
        }
        return coordinate;
    }

}
