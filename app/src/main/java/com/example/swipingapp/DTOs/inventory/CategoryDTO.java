package com.example.swipingapp.DTOs.inventory;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.swipingapp.fragments.Inventory.Model.ParentListItem;

import java.util.ArrayList;
import java.util.List;

public class CategoryDTO implements Parcelable, ParentListItem {

    // region Properties

    public int categoryId;
    public String description;
    public ArrayList<ItemDTO> items;

    // endregion

    // region Constructors

    public CategoryDTO(int categoryId, String description, ArrayList<ItemDTO> items) {
        this.categoryId = categoryId;
        this.description = description;
        this.items = items;
    }

    // endregion

    // region Parcelable

    protected CategoryDTO(Parcel in) {
        this.categoryId = in.readInt();
        this.description = in.readString();
        this.items = in.createTypedArrayList(ItemDTO.CREATOR);
    }

    public static final Creator<CategoryDTO> CREATOR = new Creator<CategoryDTO>() {
        @Override
        public CategoryDTO createFromParcel(Parcel in) {
            return new CategoryDTO(in);
        }

        @Override
        public CategoryDTO[] newArray(int size) {
            return new CategoryDTO[size];
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

    @Override
    public List<?> getChildItemList() {
        return items;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
    // endregion
}
