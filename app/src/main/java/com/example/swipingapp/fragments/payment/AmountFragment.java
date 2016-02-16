package com.example.swipingapp.fragments.payment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.swipingapp.DTOs.payment.PaymentItemDTO;
import com.example.swipingapp.DTOs.payment.PaymentDTO;
import com.example.swipingapp.R;
import com.example.swipingapp.customViews.amountSpinner.AmountSpinnerAdapter;
import com.example.swipingapp.customViews.amountSpinner.IAmountSpinnerListener;
import com.example.swipingapp.customViews.input.InputAmount;
import com.example.swipingapp.enums.Currency;
import com.example.swipingapp.fragments.base.BaseFragment;
import com.example.swipingapp.services.settings.ISettingsService;
import com.example.swipingapp.services.settings.SettingsServiceStub;

import java.util.ArrayList;

public class AmountFragment extends BaseFragment {

    // region Constants

    public static final String TAG = AmountFragment.class.getSimpleName();

    // endregion

    // region Properties

    private IAmountSpinnerListener mAmountListener;
    private ISettingsService mSettingsService;
    private Currency mCurrency;

    // endregion

    // region UI references

    private InputAmount mInputAmountView;
    private Button mNextButton;
    private ListView mAmountSpinnerList;

    // endregion

    // region Override functions

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAmountListener = new AmountSpinnerListener();
        mSettingsService = SettingsServiceStub.getInstance();
        mCurrency = mSettingsService.getUserCurrency();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_payment_amount, container, false);

        mInputAmountView = (InputAmount) view.findViewById(R.id.input_amount);
        mNextButton = (Button) view.findViewById(R.id.btn_next);
        mAmountSpinnerList = (ListView) view.findViewById(R.id.amount_spinner_list);

        mNextButton.setOnClickListener(new NextButtonClickListener());

        AmountSpinnerAdapter amountSpinnerAdapter = new AmountSpinnerAdapter(getActivity().getApplicationContext(), mCurrency, mAmountListener);
        mAmountSpinnerList.setAdapter(amountSpinnerAdapter);

        return view;
    }

    @Override
    public String getTitle() {
        return getString(R.string.fragment_payment_amount_title);
    }

    // endregion

    // region Private functions

    private boolean validateInputAmount() {
        double amount = mInputAmountView.getAmount();

        if(amount > 0) {
            mInputAmountView.setError(null);

            return true;
        } else {
            mInputAmountView.setError(getString(R.string.fragment_payment_amount_error_amount_missing));
            mInputAmountView.requestFocus();

            return false;
        }
    }

    // endregion

    // region Listeners

    private class NextButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View v) {
            if(mInputAmountView != null){
                if(validateInputAmount()) {
                    // TODO: this needs rethinking
                    ArrayList<PaymentItemDTO> items = new ArrayList<>();
                    items.add(new PaymentItemDTO("Payment", 1, mInputAmountView.getAmount()));
                    items.add(new PaymentItemDTO("Payment 2", 2, mInputAmountView.getAmount()));

                    PaymentDTO paymentDto = new PaymentDTO(items, mCurrency);

                    // TODO: Fix so that only the main content slides, not the step indicators, attach them to the header layout?
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.slide_out_left, R.anim.slide_in_right, R.anim.slide_in_left, R.anim.slide_out_right);
                    fragmentTransaction.replace(R.id.fragment_container, PaymentFragment.newInstance(paymentDto, TAG), PaymentFragment.TAG);
                    fragmentTransaction.addToBackStack(PaymentFragment.TAG);
                    fragmentTransaction.commit();
                }
            }
        }
    }

    private class AmountSpinnerListener implements IAmountSpinnerListener {

        @Override
        public void setAmount(double value) {
            mInputAmountView.setAmount(value);

            // Clear errors
            mInputAmountView.setError(null);
        }

        @Override
        public void increaseAmount(double value) {
            mInputAmountView.increaseAmount(value);

            // Clear errors
            mInputAmountView.setError(null);
        }

        @Override
        public void decreaseAmount(double value) {
            mInputAmountView.decreaseAmount(value);

            // Clear errors
            mInputAmountView.setError(null);
        }
    }

    // endregion
}