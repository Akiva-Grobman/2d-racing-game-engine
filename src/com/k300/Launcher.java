package com.k300;

import com.k300.display.Window;
import com.k300.states.GameState;
import com.k300.states.StateManager;

import java.awt.*;
import java.awt.event.KeyListener;

public class Launcher {

    private boolean isRunning;
    private Window window;

    public Launcher() {
        isRunning = false;
    }

    public void launch() {
        if(isRunning) {
            return;
        }
        initialize();
        runGameLoop();
    }

    public synchronized void stop() {
        if(!isRunning) {
            return;
        }
        isRunning = false;
        System.exit(0);
    }

    public void setKeyListener(KeyListener listener) {
        window.setKeyListener(listener);
    }

    public int getWindowHeight() {
        return window.getFrameHeight();
    }

    public int getWindowWidth() {
        return window.getFrameWidth();
    }

    private void initialize() {
        window = new Window("2d-racing-game-engine");
        window.setBufferStrategy(3);
        //Testing(this should start with the menu state)
        StateManager.setCurrentState(new GameState(this));
    }

    private void runGameLoop() {
        isRunning = true;
        final double FRAMES_PER_SECOND = 60;
        double timePerUpdate = 1000000000 / FRAMES_PER_SECOND;
        double timeFromLastUpdate = 0;
        long currentTime;
        long previousTime = System.nanoTime();
        while (isRunning) {
            currentTime = System.nanoTime();
            timeFromLastUpdate += (currentTime - previousTime) / timePerUpdate;
            previousTime = currentTime;
            if(timeFromLastUpdate >= 1) {
                tick();
                render();
                timeFromLastUpdate--;
            }
        }
    }

    private void tick() {
        if(StateManager.getCurrentState() != null) {
            StateManager.getCurrentState().tick();
        }
    }

    private void render() {
        Graphics graphics = window.getGraphics();
        window.clear();
        if(StateManager.getCurrentState() != null) {
            StateManager.getCurrentState().render(graphics);
        }
        window.show();
    }

}
