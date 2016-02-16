package com.example.swipingapp.services.cart;

import com.example.swipingapp.DTOs.payment.PaymentDTO;
import com.example.swipingapp.DTOs.payment.PaymentItemDTO;

public interface ICartService {

    // region Public functions

    public PaymentDTO getCartItems();
    public void addToCart(PaymentItemDTO paymentItem);
    public void removeFromCart(PaymentItemDTO paymentItem);
    public void clearCart();

    // endregion
}
