package com.example.swipingapp.services.payment.api;

import android.text.TextUtils;

import com.example.swipingapp.DTOs.payment.PaymentConfirmedDTO;
import com.example.swipingapp.responses.ErrorResponse;
import com.example.swipingapp.services.base.api.BaseApiServiceStub;
import com.example.swipingapp.viewModels.payment.CardPaymentViewModel;
import com.example.swipingapp.viewModels.payment.PaymentViewModel;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.mock.Calls;

public class PaymentApiServiceStub<T extends IPaymentApiService> extends BaseApiServiceStub<T> implements IPaymentApiService {

    // region API endpoints

    @Override
    public Call<PaymentConfirmedDTO> payWithCard(@Body CardPaymentViewModel cardPaymentViewModel) {
        Response<PaymentConfirmedDTO> response;
        ResponseBody responseBody;

        if(!TextUtils.isEmpty(cardPaymentViewModel.cardholder)) {
            PaymentConfirmedDTO paymentConfirmedDto = new PaymentConfirmedDTO("ORDER001", "28/08/2015", cardPaymentViewModel.amount);
            response = Response.success(paymentConfirmedDto);
        } else {
            ErrorResponse errorResponse = new ErrorResponse("Payment failed!");
            responseBody = ResponseBody.create(MediaType.parse(MEDIA_TYPE), mGson.toJson(errorResponse));
            response = Response.error(404, responseBody);
        }

        return mDelegate.returning(Calls.response(response)).payWithCard(cardPaymentViewModel);
    }

    @Override
    public Call<PaymentConfirmedDTO> payWithNfc(@Body PaymentViewModel paymentViewModel) {
        Response<PaymentConfirmedDTO> response;
        ResponseBody responseBody;

        if(paymentViewModel.amount > 0) {
            PaymentConfirmedDTO paymentConfirmedDto = new PaymentConfirmedDTO("ORDER001", "28/08/2015", paymentViewModel.amount);
            response = Response.success(paymentConfirmedDto);
        } else {
            ErrorResponse errorResponse = new ErrorResponse("Payment failed!");
            responseBody = ResponseBody.create(MediaType.parse(MEDIA_TYPE), mGson.toJson(errorResponse));
            response = Response.error(404, responseBody);
        }

        return mDelegate.returning(Calls.response(response)).payWithNfc(paymentViewModel);
    }

    // endregion
}
