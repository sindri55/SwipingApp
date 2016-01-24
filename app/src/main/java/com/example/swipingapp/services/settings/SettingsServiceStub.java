package com.example.swipingapp.services.settings;

import com.example.swipingapp.enums.Currency;

public class SettingsServiceStub implements ISettingsService {

    // Properties
    private static ISettingsService mInstance;

    // Public functions
    public static ISettingsService getInstance() {
        if(mInstance == null) {
            mInstance = new SettingsServiceStub();
        }

        return mInstance;
    }

    // Override functions
    @Override
    public Currency getUserCurrency() {
        return Currency.ICELANDIC_KRONA;
    }
}
