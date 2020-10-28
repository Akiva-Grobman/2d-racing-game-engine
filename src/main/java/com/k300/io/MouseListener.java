package com.k300.io;

import com.k300.ui.UIManager;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/*
*       Purpose:
*           pass on to a UIManager it the mouse information
*       Contains:
*           a UIManager, and current mouse position
*
*/

public class MouseListener implements java.awt.event.MouseListener, MouseMotionListener {

    // contains the current mouse position
    private final Point mousePosition;
    // the current UIManager
    private UIManager uiManager;

    // default initialization to null manager
    public MouseListener() {
        this(null);
    }

    // custom constructor with manager as parameter
    public MouseListener(UIManager uiManager) {
        // set manager
        this.uiManager = uiManager;
        // initialize point with not values
        mousePosition = new Point();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    // update manager that mouse was released
    @Override
    public void mouseReleased(MouseEvent e) {
        if(uiManager != null) {
            uiManager.onMouseRelease();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    // when the mouse is moved
    @Override
    public void mouseMoved(MouseEvent e) {
        // update the current mouse position
        mousePosition.x = e.getX();
        mousePosition.y = e.getY();
        // update the manager that the mouse was moved
        if(uiManager != null) {
            uiManager.onMouseMove(e);
        }
    }

    // manager modifier
    public void setUiManager(UIManager uiManager) {
        this.uiManager = uiManager;
    }
}
