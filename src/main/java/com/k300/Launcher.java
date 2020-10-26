package com.k300;

import com.k300.display.Window;
import com.k300.graphics.Assets;
import com.k300.graphics.FontLoader;
import com.k300.display.OpeningFadeState;
import com.k300.io.MouseListener;
import com.k300.states.SettingsState;
import com.k300.states.StateManager;
import com.k300.states.gameStates.OfflineGame;
import com.k300.states.gameStates.OnlineGame;
import com.k300.ui.listeners.OpenFadeListener;
import com.k300.utils.configarations.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

/*
*   Purpose:
*       This class represents the Launcher Object.
*   Contains:
*       The launcher object Contains a reference to the Window object, and it contains the game loop.
*   The main thread of the application will be trapped in this object (in the runGameLoop method).
*/

public class Launcher {

    // This represent the gameLoop running state (running/not running)
    private static boolean gameLoopIsRunning;
    // This is the instance of the display object
    private Window window;
    // This is the instance of the mouse listener
    private MouseListener mouseListener;
    // This will contain the number of frames rendered from the beginning of the second
    private int fps;

    // Object only initialization option
    public Launcher() {
        // This will stay false until the gameLoop is ready to begin
        gameLoopIsRunning = false;
    }

    // This method will initialize all starting data and than start the game loop
    public void start() {
        if(gameLoopIsRunning) { // this is to prevent the method from being called while a game has already been started
            return;
        }
        // initialize starting data
        initialize();
        // start game loop
        runGameLoop();
    }

    // This method will stop the game loop and close the program
    public static synchronized void stop() {
        if(!gameLoopIsRunning) { // this is so this method doesn't get called when the game loop isn't running
            return;
        }
        gameLoopIsRunning = false;
        System.exit(0);
    }

    // This method will initialize all starting data
    private void initialize() {
        // instantiate a new display object
        window = new Window("2d-racing-game-engine");
        // determine the display buffer strategy
        window.setBufferStrategy(3);
        // initialize the mouse listener
        mouseListener = new MouseListener();
        // add the mouse listener to the display object
        window.addMouseListener(mouseListener);
        // will set a key listener for the display object
        setKeyListener(new com.k300.io.KeyListener());
        // if we aren't in dev mode (see more in the config class), we'll start from the first state
        if(!Config.isInDevMode()) {
            // setting state to the first state (will show the k-300 logo with a fade effect
            StateManager.setCurrentState(
                    new OpeningFadeState(this,
                            Assets.getImage(Assets.K_300_INTRO_KEY),
                            new OpenFadeListener()
                    )
            );
        } else { // this is for development purposes only
            StateManager.setCurrentState(new SettingsState(this));
        }
    }

    // This method will start the game loop and trap the main thread of this program
    private void runGameLoop() {
        // now that everything has been initialized we can let the user see the display object
        window.setVisible();
        gameLoopIsRunning = true;
        // this will detriment the number of fps
        final double FRAMES_PER_SECOND = 60;
        // one second divided by the fps
        double timePerUpdate = 1000000000 / FRAMES_PER_SECOND;
        // when this greater or equals 1 we know we need to update again(see why in the loop)
        double timeFromLastUpdate = 0;
        // this will contain the current time
        long now;
        // this will contain the previous time of update
        long last = System.nanoTime();
        // this is used to calculate when a second has passed
        int timer = 0;
        // this will contain the current fps
        int ticks = 0;
        //game loop
        while (gameLoopIsRunning) {
            // store current time
            now = System.nanoTime();
            // add the amount of time passed form last update divided by the desired fps (this will reach 1[or more], the value of fps times per second)
            timeFromLastUpdate += (now - last) / timePerUpdate;
            // store the amount of time that has passed from last update
            timer += now - last;
            // update last
            last = now;
            // this // will happen the value of fps times per second(give or take 1)
            if(timeFromLastUpdate >= 1) {
                // update on screen backend values
                tick();
                // render screen
                render();
                // reset
                timeFromLastUpdate--;
                // add to tick count
                ticks++;
            }
            // this will happen every second
            if(timer >= 1000000000) {
                // update fps so it can be rendered on screen
                fps = ticks;
                // reset values
                ticks = 0;
                timer = 0;
            }
        }
    }

    // This method will update the current state
    private void tick() {
        // if we have a state to update
        if(StateManager.getCurrentState() != null) {
            // update the state
            StateManager.getCurrentState().tick();
        }
    }

    // This method will render the current state
    private void render() {
        // get rendering object from display object
        Graphics windowGraphics = window.getGraphics();
        // store the Minecraft font
        Font minecraft = FontLoader.loadFont("Minecraft", 40);
        // remove previously displayed images from display object
        window.clear();
        // if we have a state
        if(StateManager.getCurrentState() != null) {
            // render state
            StateManager.getCurrentState().render(windowGraphics);
            // set rendering object to white
            windowGraphics.setColor(Color.white);
            // set font to Minecraft
            windowGraphics.setFont(minecraft);
            // draw the current fps on the to left corner of the display object
            windowGraphics.drawString("FPS: " + fps, 30, 60);
        }
        // display onscreen updated rendering object
        window.show();
    }

    // This method will return the mouse listener used on the display object
    public MouseListener getMouseListener() {
        return mouseListener;
    }

    // This method will set the display object's key listener
    public void setKeyListener(KeyListener listener) {
        window.setKeyListener(listener);
    }

    // This method will set the current state to an offline game (essentially starting the game)
    public void startOfflineGame() {
        // remove the mouse listener from the display object (seeing as it's not needed for the actual game)
        mouseListener.setUiManager(null);
        // set the current state to an offline game
        StateManager.setCurrentState(new OfflineGame(this));
    }

    // This method will set the current state to an online game (essentially starting the game)
    public void startOnlineGame(int players) {
        // remove the mouse listener from the display object (seeing as it's not needed for the actual game)
        mouseListener.setUiManager(null);
        // set the current state to an offline game
        StateManager.setCurrentState(new OnlineGame(this, players));
    }

    // This method will return the JComponent of the display object
    public JComponent getWindowJComponent() {
        return window.getJComponent();
    }
}
