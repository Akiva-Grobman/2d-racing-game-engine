package com.k300.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Hashtable;
import static com.k300.utils.Utils.loadImage;
import static com.k300.utils.Utils.resizeImage;

public class Assets {

    public static final String TRACK_KEY = "Track";
    public static final String PLAY_BUTTON_KEY = "PlayButton";
    public static final String RED_CAR_KEY = "car_red";
    public static final String BLUE_CAR_KEY = "car_blue";
    public static final String YELLOW_CAR_KEY = "car_yellow";
    private volatile static Assets singletonInstance;
    private final Hashtable<String, BufferedImage> images;
    // these two variables represent the width relative to the height
    private static final int WIDTH_WEIGHT = 5;
    private static final int HEIGHT_WEIGHT = 3;
    // this will determine the size of the car(the greater the value the smaller the car)
    private static final double SCALE = 2;
    
    public static BufferedImage getImage(String imageKey) {
        if(singletonInstance == null) {
            singletonInstance = new Assets();
        }
        return singletonInstance.images.get(imageKey);
    }

    private Assets() {
        images = new Hashtable<>();
        images.put(TRACK_KEY, loadImage(TRACK_KEY, ".jpg"));
        images.put(PLAY_BUTTON_KEY, loadImage(PLAY_BUTTON_KEY, ".png"));
        int carImageWidth = getCarImageWidth();
        int carImageHeight = getCarImageHeight();
        images.put(RED_CAR_KEY, resizeImage(loadImage(RED_CAR_KEY, ".png"), carImageWidth, carImageHeight));
        images.put(BLUE_CAR_KEY, resizeImage(loadImage(BLUE_CAR_KEY, ".png"), carImageWidth, carImageHeight));
        images.put(YELLOW_CAR_KEY, resizeImage(loadImage(YELLOW_CAR_KEY, ".png"), carImageWidth, carImageHeight));
    }

    private int getCarImageWidth() {
        return Toolkit.getDefaultToolkit().getScreenSize().width / (int)Math.floor(WIDTH_WEIGHT * SCALE);
    }

    private int getCarImageHeight() {
        return Toolkit.getDefaultToolkit().getScreenSize().height / (int)Math.floor(HEIGHT_WEIGHT * SCALE);
    }

}
