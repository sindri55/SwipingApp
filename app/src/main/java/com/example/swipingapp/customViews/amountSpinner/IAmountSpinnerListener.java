package com.example.swipingapp.customViews.amountSpinner;

public interface IAmountSpinnerListener {

    // region Public functions

    void setAmount(double value);
    void increaseAmount(double value);
    void decreaseAmount(double value);

    // endregion
}
