package com.k300.ui;

import com.k300.ui.buttons.SettingsListElement;
import com.k300.utils.SETTING_LIST_ELEMENTS;
import java.util.ArrayList;
import java.util.List;

public class SettingsList {

    private final static int WIDTH = 200;
    private final static int HEIGHT = 100;
    private final List<SettingsListElement> elements;

    public SettingsList(int x, int y) {
        elements = new ArrayList<>();
        elements.add(new SettingsListElement(x, y, WIDTH, HEIGHT, SETTING_LIST_ELEMENTS.ZOOM));
        elements.add(new SettingsListElement(x, y + HEIGHT * 2, WIDTH, HEIGHT, SETTING_LIST_ELEMENTS.DEV_MODE));
    }

    public List<SettingsListElement> getElements() {
        return elements;
    }
}
