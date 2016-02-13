package com.example.swipingapp.activities.account;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.swipingapp.R;
import com.example.swipingapp.activities.main.MainActivity;
import com.example.swipingapp.responses.ErrorResponse;
import com.example.swipingapp.services.account.AccountService;
import com.example.swipingapp.services.account.IAccountService;
import com.example.swipingapp.utils.DialogUtils;
import com.example.swipingapp.viewModels.account.LoginViewModel;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
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

        updateButtonState(true);
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

            if(response.isSuccess()) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            } else {
                updateButtonState(true);

                // TODO: Should we only use the message from the response?
                String title = getString(R.string.activity_login_error_login_failed_title);
                String message = getString(R.string.activity_login_error_login_failed_message);

                try {
                    Converter<ResponseBody, ErrorResponse> errorConverter = mAccountService.getRetrofit().responseBodyConverter(ErrorResponse.class, new Annotation[0]);
                    ErrorResponse error = errorConverter.convert(response.errorBody());
                    message = error.message;
                } catch (IOException e) {
                    // TODO: Better error handling
                    e.printStackTrace();
                }

                DialogUtils.displayMessageDialog(mContext, title, message);
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