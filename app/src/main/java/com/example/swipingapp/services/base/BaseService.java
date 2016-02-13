package com.example.swipingapp.services.base;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseService<T> implements IBaseService {

    // region Constants

    private static final String API_URL = "http://tappit.osom.link/api/v1/";

    // endregion

    // region Properties

    private final Class<T> mApiServiceType;
    private T mApiService;
    private Retrofit mRetrofit;

    // endregion

    // region Constructors

    public BaseService(Class<T> apiServiceClassType) {
        mApiServiceType = apiServiceClassType;
    }

    // endregion

    // region Protected functions

    protected T getApiService() {
        if(mApiService == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            mApiService = mRetrofit.create(mApiServiceType);
        }

        return mApiService;
    }

    // endregion

    // region Override functions

    @Override
    public Retrofit getRetrofit() {
        return mRetrofit;
    }

    // endregion
}
