package com.k300.ui;

import com.k300.ui.buttons.SettingsListElement;
import com.k300.utils.SETTING_LIST_ELEMENTS;
import java.util.ArrayList;
import java.util.List;

public class SettingsList {

    private final static int WIDTH = 200;
    private final static int HEIGHT = 100;
    public static final int PADDING  = HEIGHT;
    private final List<SettingsListElement> elements;
    private final int bottomY;

    public SettingsList(int x, int y) {
        elements = new ArrayList<>();
        bottomY = y + PADDING * 2;
        elements.add(new SettingsListElement(x, y, WIDTH, HEIGHT, SETTING_LIST_ELEMENTS.ZOOM));
        elements.add(new SettingsListElement(x, bottomY, WIDTH, HEIGHT, SETTING_LIST_ELEMENTS.DEV_MODE));
    }

    public List<SettingsListElement> getElements() {
        return elements;
    }

    public int getBottomY() {
        return elements.get(elements.size() - 1).getHeight() + bottomY;
    }

}
