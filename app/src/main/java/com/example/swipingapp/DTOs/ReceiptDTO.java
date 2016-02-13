package com.example.swipingapp.DTOs;

import android.os.Parcel;
import android.os.Parcelable;

// TODO: Complete
public class ReceiptDTO implements Parcelable {

    // region Properties

    public double amount;
    public String buyerName;

    // endregion

    // region Constructors

    public ReceiptDTO(double amount, String buyerName) {
        this.amount = amount;
        this.buyerName = buyerName;
    }

    // endregion

    // region Parcelable

    protected ReceiptDTO(Parcel in) {
        amount = in.readDouble();
        buyerName = in.readString();
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
        dest.writeDouble(amount);
        dest.writeString(buyerName);
    }

    // endregion
}
