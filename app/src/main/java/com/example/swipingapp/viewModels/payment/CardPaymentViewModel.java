package com.example.swipingapp.viewModels.payment;

import com.example.swipingapp.DTOs.payment.PaymentDTO;

public class CardPaymentViewModel extends PaymentViewModel {

    // region Properties

    public String cardholder;
    public String cardNumber;
    public String expireMonth;
    public String expireYear;
    public String CVC;

    // endregion

    // region Constructors

    public CardPaymentViewModel(PaymentDTO paymentDto, String cardholder, String cardNumber, String expireMonth, String expireYear, String CVC) {
        super(paymentDto);

        this.cardholder = cardholder;
        this.cardNumber = cardNumber;
        this.expireMonth = expireMonth;
        this.expireYear = expireYear;
        this.CVC = CVC;
    }

    // endregion
}
