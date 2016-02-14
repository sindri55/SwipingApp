package com.example.swipingapp.DTOs.inventory;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class ItemCategoryDTO implements Parcelable {

    // region Properties

    public int categoryId;
    public String description;
    public ArrayList<ItemDTO> items;

    // endregion

    // region Constructors

    public ItemCategoryDTO(int categoryId, String description, ArrayList<ItemDTO> items) {
        this.categoryId = categoryId;
        this.description = description;
        this.items = items;
    }

    // endregion

    // region Parcelable

    protected ItemCategoryDTO(Parcel in) {
        this.categoryId = in.readInt();
        this.description = in.readString();
        this.items = in.createTypedArrayList(ItemDTO.CREATOR);
    }

    public static final Creator<ItemCategoryDTO> CREATOR = new Creator<ItemCategoryDTO>() {
        @Override
        public ItemCategoryDTO createFromParcel(Parcel in) {
            return new ItemCategoryDTO(in);
        }

        @Override
        public ItemCategoryDTO[] newArray(int size) {
            return new ItemCategoryDTO[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(categoryId);
        dest.writeString(description);
        dest.writeTypedList(items);
    }

    // endregion
}
