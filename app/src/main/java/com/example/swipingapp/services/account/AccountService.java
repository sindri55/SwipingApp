package com.example.swipingapp.services.account;

import com.example.swipingapp.exceptions.NotImplementedException;
import com.example.swipingapp.viewModels.account.LoginViewModel;
import com.example.swipingapp.viewModels.account.RegisterViewModel;

public class AccountService implements IAccountService {

    // Properties
    private static IAccountService mInstance;

    // Functions
    public static IAccountService getInstance() {
        if(mInstance == null) {
            mInstance = new AccountService();
        }

        return mInstance;
    }

    // Override functions
    @Override
    public boolean login(LoginViewModel model) {
        throw new NotImplementedException();
    }

    @Override
    public boolean register(RegisterViewModel model) {
        throw new NotImplementedException();
    }
}
