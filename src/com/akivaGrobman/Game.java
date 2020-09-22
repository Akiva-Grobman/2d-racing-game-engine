package com.akivaGrobman;

public class Game {

    private boolean isRunning;

    public Game() {

    }

    public void start() {
        initialize();
        runGameLoop();
    }

    public void stop() {
        isRunning = false;
    }

    private void initialize() {

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

    }

    private void render() {

    }

}
