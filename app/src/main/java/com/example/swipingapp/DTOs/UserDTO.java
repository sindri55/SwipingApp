package com.example.swipingapp.DTOs;

public class UserDTO {

    // region Properties

    public int userId;
    public String name;
    public String email;

    // endregion

    // region Constructors

    public UserDTO(int userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    // endregion
}
