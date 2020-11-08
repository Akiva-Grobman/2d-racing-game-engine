package com.k300.io.api.handlers;

import com.k300.io.api.WebInteractor;
import com.k300.utils.configarations.Config;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.net.ConnectException;

/*
*       Purpose:
*           this is an abstract representation of a response from the server.
*           both responses extend this response because they both handle errors the same.
*       Contains:
*           a WebInteractor object to modify based on the server response, a flag for dev mode(if in dev mode all incoming data is logged to the console).
*       Methods:
*           onResponse(call, response)
*               -> will be called when the server sends a response(the abstract handling only makes sure the dev mode flag is up to date).
*           onFailure(call, t)
*               -> will be called if the server fail to send a response. this method is final because their should be no other implementation for error handling.
*                  if there's a connection failure(couldn't connect to server, or disconnected for server) we let the web interactor handle it.
*                  if it's a different kind of error we exit the program will code 1
*/

public abstract class MyCallBack<T> implements Callback<T> {

    // reference to the web interactor
    protected final WebInteractor webInteractor;
    // dev mode flag
    protected boolean isInDevMode;

    // only initialization option (initialize web interactor)
    protected MyCallBack(WebInteractor webInteractor) {
        this.webInteractor = webInteractor;
    }

    // will be called when a response from the server is received
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        // update dev mode flag
        isInDevMode = Config.isInDevMode();
    }

    // will be called if there's an error with the server connection
    @Override
    public final void onFailure(Call<T> call, Throwable t) {
        // store error message
        final String errorMessage = t.getMessage();
        // log error message
        System.out.println(errorMessage);
        // if it's a connection error
        if(t instanceof ConnectException) {
            // let web interactor handle
            webInteractor.connectionError(errorMessage);
        } else {
            // exit program with code 1
            System.exit(1);
        }
    }

}
