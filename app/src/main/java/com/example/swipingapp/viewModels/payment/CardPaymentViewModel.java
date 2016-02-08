package com.example.swipingapp.viewModels.payment;

public class CardPaymentViewModel extends PaymentViewModel {

    // region Properties

    public String cardholder;
    public String cardNumber;
    public String expireMonth;
    public String expireYear;
    public String CVC;

    // endregion

    // region Constructors

    public CardPaymentViewModel(AmountViewModel amountViewModel, String cardholder, String cardNumber, String expireMonth, String expireYear, String CVC) {
        super(amountViewModel);

        this.cardholder = cardholder;
        this.cardNumber = cardNumber;
        this.expireMonth = expireMonth;
        this.expireYear = expireYear;
        this.CVC = CVC;
    }

    // endregion
}
