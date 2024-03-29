package com.example.swipingapp.services.account.api;

import com.example.swipingapp.responses.ErrorResponse;
import com.example.swipingapp.services.base.api.BaseApiServiceStub;
import com.example.swipingapp.viewModels.account.LoginViewModel;
import com.example.swipingapp.viewModels.account.RegisterViewModel;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.mock.Calls;

public class AccountApiServiceStub<T extends IAccountApiService> extends BaseApiServiceStub<T> implements IAccountApiService {

    // region API endpoints

    @Override
    public Call<ResponseBody> login(@Body LoginViewModel loginViewModel) {
        Response<ResponseBody> response;
        ResponseBody responseBody;

        if(loginViewModel.username.equals("admin") && loginViewModel.password.equals("123")) {
            responseBody = ResponseBody.create(MediaType.parse(MEDIA_TYPE), "login success");
            response = Response.success(responseBody);
        } else {
            ErrorResponse errorResponse = new ErrorResponse("Incorrect email or password.\nPlease try again.");
            responseBody = ResponseBody.create(MediaType.parse(MEDIA_TYPE), mGson.toJson(errorResponse));
            response = Response.error(404, responseBody);
        }

        return mDelegate.returning(Calls.response(response)).login(loginViewModel);
    }

    @Override
    public Call<ResponseBody> register(@Body RegisterViewModel registerViewModel) {
        Response<ResponseBody> response;
        ResponseBody responseBody;

        // Accept if the passwords match
        // TODO: Need more fail cases
        if(registerViewModel.password.length() > 0 && registerViewModel.password.equals(registerViewModel.confirmPassword)) {
            responseBody = ResponseBody.create(MediaType.parse(MEDIA_TYPE), "register success");
            response = Response.success(responseBody);
        } else {
            ErrorResponse errorResponse = new ErrorResponse("We were unable to create an account for you.\nPlease try again.");
            responseBody = ResponseBody.create(MediaType.parse(MEDIA_TYPE), mGson.toJson(errorResponse));
            response = Response.error(400, responseBody);
        }

        return mDelegate.returning(Calls.response(response)).register(registerViewModel);
    }

    // endregion
}
