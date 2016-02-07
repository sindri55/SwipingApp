package com.example.swipingapp.fragments.payment;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.swipingapp.R;
import com.example.swipingapp.customViews.input.InputCardNumber;
import com.example.swipingapp.customViews.spinner.CustomSpinner;
import com.example.swipingapp.enums.Currency;
import com.example.swipingapp.listeners.IFragmentListener;
import com.example.swipingapp.utils.FragmentUtils;
import com.example.swipingapp.viewModels.payment.AmountViewModel;

import java.text.NumberFormat;
import java.util.Calendar;

public class PaymentFragment extends Fragment {

    // region Constants

    public static final String TAG = PaymentFragment.class.getSimpleName();
    private static final String ARG_AMOUNT_VIEW_MODEL = "amountViewModel";

    // endregion

    // region Properties

    private IFragmentListener mFragmentListener;
    private FragmentManager mFragmentManager;
    private AmountViewModel mAmountViewModel;

    // endregion

    // region UI references

    private TextView mAmountText;
    private EditText mCardholderInput;
    private InputCardNumber mCardNumberInput;
    private EditText mCVCInput;
    private CustomSpinner mExpireMonthSpinner;
    private CustomSpinner mExpireYearSpinner;

    private Button mPayButton;

    // endregion

    // region Constructors

    public PaymentFragment() {  }

    public static PaymentFragment newInstance(AmountViewModel amountViewModel) {
        PaymentFragment fragment = new PaymentFragment();
        Bundle args = new Bundle();

        args.putParcelable(ARG_AMOUNT_VIEW_MODEL, amountViewModel);

        fragment.setArguments(args);
        return fragment;
    }

    // endregion

    // region Override functions

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mAmountViewModel = getArguments().getParcelable(ARG_AMOUNT_VIEW_MODEL);
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
        mExpireMonthSpinner = (CustomSpinner) view.findViewById(R.id.spinner_expire_month);
        mExpireYearSpinner = (CustomSpinner) view.findViewById(R.id.spinner_expire_year);
        mPayButton = (Button) view.findViewById(R.id.btn_confirm_payment);

        mPayButton.setOnClickListener(new ConfirmPaymentButtonClickListener());

        ArrayAdapter<CharSequence> expireMonthAdapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(), R.array.months_array, R.layout.custom_spinner_dropdown_item);
        mExpireMonthSpinner.setAdapter(expireMonthAdapter);

        ArrayAdapter<String> expireYearAdapter = new ArrayAdapter<>(getActivity().getApplicationContext(), R.layout.custom_spinner_dropdown_item, getYears());
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
        if(context instanceof IFragmentListener) {
            mFragmentListener = (IFragmentListener) context;
        } else {
            Log.e("onAttach", "context not instance of IFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mFragmentListener = null;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        if (FragmentUtils.sDisableFragmentAnimations) {
            Animation a = new Animation() {};
            a.setDuration(0);
            return a;
        }
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    // endregion

    // region Private functions

    private String[] getYears() {
        String[] years = new String[11];

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for(int i=0; i<11; i++) {
            years[i] = Integer.toString(currentYear);
            currentYear++;
        }

        return years;
    }

    // endregion

    // region Listeners

    private class ConfirmPaymentButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View v) {
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.slide_out_left, R.anim.slide_in_right);
            fragmentTransaction.replace(R.id.fragment_container, new ReceiptFragment(), ReceiptFragment.TAG);
            fragmentTransaction.addToBackStack(ReceiptFragment.TAG);
            fragmentTransaction.commit();
        }
    }

    // endregion
}
