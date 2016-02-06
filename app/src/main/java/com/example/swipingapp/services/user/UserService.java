package com.example.swipingapp.services.user;

import com.example.swipingapp.DTOs.UserDTO;
import com.example.swipingapp.exceptions.NotImplementedException;

public class UserService implements IUserService {

    // Properties
    private static IUserService mInstance;

    // Get instance (Singleton)
    public static IUserService getInstance() {
        if(mInstance == null) {
            mInstance = new UserService();
        }

        return mInstance;
    }

    // Override functions
    @Override
    public UserDTO getUser(int userId) {
        throw new NotImplementedException();
    }
}
