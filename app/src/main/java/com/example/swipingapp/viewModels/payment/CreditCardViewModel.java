package com.example.swipingapp.viewModels.payment;

public class CreditCardViewModel {

    // region Properties

    public String cardholder;
    public String cardNumber;
    public String expireMonth;
    public String expireYear;
    public String CVC;

    // endregion

    // region Constructors

    public CreditCardViewModel(String cardholder, String cardNumber, String expireMonth, String expireYear, String CVC) {
        this.cardholder = cardholder;
        this.cardNumber = cardNumber;
        this.expireMonth = expireMonth;
        this.expireYear = expireYear;
        this.CVC = CVC;
    }

    // endregion
}
