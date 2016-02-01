package com.example.swipingapp.customViews.input;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

public class InputCardNumber extends EditText {

    // Properties

    // UI references
    private InputCardNumber mInputCardNumberView;

    // Constructors
    public InputCardNumber(Context context) {
        super(context);
        initialize();
    }

    public InputCardNumber(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public InputCardNumber(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    // Initialize
    private void initialize() {
        mInputCardNumberView = this;
    }

    // TODO: Format credit card number, and more stuff
}
