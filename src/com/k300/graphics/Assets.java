package com.k300.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Hashtable;
import static com.k300.utils.Utils.loadImage;
import static com.k300.utils.Utils.resizeImage;

public class Assets {

    public static final String K_300_LOGO_KEY = "background";
    public static final String TRACK_KEY = "Track";
    public static final String INSIDE_MARGIN_KEY = "insideMargin";
    public static final String OUTSIDE_MARGIN_KEY = "OutsideMargin";
    public static final String OBSTACLE_KEY = "obstacle";
    public static final String RED_CAR_KEY = "car_red";
    public static final String BLUE_CAR_KEY = "car_blue";
    public static final String YELLOW_CAR_KEY = "car_yellow";
    public static final String PLAY_BUTTON_KEY = "PlayButton";
    public static final String PLAY_BUTTON_HOVER_KEY = "PlayButtonHover";
    public static final String EXIT_BUTTON_KEY = "ExitButton";
    public static final String EXIT_BUTTON_HOVER_KEY = "ExitButtonHover";
    private static final String CAR_DIR = "cars/";
    private static final String BUTTON_DIR = "button-images/";
    private static final String TRACK_DIR = "track-images/";
    private static final String TYPE_PNG = ".png";
    private static final String TYPE_JPG = ".jpg";
    private volatile static Assets singletonInstance;
    private final Hashtable<String, BufferedImage> images;

    public static BufferedImage getImage(String imageKey) {
        if(singletonInstance == null) {
            singletonInstance = new Assets();
        }
        return singletonInstance.images.get(imageKey);
    }

    private Assets() {
        images = new Hashtable<>();
        images.put(K_300_LOGO_KEY, loadImage(K_300_LOGO_KEY, TYPE_JPG));
        images.put(TRACK_KEY, loadImage(TRACK_DIR + TRACK_KEY, TYPE_JPG));
        addTrackLayers(INSIDE_MARGIN_KEY);
        addTrackLayers(OUTSIDE_MARGIN_KEY);
        addTrackLayers(OBSTACLE_KEY);
        addCar(RED_CAR_KEY);
        addCar(BLUE_CAR_KEY);
        addCar(YELLOW_CAR_KEY);
        addButton(PLAY_BUTTON_KEY);
        addButton(PLAY_BUTTON_HOVER_KEY);
        addButton(EXIT_BUTTON_KEY);
        addButton(EXIT_BUTTON_HOVER_KEY);
    }

    private void addTrackLayers(String key) {
        images.put(key, loadImage(TRACK_DIR + key, TYPE_PNG));
    }

    private void addCar(String key) {
        // these two variables represent the width relative to the height
        final int widthWight = 7;
        final int heightWight = 5;
        // this will determine the size of the car
        double multiplier = 2.5;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int carImageWidth = screenSize.width / (int)Math.floor(heightWight * multiplier);
        int carImageHeight = screenSize.height / (int)Math.floor(widthWight * multiplier);
        images.put(key, resizeImage(loadImage(CAR_DIR + key, TYPE_PNG), carImageWidth, carImageHeight));
    }

    private void addButton(String key) {
        images.put(key, loadImage(Assets.BUTTON_DIR + key, Assets.TYPE_PNG));
    }

}
