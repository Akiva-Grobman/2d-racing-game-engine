package com.k300.tracks;

import com.k300.cars.Car;
import com.k300.cars.player_car.PlayerCar;
import com.k300.graphics.Assets;
import com.k300.io.api.models.Player;
import com.k300.obstacles.StartLine;
import com.k300.states.gameStates.GameState;

import static com.k300.states.gameStates.GameState.startingPosition;

public class OfflineTrack extends Track {

    public OfflineTrack(GameState gameState, Car[] cars) {
        super(gameState, cars);
        Margins margins = new Margins();
        StartLine startLine = new StartLine(margins.getFrameBigBPoint(), margins.getFrameSmallBPoint());
        Collisions collisions = new Collisions(margins, obstacleManager);
        for (int i = 0; i < cars.length; i++) {
            cars[i] = getLocalPlayer(collisions, startLine, i);
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

}

