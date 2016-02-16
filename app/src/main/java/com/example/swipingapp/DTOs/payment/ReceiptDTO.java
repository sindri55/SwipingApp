package com.example.swipingapp.DTOs.payment;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.swipingapp.enums.Currency;

import java.util.ArrayList;

public class ReceiptDTO implements Parcelable {

    // region Properties

    public PaymentDTO paymentDto;
    public PaymentConfirmedDTO paymentConfirmedDto;

    // endregion

    // region Constructors

    public ReceiptDTO(PaymentDTO paymentDto, PaymentConfirmedDTO paymentConfirmedDto) {
        this.paymentDto = paymentDto;
        this.paymentConfirmedDto = paymentConfirmedDto;
    }

    // endregion

    // region Public functions

    public ArrayList<PaymentItemDTO> getItems() {
        return paymentDto.items;
    }

    public Currency getCurrency() {
        return paymentDto.currency;
    }

    public String getFormattedAmount() {
        return paymentDto.getFormattedAmount();
    }

    // endregion

    // region Parcelable

    protected ReceiptDTO(Parcel in) {
        this.paymentDto = in.readParcelable(PaymentDTO.class.getClassLoader());
        this.paymentConfirmedDto = in.readParcelable(PaymentConfirmedDTO.class.getClassLoader());
    }

    public static final Creator<ReceiptDTO> CREATOR = new Creator<ReceiptDTO>() {
        @Override
        public ReceiptDTO createFromParcel(Parcel in) {
            return new ReceiptDTO(in);
        }

        @Override
        public ReceiptDTO[] newArray(int size) {
            return new ReceiptDTO[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(paymentDto, 0);
        dest.writeParcelable(paymentConfirmedDto, 0);
    }

    // endregion
}
