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

public abstract class GameState extends State {

    private static final com.k300.utils.Point startingPosition = new Point(800, 800);
    protected final Track track;

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

    public Car getLocalPlayer(String carColor, Collisions playerCollisionLogic, StartLine startLine) {
        PlayerCar localPlayer = new PlayerCar(carColor, startingPosition);
        localPlayer.setCollisions(playerCollisionLogic);
        localPlayer.setStartLine(startLine);
        launcher.setKeyListener(localPlayer.getKeyListener());
        return localPlayer;
    }

    public abstract Car[] getInitCars(Collisions playerCollisionLogic, StartLine startLine, int sumOfCars);

}