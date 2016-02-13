package com.example.swipingapp.services.payment;

import com.example.swipingapp.DTOs.payment.PaymentConfirmedDTO;
import com.example.swipingapp.services.base.BaseServiceStub;
import com.example.swipingapp.services.payment.api.PaymentApiServiceStub;
import com.example.swipingapp.viewModels.payment.CardPaymentViewModel;
import com.example.swipingapp.viewModels.payment.PaymentViewModel;

import retrofit2.Call;
import retrofit2.Callback;

public class PaymentServiceStub<T extends PaymentApiServiceStub> extends BaseServiceStub<T> implements IPaymentService {

    // region Properties

    private static IPaymentService mInstance;

    // endregion

    // region Constructors

    public PaymentServiceStub(Class<T> apiServiceStubType) {
        super(apiServiceStubType);
    }

    // endregion

    // region Get instance (Singleton)

    public static IPaymentService getInstance() {
        if(mInstance == null) {
            mInstance = new PaymentServiceStub<>(PaymentApiServiceStub.class);
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
