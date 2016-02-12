package com.example.swipingapp.services.base;

import android.util.Log;

import com.example.swipingapp.services.base.api.IBaseApiServiceStub;

import java.util.concurrent.TimeUnit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

public class BaseServiceStub<T, S extends IBaseApiServiceStub> {

    // region Constants

    private static final String API_URL = "http://tappit.osom.link/api/v1/";

    // endregion

    // region Properties

    private Class<T> mApiServiceType;
    private Class<S> mApiServiceStubType;
    private T mApiService;

    // endregion

    // region Constructors

    public BaseServiceStub(Class<T> apiServiceType, Class<S> apiServiceStubType) {
        mApiServiceType = apiServiceType;
        mApiServiceStubType = apiServiceStubType;
    }

    // endregion

    // region Get instance (Singleton)

    protected T getApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NetworkBehavior behavior = NetworkBehavior.create();
        MockRetrofit mockRetrofit = new MockRetrofit.Builder(retrofit)
                .networkBehavior(behavior)
                .build();

        BehaviorDelegate<T> delegate = mockRetrofit.create(mApiServiceType);

        try {
            S apiServiceStub = mApiServiceStubType.newInstance();
            apiServiceStub.setBehaviorDelegate(delegate);
            mApiService = (T) apiServiceStub;
        } catch (Exception e) {
            Log.e("mApiService", e.getMessage());
        }

        behavior.setDelay(2000, TimeUnit.MILLISECONDS);

        return mApiService;
    }

    // endregion
}
