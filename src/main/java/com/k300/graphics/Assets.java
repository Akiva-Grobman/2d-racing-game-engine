package com.k300.graphics;

import com.k300.utils.math.Converter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import static com.k300.utils.Utils.loadImage;
import static com.k300.utils.Utils.resizeImage;

public class Assets {

    public static final String K_300_INTRO_KEY = "intro";
    public static final String FILTER_KEY = "filter";
    public static final String INIT_IMAGE_KEY = "InitImage";

    public static final String TRACK_KEY = "Track";
    public static final String ROAD_KEY = "Road";
    public static final String TRACK_MIDDLE_KEY = "InsideMargin";
    public static final String TRACK_MIDDLE_STROKE_KEY = "InsideMarginStroke";
    public static final String TRACK_MIDDLE_FILL_KEY = "InsideMarginFill";
    public static final String TRACK_OUTSIDE_KEY = "OutsideMargin";
    public static final String OBSTACLE_KEY = "obstacle";

    public static final String RED_CAR_KEY = "car_red";
    public static final String BLUE_CAR_KEY = "car_blue";
    public static final String YELLOW_CAR_KEY = "car_yellow";
    public static final String LOADING_CAR_KEY = "loadingCar";

    public static final String BUTTON_KEY = "Button";
    public static final String BUTTON_HOVER_KEY = "ButtonHover";

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

    public static String getCarKeyByValue(String color) {
        String[] carColors = {BLUE_CAR_KEY, RED_CAR_KEY, YELLOW_CAR_KEY};
        color = color.toLowerCase();
        for (String carColor : carColors) {
            if (carColor.contains(color)) {
                return carColor;
            }
        }
        throw new Error(color + " Color is not supported");
    }

    private Assets() {
        images = new Hashtable<>();
        images.put(K_300_INTRO_KEY, loadImage(K_300_INTRO_KEY, TYPE_JPG));
        images.put(FILTER_KEY, loadImage(FILTER_KEY, TYPE_PNG));
        images.put(OBSTACLE_KEY, loadImage(TRACK_DIR + OBSTACLE_KEY, TYPE_PNG));
        addTrackImages();
        addCarImage(RED_CAR_KEY);
        addCarImage(BLUE_CAR_KEY);
        addCarImage(YELLOW_CAR_KEY);
        addCarImage(LOADING_CAR_KEY);

        addButtonImage(BUTTON_KEY);
        addButtonImage(BUTTON_HOVER_KEY);
    }

    private void addTrackImages() {
        final BufferedImage track = loadImage(TRACK_DIR + TRACK_KEY, TYPE_JPG);
        Graphics trackGraphics = track.getGraphics();
        BufferedImage road = getTrackLayer(ROAD_KEY);
        trackGraphics.drawImage(road, (1920 - road.getWidth()) / 2, (1080 - road.getHeight()) / 2, road.getWidth(), road.getHeight(), null);

        BufferedImage trackMiddle = getTrackLayer(TRACK_MIDDLE_KEY);
        trackGraphics.drawImage(trackMiddle, (1920 - trackMiddle.getWidth()) / 2, (1080 - trackMiddle.getHeight()) / 2, trackMiddle.getWidth(), trackMiddle.getHeight(), null);

        trackGraphics.drawImage(getTrackLayer(TRACK_OUTSIDE_KEY), 0, 0, track.getWidth(), track.getHeight(), null);

        trackGraphics.drawImage(images.get(FILTER_KEY), 0, 0, Converter.FHD_SCREEN_WIDTH, Converter.FHD_SCREEN_HEIGHT, null);
        trackGraphics.dispose();

        images.put(INIT_IMAGE_KEY, loadImage(TRACK_DIR + INIT_IMAGE_KEY, TYPE_JPG));
        images.put(TRACK_KEY, track);
        images.put(ROAD_KEY, road);
        images.put(TRACK_MIDDLE_KEY, trackMiddle);
        images.put(TRACK_MIDDLE_FILL_KEY, loadImage(TRACK_DIR + TRACK_MIDDLE_FILL_KEY, TYPE_PNG));
        images.put(TRACK_MIDDLE_STROKE_KEY, loadImage(TRACK_DIR + TRACK_MIDDLE_STROKE_KEY, TYPE_PNG));
        images.put(TRACK_OUTSIDE_KEY, loadImage(TRACK_DIR + TRACK_OUTSIDE_KEY, TYPE_PNG));
    }

    private BufferedImage getTrackLayer(String key){
        return loadImage(TRACK_DIR + key, TYPE_PNG);
    }

    private void addCarImage(String key) {
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

    private void addButtonImage(String key) {
        images.put(key, loadImage(Assets.BUTTON_DIR + key, Assets.TYPE_PNG));
    }

}
