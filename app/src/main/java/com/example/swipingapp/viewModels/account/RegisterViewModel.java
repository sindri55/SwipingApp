package com.example.swipingapp.viewModels.account;

public class RegisterViewModel {

    // Properties
    public String fullName;
    public String email;
    public String password;
    public String confirmPassword;

    // Constructors
    public RegisterViewModel(String fullName, String email, String password, String confirmPassword) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
}
