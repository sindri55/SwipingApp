package com.example.swipingapp.enums;

import java.util.ArrayList;
import java.util.Locale;

public enum Currency {

    // Values
    ICELANDIC_KRONA,
    US_DOLLAR;

    // Public functions
    public Locale getLocale() {
        switch (this) {
            case ICELANDIC_KRONA:
                return new Locale("is", "IS");
            case US_DOLLAR:
                return new Locale("en", "US");
        }

        return new Locale("is", "IS");
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
}
