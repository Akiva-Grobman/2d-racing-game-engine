package com.k300.io;

import com.k300.Launcher;
import com.k300.cars.player_car.MOVEMENT_DIRECTION;

import java.awt.event.KeyEvent;

/*
*       Purpose:
*           this is a custom key listener (only listening to four keys) this is used to determine the movement direction for a car on screen
*       Contains:
*           a KEY_MOVEMENT_TYPE that will determine what are the four keys,
*           and an array to track what keys are being pressed and what keys are being released.
*/

public class PlayerKeyListener implements java.awt.event.KeyListener {

    // this will determine what are the four key that move the car
    private final KEY_MOVEMENT_TYPE keyMovementType;
    // this tracks the state of all four keys (pressed/released)
    private final boolean[] directionKeys;

    // only initialization option
    public PlayerKeyListener(KEY_MOVEMENT_TYPE keyMovementType) {
        // set the key input type
        this.keyMovementType = keyMovementType;
        // initialize tracking array
        directionKeys = new boolean[4];
    }

    // keyCode pressed/released accessor
    public boolean getKeyIsPressed(int keyCode) {
        return directionKeys[keyMovementType.getIndexForKeysPressedArray(keyCode)];
    }

    // forwards movement key code accessor
    public int getForwardsMovementKey() {
        return keyMovementType.getForwardsMovement();
    }

    // backwards movement key code accessor
    public int getBackwardsMovementKey() {
        return keyMovementType.getBackwardsMovement();
    }

    // right movement key code accessor
    public int getRightMovementKey() {
        return keyMovementType.getRightMovement();
    }

    // left movement key code accessor
    public int getLeftMovementKey() {
        return keyMovementType.getLeftMovement();
    }

    // will return a key code base on a given direction (forwards/backwards)
    public int getKeyCodeByDirection(MOVEMENT_DIRECTION direction) {
        return keyMovementType.getKeyCodeFromMovementDirection(direction);
    }

    // will return a string representation of the keys that represent the four directions
    public String getKeysAsString() {
        return keyMovementType.getKeyValuesInString();
    }

    @Override
    public void keyTyped(KeyEvent ignored) {

    }

    // called when a key is pressed
    @Override
    public void keyPressed(KeyEvent e) {
        // TODO delete those lines
//        // if the escape key is pressed exit the program
//        if(e.getKeyCode() == KeyEvent.VK_ALT) {
//            Launcher.stop();
//        }
        // update a key status to pressed
        keyPressed(e.getKeyCode(), true);
    }

    // called when a key is released
    @Override
    public void keyReleased(KeyEvent e) {
        // update a key status to released
        keyPressed(e.getKeyCode(), false);
    }

    // handle a key press
    private void keyPressed(int keyCode, boolean isPressed) {
        // if it's one of the four keys
        if(keyMovementType.isLegalKey(keyCode)) {
            // update the keys value in the tracker array
            directionKeys[keyMovementType.getIndexForKeysPressedArray(keyCode)] = isPressed;
        }
    }

}
