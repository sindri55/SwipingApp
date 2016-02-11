package com.example.swipingapp.services.base;

import com.example.swipingapp.services.api.IApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseService {

    // region Constants

    private static final String API_URL = "http://tappit.osom.link/api/v1/";

    // endregion

    // region Properties

    private static IApiService mApiService;

    // endregion

    // region Get instance (Singleton)

    protected static IApiService getApiService() {
        if(mApiService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            mApiService = retrofit.create(IApiService.class);
        }

        return mApiService;
    }

    // endregion
}
