package com.example.swipingapp.services.payment;

import com.example.swipingapp.exceptions.NotImplementedException;
import com.example.swipingapp.viewModels.payment.CardPaymentViewModel;
import com.example.swipingapp.viewModels.payment.PaymentViewModel;

public class PaymentService implements IPaymentService {

    // region Properties

    private static IPaymentService mInstance;

    // endregion

    // region Get instance (Singleton)

    public static IPaymentService getInstance() {
        if(mInstance == null) {
            mInstance = new PaymentService();
        }

        return mInstance;
    }

    // endregion

    // region Override functions

    @Override
    public boolean payWithCard(CardPaymentViewModel cardPaymentViewModel) {
        throw new NotImplementedException();
    }

    @Override
    public boolean payWithNFC(PaymentViewModel paymentViewModel) {
        throw new NotImplementedException();
    }

    // endregion
}
