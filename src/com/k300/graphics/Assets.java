package com.k300.graphics;

import java.awt.image.BufferedImage;
import java.util.Hashtable;
import static com.k300.utils.Utils.loadImage;

public class Assets {

    public static final String TRACK_KEY = "Track";
    public static final String RED_CAR_KEY = "car_red";
    public static final String BLUE_CAR_KEY = "car_blue";
    public static final String YELLOW_CAR_KEY = "car_yellow";
    public static final String PLAY_BUTTON_KEY = "PlayButton";
    private volatile static Assets singletonInstance;
    private final Hashtable<String, BufferedImage> images;

    private Assets() {
        images = new Hashtable<>();
        images.put(TRACK_KEY, loadImage(TRACK_KEY, ".jpg"));
        images.put(RED_CAR_KEY, loadImage(RED_CAR_KEY, ".png"));
        images.put(BLUE_CAR_KEY, loadImage(BLUE_CAR_KEY, ".png"));
        images.put(YELLOW_CAR_KEY, loadImage(YELLOW_CAR_KEY, ".png"));
        images.put(PLAY_BUTTON_KEY, loadImage(PLAY_BUTTON_KEY, ".png"));
    }

    public static BufferedImage getImage(String imageKey) {
        if(singletonInstance == null) {
            singletonInstance = new Assets();
        }
        return singletonInstance.images.get(imageKey);
    }

}
