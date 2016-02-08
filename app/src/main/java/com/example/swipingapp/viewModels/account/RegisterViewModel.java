package com.example.swipingapp.viewModels.account;

public class RegisterViewModel {

    // region Properties

    public String fullName;
    public String username; // TODO: remove
    public String email;
    public String password;
    public String confirmPassword;

    // endregion

    // region Constructors

    public RegisterViewModel(String fullName, String email, String password, String confirmPassword) {
        this.fullName = fullName;
        this.username = email;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    // endregion
}
