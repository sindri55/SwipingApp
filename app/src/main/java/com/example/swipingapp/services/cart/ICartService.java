package com.example.swipingapp.services.cart;

import android.content.SharedPreferences;

import com.example.swipingapp.DTOs.payment.PaymentDTO;
import com.example.swipingapp.DTOs.payment.PaymentItemDTO;

public interface ICartService {

    // region Public functions

    PaymentDTO getCartItems();
    void addToCart(PaymentItemDTO paymentItem);
    void removeFromCart(PaymentItemDTO paymentItem);
    void clearCart();

    void setSharedPreferencesListener(SharedPreferences.OnSharedPreferenceChangeListener listener);

    // endregion
}
