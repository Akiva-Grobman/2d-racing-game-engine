package com.k300.utils.configarations;

import java.io.*;
import java.util.*;

public class ConfigParser {

    private static final String FILE_URL = "/configurations/config.txt";
    private static final String TRUE = String.valueOf(true);
    private static final String ZOOM_STATUS = "zoom";
    private static final String DEV_MODE_STATUS = "devMode";
    private static final String SERVER_URL = "url";
    private static final String ZOOM_FACTOR = "zoomFactor";
    private final Map<String, Integer> infoIndex;
    private final List<String> lines;

    ConfigParser() {
         infoIndex = new HashMap<>();
         infoIndex.put(ZOOM_STATUS, 0);
         infoIndex.put(DEV_MODE_STATUS, 1);
         infoIndex.put(SERVER_URL, 2);
         infoIndex.put(ZOOM_FACTOR, 3);
         lines = getConfigAsLines();
         //setDefaultZoomFactor();
    }

    private void setDefaultZoomFactor() {
        setZoomInFactor(1);
    }

    private List<String> getConfigAsLines() {
        List<String> lines = new ArrayList<>();
        try {
            InputStream inputStream = getClass().getResource(FILE_URL).openStream();
            Scanner reader = new Scanner(inputStream);
            while (reader.hasNext()) {
                lines.add(reader.nextLine());
            }
        } catch (IOException e) {
            throw new Error(e.getMessage());
        }
        return lines;
    }

    String getServerUrl() {
        return lines.get(infoIndex.get(SERVER_URL)).split(" ")[1];
    }

    boolean isInDevMode() {
        return lines.get(infoIndex.get(DEV_MODE_STATUS)).contains(TRUE);
    }

    void setIsInDevMode(boolean isInDevMode) {
        updateBoolean(infoIndex.get(DEV_MODE_STATUS), isInDevMode);
    }

    boolean isUsingZoom() {
        return lines.get(infoIndex.get(ZOOM_STATUS)).contains(TRUE);
    }

    void setIsUsingZoom(boolean isUsingZoom) {
        updateBoolean(infoIndex.get(ZOOM_STATUS), isUsingZoom);
    }

    double getZoomInFactor() {
        return getZoomFactor();
    }

    void setZoomInFactor(double zoomFactor) {
        String[] coordinates = lines.get(infoIndex.get(ZOOM_FACTOR)).split(":")[1].split(",");
        coordinates[0] = String.valueOf(zoomFactor);
        updateString(
                infoIndex.get(ZOOM_FACTOR),
                coordinates[0]
        );
    }

    private double getZoomFactor() {
        return Double.parseDouble(lines.get(infoIndex.get(ZOOM_FACTOR)).split(":")[1]);
    }

    private void updateBoolean(int lineIndex, boolean value) {
        updateString(lineIndex, String.valueOf(value));
    }

    private void updateString(int lineIndex, String value) {
        lines.set(lineIndex, lines.get(lineIndex).split(":")[0] + ": " + value);
        updateFile();
    }

    private void updateFile() {
        try {
            PrintStream writer = new PrintStream(getClass().getResource(FILE_URL).getPath());
            lines.forEach(writer::println);
            writer.close();
        } catch (IOException e) {
            throw new Error(e.getMessage());
        }
    }
}
