package com.example.swipingapp.services.api;

import com.example.swipingapp.viewModels.account.LoginViewModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IApiService {

    // region Users

    @POST("users/login/")
    Call<ResponseBody> login(@Body LoginViewModel loginViewModel);

    // endregion
}
