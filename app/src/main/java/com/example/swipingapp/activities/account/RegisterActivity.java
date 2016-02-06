package com.example.swipingapp.activities.account;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.swipingapp.R;
import com.example.swipingapp.activities.about.TermsAndConditionsActivity;
import com.example.swipingapp.activities.main.MainActivity;
import com.example.swipingapp.services.account.AccountServiceStub;
import com.example.swipingapp.services.account.IAccountService;
import com.example.swipingapp.viewModels.account.RegisterViewModel;

public class RegisterActivity extends AppCompatActivity {

    // region Properties

    private IAccountService mAccountService;
    private UserRegisterTask mUserRegisterTask = null;
    final Context mContext = this;

    // endregion

    // region UI references

    private EditText mFullNameView;
    private EditText mEmailView;
    private EditText mPasswordView;
    private EditText mConfirmPasswordView;
    private Button mSignUpButton;
    private TextView mTermsAndConditionsText;

    // endregion

    // region Override functions

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAccountService = AccountServiceStub.getInstance();

        mFullNameView = (EditText) findViewById(R.id.full_name);
        mEmailView = (EditText) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        mConfirmPasswordView = (EditText) findViewById(R.id.confirm_password);
        mSignUpButton = (Button) findViewById(R.id.btn_sign_up);
        mTermsAndConditionsText = (TextView) findViewById(R.id.txt_terms_and_conditions);

        mSignUpButton.setOnClickListener(new SignUpButtonClickListener());
        mTermsAndConditionsText.setOnClickListener(new TermsAndConditionsTextClickListener());
    }

    // endregion

    // region Private functions

    private void UpdateButtonState(final boolean enabled) {
        mSignUpButton.setEnabled(enabled);

        if(enabled) {
            mSignUpButton.setText(R.string.activity_register_btn_sign_up);
        } else {
            mSignUpButton.setText(R.string.activity_register_btn_sign_up_processing);
        }
    }

    // endregion

    // region Listeners

    private class SignUpButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View v) {
            // If the register task is running, we do nothing
            if(mUserRegisterTask != null) {
                return;
            }

            UpdateButtonState(false);

            String fullName = mFullNameView.getText().toString();
            String email = mEmailView.getText().toString();
            String password = mPasswordView.getText().toString();
            String confirmPassword = mConfirmPasswordView.getText().toString();

            RegisterViewModel registerViewModel = new RegisterViewModel(fullName, email, password, confirmPassword);

            mUserRegisterTask = new UserRegisterTask(registerViewModel);
            mUserRegisterTask.execute();
        }
    }

    private class TermsAndConditionsTextClickListener implements TextView.OnClickListener {

        @Override
        public void onClick(View v) {
            // If the register task is running, we do nothing
            if(mUserRegisterTask != null) {
                return;
            }

            Intent intent = new Intent(RegisterActivity.this, TermsAndConditionsActivity.class);
            startActivity(intent);
        }
    }

    // endregion

    // region Async tasks

    private class UserRegisterTask extends AsyncTask<Void, Void, Boolean> {

        // Properties
        private final RegisterViewModel mRegisterViewModel;

        // Constructors
        UserRegisterTask(RegisterViewModel registerViewModel) {
            mRegisterViewModel = registerViewModel;
        }

        // Override functions
        @Override
        protected Boolean doInBackground(Void... params) {
            return mAccountService.register(mRegisterViewModel);
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mUserRegisterTask = null;
            UpdateButtonState(true);

            if (success) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            } else {
                // TODO: Style, user @strings res to get text and display as fragment?, refactor!
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        mContext);

                // set title
                alertDialogBuilder.setTitle("Couldn't Create an Account");

                // set dialog message
                alertDialogBuilder
                        .setMessage("We were unable to create an account for you.\nPlease try again.")
                        .setCancelable(true)
                        .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        }

        @Override
        protected void onCancelled() {
            mUserRegisterTask = null;
            UpdateButtonState(true);
        }
    }

    // endregion
}