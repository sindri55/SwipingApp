package com.example.swipingapp.fragments.payment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.swipingapp.R;
import com.example.swipingapp.fragments.base.BaseFragment;

public class ReceiptFragment extends BaseFragment {

    // region Constants

    public static final String TAG = ReceiptFragment.class.getSimpleName();

    // endregion

    // region Properties

    // endregion

    // region UI references

    // endregion

    // region Constructors

    // endregion

    // region Override functions

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: Get arguments and stuff
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_payment_receipt, container, false);

        // Hide the back button
        if(mFragmentListener != null) {
            mFragmentListener.setShowNavigationBackButton(false);
        }

        return view;
    }

    @Override
    public String getTitle() {
        return getString(R.string.fragment_payment_receipt_title);
    }

    // endregion

    // region Listeners

    // endregion
}