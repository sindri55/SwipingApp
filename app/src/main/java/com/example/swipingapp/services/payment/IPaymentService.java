package com.example.swipingapp.services.payment;

import com.example.swipingapp.DTOs.ReceiptDTO;
import com.example.swipingapp.services.base.IBaseService;
import com.example.swipingapp.viewModels.payment.CardPaymentViewModel;
import com.example.swipingapp.viewModels.payment.PaymentViewModel;

import retrofit2.Callback;

public interface IPaymentService extends IBaseService {

    // region Public functions

    void payWithCard(CardPaymentViewModel cardPaymentViewModel, Callback<ReceiptDTO> response);
    void payWithNFC(PaymentViewModel paymentViewModel, Callback<ReceiptDTO> response);  // TODO: Implement

    // endregion
}
