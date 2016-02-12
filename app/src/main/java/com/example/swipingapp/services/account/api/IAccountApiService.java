package com.example.swipingapp.services.account.api;

import com.example.swipingapp.viewModels.account.LoginViewModel;
import com.example.swipingapp.viewModels.account.RegisterViewModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IAccountApiService {

    // region API endpoints

    // TODO: Should be "accounts/login/"
    @POST("users/login/")
    Call<ResponseBody> login(@Body LoginViewModel loginViewModel);

    // TODO: Should be "accounts/register/"
    @POST("users/register/")
    Call<ResponseBody> register(@Body RegisterViewModel registerViewModel);

    // endregion
}
