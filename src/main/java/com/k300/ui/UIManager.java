package com.k300.ui;

import com.k300.ui.buttons.UIButton;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class UIManager {

    private final List<UIButton> uiButtons;

    public UIManager() {
        uiButtons = new ArrayList<>();
    }

    public void render(Graphics graphics) {
        for (UIButton uiButton : uiButtons) {
            uiButton.render(graphics);
        }
    }

    public void onMouseMove(MouseEvent e) {
        for (UIButton uiButton : uiButtons) {
            uiButton.onMouseMove(e);
        }
    }

    public void onMouseRelease() {
        for (UIButton uiButton : uiButtons) {
            uiButton.onMouseRelease();
        }
    }

    public void addUIObject(UIButton object) {
        uiButtons.add(object);
    }

}