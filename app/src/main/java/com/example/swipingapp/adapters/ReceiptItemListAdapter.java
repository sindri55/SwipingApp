package com.example.swipingapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.swipingapp.DTOs.payment.PaymentItemDTO;
import com.example.swipingapp.R;
import com.example.swipingapp.enums.Currency;

import java.text.NumberFormat;
import java.util.ArrayList;

public class ReceiptItemListAdapter extends BaseAdapter {

    // region Properties

    private Context mContext;
    private ArrayList<PaymentItemDTO> mItems;
    private NumberFormat mFormatter;

    // endregion

    // region Constructors

    public ReceiptItemListAdapter(Context context, ArrayList<PaymentItemDTO> items, Currency currency) {
        mContext = context;
        mItems = items;

        mFormatter = currency.getFormatter();
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
            convertView = inflater.inflate(R.layout.list_receipt_item, null);
        }

        Holder holder = new Holder();
        PaymentItemDTO item = mItems.get(position);

        holder.description = (TextView) convertView.findViewById(R.id.txt_description);
        holder.count = (TextView) convertView.findViewById(R.id.txt_count);
        holder.unitAmount = (TextView) convertView.findViewById(R.id.txt_unit_amount);
        holder.amount = (TextView) convertView.findViewById(R.id.txt_amount);

        holder.description.setText(item.description);
        holder.count.setText(Integer.toString(item.count));
        holder.unitAmount.setText(mFormatter.format(item.unitAmount));
        holder.amount.setText(mFormatter.format(item.amount));

        return convertView;
    }

    // endregion

    // region Private classes

    private class Holder
    {
        TextView description;
        TextView count;
        TextView unitAmount;
        TextView amount;
    }

    // endregion
}
