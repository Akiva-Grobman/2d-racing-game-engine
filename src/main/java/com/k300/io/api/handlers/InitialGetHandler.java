package com.k300.io.api.handlers;

import com.k300.io.api.WebInteractor;
import com.k300.io.api.models.GameStartingInfo;
import retrofit2.Call;
import retrofit2.Response;

/*
*       Purpose:
*           this will handle the first get request response.
*           the response will contain a GameStartingInfo object (see more GameStartingInfo class).
*           the room id and player in the web interactor will be set by the response object(GameStartingInfo).
*
*/

public class InitialGetHandler extends MyCallBack<GameStartingInfo> {

    // only initialization option
    public InitialGetHandler(WebInteractor webInteractor) {
        // initialize abstract call back
        super(webInteractor);
    }

    // this method will be called when the server sends a response
    @Override
    public void onResponse(Call<GameStartingInfo> call, Response<GameStartingInfo> response) {
        // call the parent method for on response
        super.onResponse(call, response);
        // initialize a GameStartingInfo object from the response object
        GameStartingInfo startingInfo = response.body();
        // set the player field in the webInteractor
        webInteractor.setLocalPlayer(startingInfo.getPlayer());
        // set the room id field in the webInteraction
        webInteractor.setRoomId(startingInfo.getRoom().getId());
        // if in dev mode
        if(isInDevMode) {
            // print the response object
            System.out.print("Initial Get Request response\n>>\n");
            System.out.println(WebInteractor.getPrettyGson().toJson(startingInfo));
        }
        // this method call will finish the setup of the online game
        webInteractor.finishGameStateSetup();
    }

}
