package com.example.swipingapp.activities.payment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.swipingapp.R;

public class CardInfoFragment extends Fragment {

    // Properties

    // UI references

    // Constructors
    public CardInfoFragment(){}

    // Override functions
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        return inflater.inflate(R.layout.fragment_payment_card_info, container, false);
    }
}
