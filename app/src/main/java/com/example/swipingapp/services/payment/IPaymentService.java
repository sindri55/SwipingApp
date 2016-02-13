package com.example.swipingapp.services.payment;

import com.example.swipingapp.DTOs.payment.PaymentConfirmedDTO;
import com.example.swipingapp.services.base.IBaseService;
import com.example.swipingapp.viewModels.payment.CardPaymentViewModel;
import com.example.swipingapp.viewModels.payment.PaymentViewModel;

import retrofit2.Callback;

public interface IPaymentService extends IBaseService {

    // region Public functions

    void payWithCard(CardPaymentViewModel cardPaymentViewModel, Callback<PaymentConfirmedDTO> response);
    void payWithNFC(PaymentViewModel paymentViewModel, Callback<PaymentConfirmedDTO> response);  // TODO: Implement

    // endregion
}
