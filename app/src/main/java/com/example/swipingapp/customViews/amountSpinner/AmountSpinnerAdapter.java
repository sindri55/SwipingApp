package com.example.swipingapp.customViews.amountSpinner;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.example.swipingapp.R;
import com.example.swipingapp.enums.Currency;

import java.util.ArrayList;

public class AmountSpinnerAdapter extends BaseAdapter {

    // Properties
    private Context mContext;
    private ArrayList<Integer> mValues;
    private Currency mCurrency;
    private IAmountListener mAmountListener;

    // Constructors
    public AmountSpinnerAdapter(Context context, Currency currency, IAmountListener amountListener) {
        mContext = context;
        mCurrency = currency;
        mAmountListener = amountListener;

        mValues = currency.getAmountSpinnerValues();
    }

    // Override functions
    @Override
    public int getCount() {
        return mValues.size();
    }

    @Override
    public Object getItem(int position) {
        return mValues.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.amount_spinner_item, null);
        }

        int value = mValues.get(position);

        Button decreaseButton = (Button) convertView.findViewById(R.id.btn_decrement);
        Button amountButton = (Button) convertView.findViewById(R.id.btn_amount);
        Button increaseButton = (Button) convertView.findViewById(R.id.btn_increment);

        String amountText = Integer.toString(value) + " " + mCurrency.getSymbol();
        amountButton.setText(amountText);

        decreaseButton.setOnClickListener(new AmountButtonClickListener(value));
        amountButton.setOnClickListener(new AmountButtonClickListener(value));
        increaseButton.setOnClickListener(new AmountButtonClickListener(value));

        return convertView;
    }

    // Listeners
    private class AmountButtonClickListener implements Button.OnClickListener {

        // Properties
        private int mValue;

        // Constructors
        public AmountButtonClickListener(int value) {
            mValue = value;
        }

        // Override functions
        @Override
        public void onClick(View v) {
            if(mAmountListener != null) {
                switch (v.getId()) {
                    case R.id.btn_decrement:
                        mAmountListener.changeAmount(-mValue);
                        break;
                    case R.id.btn_amount:
                        mAmountListener.setAmount(mValue);
                        break;
                    case R.id.btn_increment:
                        mAmountListener.changeAmount(mValue);
                        break;
                }
            }
        }
    }
}
