package com.k300.states;

import com.k300.Launcher;
//import com.k300.ui.SettingsList;
import com.k300.ui.UIManager;
//import com.k300.ui.buttons.SettingsListElement;
import com.k300.ui.buttons.UIBackButton;
import com.k300.utils.math.Converter;

import java.awt.*;

public class SettingsState extends State {

    private final UIManager uiManager;

    public SettingsState(Launcher launcher) {
        super(launcher);
        uiManager = new UIManager();
        launcher.getMouseListener().setUiManager(uiManager);
        UIBackButton backButton = new UIBackButton(Converter.FHD_SCREEN_WIDTH / 10f * 8,
                Converter.FHD_SCREEN_HEIGHT / 5f * 4,
                (int) (Converter.FHD_SCREEN_WIDTH / 10f),
                (int) (Converter.FHD_SCREEN_HEIGHT / 5f),
                launcher);
        uiManager.addUIObject(backButton);
//        SettingsList settingsList = new SettingsList(Converter.FHD_SCREEN_WIDTH / 10, Converter.FHD_SCREEN_HEIGHT / 5);
//        for (SettingsListElement element: settingsList.getElements()) {
//            uiManager.addUIObject(element);
//        }
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics graphics) {
        uiManager.render(graphics);
    }

}
