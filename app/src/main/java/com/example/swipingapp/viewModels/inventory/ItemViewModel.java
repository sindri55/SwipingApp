package com.example.swipingapp.viewModels.inventory;

import com.example.swipingapp.enums.Currency;

public class ItemViewModel {

    // region Properties

    public int categoryId;
    public String description;
    public double amount;
    public Currency currency;   // TODO: Should this be on each item?

    // endregion

    // region Constructors

    public ItemViewModel(int categoryId, String description, double amount, Currency currency) {
        this.categoryId = categoryId;
        this.description = description;
        this.amount = amount;
        this.currency = currency;
    }

    // endregion
}
