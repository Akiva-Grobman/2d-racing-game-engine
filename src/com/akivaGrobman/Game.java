package com.akivaGrobman;

import com.akivaGrobman.display.Window;
import com.akivaGrobman.graphics.Assets;

import java.awt.*;

public class Game {

    private boolean isRunning;
    private Window window;

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
//            GameStateManager.getCurrentState().render(graphics);
//        }
    }

    private void render() {
        Graphics graphics = window.getGraphics();
        window.clear();
        graphics.drawImage(Assets.getImage(Assets.TRACK_KEY), 0, 0, window.WIDTH, window.HEIGHT, null);
        // todo
//        if(GameStateManager.getCurrentState() != null) {
//            GameStateManager.getCurrentState().render(graphics);
//        }
        window.show();
    }

}
