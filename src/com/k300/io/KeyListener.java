package com.k300.io;

import com.k300.Launcher;

import java.awt.event.KeyEvent;

public class KeyListener implements java.awt.event.KeyListener {

    private final boolean[] keys;

    public KeyListener() {
        keys = new boolean[256];
    }

    public boolean getKey(int keyCode) {
        if(isInBounds(keyCode)) {
            return keys[keyCode];
        }
        return false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            Launcher.stop();
        }
        keyTyped(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyTyped(e.getKeyCode(), false);
    }

    private void keyTyped(int keyCode, boolean isPressed) {
        if(isInBounds(keyCode)) {
            keys[keyCode] = isPressed;
        }
    }

    private boolean isInBounds(int keyCode) {
        return keyCode >= 0 && keyCode < keys.length;
    }

}
