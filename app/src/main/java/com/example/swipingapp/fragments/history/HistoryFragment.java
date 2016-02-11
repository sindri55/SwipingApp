package com.example.swipingapp.fragments.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.swipingapp.R;
import com.example.swipingapp.fragments.base.BaseFragment;

public class HistoryFragment extends BaseFragment {

    // region Constants

    public static final String TAG = HistoryFragment.class.getSimpleName();

    // endregion

    // region Override functions

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        return inflater.inflate(R.layout.fragment_history_history, container, false);
    }

    @Override
    public String getTitle() {
        return getString(R.string.fragment_history_history_title);
    }

    // endregion
}
