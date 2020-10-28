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

/*
*       Purpose:
*           this represent a track for offline games i.e. two local players
*
*/

public class OfflineTrack extends Track {

    // only initialization option
    public OfflineTrack(GameState gameState, Car[] cars) {
        // initialize abstract track
        super(gameState, cars);
        // create a margins object, this is used to determine the track dimensions (see more in Margins class).
        Margins margins = new Margins();
        // initialize all cars(2) to local players
        for (int i = 0; i < cars.length; i++) {
            // create a start line object, this is used to track the number of rounds (see more in StartLine class).
            StartLine startLine = new StartLine(margins.getFrameBigBPoint(), margins.getFrameSmallBPoint());
            // create a collisions object, this is used to calculate local player collisions (see more in the Collisions class).
            Collisions collisions = new Collisions(margins, obstacleManager);
            // initialize all car to local players
            cars[i] = initLocalCar(collisions, startLine, i);
        }
        // tell user how to control the cars (unnecessary in dev mode)
        if(!Config.isInDevMode()) {
            toastToUserKeyMessage();
        }
    }

    // render the dev monitor (this is used in dev mode to display the local players x,y,angle)
    @Override
    protected void renderDevMonitor(Graphics graphics) {
        // set font to Tahoma
        graphics.setFont(new Font("Tahoma", Font.PLAIN, 40));
        for (Car car : cars) {
            assert car instanceof PlayerCar;
            // draw the blue car x,y, and angle
            if (car.carColor.contains("blue")) {
                graphics.setColor(Color.blue);
                drawCarDevModeInfo(car, 50, graphics);
            // draw the red car x,y, and angle
            } else if (car.carColor.contains("red")) {
                graphics.setColor(Color.red);
                drawCarDevModeInfo(car, Converter.FHD_SCREEN_WIDTH - 200, graphics);
            }
        }
    }

    // draw dev mode info (x is the on screen location to draw to)
    private void drawCarDevModeInfo(Car car, int x, Graphics graphics) {
        graphics.drawString("Angle: " + (int) car.angle, x, Converter.FHD_SCREEN_HEIGHT - 50);
        graphics.drawString("X: " + (int) car.position.x, x, Converter.FHD_SCREEN_HEIGHT - 100);
        graphics.drawString("Y: " + (int) car.position.y, x, Converter.FHD_SCREEN_HEIGHT - 150);
    }

    // will "toast" a message to the user explaining how to control the cars
    private void toastToUserKeyMessage() {
        // initialize the message string
        StringBuilder toastMessage = new StringBuilder();
        for (int i = 0; i < cars.length; i++) {
            // add to the message car colors
            toastMessage.append("The ");
            toastMessage.append(getCarColor(i));
            // add to the massage what keys will move the car
            toastMessage.append(" Car will be moved by ");
            toastMessage.append(getCarKeyListener(i));
            toastMessage.append("   ");
        }
        // toast the user the message
        Toast.makeToast(gameState.getLauncher().getWindowJComponent(), toastMessage.toString(), Toast.VERY_LONG);
    }

    // will return the cars color with the first letter in upper case (this is used in the toast message)
    private String getCarColor(int carIndex) {
        // get color from assets key
        String carColor = cars[carIndex].carColor.split("_")[1];
        // get first color
        final String firstLetter = String.valueOf(carColor.charAt(0));
        // switch first letter to upper case
        return carColor.replaceFirst(firstLetter, firstLetter.toUpperCase());
    }

    // will return a string containing what keys move the car
    private String getCarKeyListener(int carIndex) {
        assert cars[carIndex] instanceof PlayerCar;
        return ((PlayerKeyListener)((PlayerCar) cars[carIndex]).getKeyListener()).getKeysAsString();
    }

}

