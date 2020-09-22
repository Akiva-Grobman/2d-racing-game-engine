package com.akivaGrobman.graphics;

import java.awt.image.BufferedImage;
import java.util.Hashtable;
import static com.akivaGrobman.utils.Utils.loadImage;

public class Assets {

    public static final String TRACK_KEY = "Track";
    private volatile static Assets singletonInstance;
    private final Hashtable<String, BufferedImage> images;

    private Assets() {
        images = new Hashtable<>();
        images.put(TRACK_KEY, loadImage(TRACK_KEY, ".jpg"));
    }

    public static BufferedImage getImage(String imageKey) {
        if(singletonInstance == null) {
            singletonInstance = new Assets();
        }
        return singletonInstance.images.get(imageKey);
    }

}
