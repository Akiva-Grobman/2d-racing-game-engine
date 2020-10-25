package com.k300.io;

import com.k300.cars.player_car.MOVEMENT_DIRECTION;

import java.awt.event.KeyEvent;

public enum KEY_MOVEMENT_TYPE {

    ARROWS(KeyEvent.VK_UP,
            KeyEvent.VK_DOWN,
            KeyEvent.VK_RIGHT,
            KeyEvent.VK_LEFT),
    A_S_W_D(KeyEvent.VK_W,
            KeyEvent.VK_S,
            KeyEvent.VK_D,
            KeyEvent.VK_A);

    private final int forwardsMovement;
    private final int backwardsMovement;
    private final int rightMovement;
    private final int leftMovement;

    KEY_MOVEMENT_TYPE(int forwardsMovement, int backwardsMovement, int rightMovement, int leftMovement) {
        this.forwardsMovement = forwardsMovement;
        this.backwardsMovement = backwardsMovement;
        this.rightMovement = rightMovement;
        this.leftMovement = leftMovement;
    }

    public int getForwardsMovement() {
        return forwardsMovement;
    }

    public int getBackwardsMovement() {
        return backwardsMovement;
    }

    public int getRightMovement() {
        return rightMovement;
    }

    public int getLeftMovement() {
        return leftMovement;
    }

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
        if(this == ARROWS) {
            return keyCode >= leftMovement && keyCode <= backwardsMovement;
        } else {
            return keyCode == forwardsMovement
                    || keyCode == backwardsMovement
                    || keyCode == rightMovement
                    || keyCode == leftMovement;
        }
    }

    public int getIndexForKeysPressedArray(int keyCode){
        if(!isLegalKey(keyCode)) {
            throw new Error(keyCode + " is out of bounds of " + getKeyValuesInString());
        }
        if(this == ARROWS) {
            return keyCode - leftMovement;
        } else {
            return switch (keyCode) {
                case KeyEvent.VK_A -> 0;
                case KeyEvent.VK_W -> 1;
                case KeyEvent.VK_D -> 2;
                case KeyEvent.VK_S -> 3;
                default -> -1;
            };
        }

    }

    public int getKeyCodeFromMovementDirection(MOVEMENT_DIRECTION movementDirection) {
        if(movementDirection == MOVEMENT_DIRECTION.FORWARDS) {
            return forwardsMovement;
        } else {
            return backwardsMovement;
        }
    }

    public String getKeyValuesInString() {
        String keys;
        if(this == ARROWS) {
            keys = "Arrow keys";
        } else {
            keys = "A S W D keys";
        }
        return keys;
    }

}
