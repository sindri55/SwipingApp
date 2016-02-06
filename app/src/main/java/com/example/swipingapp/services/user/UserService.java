package com.example.swipingapp.services.user;

import com.example.swipingapp.DTOs.UserDTO;
import com.example.swipingapp.exceptions.NotImplementedException;

public class UserService implements IUserService {

    // region Properties

    private static IUserService mInstance;

    // endregion

    // region Get instance (Singleton)

    public static IUserService getInstance() {
        if(mInstance == null) {
            mInstance = new UserService();
        }

        return mInstance;
    }

    // endregion

    // region Override functions

    @Override
    public UserDTO getUser(int userId) {
        throw new NotImplementedException();
    }

    // endregion
}
