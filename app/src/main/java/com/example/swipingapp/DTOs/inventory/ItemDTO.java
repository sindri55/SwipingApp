package com.example.swipingapp.DTOs.inventory;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.swipingapp.enums.Currency;

public class ItemDTO implements Parcelable {

    // region Properties

    public int itemId;
    public String description;
    public double amount;
    public Currency currency;

    // endregion

    // region Constructors

    public ItemDTO(int itemId, String description, double amount, Currency currency) {
        this.itemId = itemId;
        this.description = description;
        this.amount = amount;
        this.currency = currency;
    }

    // endregion

    // region Parcelable

    protected ItemDTO(Parcel in) {
        this.itemId = in.readInt();
        this.description = in.readString();
        this.amount = in.readDouble();
        this.currency = (Currency) in.readSerializable();
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
        dest.writeInt(itemId);
        dest.writeString(description);
        dest.writeDouble(amount);
        dest.writeSerializable(currency);
    }

    // endregion
}
