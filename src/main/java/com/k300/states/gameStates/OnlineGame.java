package com.k300.states.gameStates;

import com.k300.Launcher;
import com.k300.cars.Car;
import com.k300.cars.player_car.PlayerCar;
import com.k300.io.api.WebInteractor;
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
        webInteractor = new WebInteractor(track);
        this.sumOfPlayers = sumOfPlayers;
        dots = "";
        timeFromLastUpdate = 0;
        track.setCars();
    }

    @Override
    public void tick() {
        if(webInteractor.gameIsStarted()) {
            super.tick();
        }
        PlayerCar playerCar = (PlayerCar) track.cars[0];
        if (playerCar != null) {
            webInteractor.update(playerCar);
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

    @Override
    public Car[] getCars(Collisions playerCollisionLogic, StartLine startLine, int sumOfCars) {
        webInteractor.startMatch(sumOfCars);
        Car[] cars = new Car[sumOfCars];
        cars[0] = webInteractor.getPlayerCar(playerCollisionLogic, startLine);
        assert cars[0] != null;
        launcher.setKeyListener(((PlayerCar) cars[0]).getKeyListener());
        return cars;
    }

}
