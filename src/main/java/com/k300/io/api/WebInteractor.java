package com.k300.io.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.k300.cars.Car;
import com.k300.cars.player_car.PlayerCar;
import com.k300.graphics.Assets;
import com.k300.io.api.handlers.InitialGetHandler;
import com.k300.io.api.handlers.PostRequestHandler;
import com.k300.io.api.models.GameStartingInfo;
import com.k300.io.api.models.Player;
import com.k300.io.api.models.PostBody;
import com.k300.io.api.models.PostResponse;
import com.k300.states.gameStates.GameState;
import com.k300.states.gameStates.OnlineGame;
import com.k300.utils.Point;
import com.k300.utils.configarations.Config;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebInteractor {

    private final OnlineGame game;
    private volatile Player player;
    private volatile String roomId;
    private final API client;
    private volatile boolean gameIsStarted;

    public WebInteractor(OnlineGame game) {
        this.game = game;
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Config.getUrl())
                .addConverterFactory(GsonConverterFactory.create(getPrettyGson()));
        Retrofit retrofit = builder.build();
        client = retrofit.create(API.class);
        gameIsStarted = false;
    }

    public void startMatch(int sumOfPlayers) {
        Call<GameStartingInfo> call = client.initLocalCar(sumOfPlayers);
        if(Config.isInDevMode()) {
            System.out.println("Initial Get Request\n>>\n" + sumOfPlayers);
        }
        call.enqueue(new InitialGetHandler(this));
    }

    public void updatePlayerPositions(Car playerCar) {
        player.setX(playerCar.position.x);
        player.setY(playerCar.position.y);
        player.setAngle(playerCar.angle);
        PostBody postBody = new PostBody();
        postBody.setPlayer(player);
        if(Config.isInDevMode()) {
            System.out.print("\n Sending Post Update \n>>\n");
            System.out.println("Room-id " + roomId);
            System.out.println("Body >>\n" + getPrettyGson().toJson(postBody));
        }
        Call<PostResponse> call = client.updateCarData(roomId, postBody);
        call.enqueue(new PostRequestHandler(this));
    }

    public void setGameIsStarted(boolean gameIsStarted) {
        this.gameIsStarted = gameIsStarted;
    }

    public boolean gameIsStarted() {
        return gameIsStarted;
    }

    public void setLocalPlayer(Player player) {
        assert this.player == null;
        this.player = player;
    }

    public void setRoomId(String roomId) {
        assert this.roomId == null;
        this.roomId = roomId;
    }

    public PlayerCar getPlayerCar() {
        Point carStartingPosition;
        carStartingPosition = GameState.startingPosition;
        return new PlayerCar(Assets.getCarKeyByValue(player.getColor()), carStartingPosition);
    }

    public static Gson getPrettyGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        return gsonBuilder.create();
    }

    public synchronized void updateCars(Player[] playersFromServer) {
        game.updateEnemyCars(getEnemies(playersFromServer));
    }

    private Player[] getEnemies(Player[] playersFromServer) {
        Player[] enemies = new Player[playersFromServer.length - 1];
        int enemyCount = 0;
        for (Player playerFromServer : playersFromServer) {
            if (playerFromServer.equals(player)) {
                continue;
            }
            enemies[enemyCount] = playerFromServer;
            enemyCount++;
        }
        return enemies;
    }

    public void connectionError(String errorMessage) {
        game.connectionError(errorMessage);
    }

    public void finishGameStateSetup() {
        game.finishSetup();
    }

}
