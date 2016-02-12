package com.example.swipingapp.services.base.api;

import retrofit2.mock.BehaviorDelegate;

public interface IBaseApiServiceStub<T> {

    void setBehaviorDelegate(BehaviorDelegate<T> delegate);
}
