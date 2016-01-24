package com.example.swipingapp.activities.payment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.swipingapp.R;
import com.example.swipingapp.customViews.amountSpinner.AmountSpinnerAdapter;
import com.example.swipingapp.customViews.amountSpinner.IAmountListener;
import com.example.swipingapp.enums.Currency;
import com.example.swipingapp.services.settings.ISettingsService;
import com.example.swipingapp.services.settings.SettingsServiceStub;

import java.util.ArrayList;

public class AmountFragment extends Fragment {

    // Properties
    private int mAmount;
    private IAmountListener mAmountListener;
    private ISettingsService mSettingsService;

    // UI references
    private EditText mAmountView;
    private Button mNextButton;
    private ListView mAmountSpinnerList;

    // Constructors
    public AmountFragment(){
        mAmount = 0;
        mAmountListener = new AmountListener();
        mSettingsService = SettingsServiceStub.getInstance();
    }

    // Override functions
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_payment_amount, container, false);

        mAmountView = (EditText) view.findViewById(R.id.amount);
        mNextButton = (Button) view.findViewById(R.id.btn_next);
        mAmountSpinnerList = (ListView) view.findViewById(R.id.amount_spinner_list);

        Currency currency = mSettingsService.getUserCurrency();

        mAmountView.setHint("0 " + currency.getSymbol());

        AmountSpinnerAdapter amountSpinnerAdapter = new AmountSpinnerAdapter(getActivity().getApplicationContext(), currency, mAmountListener);
        mAmountSpinnerList.setAdapter(amountSpinnerAdapter);

        return view;
    }

    // Listeners
    private class AmountListener implements IAmountListener {

        @Override
        public void changeAmount(int value) {
            mAmount += value;
            mAmount = (mAmount < 0) ? 0 : mAmount;

            mAmountView.setText(Integer.toString(mAmount));
        }

        @Override
        public void setAmount(int value) {
            mAmount = value;

            mAmountView.setText(Integer.toString(mAmount));
        }
    }
}