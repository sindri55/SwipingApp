package com.example.swipingapp.services.settings;

import com.example.swipingapp.enums.Currency;

public class SettingsServiceStub implements ISettingsService {

    // region Properties

    private static ISettingsService mInstance;

    // endregion

    // region Get instance (Singleton)

    public static ISettingsService getInstance() {
        if(mInstance == null) {
            mInstance = new SettingsServiceStub();
        }

        return mInstance;
    }

    // endregion

    // region Override functions

    @Override
    public Currency getUserCurrency() {
        return Currency.ICELANDIC_KRONA;
    }

    // endregion
}
