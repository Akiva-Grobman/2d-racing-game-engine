package com.akivaGrobman.input;

import java.awt.event.KeyEvent;

public class KeyListener implements java.awt.event.KeyListener {

    public static final int LEFT_ARROW = KeyEvent.VK_LEFT;
    public static final int UP_ARROW = KeyEvent.VK_UP;
    public static final int RIGHT_ARROW = KeyEvent.VK_RIGHT;
    public static final int DOWN_ARROW = KeyEvent.VK_DOWN;
    private boolean[] arrowKeys;

    public KeyListener() {
        arrowKeys = new boolean[4];
    }

    public boolean getKeyIsPressed(int keyCode) {
        return arrowKeys[keyCode - LEFT_ARROW];
    }

    @Override
    public void keyTyped(KeyEvent ignored) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyPressed(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyPressed(e.getKeyCode(), false);
    }

    private void keyPressed(int keyCode, boolean isPressed) {
        if(keyCode >= LEFT_ARROW && keyCode <= DOWN_ARROW) {
            arrowKeys[keyCode - LEFT_ARROW] = isPressed;
        }
    }

}
