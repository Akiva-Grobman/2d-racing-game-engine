package com.k300.cars;

import com.k300.io.PlayerKeyListener;
import com.k300.tracks.Collisions;
import com.k300.utils.Point;
import java.awt.event.KeyListener;

public class PlayerCar extends Car {

    private final PlayerKeyListener keyListener;
    private final Collisions collisions;
    private final PlayerCarMover mover;
    private int keyReleased;

    public PlayerCar(String carColor, Point startingPosition, Collisions collisions) {
        super(carColor, startingPosition);
        this.collisions = collisions;
        keyListener = new PlayerKeyListener();
        mover = new PlayerCarMover(this);
    }

    @Override
    public void tick() {
        if(mover.isFadeClick()) {
            mover.fade();
        } else if (keyListener.getKeyIsPressed(PlayerKeyListener.UP_ARROW)) {
            if(keyReleased != PlayerKeyListener.UP_ARROW) {
                mover.resetSpeed();
            }
            mover.keyPressed(true);
            keyReleased = PlayerKeyListener.UP_ARROW;
        } else if (keyListener.getKeyIsPressed(PlayerKeyListener.DOWN_ARROW)) {
            if(keyReleased != PlayerKeyListener.DOWN_ARROW) {
                mover.resetSpeed();
            }
            mover.keyPressed(false);
            keyReleased = PlayerKeyListener.DOWN_ARROW;
        } else if (keyReleased == PlayerKeyListener.UP_ARROW){
            mover.keyReleased(true);
        } else if (keyReleased == PlayerKeyListener.DOWN_ARROW){
            mover.keyReleased(false);
        }

        if(keyListener.getKeyIsPressed(PlayerKeyListener.RIGHT_ARROW)) {
            mover.moveRight();
        }
        if(keyListener.getKeyIsPressed(PlayerKeyListener.LEFT_ARROW)) {
            mover.moveLeft();
        }
    }

    boolean isOffTrack() {
        return !collisions.onTheTrack(x, y);
    }

    public KeyListener getKeyListener() {
        return keyListener;
    }

}
