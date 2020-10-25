package com.k300.states;

import com.k300.Launcher;
//import com.k300.ui.SettingsList;
import com.k300.display.ZoomExample;
import com.k300.ui.SettingsList;
import com.k300.ui.UIManager;
//import com.k300.ui.buttons.SettingsListElement;
import com.k300.ui.buttons.SettingsListElement;
import com.k300.ui.buttons.UIBackButton;
import com.k300.ui.buttons.ZoomCustomizerButton;
import com.k300.utils.SETTING_LIST_ELEMENTS;
import com.k300.utils.configarations.Config;
import com.k300.utils.math.Converter;

import java.awt.*;

public class SettingsState extends State {

    private final UIManager uiManager;
    private SettingsListElement devModeElement;
    private ZoomCustomizerButton plus;
    private ZoomCustomizerButton minus;
    private boolean addedZoomCustomizingButtons;

    public SettingsState(Launcher launcher) {
        super(launcher);
        uiManager = new UIManager();
        addedZoomCustomizingButtons = false;
        launcher.getMouseListener().setUiManager(uiManager);
        UIBackButton backButton = new UIBackButton(Converter.FHD_SCREEN_WIDTH / 10f * 8,
                Converter.FHD_SCREEN_HEIGHT / 5f * 4,
                (int) (Converter.FHD_SCREEN_WIDTH / 10f),
                (int) (Converter.FHD_SCREEN_HEIGHT / 5f),
                launcher);
        uiManager.addUIObject(backButton);
        SettingsList settingsList = new SettingsList(Converter.FHD_SCREEN_WIDTH / 10, Converter.FHD_SCREEN_HEIGHT / 5 - Converter.FHD_SCREEN_HEIGHT / 20);
        for (SettingsListElement element: settingsList.getElements()) {
            uiManager.addUIObject(element);
            if(element.getMessage().equals(SETTING_LIST_ELEMENTS.DEV_MODE.getMessage())){
                devModeElement = element;
            }
        }

    }

    @Override
    public void tick() {
        if(Config.isUsingZoom()) {
            if (!addedZoomCustomizingButtons) {
                uiManager.addUIObject(plus);
                uiManager.addUIObject(minus);
                addedZoomCustomizingButtons = true;
            }
        } else {
            if(addedZoomCustomizingButtons) {
                uiManager.remove(plus);
                uiManager.remove(minus);
                addedZoomCustomizingButtons = false;
            }
        }
    }

    @Override
    public void render(Graphics graphics) {
        uiManager.render(graphics);
        if(Config.isUsingZoom()) {
            renderZoomExample(graphics);
        }
    }

    private void renderZoomExample(Graphics graphics) {
        String devModeString = SETTING_LIST_ELEMENTS.DEV_MODE.getMessage();
        int endOfDevModeOnScreen = graphics.getFontMetrics().stringWidth(devModeString)
                + (int) (devModeElement.getX() + devModeElement.getWidth() * 1.5);
        ZoomExample example = new ZoomExample(endOfDevModeOnScreen);
        example.render(graphics);
    }

}
