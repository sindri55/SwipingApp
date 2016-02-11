package com.example.swipingapp.services.user;

import com.example.swipingapp.DTOs.UserDTO;

public class UserServiceStub implements IUserService {

    // region Properties

    private static IUserService mInstance;

    // endregion

    // region Get instance (Singleton)

    public static IUserService getInstance() {
        if(mInstance == null) {
            mInstance = new UserServiceStub();
        }

        return mInstance;
    }

    // endregion

    // region Override functions

    @Override
    public UserDTO getUser(int userId) {
        // Simulate network access, sleep for 2 seconds
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }

        // Return static user
        return new UserDTO(1, "Sindri Þór", "sindri@55.is");
    }

    // endregion
}
