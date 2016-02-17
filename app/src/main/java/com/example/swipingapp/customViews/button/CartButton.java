package com.example.swipingapp.customViews.button;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.widget.ImageButton;

import com.example.swipingapp.DTOs.payment.PaymentDTO;
import com.example.swipingapp.services.cart.CartService;
import com.example.swipingapp.services.cart.ICartService;

public class CartButton extends ImageButton {

    // region Properties

    private ICartService mCartService;
    private PaymentDTO mPaymentDto;

    // endregion

    // region UI references

    private CartButton mCartButton;

    // endregion

    // region Constructors

    public CartButton(Context context) {
        super(context);
        initialize();
    }

    public CartButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public CartButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    // endregion

    // region Initialize

    private void initialize() {
        mCartButton = this;
        mCartService = CartService.getInstance();

        mCartService.setSharedPreferencesListener(new CartChangedListener());
        mPaymentDto = mCartService.getCartItems();

        updateView();
    }

    // endregion

    // region Private functions

    private void updateView() {
        if(mPaymentDto.items.size() > 0) {
            mCartButton.setVisibility(VISIBLE);
        } else {
            mCartButton.setVisibility(INVISIBLE);
        }
    }

    // endregion

    // region Listeners

    private class CartChangedListener implements SharedPreferences.OnSharedPreferenceChangeListener {

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            mPaymentDto = mCartService.getCartItems();

            updateView();
        }
    }

    // endregion
}
