package com.example.swipingapp.services.base.api;

import retrofit2.mock.BehaviorDelegate;

public class BaseApiServiceStub<T> {

    // region Properties

    protected BehaviorDelegate<T> mDelegate;

    // endregion

    // region Public functions

    public void setBehaviorDelegate(BehaviorDelegate<T> delegate) {
        mDelegate = delegate;
    }

    // endregion
}
