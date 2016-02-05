package com.example.swipingapp.fragments.payment;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.swipingapp.R;
import com.example.swipingapp.customViews.input.InputCardNumber;
import com.example.swipingapp.enums.Currency;
import com.example.swipingapp.viewModels.payment.AmountViewModel;

import java.text.NumberFormat;

public class PaymentFragment extends Fragment {

    // Constants
    private static final String ARG_PAYMENT_VIEW_MODEL = "amountViewModel";

    // Properties
    private FragmentManager mFragmentManager;
    private AmountViewModel mAmountViewModel;

    // UI references
    private TextView mAmountText;
    private EditText mCardholderInput;
    private InputCardNumber mCardNumberInput;
    private EditText mCVCInput;
    private Spinner mExpireMonthSpinner;
    private Spinner mExpireYearSpinner;

    private Button mBackButton;
    private Button mPayButton;

    // Constructors
    public PaymentFragment() {  }

    public static PaymentFragment newInstance(AmountViewModel amountViewModel) {
        PaymentFragment fragment = new PaymentFragment();
        Bundle args = new Bundle();

        args.putParcelable(ARG_PAYMENT_VIEW_MODEL, amountViewModel);

        fragment.setArguments(args);
        return fragment;
    }

    // Override functions
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mAmountViewModel = getArguments().getParcelable(ARG_PAYMENT_VIEW_MODEL);
        } else {
            // TODO: Handle more elegant
            mAmountViewModel = new AmountViewModel(0, Currency.ICELANDIC_KRONA);
        }

        mFragmentManager = getFragmentManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_payment_payment, container, false);

        mAmountText = (TextView) view.findViewById(R.id.txt_amount);
        mCardholderInput = (EditText) view.findViewById(R.id.input_cardholder);
        mCardNumberInput = (InputCardNumber) view.findViewById(R.id.input_card_number);
        mCVCInput = (EditText) view.findViewById(R.id.input_cvc);
        mExpireMonthSpinner = (Spinner) view.findViewById(R.id.spinner_expire_month);
        mExpireYearSpinner = (Spinner) view.findViewById(R.id.spinner_expire_year);
        mBackButton = (Button) view.findViewById(R.id.btn_back);
        mPayButton = (Button) view.findViewById(R.id.btn_confirm_payment);

        mBackButton.setOnClickListener(new BackButtonClickListener());
        mPayButton.setOnClickListener(new ConfirmPaymentButtonClickListener());

        // TODO: Refactor spinners
        ArrayAdapter<CharSequence> expireMonthAdapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(), R.array.months_array, R.layout.input_box_spinner_item);
        expireMonthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mExpireMonthSpinner.setAdapter(expireMonthAdapter);

        ArrayAdapter<CharSequence> expireYearAdapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(), R.array.years_array, R.layout.input_box_spinner_item);
        expireYearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mExpireYearSpinner.setAdapter(expireYearAdapter);

        // TODO: This could use some refactoring...
        NumberFormat formatter = NumberFormat.getCurrencyInstance(mAmountViewModel.currency.getLocale());
        String formattedAmount = formatter.format(mAmountViewModel.amount);
        String amountText = getString(R.string.fragment_payment_payment_txt_amount) + ": " + formattedAmount;
        mAmountText.setText(amountText);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Attach listeners, etc.
    }

    @Override
    public void onDetach() {
        super.onDetach();
        // Detach listeners, etc.
    }

    // Listeners
    private class ConfirmPaymentButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View v) {
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.slide_out_left, R.anim.slide_in_right);
            fragmentTransaction.replace(R.id.main_container, new ReceiptFragment());
            fragmentTransaction.commit();
        }
    }

    private class BackButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View v) {
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
            fragmentTransaction.replace(R.id.main_container, new AmountFragment());
            fragmentTransaction.commit();
        }
    }
}
