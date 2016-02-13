package com.example.swipingapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.swipingapp.DTOs.payment.ItemDTO;
import com.example.swipingapp.R;
import com.example.swipingapp.enums.Currency;

import java.text.NumberFormat;
import java.util.ArrayList;

public class ReceiptItemListAdapter extends BaseAdapter {

    // region Properties

    private Context mContext;
    private ArrayList<ItemDTO> mItems;
    private NumberFormat mFormatter;

    // endregion

    // region Constructors

    public ReceiptItemListAdapter(Context context, ArrayList<ItemDTO> items, Currency currency) {
        mContext = context;
        mItems = items;

        mFormatter = NumberFormat.getCurrencyInstance(currency.getLocale());
    }

    // endregion

    // region Override functions

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_receipt_item_list_item, null);
        }

        Holder holder = new Holder();
        ItemDTO item = mItems.get(position);


        String formattedAmount = mFormatter.format(item.amount);

        holder.id = (TextView) convertView.findViewById(R.id.receipt_customlistview_id);
        holder.item = (TextView) convertView.findViewById(R.id.receipt_customlistview_item_name);
        holder.amount = (TextView) convertView.findViewById(R.id.receipt_customlistview_amount);

        holder.id.setText(String.valueOf(position + 1));
        holder.item.setText(item.description);
        holder.amount.setText(formattedAmount);

        return convertView;
    }

    // endregion

    // region Private classes

    private class Holder
    {
        TextView id;
        TextView item;
        TextView amount;
    }

    // endregion
}
