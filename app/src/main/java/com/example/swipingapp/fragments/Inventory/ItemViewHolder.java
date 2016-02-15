package com.example.swipingapp.fragments.Inventory;

import android.view.View;
import android.widget.TextView;

import com.example.swipingapp.DTOs.inventory.ItemDTO;
import com.example.swipingapp.R;
import com.example.swipingapp.fragments.Inventory.ViewHolder.ChildViewHolder;

/**
 * Created by Sindri on 14/02/16.
 */
public class ItemViewHolder extends ChildViewHolder {

    private TextView mItemTextView;

    public ItemViewHolder(View itemView) {
        super(itemView);
        mItemTextView = (TextView) itemView.findViewById(R.id.child_list_item_crime_date_text_view);
    }

    public void bind(ItemDTO item) {
        mItemTextView.setText((item.description));
    }
}
