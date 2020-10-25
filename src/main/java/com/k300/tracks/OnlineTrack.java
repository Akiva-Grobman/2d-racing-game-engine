package com.k300.tracks;

import com.k300.cars.Car;
import com.k300.cars.player_car.PlayerCar;
import com.k300.graphics.ZoomInCamera;
import com.k300.io.api.models.Player;
import com.k300.obstacles.StartLine;
import com.k300.states.gameStates.GameState;

import java.awt.*;

public class OnlineTrack extends Track {

    public static final int LOCAL_PLAYER_INDEX = 0;

    public OnlineTrack(GameState gameState, Car[] cars) {
        super(gameState, cars);
        Margins margins = new Margins();
        StartLine startLine = new StartLine(margins.getFrameBigBPoint(), margins.getFrameSmallBPoint());
        Collisions collisions = new Collisions(margins, obstacleManager);
        cars[LOCAL_PLAYER_INDEX] = initLocalCar(collisions, startLine);
    }

    void renderWithZoom(Graphics graphics) {
        ZoomInCamera zoomInCamera = new ZoomInCamera(graphics, cars);
        zoomInCamera.render();
    }

    public Car getLocalCar() {
        return cars[LOCAL_PLAYER_INDEX];
    }

    private Car initLocalCar(Collisions playerCollisionLogic, StartLine startLine) {
        assert cars[LOCAL_PLAYER_INDEX] instanceof PlayerCar;
        PlayerCar localPlayer = (PlayerCar) cars[LOCAL_PLAYER_INDEX];
        localPlayer.setCollisions(playerCollisionLogic);
        localPlayer.setStartLine(startLine);
        gameState.getLauncher().setKeyListener(localPlayer.getKeyListener());
        return localPlayer;
    }

    public void updateEnemyCars(Player[] newCars) {
        //update only enemy cars
        assert newCars.length + 1 == cars.length;
        int index = 1;
        for (Player updatedCar: newCars) {
            updateLocalCar(index, updatedCar);
            index++;
        }
    }

    private void updateLocalCar(int index, Player updatedCar) {
        cars[index].updateColor(updatedCar.getColor());
        cars[index].position.x = updatedCar.getX();
        cars[index].position.y = updatedCar.getY();
        cars[index].angle = updatedCar.getAngle();
    }

}
