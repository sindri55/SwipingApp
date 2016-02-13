package com.example.swipingapp.services.base.api;

import com.google.gson.Gson;

import retrofit2.mock.BehaviorDelegate;

public class BaseApiServiceStub<T> {

    // region Constants

    protected static final String MEDIA_TYPE = "application/json";

    // endregion

    // region Properties

    protected BehaviorDelegate<T> mDelegate;
    protected static Gson mGson;

    // endregion

    // region Constructors

    public BaseApiServiceStub() {
        mGson = new Gson();
    }

    // endregion

    // region Public functions

    public void setBehaviorDelegate(BehaviorDelegate<T> delegate) {
        mDelegate = delegate;
    }

    // endregion
}
