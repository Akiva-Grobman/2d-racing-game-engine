package com.k300.states.gameStates;

import com.k300.Launcher;
import com.k300.cars.Car;
import com.k300.cars.EnemyCar;
import com.k300.display.Toast;
import com.k300.graphics.Assets;
import com.k300.io.api.WebInteractor;
import com.k300.io.api.models.Player;
import com.k300.states.MenuState;
import com.k300.states.StateManager;
import com.k300.tracks.OnlineTrack;
import com.k300.utils.math.Converter;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import static com.k300.utils.Utils.drawStringInCenter;

public class OnlineGame extends GameState {

    public final int sumOfPlayers;
    private String dots;
    private int timeFromLastUpdate;
    private double loadingAngle;
    private BufferedImage loadingCar;
    final WebInteractor webInteractor;

    public OnlineGame(Launcher launcher, int sumOfPlayers) {
        super(launcher);
        webInteractor = new WebInteractor(this);
        this.sumOfPlayers = sumOfPlayers;
        dots = "";
        timeFromLastUpdate = 0;

        loadingAngle = 0;
        loadingCar = Assets.getImage(Assets.RED_CAR_KEY);


        webInteractor.startMatch(sumOfPlayers);
    }

    public void updateEnemyCars(Player[] enemyPlayers) {
        ((OnlineTrack) track).updateEnemyCars(enemyPlayers);
    }

    @Override
    public void tick() {
        if(webInteractor.gameIsStarted()) {
            super.tick();
        }
        if(track != null) {
            webInteractor.updatePlayerPositions(((OnlineTrack) track).getLocalCar());
        }
    }

    @Override
    public void render(Graphics graphics) {
        if(webInteractor.gameIsStarted()) {
            super.render(graphics);
        } else {
            loading(graphics);
            //updateDots();
//            drawStringInCenter(graphics.getFontMetrics().stringWidth(dots),
//                    0,
//                    Converter.FHD_SCREEN_WIDTH,
//                    Converter.FHD_SCREEN_HEIGHT,
//                    graphics,
//                    "Loading" + dots);
        }
    }

    private void updateDots() {
        timeFromLastUpdate++;
        if (timeFromLastUpdate >= 30) {
            timeFromLastUpdate = 0;
            if(dots.length() == 3){
                dots = "";
            } else {
                dots += ".";
            }
        }
    }

    private void loading(Graphics graphics) {
        AffineTransform carAngle = AffineTransform.getTranslateInstance(Converter.FHD_SCREEN_WIDTH / 2f - loadingCar.getWidth() / 2f - 100, Converter.FHD_SCREEN_HEIGHT / 2f -  loadingCar.getHeight() / 2f - 100);
        carAngle.rotate(Math.toRadians(-loadingAngle), loadingCar.getWidth() / 2f, loadingCar.getHeight() / 2f); //need Minus because Java is multiplier minus
        ((Graphics2D) graphics).drawImage(loadingCar, carAngle, null);
        loadingAngle += 1;
    }

    public void connectionError(String errorMessage) {
        Toast.makeToast(launcher.getWindowJComponent(), errorMessage, Toast.LONG);
        StateManager.setCurrentState(new MenuState(launcher));
    }

    public void finishSetup() {
        track = new OnlineTrack(this, getInitCars());
    }

    private Car[] getInitCars() {
        Car[] cars = new Car[sumOfPlayers];
        cars[OnlineTrack.LOCAL_PLAYER_INDEX] = webInteractor.getPlayerCar();
        assert cars[OnlineTrack.LOCAL_PLAYER_INDEX] != null;
        for (int i = 1; i < cars.length; i++) {
            cars[i] = new EnemyCar();
        }
        return cars;
    }

}
