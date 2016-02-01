package com.example.swipingapp.viewModels.payment;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.swipingapp.enums.Currency;

public class AmountViewModel implements Parcelable {

    // Properties
    public double amount;
    public Currency currency;

    // Constructors
    public AmountViewModel(double amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    // Parcelable
    protected AmountViewModel(Parcel in) {
        amount = in.readDouble();
        currency = (Currency) in.readSerializable();
    }

    public static final Creator<AmountViewModel> CREATOR = new Creator<AmountViewModel>() {
        @Override
        public AmountViewModel createFromParcel(Parcel in) {
            return new AmountViewModel(in);
        }

        @Override
        public AmountViewModel[] newArray(int size) {
            return new AmountViewModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(amount);
        dest.writeSerializable(currency);
    }
}
