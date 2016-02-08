package com.example.swipingapp.viewModels.payment;

import com.example.swipingapp.enums.Currency;

public class PaymentViewModel {

    // region Properties

    public Currency currency;
    public double amount;

    // endregion

    // region Constructors

    public PaymentViewModel(AmountViewModel amountViewModel) {
        this.amount = amountViewModel.amount;
        this.currency= amountViewModel.currency;
    }

    // endregion
}
