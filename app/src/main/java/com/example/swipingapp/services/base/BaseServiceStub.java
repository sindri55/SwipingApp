package com.example.swipingapp.services.base;

import com.example.swipingapp.services.base.api.BaseApiServiceStub;

import java.util.concurrent.TimeUnit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

public class BaseServiceStub<T extends BaseApiServiceStub> {

    // region Constants

    private static final String API_URL = "http://tappit.osom.link/api/v1/";

    // endregion

    // region Properties

    private Class<T> mApiServiceStubType;
    private T mApiService;

    // endregion

    // region Constructors

    public BaseServiceStub(Class<T> apiServiceStubType) {
        mApiServiceStubType = apiServiceStubType;
    }

    // endregion

    // region Get instance (Singleton)

    protected T getApiService() {
        T apiServiceStub;
        Class<?> apiServiceInterface;
        try {
            apiServiceStub = mApiServiceStubType.newInstance();
            apiServiceInterface = apiServiceStub.getClass().getInterfaces()[0];
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NetworkBehavior behavior = NetworkBehavior.create();
        MockRetrofit mockRetrofit = new MockRetrofit.Builder(retrofit)
                .networkBehavior(behavior)
                .build();

        BehaviorDelegate delegate = mockRetrofit.create(apiServiceInterface);
        apiServiceStub.setBehaviorDelegate(delegate);

        mApiService = apiServiceStub;

        behavior.setDelay(2000, TimeUnit.MILLISECONDS);

        return mApiService;
    }

    // endregion
}
