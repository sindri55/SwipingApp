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
import com.example.swipingapp.activities.main.MainActivity;
import com.example.swipingapp.services.account.AccountServiceStub;
import com.example.swipingapp.services.account.IAccountService;
import com.example.swipingapp.viewModels.account.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    // Properties
    private IAccountService mAccountService;
    private UserLoginTask mUserLoginTask = null;
    final Context mContext = this;

    // UI references
    private EditText mEmailView;
    private EditText mPasswordView;
    private Button mLoginButton;
    private TextView mRegisterText;



    // Override functions
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAccountService = AccountServiceStub.getInstance();

        mEmailView = (EditText) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        mLoginButton = (Button) findViewById(R.id.btn_login);
        mRegisterText = (TextView) findViewById(R.id.txt_register);

        mLoginButton.setOnClickListener(new LoginButtonClickListener());
        mRegisterText.setOnClickListener(new RegisterTextClickListener());
    }

    // Functions
    private void UpdateButtonState(final boolean enabled) {
        mLoginButton.setEnabled(enabled);

        if(enabled) {
            mLoginButton.setText(R.string.activity_login_btn_login);
        } else {
            mLoginButton.setText(R.string.activity_login_btn_login_processing);
        }
    }

    // Listeners
    private class LoginButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View v) {
            // If the login task is running, we do nothing
            if(mUserLoginTask != null) {
                return;
            }

            UpdateButtonState(false);

            String email = mEmailView.getText().toString();
            String password = mPasswordView.getText().toString();

            LoginViewModel loginViewModel = new LoginViewModel(email, password);

            mUserLoginTask = new UserLoginTask(loginViewModel);
            mUserLoginTask.execute();
        }
    }

    private class RegisterTextClickListener implements TextView.OnClickListener {

        @Override
        public void onClick(View v) {
            // If the login task is running, we do nothing
            if(mUserLoginTask != null) {
                return;
            }

            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        }
    }

    // Async tasks
    private class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        // Properties
        private final LoginViewModel mLoginViewModel;

        // Constructors
        UserLoginTask(LoginViewModel loginViewModel) {
            mLoginViewModel = loginViewModel;
        }

        // Override functions
        @Override
        protected Boolean doInBackground(Void... params) {
            return mAccountService.login(mLoginViewModel);
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mUserLoginTask = null;
            UpdateButtonState(true);

            if (success) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);

            } else {
                // TODO: Style, user @strings res to get text and display as fragment?, refactor
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        mContext);

                // set title
                alertDialogBuilder.setTitle("Couldn't Login");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Incorrect email or password.\nPlease try again.")
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
            mUserLoginTask = null;
            UpdateButtonState(true);
        }
    }
}