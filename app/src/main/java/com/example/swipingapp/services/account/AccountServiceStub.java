package com.example.swipingapp.services.account;

import com.example.swipingapp.services.base.BaseServiceMock;
import com.example.swipingapp.viewModels.account.LoginViewModel;
import com.example.swipingapp.viewModels.account.RegisterViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    public void login(LoginViewModel model, Callback<Response> response) {
        Call<Response> result = getApiService().login(model.email, model.password);
        result.enqueue(response);
    }

    @Override
    public boolean register(RegisterViewModel model) {
        // Just always return true for now
        return true;
    }

    // endregion
}