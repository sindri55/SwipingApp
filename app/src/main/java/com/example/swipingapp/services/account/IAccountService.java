package com.example.swipingapp.services.account;

import com.example.swipingapp.viewModels.account.LoginViewModel;
import com.example.swipingapp.viewModels.account.RegisterViewModel;

public interface IAccountService {

    // Functions
    boolean login(LoginViewModel model);
    boolean register(RegisterViewModel model);
}