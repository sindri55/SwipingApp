package com.example.swipingapp.services.user;

import com.example.swipingapp.DTOs.UserDTO;

public interface IUserService {

    // region Public functions

    UserDTO getUser(int userId);

    // endregion
}
