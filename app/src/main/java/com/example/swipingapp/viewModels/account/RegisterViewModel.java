package com.example.swipingapp.viewModels.account;

public class RegisterViewModel {

    // region Properties

    public String name;
    public String username; // TODO: remove
    public String email;
    public String password;
    public String confirmPassword;

    // endregion

    // region Constructors

    public RegisterViewModel(String name, String email, String password, String confirmPassword) {
        this.name = name;
        this.username = email;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    // endregion
}
