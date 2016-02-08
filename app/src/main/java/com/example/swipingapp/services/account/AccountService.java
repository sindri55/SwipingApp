package com.example.swipingapp.services.account;

import com.example.swipingapp.exceptions.NotImplementedException;
import com.example.swipingapp.services.base.BaseService;
import com.example.swipingapp.viewModels.account.LoginViewModel;
import com.example.swipingapp.viewModels.account.RegisterViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountService extends BaseService implements IAccountService {

    // region Properties

    private static IAccountService mInstance;

    // endregion

    // region Get instance (Singleton)

    public static IAccountService getInstance() {
        if(mInstance == null) {
            mInstance = new AccountService();
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
        throw new NotImplementedException();
    }

    // endregion
}
