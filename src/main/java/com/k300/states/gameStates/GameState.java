package com.k300.states.gameStates;

import com.k300.Launcher;
import com.k300.cars.Car;
import com.k300.cars.player_car.PlayerCar;
import com.k300.states.State;
import com.k300.tracks.Collisions;
import com.k300.tracks.StartLine;
import com.k300.tracks.Track;
import com.k300.utils.Point;

import java.awt.*;
import java.awt.event.KeyListener;

public abstract class GameState extends State {

    private static final com.k300.utils.Point startingPosition = new Point(800, 800);
    private final Track track;

    public GameState(Launcher launcher) {
        super(launcher);
        track = new Track(this);
    }

    @Override
    public void tick() {
        track.tick();
    }

    @Override
    public void render(Graphics graphics) {
        track.render(graphics);
    }

    public void setKeyListener(KeyListener keyListener) {
        launcher.setKeyListener(keyListener);
    }

    public Car getLocalPlayer(String carColor, Collisions playerCollisionLogic, StartLine startLine) {
        PlayerCar localPlayer = new PlayerCar(carColor, startingPosition, playerCollisionLogic, startLine);
        setKeyListener(localPlayer.getKeyListener());
        return localPlayer;
    }

    public abstract Car[] getCars(Collisions playerCollisionLogic, StartLine startLine);

}