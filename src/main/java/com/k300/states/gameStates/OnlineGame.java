package com.k300.states.gameStates;

import com.k300.Launcher;
import com.k300.cars.Car;
import com.k300.cars.EnemyCar;
import com.k300.cars.player_car.PlayerCar;
import com.k300.io.api.WebInteractor;
import com.k300.io.api.models.Player;
import com.k300.tracks.Collisions;
import com.k300.tracks.StartLine;
import com.k300.utils.math.Converter;
import java.awt.*;
import static com.k300.utils.Utils.drawStringInCenter;

public class OnlineGame extends GameState {

    public final int sumOfPlayers;
    private String dots;
    private int timeFromLastUpdate;
    WebInteractor webInteractor;

    public OnlineGame(Launcher launcher, int sumOfPlayers) {
        super(launcher);
        webInteractor = new WebInteractor(this);
        this.sumOfPlayers = sumOfPlayers;
        dots = "";
        timeFromLastUpdate = 0;
        new Thread(track::setCars).start();
    }

    public void updateCars(Player[] enemyPlayers) {
        track.updateCars(enemyPlayers);
    }

    @Override
    public void tick() {
        if(webInteractor.gameIsStarted()) {
            super.tick();
        }
        final Car[] cars = track.getCars();
        if(cars != null) {
            PlayerCar playerCar = (PlayerCar) cars[0];
            if (playerCar != null) {
                webInteractor.updatePlayerPositions(playerCar);
            }
        }
    }

    @Override
    public void render(Graphics graphics) {
        if(webInteractor.gameIsStarted()) {
            super.render(graphics);
        } else {
            updateDots();
            drawStringInCenter(graphics.getFontMetrics().stringWidth(dots),
                    0,
                    Converter.FHD_SCREEN_WIDTH,
                    Converter.FHD_SCREEN_HEIGHT,
                    graphics,
                    "Loading" + dots);
        }
    }

    @Override
    public Car[] getCars(Collisions playerCollisionLogic, StartLine startLine, int sumOfCars) {
        webInteractor.startMatch(sumOfCars);
        Car[] cars = new Car[sumOfCars];
        cars[0] = webInteractor.getPlayerCar(playerCollisionLogic, startLine);
        assert cars[0] != null;
        launcher.setKeyListener(((PlayerCar) cars[0]).getKeyListener());
        for (int i = 1; i < cars.length; i++) {
            cars[i] = new EnemyCar();
        }
        return cars;
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

}
