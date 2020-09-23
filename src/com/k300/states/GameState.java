package com.k300.states;

import com.k300.Launcher;
import com.k300.tracks.Track;

import java.awt.*;

public class GameState extends State {

    private Track track;

    public GameState(Launcher launcher) {
        super(launcher);
        track = new Track(this);
        //launcher.game.setTrack(track);
    }

    @Override
    public void tick() {
        track.tick();
    }

    @Override
    public void render(Graphics graphics) {
        track.render(graphics);
    }

}