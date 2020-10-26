package com.k300.states.gameStates;

import com.k300.Launcher;
import com.k300.states.State;
import com.k300.tracks.Track;
import com.k300.utils.Point;
import com.k300.utils.math.Converter;

import java.awt.*;

public abstract class GameState extends State {

    public static final Point startingPosition = new Point(800, 880);
    protected volatile Track track;

    public GameState(Launcher launcher) {
        super(launcher);
    }

    @Override
    public void tick() {
        track.tick();
    }

    @Override
    public void render(Graphics graphics) {
        if(track == null) {
            return;
        }
        track.render(graphics);
    }

    public Launcher getLauncher() {
        return launcher;
    }

}