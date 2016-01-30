package com.example.swipingapp.viewModels.payment;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.swipingapp.enums.Currency;

public class PaymentViewModel implements Parcelable {

    // Properties
    public double amount;
    public Currency currency;
    public CreditCardViewModel creditCardViewModel;

    // Constructors
    public PaymentViewModel(double amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    // Parcelable
    protected PaymentViewModel(Parcel in) {
        amount = in.readDouble();
        currency = (Currency) in.readSerializable();
    }

    public static final Creator<PaymentViewModel> CREATOR = new Creator<PaymentViewModel>() {
        @Override
        public PaymentViewModel createFromParcel(Parcel in) {
            return new PaymentViewModel(in);
        }

        @Override
        public PaymentViewModel[] newArray(int size) {
            return new PaymentViewModel[size];
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
