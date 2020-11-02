package com.k300.ui;

import com.k300.ui.buttons.SettingsListElement;
import com.k300.utils.SETTING_LIST_ELEMENTS;
import java.util.ArrayList;
import java.util.List;

/*
*       Purpose:
*           hold all of the setting elements
*       Contains:
*           a list of all setting elements to be displayed as well as some size measurements
*/

public class SettingsList {

    // element width
    private final static int WIDTH = 200;
    // element height
    private final static int HEIGHT = 100;
    // padding (space between elements)
    public static final int PADDING  = HEIGHT;
    // list of elements
    private final List<SettingsListElement> elements;
    // the bottom of this whole container (this is used to render relative to the position of this list)
    private final int bottomY;

    // only initialization option, setting the on screen x,y of this list
    public SettingsList(int x, int y) {
        // initialize elements list
        elements = new ArrayList<>();
        // set lowest y position
        bottomY = y + PADDING * 2;
        // add a zoom settings element
        elements.add(new SettingsListElement(x, y, WIDTH, HEIGHT, SETTING_LIST_ELEMENTS.ZOOM));
        // add a dev mode settings element
        elements.add(new SettingsListElement(x, bottomY, WIDTH, HEIGHT, SETTING_LIST_ELEMENTS.DEV_MODE));
    }

    // element list accessor
    public List<SettingsListElement> getElements() {
        return elements;
    }

    // bottom y (lowest on screen y of this list container) accessor
    public int getBottomY() {
        return elements.get(elements.size() - 1).getHeight() + bottomY;
    }

}
