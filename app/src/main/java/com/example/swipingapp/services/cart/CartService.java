package com.example.swipingapp.services.cart;

import android.content.SharedPreferences;

import com.example.swipingapp.DTOs.payment.PaymentDTO;
import com.example.swipingapp.DTOs.payment.PaymentItemDTO;
import com.example.swipingapp.enums.Currency;
import com.example.swipingapp.extensions.App;
import com.google.gson.Gson;

import java.util.ArrayList;

public class CartService implements ICartService {

    // region Constants

    private static final String PREFS_NAME = "tapit_cart";

    // endregion

    // region Properties

    private static ICartService mInstance;
    private static Gson mGson;
    private static SharedPreferences mSharedPreferences;
    private static SharedPreferences.OnSharedPreferenceChangeListener mSharedPreferencesListener;

    // endregion

    // region Constructors

    public CartService() {
        mGson = new Gson();
        mSharedPreferences = App.getContext().getSharedPreferences(PREFS_NAME, 0);

        // TODO: Remove
        ArrayList<PaymentItemDTO> items = new ArrayList<>();
        items.add(new PaymentItemDTO("Peysa", 2, 500));
        items.add(new PaymentItemDTO("Buxur", 1, 20000));
        items.add(new PaymentItemDTO("Sokkar", 20, 100));

        PaymentDTO paymentDto = new PaymentDTO(items, Currency.ICELANDIC_KRONA);

        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString("items", mGson.toJson(paymentDto)).apply();
    }

    // region Get instance (Singleton)

    public static ICartService getInstance() {
        if(mInstance == null) {
            mInstance = new CartService();
        }

        return mInstance;
    }

    // endregion

    // region Override functions

    @Override
    public PaymentDTO getCartItems() {
        String json = mSharedPreferences.getString("items", null);

        PaymentDTO paymentDto = mGson.fromJson(json, PaymentDTO.class);
        if(paymentDto == null) {
            paymentDto = new PaymentDTO(new ArrayList<PaymentItemDTO>(), Currency.ICELANDIC_KRONA);
        }

        return paymentDto;
    }

    @Override
    public void addToCart(PaymentItemDTO paymentItem) {
        PaymentDTO paymentDto = getCartItems();

        paymentDto.items.add(paymentItem);

        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString("items", mGson.toJson(paymentDto)).apply();
    }

    @Override
    public void removeFromCart(PaymentItemDTO paymentItem) {
        PaymentDTO paymentDto = getCartItems();

        paymentDto.items.remove(paymentItem);

        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString("items", mGson.toJson(paymentDto)).apply();
    }

    @Override
    public void clearCart() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString("items", null).apply();
    }

    @Override
    public void setSharedPreferencesListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        mSharedPreferencesListener = listener;

        mSharedPreferences.registerOnSharedPreferenceChangeListener(mSharedPreferencesListener);
    }

    // endregion
}
