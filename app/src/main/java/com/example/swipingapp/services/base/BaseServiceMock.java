package com.example.swipingapp.services.base;

import com.example.swipingapp.services.api.ApiServiceMock;
import com.example.swipingapp.services.api.IApiService;

import java.util.concurrent.TimeUnit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

public class BaseServiceMock {

    // region Constants

    private static final String API_URL = "http://tappit.osom.link/api/v1/";

    // endregion

    // region Properties

    private static IApiService mApiService;

    // endregion

    // region Get instance (Singleton)

    protected static IApiService getApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NetworkBehavior behavior = NetworkBehavior.create();
        MockRetrofit mockRetrofit = new MockRetrofit.Builder(retrofit)
                .networkBehavior(behavior)
                .build();

        BehaviorDelegate<IApiService> delegate = mockRetrofit.create(IApiService.class);
        mApiService = new ApiServiceMock(delegate);

        behavior.setDelay(2000, TimeUnit.MILLISECONDS);

        return mApiService;
    }

    // endregion
}
