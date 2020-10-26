package com.k300.utils.configarations;

public class Config {

    private static Config singletonInstance = null;
    private final PreferenceReader preferenceReader;

    private Config() {
        preferenceReader = new PreferenceReader();
    }

    public static boolean isInDevMode() {
        handleInstance();
        return singletonInstance.preferenceReader.isInDevMode();
    }

    public static void setInDevMode(boolean isInDevMode) {
        handleInstance();
        singletonInstance.preferenceReader.setIsInDevMode(isInDevMode);
    }

    public static boolean isUsingZoom() {
        handleInstance();
        return singletonInstance.preferenceReader.isUsingZoom();
    }

    public static void setUsingZoom(boolean isUsingZoom) {
        handleInstance();
        singletonInstance.preferenceReader.setIsUsingZoom(isUsingZoom);
    }

    public static String getUrl() {
        handleInstance();
        return singletonInstance.preferenceReader.getServerUrl();
    }

    public static double getZoomInFactor() {
        handleInstance();
        return singletonInstance.preferenceReader.getZoomInFactor();
    }

    public static void setZoomInFactor(double zoomFactor) {
        handleInstance();
        singletonInstance.preferenceReader.setZoomInFactor(zoomFactor);
    }

    // this method most be called on the first line of all public static methods in this class
    private static void handleInstance() {
        if(singletonInstance == null) {
            singletonInstance = new Config();
        }
    }

}
