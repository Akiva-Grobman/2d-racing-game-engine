package com.k300;

import com.k300.display.Window;
import com.k300.graphics.Assets;
import com.k300.graphics.OpeningFadeState;
import com.k300.io.MouseListener;
import com.k300.states.GameState;
import com.k300.states.MenuState;
import com.k300.states.SettingsState;
import com.k300.states.StateManager;
import com.k300.ui.listeners.OpenFadeListener;
import com.k300.utils.configarations.Config;

import java.awt.*;
import java.awt.event.KeyListener;

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

    private void initialize() {
        window = new Window("2d-racing-game-engine");
        window.setBufferStrategy(3);
        mouseListener = new MouseListener();
        window.addMouseListener(mouseListener);
        setKeyListener(new com.k300.io.KeyListener());
        if(!Config.isInDevMode()) {
            StateManager.setCurrentState(
                    new OpeningFadeState(this,
                            Assets.getImage(Assets.K_300_INTRO_KEY),
                            new OpenFadeListener()
                    )
            );
        }/*enter testing code here and change in config to true*/ else {
            StateManager.setCurrentState(new SettingsState(this));
        }
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
            StateManager.getCurrentState().render(windowGraphics);
            windowGraphics.setColor(Color.white);
            windowGraphics.setFont(new Font("TimesRoman", Font.BOLD, 40));
            windowGraphics.drawString("FPS: " + fps, 30, 60);
        }
        window.show();
    }

    public MouseListener getMouseListener() {
        return mouseListener;
    }

    public void startGame() {
        mouseListener.setUiManager(null);
        StateManager.setCurrentState(new GameState(this));
    }

    public void startOnlineGame(int players) {
        mouseListener.setUiManager(null);
        StateManager.setCurrentState(new GameState(this));
    }

}
