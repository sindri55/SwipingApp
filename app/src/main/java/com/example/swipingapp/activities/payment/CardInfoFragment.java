package com.example.swipingapp.activities.payment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.swipingapp.R;

public class CardInfoFragment extends Fragment {

    // Properties

    // UI references
    private View view;
    private Button mNextButton;
    // Constructors
    public CardInfoFragment(){}

    // Override functions
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        return inflater.inflate(R.layout.fragment_payment_payment, container, false);

        /*mNextButton = (Button) view.findViewById(R.id.btnPaymentNext);


        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.slide_out_left, R.anim.slide_in_right
                );
                fragmentTransaction.replace(R.id.main_container, new ReceiptFragment());
                fragmentTransaction.commit();

            }
        });

        return view;*/
    }
}
