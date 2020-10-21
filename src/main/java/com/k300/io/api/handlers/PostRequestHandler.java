package com.k300.io.api.handlers;

import com.k300.io.api.WebInteractor;
import com.k300.io.api.models.PostResponse;
import com.k300.utils.configarations.Config;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostRequestHandler implements Callback<PostResponse> {

    private final WebInteractor webInteractor;

    public PostRequestHandler(WebInteractor webInteractor) {
        this.webInteractor = webInteractor;
    }

    @Override
    public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
        final PostResponse body = response.body();
        boolean isInDevMode = Config.isInDevMode();
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

    @Override
    public void onFailure(Call<PostResponse> call, Throwable t) {
        System.out.println(t.getMessage());
        System.exit(1);
    }

}
