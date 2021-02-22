package com.k300.utils.configarations;

/*
*   Purpose:
*       This class will serve as an interactor with the PreferenceReader class
*   This class implement the singleton strategy so the preferences can be accessed in a static way
*   Contains:
*       A static object of this class and static methods to interact with the preference data
*/

public class Config {

    // This will contain one and only instance of this class object
    private static Config singletonInstance = null;
    // This is our preference object
    private final PreferenceReader preferenceReader;

    // This will not allow the class object to be initialized for out side of this class
    // The constructor will initialize the preference object
    private Config() {
        preferenceReader = new PreferenceReader();
    }

    // this method most be called on the first line of all public static methods in this class
    private static void handleInstance() {
        // if static instance of this class object hasn't been initialized
        if(singletonInstance == null) {
            // initialize class object
            singletonInstance = new Config();
        }
    }

    // dev mode status accessor
    public static boolean isInDevMode() {
        handleInstance();
        return singletonInstance.preferenceReader.isInDevMode();
    }

    // dev mode modifier
    public static void setInDevMode(boolean isInDevMode) {
        handleInstance();
        singletonInstance.preferenceReader.setIsInDevMode(isInDevMode);
    }

    // zoom using accessor
    public static boolean isUsingZoom() {
        handleInstance();
        return singletonInstance.preferenceReader.isUsingZoom();
    }

    // zoom using modifier
    public static void setUsingZoom(boolean isUsingZoom) {
        handleInstance();
        singletonInstance.preferenceReader.setIsUsingZoom(isUsingZoom);
    }

    // zoom factor accessor
    public static double getZoomInFactor() {
        handleInstance();
        return singletonInstance.preferenceReader.getZoomInFactor();
    }

    // zoom factor modifier
    public static void setZoomInFactor(double zoomFactor) {
        handleInstance();
        singletonInstance.preferenceReader.setZoomInFactor(zoomFactor);
    }

    // url accessor
    public static String getUrl() {
        handleInstance();
        return singletonInstance.preferenceReader.getServerUrl();
    }

    // url modifier
    public static void setUrl(String url) {
        handleInstance();
        singletonInstance.preferenceReader.setServerUrl(url);
    }
}
