package com.example.swipingapp.activities.about;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.swipingapp.R;

// This is just some temp stuff
public class TermsAndConditionsActivity extends AppCompatActivity {

    // Properties

    // UI References
    private TextView mTermsAndConditionsText;

    // Override functions
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_conditions);

        mTermsAndConditionsText = (TextView) findViewById(R.id.txt_terms_and_conditions);

        // Just some rubbish text, fetched from the server?
        mTermsAndConditionsText.setText("All copyright, trade marks, design rights, patents and other intellectual property rights (registered and unregistered) in and on BBC Online Services and BBC Content belong to the BBC and/or third parties (which may include you or other users.) The BBC reserves all of its rights in BBC Content and BBC Online Services. Nothing in the Terms grants you a right or licence to use any trade mark, design right or copyright owned or controlled by the BBC or any other third party except as expressly provided in the Terms.");
    }
}