package com.k300.io.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.k300.cars.Car;
import com.k300.cars.EnemyCar;
import com.k300.cars.player_car.PlayerCar;
import com.k300.graphics.Assets;
import com.k300.io.api.models.GameStartingInfo;
import com.k300.io.api.models.Player;
import com.k300.io.api.models.PostBody;
import com.k300.io.api.models.PostResponse;
import com.k300.tracks.Collisions;
import com.k300.tracks.StartLine;
import com.k300.tracks.Track;
import com.k300.utils.Point;
import com.k300.utils.configarations.Config;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebInteractor {

    private final Track track;
    private volatile Player player;
    private volatile String roomId;
    private final API client;
    private final InitialCallBack initialCallBack;
    private volatile boolean gameIsStarted;

    public WebInteractor(Track track) {
        this.track = track;
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Config.getUrl())
                .addConverterFactory(GsonConverterFactory.create(getPrettyGson()));
        Retrofit retrofit = builder.build();
        client = retrofit.create(API.class);
        initialCallBack = new InitialCallBack();
        gameIsStarted = false;
    }

    public void startMatch(int sumOfPlayers) {
        Call<GameStartingInfo> call = client.getCarColor(sumOfPlayers);
        call.enqueue(initialCallBack);
    }

    public void update(PlayerCar playerCar) {
        Player player = new Player();
        player.setFinished(this.player.isFinished());
        player.setId(this.player.getId());
        player.setColor(this.player.getColor());
        player.setX(playerCar.position.x);
        player.setY(playerCar.position.y);
        player.setAngle(playerCar.angle);
        PostBody postBody = new PostBody();
        postBody.setPlayer(player);
        Call<PostResponse> call = client.updateCarDate(roomId, postBody);
        call.enqueue(new PostCallBack());
    }

    public boolean gameIsStarted() {
        return gameIsStarted;
    }

    private Gson getPrettyGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        return gsonBuilder.create();
    }

    private class InitialCallBack implements Callback<GameStartingInfo>{

        private volatile GameStartingInfo startingInfo = null;

        @Override
        public void onResponse(Call<GameStartingInfo> call, Response<GameStartingInfo> response) {
            startingInfo = response.body();
            player = startingInfo.getPlayer();
            roomId = startingInfo.getRoom().getId();
        }

        @Override
        public void onFailure(Call<GameStartingInfo> call, Throwable t) {
            System.out.println("Error " + t.getMessage());
            System.exit(1);
        }
    }

    public PlayerCar getPlayerCar(Collisions playersCollisionLogic, StartLine startLine) {
        waitUntilPlayerHasBeenInitialized();
        Player player = initialCallBack.startingInfo.getPlayer();
        return new PlayerCar(getCarColor(player.getColor()),
//                new Point(player.getX(), player.getY()),
                new Point(800, 800), //todo remove
                playersCollisionLogic,
                startLine);
    }

    private void waitUntilPlayerHasBeenInitialized() {
        while (initialCallBack.startingInfo == null) {
            try {
                //noinspection BusyWait
                Thread.sleep(5);
            } catch (InterruptedException ignored) {}
        }
    }

    private String getCarColor(String color) {
        color = color.toLowerCase();
        if(color.contains("red")) {
            return Assets.RED_CAR_KEY;
        } else if(color.contains("blue")) {
            return Assets.BLUE_CAR_KEY;
        }
        return Assets.YELLOW_CAR_KEY;
    }

    private class PostCallBack implements Callback<PostResponse> {

        @Override
        public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
            final Player[] players = response.body().getPlayers();
            gameIsStarted = response.body().getRoom().isGameStarted();
            track.updateCars(getCarArray(players));
        }

        private Car[] getCarArray(Player[] players) {
            Car[] cars = new Car[players.length - 1];
            int count = 0;
            for (Player player: players) {
                if(player.equals(WebInteractor.this.player)) {
                    continue;
                }
                cars[count] = new EnemyCar(getCarColor(player.getColor()), new Point(player.getX(), player.getY()));
            }
            return cars;
        }

        @Override
        public void onFailure(Call<PostResponse> call, Throwable t) {
            System.out.println(t.getMessage());
            System.exit(1);
        }

    }

}
