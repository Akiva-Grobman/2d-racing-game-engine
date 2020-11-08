package com.k300.io.api.handlers;

import com.k300.io.api.WebInteractor;
import com.k300.io.api.models.PostResponse;
import retrofit2.Call;
import retrofit2.Response;

/*
*       Purpose:
*           this will handle all post request responses.
*           the response body will contain a PostResponse object (see more in the PostResponse class).
*           the updateCars method from the webInteractor object will be called with the updated data form the server.
*/

public class PostRequestHandler extends MyCallBack<PostResponse> {

    // only initialization option
    public PostRequestHandler(WebInteractor webInteractor) {
        // initialize abstract call back
        super(webInteractor);
    }

    // this method will be called when the server sends a response to a post request
    @Override
    public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
        // call the parent method for on response
        super.onResponse(call, response);
        // store the response body
        final PostResponse body = response.body();
        // if in dev mode
        if(isInDevMode) {
            // print response
            System.out.print("Update Response \n>>\n");
            System.out.println(WebInteractor.getPrettyGson().toJson(body));
        }
        // update the game status field in the webInteractor object (true/false)
        webInteractor.setGameIsStarted(body.getRoom().isGameStarted());
        if(isInDevMode) {
            System.out.println("Updating cars...");
        }
        // this will tell the webInteractor object to update the cars based on the updated info
        webInteractor.updateCars(body.getPlayers());
    }

}
