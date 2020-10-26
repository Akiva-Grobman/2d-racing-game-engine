package com.k300.utils.configarations;

public class Config {

    private static Config singletonInstance = null;
    private final ConfigParser parser;

    private Config() {
        parser = new ConfigParser();
    }

    public static boolean isInDevMode() {
        handleInstance();
        return singletonInstance.parser.isInDevMode();
    }

    public static void setInDevMode(boolean isInDevMode) {
        handleInstance();
        singletonInstance.parser.setIsInDevMode(isInDevMode);
    }

    public static boolean isUsingZoom() {
        handleInstance();
        return singletonInstance.parser.isUsingZoom();
    }

    public static void setUsingZoom(boolean isUsingZoom) {
        handleInstance();
        singletonInstance.parser.setIsUsingZoom(isUsingZoom);
    }

    public static String getUrl() {
        handleInstance();
        return singletonInstance.parser.getServerUrl();
    }

    public static double getZoomInFactor() {
        handleInstance();
        return singletonInstance.parser.getZoomInFactor();
    }

    public static void setZoomInFactor(double zoomFactor) {
        handleInstance();
        singletonInstance.parser.setZoomInFactor(zoomFactor);
    }

    // this method most be called on the first line of all public static methods in this class
    private static void handleInstance() {
        if(singletonInstance == null) {
            singletonInstance = new Config();
        }
    }

}
