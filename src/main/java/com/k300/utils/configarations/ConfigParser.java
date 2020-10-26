package com.k300.utils.configarations;

import java.util.*;
import java.util.prefs.Preferences;

public class ConfigParser {

    private static final String FILE_URL = "/configurations/config.txt";
    private static final String TRUE = String.valueOf(true);
    private static final String ZOOM_STATUS = "zoom";
    private static final String DEV_MODE_STATUS = "devMode";
    private static final String SERVER_URL = "url";
    private static final String ZOOM_FACTOR = "zoomFactor";
    private final Preferences preferences;
    private final Map<String, String> configData;

    ConfigParser() {
        preferences = Preferences.userRoot().node(this.getClass().getName());
        configData = getConfigAsMap();
    }

    private Map<String, String> getConfigAsMap() {
        Map<String, String> map = new HashMap<>();
        String defaultZoomStatus = "Zoom status: false";
        map.put(ZOOM_STATUS, preferences.get(ZOOM_STATUS, defaultZoomStatus));
        String defaultDevMode = "dev mode: false";
        map.put(DEV_MODE_STATUS, preferences.get(DEV_MODE_STATUS, defaultDevMode));
        String defaultUrl = "url: http://localhost:3000";
        map.put(SERVER_URL, preferences.get(SERVER_URL, defaultUrl));
        String defaultZoomFactor = "zoom factor: " + (4 * 11) + "," + (4 * 16);
        map.put(ZOOM_FACTOR, preferences.get(ZOOM_FACTOR, defaultZoomFactor));
        return map;
    }

    String getServerUrl() {
        return configData.get(SERVER_URL).split(" ")[1];
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

    double getZoomInWidthFactor() {
        return getZoomFactor(0);
    }

    double getZoomInHeightFactor() {
        return getZoomFactor(1);
    }

    void setZoomInFactor(double widthFactor, double heightFactor) {
        String[] coordinates = configData.get(ZOOM_FACTOR).split(":")[1].split(",");
        coordinates[0] = String.valueOf(widthFactor);
        coordinates[1] = String.valueOf(heightFactor);
        updateString(ZOOM_FACTOR,
                coordinates[0] + "," + coordinates[1]);
    }

    private double getZoomFactor(int indexInString) {
        return Double.parseDouble(configData.get(ZOOM_FACTOR).split(":")[1].split(",")[indexInString]);
    }

    private void updateBoolean(String key, boolean value) {
        updateString(key, String.valueOf(value));
    }

    private void updateString(String  key, String value) {
        String newValue = configData.get(key).split(":")[0] + ": " + value;
        preferences.put(key, newValue);
    }

}
