package com.example.swipingapp.services.account;

import com.example.swipingapp.exceptions.NotImplementedException;
import com.example.swipingapp.viewModels.account.LoginViewModel;
import com.example.swipingapp.viewModels.account.RegisterViewModel;

public class AccountService implements IAccountService {

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
    public boolean login(LoginViewModel model) {
        throw new NotImplementedException();
    }

    @Override
    public boolean register(RegisterViewModel model) {
        throw new NotImplementedException();
    }

    // endregion
}
