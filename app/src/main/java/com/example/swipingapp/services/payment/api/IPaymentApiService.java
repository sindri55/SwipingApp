package com.example.swipingapp.services.payment.api;

import com.example.swipingapp.DTOs.ReceiptDTO;
import com.example.swipingapp.viewModels.payment.CardPaymentViewModel;
import com.example.swipingapp.viewModels.payment.PaymentViewModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IPaymentApiService {

    // region API endpoints

    @POST("payments/card/")
    Call<ReceiptDTO> payWithCard(@Body CardPaymentViewModel cardPaymentViewModel);

    @POST("payments/nfc/")
    Call<ReceiptDTO> payWithNfc(@Body PaymentViewModel paymentViewModel);

    // endregion
}
