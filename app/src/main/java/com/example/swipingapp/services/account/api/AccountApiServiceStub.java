package com.example.swipingapp.services.account.api;

import com.example.swipingapp.services.base.api.IBaseApiServiceStub;
import com.example.swipingapp.viewModels.account.LoginViewModel;
import com.example.swipingapp.viewModels.account.RegisterViewModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.Calls;

public class AccountApiServiceStub<T extends IAccountApiService> implements IAccountApiService, IBaseApiServiceStub<T> {

    // region Constants

    private BehaviorDelegate<T> delegate;

    // endregion

    // region Users

    @Override
    public Call<ResponseBody> login(@Body LoginViewModel loginViewModel) {
        Response<ResponseBody> response;
        ResponseBody responseBody;

        if(loginViewModel.username.equals("admin") && loginViewModel.password.equals("123")) {
            responseBody = ResponseBody.create(null, "login success");
            response = Response.success(responseBody);
        } else {
            responseBody = ResponseBody.create(null, "login error");
            response = Response.error(404, responseBody);
        }

        return delegate.returning(Calls.response(response)).login(loginViewModel);
    }

    @Override
    public Call<ResponseBody> register(@Body RegisterViewModel registerViewModel) {
        Response<ResponseBody> response;
        ResponseBody responseBody;

        // Accept if the passwords match
        // TODO: Need more fail cases
        if(registerViewModel.password.length() > 0 && registerViewModel.password.equals(registerViewModel.confirmPassword)) {
            responseBody = ResponseBody.create(null, "register success");
            response = Response.success(responseBody);
        } else {
            responseBody = ResponseBody.create(null, "register error");
            response = Response.error(400, responseBody);
        }

        return delegate.returning(Calls.response(response)).register(registerViewModel);
    }

    @Override
    public void setBehaviorDelegate(BehaviorDelegate<T> delegate) {
        this.delegate = delegate;
    }

    // endregion
}
