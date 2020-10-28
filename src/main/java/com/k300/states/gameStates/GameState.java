package com.k300.states.gameStates;

import com.k300.Launcher;
import com.k300.states.State;
import com.k300.tracks.Track;
import com.k300.utils.Point;

import java.awt.*;

/*
*       Purpose:
*           this is an abstract representation of a game
*       Contains:
*           a track and default starting position
*/

public abstract class GameState extends State {

    public static final Point startingPosition = new Point(800, 880);
    // this contains the current state of the game (see more in Track, OnlineTrack, OfflineTrack classes)
    protected volatile Track track;

    // only initialization option
    public GameState(Launcher launcher) {
        // initialize abstract state
        super(launcher);
    }

    // update track
    @Override
    public void tick() {
        track.tick();
    }

    // render track
    @Override
    public void render(Graphics graphics) {
        // this will make sure we only render the track when it's initialized that might not happen right away
        if(track == null) {
            return;
        }
        // render track
        track.render(graphics);
    }

    // accessor for the launcher reference
    public Launcher getLauncher() {
        return launcher;
    }

}