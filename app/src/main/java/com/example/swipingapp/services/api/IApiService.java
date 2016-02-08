package com.example.swipingapp.services.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IApiService {

    // region Users

    @FormUrlEncoded
    @POST("users/login/")
    Call<Response> login(@Field("username") String email, @Field("password") String password);

    // endregion
}
