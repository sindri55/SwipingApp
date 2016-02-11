package com.example.swipingapp.services.account;

import com.example.swipingapp.services.base.BaseServiceMock;
import com.example.swipingapp.viewModels.account.LoginViewModel;
import com.example.swipingapp.viewModels.account.RegisterViewModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

public class AccountServiceStub extends BaseServiceMock implements IAccountService {

    // region Properties

    private static IAccountService mInstance;

    // endregion

    // region Get instance (Singleton)

    public static IAccountService getInstance() {
        if(mInstance == null) {
            mInstance = new AccountServiceStub();
        }

        return mInstance;
    }

    // endregion

    // region Override functions

    @Override
    public void login(LoginViewModel loginViewModel, Callback<ResponseBody> response) {
        Call<ResponseBody> result = getApiService().login(loginViewModel);
        result.enqueue(response);
    }

    @Override
    public void register(RegisterViewModel registerViewModel, Callback<ResponseBody> response) {
        Call<ResponseBody> result = getApiService().register(registerViewModel);
        result.enqueue(response);
    }

    // endregion
}