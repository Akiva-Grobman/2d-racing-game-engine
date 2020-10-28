package com.k300.states.gameStates;

import com.k300.Launcher;
import com.k300.cars.Car;
import com.k300.cars.EnemyCar;
import com.k300.display.Toast;
import com.k300.io.api.WebInteractor;
import com.k300.io.api.models.Player;
import com.k300.states.MenuState;
import com.k300.states.StateManager;
import com.k300.tracks.OnlineTrack;
import com.k300.utils.math.Converter;
import java.awt.*;
import static com.k300.utils.Utils.drawStringInCenter;

/*
*       Purpose:
*           manage the online game
*       Contains:
*           the sum of players (including the local player), a webInteractor object that will communicate with the server,
*           it also contains a dots string a timeFromLastUpdate that are used for the loading display before a game is started.
*/

public class OnlineGame extends GameState {

    // the sum of players (including the local player)
    public final int sumOfPlayers;
    // this is used for the loading display (will contain 0-3 dots)
    private String dots;
    // this represents the number of frames passed from last update to the dots variable
    private int timeFromLastUpdate;
    // this is the way we interact with the server
    private final WebInteractor webInteractor;

    // only initialization option
    public OnlineGame(Launcher launcher, int sumOfPlayers) {
        // initialize abstract game
        super(launcher);
        // initialize the interactor object
        webInteractor = new WebInteractor(this);
        // set sum of players
        this.sumOfPlayers = sumOfPlayers;
        // initialize dots as empty
        dots = "";
        // initialize as default
        timeFromLastUpdate = 0;
        // start the match
        webInteractor.startMatch(sumOfPlayers);
    }

    // this will update all of the enemy cars in the track
    public void updateEnemyCars(Player[] enemyPlayers) {
        ((OnlineTrack) track).updateEnemyCars(enemyPlayers);
    }

    // update local and online info
    @Override
    public void tick() {
        // if the game has started
        if(webInteractor.gameIsStarted()) {
            // update the local game
            super.tick();
        }
        // if the track has been initialized
        if(track != null) {
            // update the enemy players info(x,y, angle)
            webInteractor.updatePlayerPositions(((OnlineTrack) track).getLocalCar());
        }
    }

    // render the state
    @Override
    public void render(Graphics graphics) {
        // if the game has started
        if(webInteractor.gameIsStarted()) {
            // render the game
            super.render(graphics);
        } else {
            // update the string of dots
            updateDots();
            // draw "Loading" + the current state of dots in the middle of the screen
            drawStringInCenter(graphics.getFontMetrics().stringWidth(dots),
                    0,
                    Converter.FHD_SCREEN_WIDTH,
                    Converter.FHD_SCREEN_HEIGHT,
                    graphics,
                    "Loading" + dots);
        }
    }

    private void updateDots() {
        // add frame to timeFromLast Update
        timeFromLastUpdate++;
        // this will happen every 30 frames (usually twice per second)
        if (timeFromLastUpdate >= 30) {
            // reset frame count
            timeFromLastUpdate = 0;
            // if max dots reset
            if(dots.length() == 3){
                dots = "";
            } else {
                // add a dot
                dots += ".";
            }
        }
    }

    // this will "toast" the error message and switch to the menu state
    public void connectionError(String errorMessage) {
        Toast.makeToast(launcher.getWindowJComponent(), errorMessage, Toast.LONG);
        StateManager.setCurrentState(new MenuState(launcher));
    }

    // initialize the track
    public void finishSetup() {
        // initialize the track as a online track with an array of cars (first position as local player)
        track = new OnlineTrack(this, getInitCars());
    }

    // initialize cars array
    private Car[] getInitCars() {
        // initialize empty car array
        Car[] cars = new Car[sumOfPlayers];
        // set local player to player received from the server
        cars[OnlineTrack.LOCAL_PLAYER_INDEX] = webInteractor.getPlayerCar();
        // initialize the rest of the cars to enemy cars
        for (int i = 1; i < cars.length; i++) {
            cars[i] = new EnemyCar();
        }
        // return initialized cars array
        return cars;
    }

}
