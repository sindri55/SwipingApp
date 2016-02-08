package com.example.swipingapp.services.account;

import com.example.swipingapp.viewModels.account.LoginViewModel;
import com.example.swipingapp.viewModels.account.RegisterViewModel;

import retrofit2.Callback;
import retrofit2.Response;

public interface IAccountService {

    // region Public functions

    void login(LoginViewModel model, Callback<Response> response);
    boolean register(RegisterViewModel model);

    // endregion
}