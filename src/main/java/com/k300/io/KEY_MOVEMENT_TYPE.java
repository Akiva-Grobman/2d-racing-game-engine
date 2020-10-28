package com.k300.io;

import com.k300.cars.player_car.MOVEMENT_DIRECTION;

import java.awt.event.KeyEvent;

/*
*       Purpose:
*           this represents all possible input types (arrows or ASWD).
*       Contains:
*           the Arrows and ASWD instances, as well as four keyCode values each one representing a different direction.
*       Methods:
*           isLegalKey(keyCode)
*               -> will return if the key is one of the four keys
*           getIndexForKeysPressedArray(keyCode)
*               -> will return a number 0-3 representing the key index in an array of size 4(if the input isn't valid an error will be thrown)
*           getKeyCodeFromMovementDirection(movementDirection)
*               -> will return the key code that represents forward movement
*           getKeyValuesInString()
*               -> will return the types of keys in a string (Arrow keys/A S W D keys)
*
*/

public enum KEY_MOVEMENT_TYPE {

    // the arrow key instance
    ARROWS(KeyEvent.VK_UP,
            KeyEvent.VK_DOWN,
            KeyEvent.VK_RIGHT,
            KeyEvent.VK_LEFT),
    // the ASWD key instance
    A_S_W_D(KeyEvent.VK_W,
            KeyEvent.VK_S,
            KeyEvent.VK_D,
            KeyEvent.VK_A);

    // key code representing forwards movement
    private final int forwardsMovement;
    // key code representing backwards movement
    private final int backwardsMovement;
    // key code representing right movement
    private final int rightMovement;
    // key code representing left movement
    private final int leftMovement;

    // initialize all direction codes(using the keyCode values of the keys)
    KEY_MOVEMENT_TYPE(int forwardsMovement, int backwardsMovement, int rightMovement, int leftMovement) {
        this.forwardsMovement = forwardsMovement;
        this.backwardsMovement = backwardsMovement;
        this.rightMovement = rightMovement;
        this.leftMovement = leftMovement;
    }

    // forwards movement key code accessor
    public int getForwardsMovement() {
        return forwardsMovement;
    }

    // backwards movement key code accessor
    public int getBackwardsMovement() {
        return backwardsMovement;
    }

    // right movement key code accessor
    public int getRightMovement() {
        return rightMovement;
    }

    // left movement key code accessor
    public int getLeftMovement() {
        return leftMovement;
    }

    // will return if the key code is one of the four movements cods
    public boolean isLegalKey(int keyCode) {
        /*
            Key code values:
                37 -- Left
                38 -- Up
                39 -- Right
                40 -- Down
                65 -- A
                68 -- D
                83 -- S
                87 -- W
        */
        // if this is the arrow key input type
        if(this == ARROWS) {
            // this is because the arrow keys are in order
            return keyCode >= leftMovement && keyCode <= backwardsMovement;
        } else {
            // if this is the ASWD key input type check if the key code equals one of their(ASWD) key codes
            return keyCode == forwardsMovement
                    || keyCode == backwardsMovement
                    || keyCode == rightMovement
                    || keyCode == leftMovement;
        }
    }

    // will return an index 0-3
    public int getIndexForKeysPressedArray(int keyCode){
        // if isn't one of the key input values throw error
        if(!isLegalKey(keyCode)) {
            throw new Error(keyCode + " is out of bounds of " + getKeyValuesInString());
        }
        // if this is the arrow input type
        if(this == ARROWS) {
            // return the key value minus the smallest of the four arrows (this will give us a number from 0 to 3)
            return keyCode - leftMovement;
        } else {
            // if this is the ASWD key input type manually diced what index to return
            return switch (keyCode) {
                case KeyEvent.VK_A -> 0;
                case KeyEvent.VK_W -> 1;
                case KeyEvent.VK_D -> 2;
                case KeyEvent.VK_S -> 3;
                default -> -1;
            };
        }

    }

    // will return the forwards or backwards key code based on the movement direction
    public int getKeyCodeFromMovementDirection(MOVEMENT_DIRECTION movementDirection) {
        // if moving forwards
        if(movementDirection == MOVEMENT_DIRECTION.FORWARDS) {
            // return forwards movement key code value
            return forwardsMovement;
            // if moving backwards
        } else {
            // return backwards movement key code value
            return backwardsMovement;
        }
    }

    // will return a string representation of the four keys being used
    public String getKeyValuesInString() {
        String keys;
        // if this is the arrow key input type
        if(this == ARROWS) {
            keys = "Arrow";
            // if this is the ASWD key input type
        } else {
            keys = "A S W D";
        }
        // return string representation
        return keys + " keys";
    }

}
