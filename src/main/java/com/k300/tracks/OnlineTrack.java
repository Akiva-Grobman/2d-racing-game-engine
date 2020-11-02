package com.k300.tracks;

import com.k300.cars.Car;
import com.k300.cars.player_car.PlayerCar;
import com.k300.graphics.ZoomInCamera;
import com.k300.io.api.models.Player;
import com.k300.obstacles.StartLine;
import com.k300.states.gameStates.GameState;
import com.k300.tracks.trackLogic.Collisions;
import com.k300.tracks.trackLogic.Margins;
import com.k300.utils.math.Converter;

import java.awt.*;
import java.util.Arrays;
import java.util.stream.IntStream;

/*
*       Purpose:
*           this represents a track for an online game i.e. only one local player, and with a zoom in option.
*
*/

public class OnlineTrack extends Track {

    // store the index of the local player
    public static final int LOCAL_PLAYER_INDEX = 0;

    // only initialization option
    public OnlineTrack(GameState gameState, Car[] cars) {
        // initialize the abstract track
        super(gameState, cars);
        // create a margins object, this is used to determine the track dimensions (see more in Margins class).
        Margins margins = new Margins();
        // create a start line object, this is used to track the number of rounds (see more in StartLine class).
        StartLine startLine = new StartLine(margins.getFrameBigBPoint(), margins.getFrameSmallBPoint());
        // create a collisions object, this is used to calculate local player collisions (see more in the Collisions class).
        Collisions collisions = new Collisions(margins, obstacleManager);
        // initialize the local player (with the collisions, and start line information).
        cars[LOCAL_PLAYER_INDEX] = initLocalCar(collisions, startLine, LOCAL_PLAYER_INDEX);
    }

    // render using the zoom camera (see more in the ZoomCamera class)
    void renderWithZoom(Graphics graphics) {
        // initialize a zoom in camera object
        ZoomInCamera zoomInCamera = new ZoomInCamera(graphics, cars);
        // render
        zoomInCamera.render();
    }

    // local car accessor
    public Car getLocalCar() {
        return cars[LOCAL_PLAYER_INDEX];
    }

    // will update all enemy cars based on the list received from the server
    public void updateEnemyCars(Player[] newCars) {
        // this list will not contain the local players values hence it being one item shorter than the local car list
        assert newCars.length + 1 == cars.length;
        // the first position contains the local player so we only start changing cars from index 1
        for (int i = 0; i < newCars.length; i++) {
            updateEnemyCar(i + 1, newCars[i]);
        }
//        IntStream.range(1, newCars.length)
//                .forEach(i -> updateEnemyCar(i, newCars[i]));
    }

    // render the dev monitor (this is used in dev mode to display the local players x,y,angle)
    @Override
    protected void renderDevMonitor(Graphics graphics) {
        // change font to Tahoma
        graphics.setFont(new Font("Tahoma", Font.PLAIN, 40));
        // the first car will always be the local car instance
        assert cars[LOCAL_PLAYER_INDEX] instanceof PlayerCar;
        // change color to white
        graphics.setColor(Color.white);
        // draw angle, x, y on the bottom right corner
        graphics.drawString("Angle: " + (int) cars[LOCAL_PLAYER_INDEX].angle, 50, Converter.FHD_SCREEN_HEIGHT - 50);
        graphics.drawString("X: " + (int) cars[LOCAL_PLAYER_INDEX].position.x, 50, Converter.FHD_SCREEN_HEIGHT - 100);
        graphics.drawString("Y: " + (int) cars[LOCAL_PLAYER_INDEX].position.y, 50, Converter.FHD_SCREEN_HEIGHT - 150);
    }

    // will update the values of an enemy
    private void updateEnemyCar(int index, Player updatedCar) {
        // this done in case to order of players in the list received from the server has change it's order
        cars[index].updateColor(updatedCar.getColor());
        // update the x coordinate
        cars[index].position.x = updatedCar.getX();
        // update the y coordinate
        cars[index].position.y = updatedCar.getY();
        // update the car angle
        cars[index].angle = updatedCar.getAngle();
    }

}
