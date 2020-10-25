package com.k300.io.api.handlers;

import com.k300.io.api.WebInteractor;
import com.k300.utils.configarations.Config;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.net.ConnectException;

public abstract class MyCallBack<T> implements Callback<T> {

    protected final WebInteractor webInteractor;
    protected boolean isInDevMode;

    protected MyCallBack(WebInteractor webInteractor) {
        this.webInteractor = webInteractor;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        isInDevMode = Config.isInDevMode();
    }

    @Override
    public final void onFailure(Call<T> call, Throwable t) {
        final String errorMessage = t.getMessage();
        System.out.println(errorMessage);
        if(t instanceof ConnectException) {
            webInteractor.connectionError(errorMessage);
        } else {
            System.out.println(errorMessage);
            System.exit(1);
        }
    }

}
