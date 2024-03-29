package com.example.swipingapp.fragments.payment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.swipingapp.DTOs.payment.PaymentConfirmedDTO;
import com.example.swipingapp.DTOs.payment.PaymentDTO;
import com.example.swipingapp.DTOs.payment.ReceiptDTO;
import com.example.swipingapp.R;
import com.example.swipingapp.customViews.input.InputCardNumber;
import com.example.swipingapp.customViews.spinner.CustomSpinner;
import com.example.swipingapp.fragments.base.BaseFragment;
import com.example.swipingapp.responses.ErrorResponse;
import com.example.swipingapp.services.payment.IPaymentService;
import com.example.swipingapp.services.payment.PaymentServiceStub;
import com.example.swipingapp.utils.DialogUtils;
import com.example.swipingapp.viewModels.payment.CardPaymentViewModel;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Calendar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

public class PaymentFragment extends BaseFragment {

    // region Constants

    public static final String TAG = PaymentFragment.class.getSimpleName();
    private static final String ARG_PAYMENT_DTO = PaymentDTO.class.getSimpleName();
    private static final String ARG_CALLING_FRAGMENT_TAG = "CallingFragmentTag";

    // endregion

    // region Properties

    private PaymentDTO mPaymentDto;
    private IPaymentService mPaymentService;
    private PaymentResponse mPaymentResponse = null;
    private String mCallingFragmentTag;

    // endregion

    // region UI references

    private TextView mStepOneText;

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

    public static PaymentFragment newInstance(PaymentDTO paymentDto, String callingFragmentTag) {
        PaymentFragment fragment = new PaymentFragment();
        Bundle args = new Bundle();

        args.putParcelable(ARG_PAYMENT_DTO, paymentDto);
        args.putString(ARG_CALLING_FRAGMENT_TAG, callingFragmentTag);

        fragment.setArguments(args);
        return fragment;
    }

    // endregion

    // region Override functions

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mPaymentDto = getArguments().getParcelable(ARG_PAYMENT_DTO);
            mCallingFragmentTag = getArguments().getString(ARG_CALLING_FRAGMENT_TAG);
        } else {
            // TODO: Handle more elegant
            throw new RuntimeException("PaymentFragment: Unable to get arguments");
        }

        mPaymentService = PaymentServiceStub.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_payment_payment, container, false);

        mStepOneText = (TextView) view.findViewById(R.id.txt_step_one);

        mAmountText = (TextView) view.findViewById(R.id.txt_amount);
        mCardholderInput = (EditText) view.findViewById(R.id.input_cardholder);
        mCardNumberInput = (InputCardNumber) view.findViewById(R.id.input_card_number);
        mCVCInput = (EditText) view.findViewById(R.id.input_cvc);
        mExpireMonthSpinner = (CustomSpinner) view.findViewById(R.id.spinner_expire_month);
        mExpireYearSpinner = (CustomSpinner) view.findViewById(R.id.spinner_expire_year);
        mPayButton = (Button) view.findViewById(R.id.btn_confirm_payment);

        // TODO: Do this different?
        if(mCallingFragmentTag.equals(AmountFragment.TAG)) {
            mStepOneText.setText(R.string.fragment_payment_step_amount);
        } else {
            mStepOneText.setText(R.string.fragment_payment_step_items);
        }

        mPayButton.setOnClickListener(new ConfirmPaymentButtonClickListener());

        ArrayAdapter<CharSequence> expireMonthAdapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(), R.array.months_array, R.layout.custom_spinner_dropdown_item);
        mExpireMonthSpinner.setAdapter(expireMonthAdapter);

        ArrayAdapter<String> expireYearAdapter = new ArrayAdapter<>(getActivity().getApplicationContext(), R.layout.custom_spinner_dropdown_item, getYears());
        mExpireYearSpinner.setAdapter(expireYearAdapter);

        // TODO: This could use some refactoring...
        String formattedAmount = mPaymentDto.getFormattedAmount();
        String amountText = getString(R.string.fragment_payment_payment_txt_amount) + ": " + formattedAmount;
        mAmountText.setText(amountText);

        updateButtonState(true);

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
            if(mPaymentResponse != null) {
                return;
            }

            updateButtonState(false);

            String cardholder = mCardholderInput.getText().toString();
            String cardNumber = mCardNumberInput.getText().toString();
            String expireMonth = mExpireMonthSpinner.getText().toString();
            String expireYear = mExpireYearSpinner.getText().toString();
            String cvc = mCVCInput.getText().toString();

            CardPaymentViewModel cardPaymentViewModel = new CardPaymentViewModel(
                    mPaymentDto,
                    cardholder,
                    cardNumber,
                    expireMonth,
                    expireYear,
                    cvc
            );

            mPaymentResponse = new PaymentResponse();
            mPaymentService.payWithCard(cardPaymentViewModel, mPaymentResponse);
        }
    }

    // endregion

    // region Response callbacks

    private class PaymentResponse implements Callback<PaymentConfirmedDTO> {

        @Override
        public void onResponse(Call<PaymentConfirmedDTO> call, Response<PaymentConfirmedDTO> response) {
            mPaymentResponse = null;

            if(response.isSuccess()) {
                PaymentConfirmedDTO paymentConfirmedDto = response.body();

                ReceiptDTO receiptDto = new ReceiptDTO(mPaymentDto, paymentConfirmedDto);

                FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.slide_out_left, R.anim.slide_in_right);
                fragmentTransaction.replace(R.id.fragment_container, ReceiptFragment.newInstance(receiptDto, mCallingFragmentTag), ReceiptFragment.TAG);
                fragmentTransaction.addToBackStack(ReceiptFragment.TAG);
                fragmentTransaction.commit();
            } else {
                updateButtonState(true);

                // TODO: Should we only use the message from the response?
                String title = getString(R.string.fragment_payment_payment_error_payment_failed_title);
                String message = getString(R.string.fragment_payment_payment_error_payment_failed_message);

                try {
                    Converter<ResponseBody, ErrorResponse> errorConverter = mPaymentService.getRetrofit().responseBodyConverter(ErrorResponse.class, new Annotation[0]);
                    ErrorResponse error = errorConverter.convert(response.errorBody());
                    message = error.message;
                } catch (IOException e) {
                    // TODO: Better error handling
                    e.printStackTrace();
                }

                DialogUtils.displayMessageDialog(mContext, title, message);
            }
        }

        @Override
        public void onFailure(Call<PaymentConfirmedDTO> call, Throwable t) {
            mPaymentResponse = null;
            updateButtonState(true);
            Log.e("onFailure", "Something went terribly wrong :/");
        }
    }

    // endregion
}
