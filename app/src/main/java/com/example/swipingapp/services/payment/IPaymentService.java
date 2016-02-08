package com.example.swipingapp.services.payment;

import com.example.swipingapp.viewModels.payment.CardPaymentViewModel;
import com.example.swipingapp.viewModels.payment.PaymentViewModel;

public interface IPaymentService {

    // region Public functions

    boolean payWithCard(CardPaymentViewModel cardPaymentViewModel);
    boolean payWithNFC(PaymentViewModel paymentViewModel);  // TODO: Implement

    // endregion
}
