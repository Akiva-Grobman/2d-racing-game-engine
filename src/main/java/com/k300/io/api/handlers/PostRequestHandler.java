package com.k300.io.api.handlers;

import com.k300.io.api.WebInteractor;
import com.k300.io.api.models.PostResponse;
import com.k300.utils.configarations.Config;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostRequestHandler extends MyCallBack<PostResponse> {

    public PostRequestHandler(WebInteractor webInteractor) {
        super(webInteractor);
    }

    @Override
    public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
        super.onResponse(call, response);
        final PostResponse body = response.body();
        if(isInDevMode) {
            System.out.print("Update Response \n>>\n");
            System.out.println(WebInteractor.getPrettyGson().toJson(body));
        }
        webInteractor.setGameIsStarted(body.getRoom().isGameStarted());
        if(isInDevMode) {
            System.out.println("Updating cars...");
        }
        webInteractor.updateCars(body.getPlayers());
    }

}
