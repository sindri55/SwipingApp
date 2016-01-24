package com.example.swipingapp.services.settings;

import com.example.swipingapp.enums.Currency;
import com.example.swipingapp.exceptions.NotImplementedException;

public class SettingsService implements ISettingsService {

    // Properties
    private static ISettingsService mInstance;

    // Public functions
    public static ISettingsService getInstance() {
        if(mInstance == null) {
            mInstance = new SettingsService();
        }

        return mInstance;
    }

    // Override functions
    @Override
    public Currency getUserCurrency() {
        throw new NotImplementedException();
    }
}
