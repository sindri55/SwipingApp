package com.example.swipingapp.fragments.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.swipingapp.R;
import com.example.swipingapp.fragments.base.BaseFragment;

public class VerificationFragment extends BaseFragment {

    // region Constants

    public static final String TAG = VerificationFragment.class.getSimpleName();

    // endregion

    // region Properties

    // endregion

    // region UI references

    // endregion

    // region Override functions

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        return inflater.inflate(R.layout.fragment_profile_verification_settings, container, false);
    }

    @Override
    public String getTitle() {
        return null;
    }

    // endregion
}
