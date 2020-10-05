package com.k300.ui;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class UIManager {

    private final List<UIObject> uiObjects;

    public UIManager() {
        uiObjects = new ArrayList<>();
    }

    public void tick() {
        for (UIObject uiObject: uiObjects) {
            uiObject.tick();
        }
    }

    public void render(Graphics graphics) {
        for (UIObject uiObject: uiObjects) {
            uiObject.render(graphics);
        }
    }

    public void onMouseMove(MouseEvent e) {
        for (UIObject uiObject: uiObjects) {
            uiObject.onMouseMove(e);
        }
    }

    public void onMouseRelease(MouseEvent e) {
        for (UIObject uiObject: uiObjects) {
            uiObject.onMouseRelease(e);
        }
    }

    public void addUIObject(UIObject object) {
        uiObjects.add(object);
    }

    public void removeUIObject(UIObject object){
        uiObjects.remove(object);
    }

}