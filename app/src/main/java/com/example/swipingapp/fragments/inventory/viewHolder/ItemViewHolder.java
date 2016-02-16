package com.example.swipingapp.fragments.inventory.viewHolder;

import android.view.View;
import android.widget.TextView;

import com.example.swipingapp.DTOs.inventory.ItemDTO;
import com.example.swipingapp.R;

public class ItemViewHolder extends ChildViewHolder {

    private TextView mItemTextView;

    public ItemViewHolder(View itemView) {
        super(itemView);
        mItemTextView = (TextView) itemView.findViewById(R.id.txt_category_description);
    }

    public void bind(ItemDTO item) {
        mItemTextView.setText((item.description));
    }
}
