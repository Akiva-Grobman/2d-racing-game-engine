package com.akivaGrobman;

import com.akivaGrobman.display.Window;
import com.akivaGrobman.tracks.Track;
import java.awt.*;

public class Launcher {

    private boolean isRunning;
    private Window window;

    public Launcher() {

    }

    public void start() {
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

    public Window getWindow() {
        return window;
    }

    private void initialize() {
        window = new Window("2d-racing-game-engine");
        window.setBufferStrategy(3);
    }

    private void runGameLoop() {
        isRunning = true;
        final double FRAMES_PER_SECOND = 60;
        double timePerUpdate = 1000000000 / FRAMES_PER_SECOND;
        double timeFromLastUpdate = 0;
        long now;
        long last = System.nanoTime();
        while (isRunning) {
            now = System.nanoTime();
            timeFromLastUpdate += (now - last) / timePerUpdate;
            last = now;
            if(timeFromLastUpdate >= 1) {
                tick();
                render();
                timeFromLastUpdate--;
            }
        }
    }

    private void tick() {
        // todo
//        if(GameStateManager.getCurrentState() != null) {
//            GameStateManager.getCurrentState().tick();
//        }
    }

    private void render() {
        Graphics graphics = window.getGraphics();
        window.clear();
        // todo
//        if(GameStateManager.getCurrentState() != null) {
//            GameStateManager.getCurrentState().render(graphics);
//        }
        window.show();
    }

}
