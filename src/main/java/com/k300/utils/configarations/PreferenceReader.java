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

    private Map<String, String> getConfigAsMap() {
        Map<String, String> map = new HashMap<>();
        String defaultZoomStatus = "false";
        map.put(ZOOM_STATUS, preferences.get(ZOOM_STATUS, defaultZoomStatus));
        String defaultDevMode = "false";
        map.put(DEV_MODE_STATUS, preferences.get(DEV_MODE_STATUS, defaultDevMode));
        String defaultUrl = "http://localhost:3000";
        map.put(SERVER_URL, preferences.get(SERVER_URL, defaultUrl));
        String defaultZoomFactor = "3";
        map.put(ZOOM_FACTOR, preferences.get(ZOOM_FACTOR, defaultZoomFactor));
        return map;
    }

    String getServerUrl() {
        return configData.get(SERVER_URL);
    }

    boolean isInDevMode() {
        return configData.get(DEV_MODE_STATUS).contains(TRUE);
    }

    void setIsInDevMode(boolean isInDevMode) {
        updateBoolean(DEV_MODE_STATUS, isInDevMode);
    }

    boolean isUsingZoom() {
        return configData.get(ZOOM_STATUS).contains(TRUE);
    }

    void setIsUsingZoom(boolean isUsingZoom) {
        updateBoolean(ZOOM_STATUS, isUsingZoom);
    }

    void setZoomInFactor(double zoomInFactor) {
        updateString(ZOOM_FACTOR,
                String.valueOf(zoomInFactor));
    }

    double getZoomInFactor() {
        return Double.parseDouble(configData.get(ZOOM_FACTOR));
    }

    private void updateBoolean(String key, boolean value) {
        updateString(key, String.valueOf(value));
    }

    private void updateString(String  key, String value) {
        configData.put(key, value);
        preferences.put(key, value);
    }

}