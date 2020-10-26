package com.k300.io.api.handlers;

import com.k300.io.api.WebInteractor;
import com.k300.io.api.models.GameStartingInfo;
import retrofit2.Call;
import retrofit2.Response;

public class InitialGetHandler extends MyCallBack<GameStartingInfo> {

    private volatile GameStartingInfo startingInfo;

    public InitialGetHandler(WebInteractor webInteractor) {
        super(webInteractor);
        startingInfo = null;
    }

    @Override
    public void onResponse(Call<GameStartingInfo> call, Response<GameStartingInfo> response) {
        super.onResponse(call, response);
        startingInfo = response.body();
        webInteractor.setLocalPlayer(startingInfo.getPlayer());
        webInteractor.setRoomId(startingInfo.getRoom().getId());
        if(isInDevMode) {
            System.out.print("Initial Get Request response\n>>\n");
            System.out.println(WebInteractor.getPrettyGson().toJson(startingInfo));
        }
        webInteractor.finishGameStateSetup();
    }

}
