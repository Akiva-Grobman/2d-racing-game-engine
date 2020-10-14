package com.k300.states;

import com.k300.Launcher;
import com.k300.utils.math.Converter;

import java.awt.*;

public abstract class State {

    protected Launcher launcher;

    public State(Launcher launcher) {
        this.launcher = launcher;
    }

    public abstract void tick();

    public abstract void render(Graphics graphics);

    public int getWindowWidth() {
        return Converter.FHD_SCREEN_WIDTH;
    }

    public int getWindowHeight() {
        return Converter.FHD_SCREEN_HEIGHT;
    }
}