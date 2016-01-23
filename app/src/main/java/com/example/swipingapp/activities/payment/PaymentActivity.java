package com.example.swipingapp.activities.payment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.swipingapp.R;

public class PaymentActivity extends Activity {

    // Properties

    // UI references
    private Button mAmountButton;
    private Button mCardInfoButton;
    private Button mReceiptButton;

    // Override functions
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        mAmountButton = (Button) findViewById(R.id.btn_amount);
        mCardInfoButton = (Button) findViewById(R.id.btn_card_info);
        mReceiptButton = (Button) findViewById(R.id.btn_receipt);

        mAmountButton.setOnClickListener(new FragmentButtonClickListener());
        mCardInfoButton.setOnClickListener(new FragmentButtonClickListener());
        mReceiptButton.setOnClickListener(new FragmentButtonClickListener());
    }

    // Listeners
    private class FragmentButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View v) {
            Fragment fragment = null;

            switch (v.getId()){
                case R.id.btn_amount:
                    fragment = new AmountFragment();
                    break;
                case R.id.btn_card_info:
                    fragment = new CardInfoFragment();
                    break;
                case R.id.btn_receipt:
                    fragment = new ReceiptFragment();
                    break;
            }

            if (fragment != null) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, fragment).commit();
            } else {
                // error in creating fragment
                Log.e("MainActivity", "Error in creating fragment");
            }

        }
    }
}