package com.k300.states;

import com.k300.Launcher;
import com.k300.display.ZoomExample;
import com.k300.ui.SettingsList;
import com.k300.ui.UIManager;
import com.k300.ui.buttons.*;
import com.k300.utils.SETTING_LIST_ELEMENTS;
import com.k300.utils.configarations.Config;
import com.k300.utils.math.Converter;
import java.awt.*;

/*
*       Purpose:
*           this represents the settings state, in here the user can change the zoom in factor, and the developer can turn on/off the developer mode.
*       Contains:
*           a container for all of the buttons, both zoom customizing buttons(to ovid unnecessary initialization, that the purpose of the boolean flag).
*/

public class SettingsState extends State {

    // maneges all buttons(rendering, on click)
    private final UIManager uiManager;
    // this is a reference to the lowest element on the settings list(this used to determine the location of the plus, minus buttons
    private SettingsListElement lowestListElement;
    // this button will zoom in (class variable to avoid unnecessary initialization)
    private final ZoomCustomizerButton plus;
    // this button will zoom out (class variable to avoid unnecessary initialization)
    private final ZoomCustomizerButton minus;
    // a flag to know if the zoom in/out buttons are already in the uiManager
    private boolean addedZoomCustomizingButtons;

    public SettingsState(Launcher launcher) {
        // initialize abstract state
        super(launcher);
        // initialize empty manager
        uiManager = new UIManager();
        // set flag to default
        addedZoomCustomizingButtons = false;
        // set manager to be the mouse manager
        launcher.getMouseListener().setUiManager(uiManager);
        // create a back button that will return to the menu state when clicked
        UIButton backButton = new UIMenuButton(Converter.FHD_SCREEN_WIDTH / 5f * 4,
                Converter.FHD_SCREEN_HEIGHT / 5f * 4,
                (int) (Converter.FHD_SCREEN_WIDTH / 10.2f),
                (int) (Converter.FHD_SCREEN_HEIGHT / 10f),
                "Back",
                40,
                () -> StateManager.setCurrentState(new MenuState(launcher)));
        // add the back button to the manager
        uiManager.addUIObject(backButton);
        // create a settings list
        SettingsList settingsList = new SettingsList(Converter.FHD_SCREEN_WIDTH / 10, Converter.FHD_SCREEN_HEIGHT / 5 - Converter.FHD_SCREEN_HEIGHT / 20);
        // add all elements in the settings list to the manager(and save the lowest(by y) element)
        for (SettingsListElement element: settingsList.getElements()) {
            uiManager.addUIObject(element);
            if(element.getMessage().equals(SETTING_LIST_ELEMENTS.DEV_MODE.getMessage())){
                lowestListElement = element;
            }
        }
        // get bottom(y) of the settings list
        int bottomOfSettingsList = settingsList.getBottomY();
        // set zoom in/out button width
        int zoomCustomizerWidth = Converter.FHD_SCREEN_WIDTH / 8;
        // initialize the plus button
        //noinspection SuspiciousNameCombination
        plus = new ZoomCustomizerButton(zoomCustomizerWidth,
                bottomOfSettingsList + SettingsList.PADDING,
                zoomCustomizerWidth,
                zoomCustomizerWidth,
                "+");
        // initialize the minus button
        //noinspection SuspiciousNameCombination
        minus = new ZoomCustomizerButton(zoomCustomizerWidth * 2,
                bottomOfSettingsList + SettingsList.PADDING,
                zoomCustomizerWidth,
                zoomCustomizerWidth,
                "-");
    }

    // update the setting state
    @Override
    public void tick() {
        // this will make sure the zoom in/out buttons are only working and visible when in zoom state
        if(Config.isUsingZoom()) {
            // if they are aren't in the manager
            if (!addedZoomCustomizingButtons) {
                // add them to the manager
                uiManager.addUIObject(plus);
                uiManager.addUIObject(minus);
                // set flag
                addedZoomCustomizingButtons = true;
            }
        } else {
            // if they are in the manager
            if(addedZoomCustomizingButtons) {
                // remove them
                uiManager.remove(plus);
                uiManager.remove(minus);
                // set flag
                addedZoomCustomizingButtons = false;
            }
        }
    }

    // render settings state
    @Override
    public void render(Graphics graphics) {
        // render all buttons in manager
        uiManager.render(graphics);
        // if the is in zoom mode
        if(Config.isUsingZoom()) {
            renderZoomExample(graphics);
        }
    }

    // render a mini window showing the user how the zoom will look
    private void renderZoomExample(Graphics graphics) {
        // this is used to calculate the right edge of the bottom element of the settings list
        String bottomElementString = lowestListElement.getMessage();
        int endOfBottomElementOnScreen = graphics.getFontMetrics().stringWidth(bottomElementString)
                + (int) (lowestListElement.getX() + lowestListElement.getWidth() * 1.5);
        // initialize a zoom example (see more in the ZoomExample class)
        ZoomExample example = new ZoomExample(endOfBottomElementOnScreen);
        // render zoom example
        example.render(graphics);
    }

}
