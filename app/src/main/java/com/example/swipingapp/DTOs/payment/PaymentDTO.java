package com.example.swipingapp.DTOs.payment;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.swipingapp.enums.Currency;

import java.util.ArrayList;

public class PaymentDTO implements Parcelable {

    // region Properties

    public ArrayList<PaymentItemDTO> items;
    public Currency currency;
    public double amount;

    // endregion

    public PaymentDTO(ArrayList<PaymentItemDTO> items, Currency currency) {
        this.items = items;
        this.currency = currency;

        this.amount = 0;
        for (PaymentItemDTO item: items) {
            this.amount += item.amount;
        }
    }

    // region Public functions

    public String getFormattedAmount() {
        return currency.getFormatter().format(amount);
    }

    // region Parcelable

    protected PaymentDTO(Parcel in) {
        this.items = in.createTypedArrayList(PaymentItemDTO.CREATOR);
        this.currency = (Currency) in.readSerializable();
        this.amount = in.readDouble();
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
        dest.writeTypedList(items);
        dest.writeSerializable(currency);
        dest.writeDouble(amount);
    }

    // endregion
}
