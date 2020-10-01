package com.k300.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Hashtable;
import static com.k300.utils.Utils.loadImage;
import static com.k300.utils.Utils.resizeImage;

public class Assets {

    public static final String K_300_LOGO_KEY = "background";
    public static final String TRACK_KEY = "Track";
    public static final String RED_CAR_KEY = "car_red";
    public static final String BLUE_CAR_KEY = "car_blue";
    public static final String YELLOW_CAR_KEY = "car_yellow";
    public static final String PLAY_BUTTON_KEY = "PlayButton";
    public static final String PLAY_BUTTON_HOVER_KEY = "PlayButtonHover";
    public static final String EXIT_BUTTON_KEY = "ExitButton";
    public static final String EXIT_BUTTON_HOVER_KEY = "ExitButtonHover";
    private volatile static Assets singletonInstance;
    private final Hashtable<String, BufferedImage> images;

    private Assets() {
        images = new Hashtable<>();
        // these two variables represent the width relative to the height
        final int widthWight = 3;
        final int heightWight = 5;
        // this will determine the size of the car
        double multiplier = 2;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int carImageWidth = screenSize.width / (int)Math.floor(heightWight * multiplier);
        int carImageHeight = screenSize.height / (int)Math.floor(widthWight * multiplier);
        images.put(TRACK_KEY, loadImage(TRACK_KEY, ".jpg"));
        images.put(K_300_LOGO_KEY, loadImage(K_300_LOGO_KEY, ".jpg"));
        images.put(RED_CAR_KEY, resizeImage(loadImage(RED_CAR_KEY, ".png"), carImageWidth, carImageHeight));
        images.put(BLUE_CAR_KEY, resizeImage(loadImage(BLUE_CAR_KEY, ".png"), carImageWidth, carImageHeight));
        images.put(YELLOW_CAR_KEY, resizeImage(loadImage(YELLOW_CAR_KEY, ".png"), carImageWidth, carImageHeight));
        images.put(PLAY_BUTTON_KEY, loadImage(PLAY_BUTTON_KEY, ".png"));
        images.put(PLAY_BUTTON_HOVER_KEY, loadImage(PLAY_BUTTON_HOVER_KEY, ".png"));
        images.put(EXIT_BUTTON_KEY, loadImage(EXIT_BUTTON_KEY, ".png"));
        images.put(EXIT_BUTTON_HOVER_KEY, loadImage(EXIT_BUTTON_HOVER_KEY, ".png"));
    }

    public static BufferedImage getImage(String imageKey) {
        if(singletonInstance == null) {
            singletonInstance = new Assets();
        }
        return singletonInstance.images.get(imageKey);
    }

}
