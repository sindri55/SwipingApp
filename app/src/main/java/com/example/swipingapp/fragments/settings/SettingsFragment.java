package com.example.swipingapp.fragments.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.swipingapp.R;
import com.example.swipingapp.fragments.base.BaseFragment;

public class SettingsFragment extends BaseFragment {

    // region Constants

    public static final String TAG = SettingsFragment.class.getSimpleName();

    // endregion

    // region Override functions

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        return inflater.inflate(R.layout.fragment_settings_settings, container, false);
    }

    @Override
    public String getTitle() {
        return getString(R.string.fragment_settings_settings_title);
    }

    // endregion
}
