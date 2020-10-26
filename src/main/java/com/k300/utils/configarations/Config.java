package com.k300.utils.configarations;

public class Config {

    private static final ConfigParser parser = new ConfigParser();

    public static boolean isInDevMode() {
        return parser.isInDevMode();
    }

    public static void setInDevMode(boolean isInDevMode) {
        parser.setIsInDevMode(isInDevMode);
    }

    public static boolean isUsingZoom() {
        return parser.isUsingZoom();
    }

    public static void setUsingZoom(boolean isUsingZoom) {
        parser.setIsUsingZoom(isUsingZoom);
    }

    public static String getUrl() {
        return parser.getServerUrl();
    }

    public static double getZoomInFactor() {
        return parser.getZoomInFactor();
    }

    public static void setZoomInFactor(double zoomFactor) {
        parser.setZoomInFactor(zoomFactor);
    }

}
