package com.k300.io.api;

import com.k300.io.api.models.GameStartingInfo;
import com.k300.io.api.models.PostBody;
import com.k300.io.api.models.PostResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/*
*       Purpose:
*           this class contains abstract representations of the methods that will interact with the server.
*       Methods:
*           (get) initLocalCar(sumOfPlayers)
*               this will send a get request to a url that is determined by the method parameter.
*               the url looks as follows: base url + /api/quick-match/ + the method parameter (a number representing the sum of players the game will have).
*               the server will respond to this by adding the local player to a room (if all rooms are full a new one will be opened).
*               the program will wait for the response asynchronously,
*               the servers response will contain a GameStartingInfo object that contains all the necessary data to start a game(see more in the GameStartingInfo class).
*           (post) updateCarData(roomId, postBody)
*               this will set a post request to a url that is determined by the methods first parameter.
*               the url looks as follows: base url + /api/room/ + the first parameter of the method (the room id).
*               the post body will contain a PostBody object that contains all of the local players info (see more in the PostBody class).
*               the server will update the info of the player based on the post request body.
*               the program will wait for the response asynchronously,
*               the servers response will contain a PostResponse object that contains the data of the other players (see more in the PostBody class).
*/

public interface API {

    // this get request will add the local player to a room and get the local players starting info.
    @GET("/api/quick-match/{sumOfPlayers}")
    Call<GameStartingInfo> initLocalCar(@Path("sumOfPlayers")  int sumOfPlayers);

    // this post request will update the server representation of the local player and will get back the info of the other players
    @POST("/api/room/{roomId}")
    Call<PostResponse> updateCarData(@Path("roomId") String roomId, @Body PostBody postBody);

}
