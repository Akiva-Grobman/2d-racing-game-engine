package com.k300.ui;

import com.k300.ui.buttons.UIButton;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/*
*       Purpose:
*           to update and render all buttons
*       Contains:
*           a list representing all buttons on screen
*/

public class UIManager {

    // list of all buttons
    private final List<UIButton> uiButtons;

    // only initialization options
    public UIManager() {
        // sets list to empty list
        uiButtons = new ArrayList<>();
    }

    // This method will render all of the buttons in the button list
    public void render(Graphics graphics) {
        uiButtons.forEach(uiButton -> uiButton.render(graphics));
    }

    // This method will send the updated mouse position to all buttons
    public void onMouseMove(MouseEvent e) {
        uiButtons.forEach(uiButton -> uiButton.onMouseMove(e));
    }

    // This method will update all buttons that the mouse has been released
    public void onMouseRelease() {
        uiButtons.forEach(UIButton::onMouseRelease);
    }

    // This method will add a button to the list of buttons
    public void addUIObject(UIButton object) {
        uiButtons.add(object);
    }

    /// This method will remove a button from the list of buttons
    public void remove(UIButton uiButton) {
        uiButtons.remove(uiButton);
    }

}