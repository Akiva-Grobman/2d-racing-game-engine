package com.k300.io.api;

import com.k300.io.api.models.GameStartingInfo;
import com.k300.io.api.models.PostBody;
import com.k300.io.api.models.PostResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface API {

    @GET("/api/quick-match/{sumOfPlayers}")
    Call<GameStartingInfo> getCarLocalColor(@Path("sumOfPlayers")  int sumOfPlayers);

    @POST("/api/room/{roomId}")
    Call<PostResponse> updateCarData(@Path("roomId") String roomId, @Body PostBody postBody);

}
