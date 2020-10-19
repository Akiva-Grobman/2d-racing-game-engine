package com.k300.io;

import com.k300.ui.UIManager;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseListener implements java.awt.event.MouseListener, MouseMotionListener {

    private final Point mousePosition;
    private UIManager uiManager;

    public MouseListener() {
        this(null);
    }

    public MouseListener(UIManager uiManager) {
        this.uiManager = uiManager;
        mousePosition = new Point();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

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

    @Override
    public void mouseMoved(MouseEvent e) {
        mousePosition.x = e.getX();
        mousePosition.y = e.getY();
        if(uiManager != null) {
            uiManager.onMouseMove(e);
        }
    }

    public void setUiManager(UIManager uiManager) {
        this.uiManager = uiManager;
    }
}
