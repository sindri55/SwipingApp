package com.example.swipingapp.viewModels.payment;

import com.example.swipingapp.DTOs.payment.ItemDTO;
import com.example.swipingapp.DTOs.payment.PaymentDTO;
import com.example.swipingapp.enums.Currency;

import java.util.ArrayList;

public class PaymentViewModel {

    // region Properties

    public Currency currency;
    public double amount;
    public ArrayList<ItemDTO> items;

    // endregion

    // region Constructors

    public PaymentViewModel(PaymentDTO paymentDto) {
        this.amount = paymentDto.amount;
        this.currency= paymentDto.currency;
        this.items = paymentDto.items;
    }

    // endregion
}
