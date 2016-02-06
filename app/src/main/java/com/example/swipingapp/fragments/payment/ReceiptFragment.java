package com.example.swipingapp.fragments.payment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.swipingapp.R;

public class ReceiptFragment extends Fragment {

    // Properties
    private FragmentManager mFragmentManager;

    // UI references
    private View view;
    private Button mButton;
    private Button mBackButton;

    // Constructors

    // Override functions
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        view = inflater.inflate(R.layout.fragment_payment_receipt, container, false);

        mFragmentManager = getFragmentManager();
        mButton = (Button) view.findViewById(R.id.done);
        mBackButton = (Button) view.findViewById(R.id.btn_back);

        mBackButton.setOnClickListener(new BackButtonClickListener());

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_top);
                fragmentTransaction.replace(R.id.fragment_container, new AmountFragment());
                fragmentTransaction.commit();


            }
        });

        return view;
    }

    // Listeners
    private class BackButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View v) {
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
            fragmentTransaction.replace(R.id.fragment_container, new PaymentFragment());
            fragmentTransaction.commit();
        }
    }
}