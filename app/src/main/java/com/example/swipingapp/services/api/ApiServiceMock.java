package com.example.swipingapp.services.api;

import com.example.swipingapp.viewModels.account.LoginViewModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.Calls;

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
    public Call<ResponseBody> login(@Body LoginViewModel loginViewModel) {
        Response<ResponseBody> response;
        ResponseBody responseBody;

        if(loginViewModel.username.equals("admin") && loginViewModel.password.equals("123")) {
            responseBody = ResponseBody.create(null, "success");
            response = Response.success(responseBody);
        } else {
            responseBody = ResponseBody.create(null, "error");
            response = Response.error(404, responseBody);
        }

        return delegate.returning(Calls.response(response)).login(loginViewModel);
    }

    // endregion
}