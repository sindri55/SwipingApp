package com.example.swipingapp.customViews.input;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

import com.example.swipingapp.enums.Currency;
import com.example.swipingapp.services.settings.ISettingsService;
import com.example.swipingapp.services.settings.SettingsServiceStub;

import java.text.NumberFormat;

public class InputAmount extends EditText {

    // Properties
    private double mAmount;
    private String mDigits;
    private String mFormattedString;

    private ISettingsService mSettingsService;
    private NumberFormat mFormatter;
    private Currency mCurrency;

    // UI references
    private InputAmount mInputAmountView;

    // Constructors
    public InputAmount(Context context) {
        super(context);
        initialize();
    }

    public InputAmount(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public InputAmount(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    // Initialize
    private void initialize() {
        mAmount = 0;
        mDigits = "0";
        mInputAmountView = this;

        mSettingsService = SettingsServiceStub.getInstance();
        mCurrency = mSettingsService.getUserCurrency();
        mFormatter = NumberFormat.getCurrencyInstance(mCurrency.getLocale());

        mFormattedString = mFormatter.format(mAmount) + " ";    // TODO: Find solution to hack (spacing for error popup)

        mInputAmountView.setHint(mFormattedString);
        mInputAmountView.setOnClickListener(new InputAmountClickListener());
        mInputAmountView.addTextChangedListener(new InputAmountTextChangedListener());
    }

    // Public functions
    public double getAmount() {
        return mAmount;
    }

    public void setAmount(double amount) {
        mAmount = amount;

        updateView();
    }

    public void increaseAmount(double amount) {
        mAmount += amount;

        updateView();
    }

    public void decreaseAmount(double amount) {
        mAmount -= amount;
        mAmount = (mAmount < 0) ? 0 : mAmount;

        updateView();
    }

    // Private functions
    private void updateView() {
        mFormattedString = mFormatter.format(mAmount) + " ";    // TODO: Find solution to hack (spacing for error popup)
        mDigits = mFormattedString.replaceAll("\\D", "");

        mInputAmountView.setText(mFormattedString);
        mInputAmountView.setSelection(mFormattedString.length() - 1);
    }

    // Listeners
    private class InputAmountClickListener implements EditText.OnClickListener {

        @Override
        public void onClick(View v) {
            mInputAmountView.setSelection(mInputAmountView.getText().length());
        }
    }

    private class InputAmountTextChangedListener implements TextWatcher {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // No input from keypad (text changed from IAmountSpinnerListener)
            if((count == 0 && before == 0) || (count > 1 || before > 1)) {
                return;
            }

            // remove listener to prevent stack overflow from infinite loop
            mInputAmountView.removeTextChangedListener(this);

            if(count == 0) {
                if(mDigits.length() > 1) {
                    mDigits = mDigits.substring(0, mDigits.length() - 1);
                } else {
                    mDigits = "0";
                }
            } else {
                char newChar = s.charAt(start);
                if(Character.isDigit(newChar)) {
                    mDigits += newChar;
                }
            }

            mAmount = Double.parseDouble(mDigits);
            updateView();

            // Add listener back
            mInputAmountView.addTextChangedListener(this);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {  }

        @Override
        public void afterTextChanged(Editable s) {  }
    }
}
