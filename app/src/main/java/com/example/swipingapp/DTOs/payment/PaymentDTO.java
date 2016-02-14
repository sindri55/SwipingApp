package com.example.swipingapp.DTOs.payment;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.swipingapp.enums.Currency;

import java.util.ArrayList;

public class PaymentDTO implements Parcelable {

    // region Properties

    public Currency currency;
    public double amount;
    public ArrayList<PaymentItemDTO> items;

    // endregion

    public PaymentDTO(Currency currency, double amount, ArrayList<PaymentItemDTO> items) {
        this.currency = currency;
        this.amount = amount;
        this.items = items;
    }

    // region Parcelable

    protected PaymentDTO(Parcel in) {
        this.currency = (Currency) in.readSerializable();
        this.amount = in.readDouble();
        this.items = in.createTypedArrayList(PaymentItemDTO.CREATOR);
    }

    public static final Creator<PaymentDTO> CREATOR = new Creator<PaymentDTO>() {
        @Override
        public PaymentDTO createFromParcel(Parcel in) {
            return new PaymentDTO(in);
        }

        @Override
        public PaymentDTO[] newArray(int size) {
            return new PaymentDTO[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(currency);
        dest.writeDouble(amount);
        dest.writeTypedList(items);
    }

    // endregion
}
