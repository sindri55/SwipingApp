package com.example.swipingapp.services.account;

import com.example.swipingapp.viewModels.account.LoginViewModel;
import com.example.swipingapp.viewModels.account.RegisterViewModel;

import okhttp3.ResponseBody;
import retrofit2.Callback;

public interface IAccountService {

    // region Public functions

    void login(LoginViewModel loginViewModel, Callback<ResponseBody> response);
    void register(RegisterViewModel registerViewModel, Callback<ResponseBody> response);

    // endregion
}