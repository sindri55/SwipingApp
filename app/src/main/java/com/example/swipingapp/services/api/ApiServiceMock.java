package com.example.swipingapp.services.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.mock.BehaviorDelegate;

public final class ApiServiceMock implements IApiService {

    // region Constants

    private final BehaviorDelegate<IApiService> delegate;

    // endregion

    // region Constructors

    public ApiServiceMock(BehaviorDelegate<IApiService> delegate) {
        this.delegate = delegate;
    }

    // endregion

    // region Users

    @Override
    public Call<Response> login(@Field("username") String email, @Field("password") String password) {
        Response response;
        ResponseBody responseBody;

        if(email.equals("admin") && password.equals("123")) {
            responseBody = ResponseBody.create(null, "error");
            response = Response.success(responseBody);
        } else {
            responseBody = ResponseBody.create(null, "success");
            response = Response.error(401, responseBody);
        }

        return delegate.returningResponse(response).login(email, password);
    }

    // endregion
}