package com.example.swipingapp.services.base;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseService<T> {

    // region Constants

    private static final String API_URL = "http://tappit.osom.link/api/v1/";

    // endregion

    // region Properties

    private final Class<T> mApiServiceType;
    private T mApiService;

    // endregion

    // region Constructors

    public BaseService(Class<T> apiServiceClassType) {
        mApiServiceType = apiServiceClassType;
    }

    // region Get instance (Singleton)

    protected T getApiService() {
        if(mApiService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            mApiService = retrofit.create(mApiServiceType);
        }

        return mApiService;
    }

    // endregion
}
