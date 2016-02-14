package com.example.swipingapp.viewModels.inventory;

import com.example.swipingapp.enums.Currency;

public class ItemViewModel {

    // region Properties

    public String description;
    public double amount;
    public Currency currency;   // TODO: Should this be on each item?

    // endregion

    // region Constructors

    public ItemViewModel(String description, double amount, Currency currency) {
        this.description = description;
        this.amount = amount;
        this.currency = currency;
    }

    // endregion
}
