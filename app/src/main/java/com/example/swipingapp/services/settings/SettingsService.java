package com.example.swipingapp.services.settings;

import com.example.swipingapp.enums.Currency;
import com.example.swipingapp.exceptions.NotImplementedException;

public class SettingsService implements ISettingsService {

    // region Properties

    private static ISettingsService mInstance;

    // endregion

    // region Get instance (Singleton)

    public static ISettingsService getInstance() {
        if(mInstance == null) {
            mInstance = new SettingsService();
        }

        return mInstance;
    }

    // endregion

    // region Override functions

    @Override
    public Currency getUserCurrency() {
        throw new NotImplementedException();
    }

    // endregion
}
