package com.k300.utils.configarations;

import java.io.*;
import java.util.*;

public class ConfigParser {

    private static final String FILE_URL = "/configurations/config.txt";
    private static final String TRUE = String.valueOf(true);
    private static final String ZOOM_STATUS = "zoom";
    private static final String DEV_MODE_STATUS = "devMode";
    private static final String SERVER_URL = "url";
    private final Map<String, Integer> infoIndex;
    private final List<String> lines;

    ConfigParser() {
         infoIndex = new HashMap<>();
         infoIndex.put(ZOOM_STATUS, 0);
         infoIndex.put(DEV_MODE_STATUS, 1);
         infoIndex.put(SERVER_URL, 2);
         lines = getConfigAsLines();
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
        update(infoIndex.get(DEV_MODE_STATUS), isInDevMode);
    }

    boolean isUsingZoom() {
        return lines.get(infoIndex.get(ZOOM_STATUS)).contains(TRUE);
    }

    void setIsUsingZoom(boolean isUsingZoom) {
        update(infoIndex.get(ZOOM_STATUS), isUsingZoom);
    }

    private void update(int lineIndex, boolean value) {
        lines.set(lineIndex, lines.get(lineIndex).split(":")[0] + ": " + value);
        try {
            PrintStream writer = new PrintStream(getClass().getResource(FILE_URL).getPath());
            lines.forEach(writer::println);
            writer.close();
        } catch (IOException e) {
            throw new Error(e.getMessage());
        }
    }

}
