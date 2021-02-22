package com.k300.utils.configarations;

import java.util.*;
import java.util.prefs.Preferences;

/*
*   Purpose:
*       This class object will represent the application database
*   Contains:
*       The preference object that is our database, and a HashMap that stores the date while the program is running.
*       The Config class will read from the HashMap and write to both objects
*   Values being stored:
*       Zoom status: whether or not the user want to use the zoom in option in an online game
*       Dev mode: used to flag parts that should only be called during development
*       Server url: the url for the server duh
*       Zoom factor: this is used to determine how much to zoom in when using the zoom in view
*/

public class PreferenceReader {

    // This represent the string of the true boolean value
    private static final String TRUE = String.valueOf(true);
    // zoom status key
    private static final String ZOOM_STATUS = "zoom";
    // dev mode key
    private static final String DEV_MODE_STATUS = "devMode";
    // server url key
    private static final String SERVER_URL = "url";
    // zoom factor key
    private static final String ZOOM_FACTOR = "zoomFactor";
    // the actual database
    private final Preferences preferences;
    // the Hash map for run time storing
    private final Map<String, String> configData;

    // only initialization option
    PreferenceReader() {
        // bind to this object
        preferences = Preferences.userRoot().node(this.getClass().getName());
        // initialize to previous preferences
        configData = getConfigAsMap();
    }

    // This method will return the stored value in our database as a hashmap
    private Map<String, String> getConfigAsMap() {
        //Initialize hashmap
        Map<String, String> map = new HashMap<>();
        // This method follows this pattern
        // 1. define default value
        // 2. retrieve from database previous/default value
        // set default
        String defaultZoomStatus = "false";
        // retrieve data
        map.put(ZOOM_STATUS, preferences.get(ZOOM_STATUS, defaultZoomStatus));
        // set default
        String defaultDevMode = "false";
        // retrieve data
        map.put(DEV_MODE_STATUS, preferences.get(DEV_MODE_STATUS, defaultDevMode));
        // set default
        String defaultUrl = "http://localhost:3000";
        // retrieve data
        map.put(SERVER_URL, preferences.get(SERVER_URL, defaultUrl));
        // set default
        String defaultZoomFactor = "3";
        // retrieve data
        map.put(ZOOM_FACTOR, preferences.get(ZOOM_FACTOR, defaultZoomFactor));
        return map;
    }

    // url accessor
    String getServerUrl() {
        return configData.get(SERVER_URL);
    }

    // url modifier
    void setServerUrl(String url) {
        updateString(SERVER_URL, String.valueOf(url));
    }

    // dev mode accessor
    boolean isInDevMode() {
        return configData.get(DEV_MODE_STATUS).contains(TRUE);
    }

    // dev mode modifier
    void setIsInDevMode(boolean isInDevMode) {
        updateBoolean(DEV_MODE_STATUS, isInDevMode);
    }

    // zoom mode accessor
    boolean isUsingZoom() {
        return configData.get(ZOOM_STATUS).contains(TRUE);
    }

    // zoom factor accessor
    double getZoomInFactor() {
        return Double.parseDouble(configData.get(ZOOM_FACTOR));
    }

    // zoom mode modifier
    void setZoomInFactor(double zoomInFactor) {
        updateString(ZOOM_FACTOR, String.valueOf(zoomInFactor));
    }

    // zoom factor modifier
    void setIsUsingZoom(boolean isUsingZoom) {
        updateBoolean(ZOOM_STATUS, isUsingZoom);
    }

    // update database and hashmap with a boolean value
    private void updateBoolean(String key, boolean value) {
        updateString(key, String.valueOf(value));
    }

    // update database and hashmap with string value
    private void updateString(String  key, String value) {
        // update hashmap
        configData.put(key, value);
        // update database
        preferences.put(key, value);
    }

}