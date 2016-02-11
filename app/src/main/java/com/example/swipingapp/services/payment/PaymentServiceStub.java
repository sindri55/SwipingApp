package com.example.swipingapp.services.payment;

import com.example.swipingapp.exceptions.NotImplementedException;
import com.example.swipingapp.viewModels.payment.CardPaymentViewModel;
import com.example.swipingapp.viewModels.payment.PaymentViewModel;

public class PaymentServiceStub implements IPaymentService {

    // region Properties

    private static IPaymentService mInstance;

    // endregion

    // region Get instance (Singleton)

    public static IPaymentService getInstance() {
        if(mInstance == null) {
            mInstance = new PaymentServiceStub();
        }

        return mInstance;
    }

    // endregion

    // region Override functions

    @Override
    public boolean payWithCard(CardPaymentViewModel cardPaymentViewModel) {
        // Simulate network access, sleep 2 seconds
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }

        // If amount is 500, payment is rejected, otherwise accepted
        return cardPaymentViewModel.amount != 500;
    }

    @Override
    public boolean payWithNFC(PaymentViewModel paymentViewModel) {
        throw new NotImplementedException();
    }

    // endregion
}
