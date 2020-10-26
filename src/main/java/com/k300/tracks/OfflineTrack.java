package com.k300.tracks;

import com.k300.cars.Car;
import com.k300.cars.player_car.PlayerCar;
import com.k300.display.Toast;
import com.k300.io.PlayerKeyListener;
import com.k300.obstacles.StartLine;
import com.k300.states.gameStates.GameState;
import com.k300.tracks.trackLogic.Collisions;
import com.k300.tracks.trackLogic.Margins;
import com.k300.utils.configarations.Config;
import com.k300.utils.math.Converter;

import java.awt.*;

public class OfflineTrack extends Track {

    public OfflineTrack(GameState gameState, Car[] cars) {
        super(gameState, cars);
        Margins margins = new Margins();
        for (int i = 0; i < cars.length; i++) {
            StartLine startLine = new StartLine(margins.getFrameBigBPoint(), margins.getFrameSmallBPoint());
            Collisions collisions = new Collisions(margins, obstacleManager);
            cars[i] = getLocalPlayer(collisions, startLine, i);
        }
        if(!Config.isInDevMode()) {
            toastToUserKeyMessage();
        }
    }

    private Car getLocalPlayer(Collisions playerCollisionLogic, StartLine startLine, int carIndex) {
        assert cars[carIndex] instanceof PlayerCar;
        PlayerCar localPlayer = (PlayerCar) cars[carIndex];
        localPlayer.setCollisions(playerCollisionLogic);
        localPlayer.setStartLine(startLine);
        gameState.getLauncher().setKeyListener(localPlayer.getKeyListener());
        return localPlayer;
    }

    private void toastToUserKeyMessage() {
        StringBuilder toastMessage = new StringBuilder();
        for (int i = 0; i < cars.length; i++) {
            toastMessage.append("The ");
            toastMessage.append(getCarColor(i));
            toastMessage.append(" Car will be moved by ");
            toastMessage.append(getCarKeyListener(i));
            toastMessage.append("   ");
        }
        Toast.makeToast(gameState.getLauncher().getWindowJComponent(), toastMessage.toString(), Toast.VERY_LONG);
    }

    private String getCarColor(int carIndex) {
        String carColor = cars[carIndex].carColor.split("_")[1];
        final String firstLetter = String.valueOf(carColor.charAt(0));
        return carColor.replaceFirst(firstLetter, firstLetter.toUpperCase());
    }

    private String getCarKeyListener(int carIndex) {
        assert cars[carIndex] instanceof PlayerCar;
        return ((PlayerKeyListener)((PlayerCar) cars[carIndex]).getKeyListener()).getKeysAsString();
    }

    @Override
    protected void renderDevMonitor(Graphics graphics) {
        graphics.setFont(new Font("Tahoma", Font.PLAIN, 40));

        for (Car car : cars) {
            assert car instanceof PlayerCar;
            if (car.carColor.contains("blue")) {
                graphics.setColor(Color.blue);

                graphics.drawString("Angle: " + (int) car.angle, 50, Converter.FHD_SCREEN_HEIGHT - 50);
                graphics.drawString("X: " + (int) car.position.x, 50, Converter.FHD_SCREEN_HEIGHT - 100);
                graphics.drawString("Y: " + (int) car.position.y, 50, Converter.FHD_SCREEN_HEIGHT - 150);

            } else if (car.carColor.contains("red")) {
                graphics.setColor(Color.red);

                graphics.drawString("Angle: " + (int) car.angle, Converter.FHD_SCREEN_WIDTH - 200, Converter.FHD_SCREEN_HEIGHT - 50);
                graphics.drawString("X: " + (int) car.position.x, Converter.FHD_SCREEN_WIDTH - 200, Converter.FHD_SCREEN_HEIGHT - 100);
                graphics.drawString("Y: " + (int) car.position.y, Converter.FHD_SCREEN_WIDTH - 200, Converter.FHD_SCREEN_HEIGHT - 150);
            }
        }
    }

}

