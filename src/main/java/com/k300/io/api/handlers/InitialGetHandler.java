package com.k300.io.api.handlers;

import com.k300.io.api.WebInteractor;
import com.k300.io.api.models.GameStartingInfo;
import com.k300.utils.configarations.Config;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InitialGetHandler implements Callback<GameStartingInfo> {

    private final WebInteractor webInteractor;
    private volatile GameStartingInfo startingInfo;

    public InitialGetHandler(WebInteractor webInteractor) {
        this.webInteractor = webInteractor;
        startingInfo = null;
    }

    @Override
    public void onResponse(Call<GameStartingInfo> call, Response<GameStartingInfo> response) {
        startingInfo = response.body();
        webInteractor.setLocalPlayer(startingInfo.getPlayer());
        webInteractor.setRoomId(startingInfo.getRoom().getId());
        if(Config.isInDevMode()) {
            System.out.print("Initial Get Request response\n>>\n");
            System.out.println(WebInteractor.getPrettyGson().toJson(startingInfo));
        }
    }

    @Override
    public void onFailure(Call<GameStartingInfo> call, Throwable t) {
        System.out.println("Error " + t.getMessage());
        System.exit(1);
    }

    public boolean wasInitialized() {
        return startingInfo != null;
    }

}
