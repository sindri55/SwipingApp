package com.example.swipingapp.activities.account;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.swipingapp.R;
import com.example.swipingapp.activities.main.MainActivity;
import com.example.swipingapp.services.account.AccountService;
import com.example.swipingapp.services.account.IAccountService;
import com.example.swipingapp.viewModels.account.LoginViewModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    // region Properties

    private IAccountService mAccountService;
    private UserLoginResponse mUserLoginResponse = null;
    final Context mContext = this;

    // endregion

    // region UI references

    private EditText mEmailView;
    private EditText mPasswordView;
    private Button mLoginButton;
    private TextView mRegisterText;

    // endregion

    // region Override functions

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAccountService = AccountService.getInstance();

        mEmailView = (EditText) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        mLoginButton = (Button) findViewById(R.id.btn_login);
        mRegisterText = (TextView) findViewById(R.id.txt_register);

        mLoginButton.setOnClickListener(new LoginButtonClickListener());
        mRegisterText.setOnClickListener(new RegisterTextClickListener());
    }

    // endregion

    // region Private functions

    private void updateButtonState(final boolean enabled) {
        mLoginButton.setEnabled(enabled);

        if(enabled) {
            mLoginButton.setText(R.string.activity_login_btn_login);
        } else {
            mLoginButton.setText(R.string.activity_login_btn_login_processing);
        }
    }

    // endregion

    // region Listeners

    private class LoginButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View v) {
            // If the login task is running, we do nothing
            if(mUserLoginResponse != null) {
                return;
            }

            updateButtonState(false);

            String email = mEmailView.getText().toString();
            String password = mPasswordView.getText().toString();

            LoginViewModel loginViewModel = new LoginViewModel(email, password);

            mUserLoginResponse = new UserLoginResponse();
            mAccountService.login(loginViewModel, mUserLoginResponse);
        }
    }

    private class RegisterTextClickListener implements TextView.OnClickListener {

        @Override
        public void onClick(View v) {
            // If the login task is running, we do nothing
            if(mUserLoginResponse != null) {
                return;
            }

            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        }
    }

    // endregion

    // region Response callbacks

    // TODO: Make one BaseResponse class, that other extend from
    private class UserLoginResponse implements Callback<ResponseBody> {

        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            mUserLoginResponse = null;
            updateButtonState(true);

            if(response.isSuccess()) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            } else {
                // TODO: Extract to some AlertUtils class, style, user @strings res to get text and display as fragment?, refactor
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
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            mUserLoginResponse = null;
            updateButtonState(true);
            Log.e("onFailure", "Something went terribly wrong :/");
        }
    }

    // endregion
}