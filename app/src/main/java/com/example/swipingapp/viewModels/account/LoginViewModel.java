package com.example.swipingapp.viewModels.account;

public class LoginViewModel {

    // region Properties

    public String email;
    public String password;

    // endregion

    // region Constructors

    public LoginViewModel(String email, String password){
        this.email = email;
        this.password = password;
    }

    // endregion
}
