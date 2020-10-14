package com.k300;

import com.k300.display.Window;
import com.k300.graphics.Assets;
import com.k300.io.MouseListener;
import com.k300.states.GameState;
import com.k300.states.StateManager;
import com.k300.utils.math.Converter;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Launcher {

    private static boolean isRunning;
    private Window window;
    private MouseListener mouseListener;
    private int fps;

    public Launcher() {
        isRunning = false;
    }

    public void start() {
        if(isRunning) {
            return;
        }
        initialize();
        runGameLoop();
    }

    public static synchronized void stop() {
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
        mouseListener = new MouseListener();
        window.addMouseListener(mouseListener);
        setKeyListener(new com.k300.io.KeyListener());
//        StateManager.setCurrentState(
//                new OpeningFadeState(this,
//                    Assets.getImage(Assets.K_300_LOGO_KEY),
//                    new OpenFadeListener()
//                )
//        );

        //Testing
        StateManager.setCurrentState(new GameState(this));
    }

    private void runGameLoop() {
        window.setVisible();
        isRunning = true;
        final double FRAMES_PER_SECOND = 60;
        double timePerUpdate = 1000000000 / FRAMES_PER_SECOND;
        double timeFromLastUpdate = 0;
        long now;
        long last = System.nanoTime();
        int timer = 0;
        int ticks = 0;

        while (isRunning) {
            now = System.nanoTime();
            timeFromLastUpdate += (now - last) / timePerUpdate;

            timer += now - last;

            last = now;
            if(timeFromLastUpdate >= 1) {
                tick();
                render();
                timeFromLastUpdate--;

                ticks++;
            }

            if(timer >= 1000000000) {
                fps = ticks;
                ticks = 0;
                timer = 0;
            }
        }
    }

    private void tick() {
        if(StateManager.getCurrentState() != null) {
            StateManager.getCurrentState().tick();
        }
    }

    private void render() {
        Graphics windowGraphics = window.getGraphics();
        window.clear();
        if(StateManager.getCurrentState() != null) {
            BufferedImage fhdImage = getFDHImage();
            Graphics fhdGraphics = fhdImage.getGraphics();
            StateManager.getCurrentState().render(fhdGraphics);
            drawImageRelativeToScreen(windowGraphics, fhdImage);
            windowGraphics.drawString("FPS: " + fps, 30, 60);
        }
        window.show();
    }

    private BufferedImage getFDHImage() {
        return new BufferedImage(Converter.FHD_SCREEN_WIDTH, Converter.FHD_SCREEN_HEIGHT, Assets.getImage(Assets.TRACK_KEY).getType());
    }

    private void drawImageRelativeToScreen(Graphics windowGraphics, BufferedImage fullHdImage) {
        double relativeWidth = Converter.getProportionalNumber(Converter.FHD_SCREEN_WIDTH);
        double relativeHeight = Converter.getProportionalNumber(Converter.FHD_SCREEN_HEIGHT);
        double centeredY = getCenteredY(relativeHeight);
        windowGraphics.drawImage(fullHdImage, 0, (int) centeredY, (int) relativeWidth, (int) relativeHeight, null);
    }

    private double getCenteredY(double relativeHeight) {
        double fullOriginalHeight = Converter.SCREEN_HEIGHT;
        return (fullOriginalHeight - relativeHeight) / 2;
    }

    public MouseListener getMouseListener() {
        return mouseListener;
    }

    public void startGame() {
        mouseListener.setUiManager(null);
        StateManager.setCurrentState(new GameState(this));
    }

}
