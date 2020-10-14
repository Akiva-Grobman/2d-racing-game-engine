package com.k300.utils.configarations;

public class Config {

    public final static String SERVER_URL = "";
    private static volatile Config singletonConfig;
    private boolean isInDevMode;
    private boolean isUsingZoom;

    private Config() {
        isUsingZoom = false;
        isInDevMode = true;
    }

    public static void setIsInDevMode(boolean isInDevMode) {
        handleInstance();
        singletonConfig.isInDevMode = isInDevMode;
    }

    public static void setIsUsingZoom(boolean isUsingZoom) {
        handleInstance();
        singletonConfig.isUsingZoom = isUsingZoom;
    }

    public static boolean getIsUsingZoom() {
        handleInstance();
        return singletonConfig.isUsingZoom;

    }

    public static boolean getIsInDevMode() {
        handleInstance();
        return singletonConfig.isInDevMode;
    }

    private static void handleInstance() {
        if(!hasBeanInstantiated()) {
            instantiate();
        }
    }

    private static boolean hasBeanInstantiated() {
        return singletonConfig != null;
    }

    private static void instantiate() {
        singletonConfig = new Config();
    }
}
