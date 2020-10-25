package com.k300.io;

import com.k300.Launcher;
import com.k300.cars.player_car.MOVEMENT_DIRECTION;

import java.awt.event.KeyEvent;

public class PlayerKeyListener implements java.awt.event.KeyListener {


    private final KEY_MOVEMENT_TYPE keyMovementType;
    private final boolean[] directionKeys;

    public PlayerKeyListener(KEY_MOVEMENT_TYPE keyMovementType) {
        this.keyMovementType = keyMovementType;
        directionKeys = new boolean[4];
    }

    public boolean getKeyIsPressed(int keyCode) {
        return directionKeys[keyMovementType.getIndexForKeysPressedArray(keyCode)];
    }

    public int getForwardsMovementKey() {
        return keyMovementType.getForwardsMovement();
    }

    public int getBackwardsMovementKey() {
        return keyMovementType.getBackwardsMovement();
    }

    public int getRightMovementKey() {
        return keyMovementType.getRightMovement();
    }

    public int getLeftMovementKey() {
        return keyMovementType.getLeftMovement();
    }

    public int getKeyCodeByDirection(MOVEMENT_DIRECTION direction) {
        return keyMovementType.getKeyCodeFromMovementDirection(direction);
    }

    public String getKeysAsString() {
        return keyMovementType.getKeyValuesInString();
    }

    @Override
    public void keyTyped(KeyEvent ignored) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            Launcher.stop();
        }
        keyPressed(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyPressed(e.getKeyCode(), false);
    }

    private void keyPressed(int keyCode, boolean isPressed) {
        if(keyMovementType.isLegalKey(keyCode)) {
            directionKeys[keyMovementType.getIndexForKeysPressedArray(keyCode)] = isPressed;
        }
    }

}
