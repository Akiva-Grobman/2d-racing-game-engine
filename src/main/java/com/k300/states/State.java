package com.k300.states;

import com.k300.Launcher;
import java.awt.*;

/*
*       Quick explanation:
*           a state is what the user is currently seeing on screen
*       Purpose:
*           an abstract representation of a state.
*           all states must have access to the launcher, be updated, and rendered
*/

public abstract class State {

    // a reference to the launcher
    protected final Launcher launcher;

    // only initialization option (will initialize the launcher reference)
    public State(Launcher launcher) {
        this.launcher = launcher;
    }

    // all states must be updatable
    public abstract void tick();

    // all states must be renderable
    public abstract void render(Graphics graphics);

}