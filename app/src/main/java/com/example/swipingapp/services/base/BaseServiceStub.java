package com.example.swipingapp.services.base;

import com.example.swipingapp.services.base.api.BaseApiServiceStub;

import java.util.concurrent.TimeUnit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

public class BaseServiceStub<T extends BaseApiServiceStub> implements IBaseService {

    // region Constants

    private static final String API_URL = "http://tappit.osom.link/api/v1/";

    // endregion

    // region Properties

    private Class<T> mApiServiceStubType;
    private T mApiService;
    private Retrofit mRetrofit;

    // endregion

    // region Constructors

    public BaseServiceStub(Class<T> apiServiceStubType) {
        mApiServiceStubType = apiServiceStubType;
    }

    // endregion

    // region Protected functions

    protected T getApiService() {
        if(mApiService != null) {
            return mApiService;
        }

        T apiServiceStub;
        Class<?> apiServiceInterface;
        try {
            apiServiceStub = mApiServiceStubType.newInstance();
            apiServiceInterface = apiServiceStub.getClass().getInterfaces()[0];
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        mRetrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NetworkBehavior behavior = NetworkBehavior.create();
        MockRetrofit mockRetrofit = new MockRetrofit.Builder(mRetrofit)
                .networkBehavior(behavior)
                .build();

        BehaviorDelegate delegate = mockRetrofit.create(apiServiceInterface);
        apiServiceStub.setBehaviorDelegate(delegate);

        mApiService = apiServiceStub;

        behavior.setDelay(2000, TimeUnit.MILLISECONDS);

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
