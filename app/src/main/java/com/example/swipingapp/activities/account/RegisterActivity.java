package com.example.swipingapp.activities.account;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.swipingapp.R;
import com.example.swipingapp.activities.about.TermsAndConditionsActivity;
import com.example.swipingapp.activities.main.MainActivity;
import com.example.swipingapp.responses.ErrorResponse;
import com.example.swipingapp.services.account.AccountService;
import com.example.swipingapp.services.account.IAccountService;
import com.example.swipingapp.utils.DialogUtils;
import com.example.swipingapp.viewModels.account.RegisterViewModel;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    // region Properties

    private IAccountService mAccountService;
    private UserRegisterResponse mUserRegisterResponse = null;
    final Context mContext = this;

    // endregion

    // region UI references

    private EditText mNameInput;
    private EditText mEmailInput;
    private EditText mPasswordInput;
    private EditText mConfirmPasswordInput;
    private Button mSignUpButton;
    private TextView mTermsAndConditionsText;

    // endregion

    // region Override functions

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAccountService = AccountService.getInstance();

        mNameInput = (EditText) findViewById(R.id.input_name);
        mEmailInput = (EditText) findViewById(R.id.input_email);
        mPasswordInput = (EditText) findViewById(R.id.input_password);
        mConfirmPasswordInput = (EditText) findViewById(R.id.input_confirm_password);
        mSignUpButton = (Button) findViewById(R.id.btn_sign_up);
        mTermsAndConditionsText = (TextView) findViewById(R.id.txt_terms_and_conditions);

        mSignUpButton.setOnClickListener(new SignUpButtonClickListener());
        mTermsAndConditionsText.setOnClickListener(new TermsAndConditionsTextClickListener());

        updateButtonState(true);
    }

    // endregion

    // region Private functions

    private boolean validateModel() {
        boolean valid = true;
        View focusView = null;

        if(TextUtils.isEmpty(mNameInput.getText())) {
            mNameInput.setError(getString(R.string.error_field_required));
            focusView = mNameInput;
            valid = false;
        }
        if(TextUtils.isEmpty(mEmailInput.getText())) {
            mEmailInput.setError(getString(R.string.error_field_required));
            focusView = (focusView == null) ? mEmailInput : focusView;
            valid = false;
        }
        if(TextUtils.isEmpty(mPasswordInput.getText())) {
            mPasswordInput.setError(getString(R.string.error_field_required));
            focusView = (focusView == null) ? mPasswordInput : focusView;
            valid = false;
        }
        if(TextUtils.isEmpty(mConfirmPasswordInput.getText())) {
            mConfirmPasswordInput.setError(getString(R.string.error_field_required));
            focusView = (focusView == null) ? mConfirmPasswordInput : focusView;
            valid = false;
        }

        if(focusView != null) {
            focusView.requestFocus();
        }

        return valid;
    }

    private void updateButtonState(final boolean enabled) {
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
            if(mUserRegisterResponse != null) {
                return;
            }

            if(validateModel()) {
                updateButtonState(false);

                String name = mNameInput.getText().toString();
                String email = mEmailInput.getText().toString();
                String password = mPasswordInput.getText().toString();
                String confirmPassword = mConfirmPasswordInput.getText().toString();

                RegisterViewModel registerViewModel = new RegisterViewModel(name, email, password, confirmPassword);

                mUserRegisterResponse = new UserRegisterResponse();
                mAccountService.register(registerViewModel, mUserRegisterResponse);
            }
        }
    }

    private class TermsAndConditionsTextClickListener implements TextView.OnClickListener {

        @Override
        public void onClick(View v) {
            // If the register task is running, we do nothing
            if(mUserRegisterResponse != null) {
                return;
            }

            Intent intent = new Intent(RegisterActivity.this, TermsAndConditionsActivity.class);
            startActivity(intent);
        }
    }

    // endregion

    // region Response callbacks

    // TODO: Make one BaseResponse class, that other extend from
    private class UserRegisterResponse implements Callback<ResponseBody> {

        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            mUserRegisterResponse = null;

            if(response.isSuccess()) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            } else {
                updateButtonState(true);

                // TODO: Should we only use the message from the response?
                String title = getString(R.string.activity_register_error_register_failed_title);
                String message = getString(R.string.activity_register_error_register_failed_message);

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
            mUserRegisterResponse = null;
            updateButtonState(true);
            Log.e("onFailure", "Something went terribly wrong :/");
        }
    }

    // endregion
}