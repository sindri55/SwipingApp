package com.example.swipingapp.services.payment;

import com.example.swipingapp.DTOs.payment.PaymentConfirmedDTO;
import com.example.swipingapp.services.base.BaseService;
import com.example.swipingapp.services.payment.api.IPaymentApiService;
import com.example.swipingapp.viewModels.payment.CardPaymentViewModel;
import com.example.swipingapp.viewModels.payment.PaymentViewModel;

import retrofit2.Call;
import retrofit2.Callback;

public class PaymentService<T extends IPaymentApiService> extends BaseService<T> implements IPaymentService {

    // region Properties

    private static IPaymentService mInstance;

    // endregion

    // region Constructors

    public PaymentService(Class<T> apiServiceClassType) {
        super(apiServiceClassType);
    }

    // endregion

    // region Get instance (Singleton)

    public static IPaymentService getInstance() {
        if(mInstance == null) {
            mInstance = new PaymentService<>(IPaymentApiService.class);
        }

        return mInstance;
    }

    // endregion

    // region Override functions

    @Override
    public void payWithCard(CardPaymentViewModel cardPaymentViewModel, Callback<PaymentConfirmedDTO> response) {
        Call<PaymentConfirmedDTO> result = getApiService().payWithCard(cardPaymentViewModel);
        result.enqueue(response);
    }

    @Override
    public void payWithNFC(PaymentViewModel paymentViewModel, Callback<PaymentConfirmedDTO> response) {
        Call<PaymentConfirmedDTO> result = getApiService().payWithNfc(paymentViewModel);
        result.enqueue(response);
    }

    // endregion
}
