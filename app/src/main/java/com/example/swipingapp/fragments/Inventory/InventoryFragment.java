package com.example.swipingapp.fragments.Inventory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.swipingapp.R;
import com.example.swipingapp.fragments.base.BaseFragment;

/**
 * Created by Sindri on 13/02/16.
 */
public class InventoryFragment extends BaseFragment {

    // region Constants

    public static final String TAG = InventoryFragment.class.getSimpleName();

    // endregion

    // region Override functions

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        return inflater.inflate(R.layout.fragment_inventory, container, false);
    }

    @Override
    public String getTitle() {
        return getString(R.string.fragment_inventory_title);
    }

    // endregion
}

