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

    // Override functions
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        // TODO: Rethink, make fragment as property?
        Fragment fragment = new AmountFragment();
        displayFragment(fragment);
    }

    // Private functions
    private void displayFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment).commit();
    }

    // Listeners
    /*private class FragmentButtonClickListener implements Button.OnClickListener {

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
                displayFragment(fragment);
            } else {
                // error in creating fragment
                Log.e("MainActivity", "Error in creating fragment");
            }

        }
    }*/
}