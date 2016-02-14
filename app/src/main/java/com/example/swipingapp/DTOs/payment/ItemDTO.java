package com.example.swipingapp.DTOs.payment;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemDTO implements Parcelable {

    // region Properties

    public String description;
    public int count;
    public double unitAmount;
    public double amount;

    // endregion

    // region Constructors

    public ItemDTO(String description, int count, double unitAmount) {
        this.description = description;
        this.count = count;
        this.unitAmount = unitAmount;
        this.amount = unitAmount * count;
    }

    // endregion

    // region Parcelable

    protected ItemDTO(Parcel in) {
        this.description = in.readString();
        this.count = in.readInt();
        this.unitAmount = in.readDouble();
        this.amount = count * unitAmount;
    }

    public static final Creator<ItemDTO> CREATOR = new Creator<ItemDTO>() {
        @Override
        public ItemDTO createFromParcel(Parcel in) {
            return new ItemDTO(in);
        }

        @Override
        public ItemDTO[] newArray(int size) {
            return new ItemDTO[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(description);
        dest.writeInt(count);
        dest.writeDouble(unitAmount);
    }

    // endregion
}
