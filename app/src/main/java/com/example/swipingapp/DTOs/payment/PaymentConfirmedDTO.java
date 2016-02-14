package com.example.swipingapp.DTOs.payment;

import android.os.Parcel;
import android.os.Parcelable;

public class PaymentConfirmedDTO implements Parcelable {

    // region Properties

    public String orderId;
    public String orderDate; // TODO: Should be Date type
    public double amount;

    // endregion

    // region Constructors

    public PaymentConfirmedDTO(String orderId, String orderDate, double amount) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.amount = amount;
    }

    // endregion

    // region Parcelable

    protected PaymentConfirmedDTO(Parcel in) {
        this.orderId = in.readString();
        this.orderDate = in.readString();
        this.amount = in.readDouble();
    }

    public static final Creator<PaymentConfirmedDTO> CREATOR = new Creator<PaymentConfirmedDTO>() {
        @Override
        public PaymentConfirmedDTO createFromParcel(Parcel in) {
            return new PaymentConfirmedDTO(in);
        }

        @Override
        public PaymentConfirmedDTO[] newArray(int size) {
            return new PaymentConfirmedDTO[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(orderId);
        dest.writeString(orderDate);
        dest.writeDouble(amount);
    }

    // endregion
}
