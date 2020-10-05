package com.k300.states;

import com.k300.Launcher;
import java.awt.*;

public abstract class State {

    protected Launcher launcher;

    public State(Launcher launcher) {
        this.launcher = launcher;
    }

    public abstract void tick();

    public abstract void render(Graphics graphics);

    public int getWindowWidth() {
        return launcher.getWindowWidth();
    }

    public int getWindowHeight() {
        return launcher.getWindowHeight();
    }
}