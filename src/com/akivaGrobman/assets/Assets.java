package com.akivaGrobman.assets;

import java.awt.image.BufferedImage;
import java.util.Hashtable;

public class Assets {

    private volatile static Assets singletonInstance;
    private Hashtable<String, BufferedImage> images;

    private Assets() {
        images = new Hashtable<>();
        //todo load images into hashtable
    }

    public static BufferedImage getImage(String imageKey) {
        if(singletonInstance == null) {
            singletonInstance = new Assets();
        }
        return singletonInstance.images.get(imageKey);
    }

}
