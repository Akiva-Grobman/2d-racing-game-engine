package com.k300.utils.configarations;

public class Config {

    public final static String SERVER_URL = "";
    private static volatile Config singletonConfig;
    private boolean isInDevMode;
    private boolean isUsingZoom;

    private Config() {
        isUsingZoom = true;
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

    public static boolean IsUsingZoom() {
        handleInstance();
        return singletonConfig.isUsingZoom;

    }

    public static boolean IsInDevMode() {
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
