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

import java.text.NumberFormat;
import java.util.ArrayList;

public class AmountSpinnerAdapter extends BaseAdapter {

    // region Properties

    private Context mContext;
    private ArrayList<Integer> mValues;
    private Currency mCurrency;
    private IAmountSpinnerListener mAmountListener;

    // endregion

    // region Constructors

    public AmountSpinnerAdapter(Context context, Currency currency, IAmountSpinnerListener amountListener) {
        mContext = context;
        mCurrency = currency;
        mAmountListener = amountListener;

        mValues = currency.getAmountSpinnerValues();
    }

    // endregion

    // region Override functions

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

        Button decreaseButton = (Button) convertView.findViewById(R.id.btn_decrease);
        Button amountButton = (Button) convertView.findViewById(R.id.btn_amount);
        Button increaseButton = (Button) convertView.findViewById(R.id.btn_increase);

        NumberFormat formatter = NumberFormat.getCurrencyInstance(mCurrency.getLocale());
        String amountText = formatter.format(value);
        amountButton.setText(amountText);

        decreaseButton.setOnClickListener(new AmountButtonClickListener(value));
        amountButton.setOnClickListener(new AmountButtonClickListener(value));
        increaseButton.setOnClickListener(new AmountButtonClickListener(value));

        return convertView;
    }

    // endregion

    // region Listeners

    private class AmountButtonClickListener implements Button.OnClickListener {

        // Properties
        private double mValue;

        // Constructors
        public AmountButtonClickListener(double value) {
            mValue = value;
        }

        // Override functions
        @Override
        public void onClick(View v) {
            if(mAmountListener != null) {
                switch (v.getId()) {
                    case R.id.btn_decrease:
                        mAmountListener.decreaseAmount(mValue);
                        break;
                    case R.id.btn_amount:
                        mAmountListener.setAmount(mValue);
                        break;
                    case R.id.btn_increase:
                        mAmountListener.increaseAmount(mValue);
                        break;
                }
            }
        }
    }

    // endregion
}
