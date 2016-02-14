package com.example.swipingapp.enums;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public enum Currency {

    // region Values

    ICELANDIC_KRONA         ("ISK"),
    US_DOLLAR               ("USD");

    // endregion

    // region Properties

    private String currencyCode;

    // endregion

    // region Constructors

    Currency(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    // endregion

    // region Public functions

    public String getCurrencyCode() {
        return this.currencyCode;
    }

    public NumberFormat getFormatter() {
        Locale locale = new Locale("is", "IS");
        switch (this) {
            case ICELANDIC_KRONA:
                locale = new Locale("is", "IS");
                break;
            case US_DOLLAR:
                locale = new Locale("en", "US");
                break;
        }

        return NumberFormat.getCurrencyInstance(locale);
    }

    public ArrayList<Integer> getAmountSpinnerValues() {
        ArrayList<Integer> values = new ArrayList<>();

        switch (this) {
            case ICELANDIC_KRONA:
                values.add(500);
                values.add(1000);
                values.add(2000);
                values.add(5000);
                values.add(10000);
                break;
            case US_DOLLAR:
                values.add(5);
                values.add(10);
                values.add(25);
                values.add(50);
                values.add(100);
                break;
        }

        return values;
    }

    // endregion
}
