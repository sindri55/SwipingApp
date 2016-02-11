package com.example.swipingapp.fragments.payment;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.swipingapp.R;
import com.example.swipingapp.customViews.input.InputCardNumber;
import com.example.swipingapp.customViews.spinner.CustomSpinner;
import com.example.swipingapp.enums.Currency;
import com.example.swipingapp.fragments.base.BaseFragment;
import com.example.swipingapp.services.payment.IPaymentService;
import com.example.swipingapp.services.payment.PaymentServiceStub;
import com.example.swipingapp.viewModels.payment.AmountViewModel;
import com.example.swipingapp.viewModels.payment.CardPaymentViewModel;

import java.text.NumberFormat;
import java.util.Calendar;

public class PaymentFragment extends BaseFragment {

    // region Constants

    public static final String TAG = PaymentFragment.class.getSimpleName();
    private static final String ARG_AMOUNT_VIEW_MODEL = "amountViewModel";

    // endregion

    // region Properties

    private AmountViewModel mAmountViewModel;
    private IPaymentService mPaymentService;
    private PaymentTask mPaymentTask = null;

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

        mPaymentService = PaymentServiceStub.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

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
    public String getTitle() {
        return getString(R.string.fragment_payment_payment_title);
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

    private void updateButtonState(final boolean enabled) {
        mPayButton.setEnabled(enabled);

        if(enabled) {
            mPayButton.setText(R.string.fragment_payment_payment_btn_confirm);
        } else {
            mPayButton.setText(R.string.fragment_payment_payment_btn_confirm_processing);
        }
    }

    // endregion

    // region Listeners

    private class ConfirmPaymentButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View v) {
            // If the payment task is running, we do nothing
            if(mPaymentTask != null) {
                return;
            }

            updateButtonState(false);

            String cardholder = mCardholderInput.getText().toString();
            String cardNumber = mCardNumberInput.getText().toString();
            String expireMonth = mExpireMonthSpinner.getText().toString();
            String expireYear = mExpireYearSpinner.getText().toString();
            String cvc = mCVCInput.getText().toString();

            CardPaymentViewModel cardPaymentViewModel = new CardPaymentViewModel(
                    mAmountViewModel,
                    cardholder,
                    cardNumber,
                    expireMonth,
                    expireYear,
                    cvc
            );

            mPaymentTask = new PaymentTask(cardPaymentViewModel);
            mPaymentTask.execute();
        }
    }

    // endregion

    // region Async tasks

    private class PaymentTask extends AsyncTask<Void, Void, Boolean> {

        // Properties
        private final CardPaymentViewModel mCardPaymentViewModel;

        // Constructors
        public PaymentTask(CardPaymentViewModel cardPaymentViewModel) {
            mCardPaymentViewModel = cardPaymentViewModel;
        }

        // Override functions
        @Override
        protected Boolean doInBackground(Void... params) {
            return true;
            //return mPaymentService.payWithCard(mCardPaymentViewModel);
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mPaymentTask = null;
            updateButtonState(true);

            if (success) {
                FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.slide_out_left, R.anim.slide_in_right);
                fragmentTransaction.replace(R.id.fragment_container, new ReceiptFragment(), ReceiptFragment.TAG);
                fragmentTransaction.addToBackStack(ReceiptFragment.TAG);
                fragmentTransaction.commit();
            } else {
                // TODO: Some error handling
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        mContext);

                // set title
                alertDialogBuilder.setTitle("Payment failed");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Something went wrong.\nPlease try again.")
                        .setCancelable(true)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        }

        @Override
        protected void onCancelled() {
            mPaymentTask = null;
            updateButtonState(true);
        }
    }

    // endregion
}
