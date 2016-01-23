package com.example.swipingapp.services.account;

import com.example.swipingapp.viewModels.account.LoginViewModel;
import com.example.swipingapp.viewModels.account.RegisterViewModel;

public class AccountServiceStub implements IAccountService {

    // Properties
    private static IAccountService mInstance;

    // Functions
    public static IAccountService getInstance() {
        if(mInstance == null) {
            mInstance = new AccountServiceStub();
        }

        return mInstance;
    }

    // Override functions
    @Override
    public boolean login(LoginViewModel model) {

        // Simulate network access, sleep 2 seconds
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }

        // only accept login with email: "user" and pass: "123"
        return model.email.equals("user") && model.password.equals("123");
    }

    @Override
    public boolean register(RegisterViewModel model) {

        // Simulate network access, sleep for 2 seconds
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
        // Just always return true for now
        return true;
    }
}