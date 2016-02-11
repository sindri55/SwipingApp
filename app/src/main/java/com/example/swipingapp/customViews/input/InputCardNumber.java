package com.example.swipingapp.customViews.input;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

public class InputCardNumber extends EditText {

    // region Properties

    // endregion

    // region UI references

    private InputCardNumber mInputCardNumberView;

    // endregion

    // region Constructors

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

    // endregion

    // region Initialize

    private void initialize() {
        mInputCardNumberView = this;
    }

    // endregion

    // TODO: Format credit card number, and more stuff
}
