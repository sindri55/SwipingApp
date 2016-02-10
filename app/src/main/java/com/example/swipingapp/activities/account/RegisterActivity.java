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
import com.example.swipingapp.activities.about.TermsAndConditionsActivity;
import com.example.swipingapp.activities.main.MainActivity;
import com.example.swipingapp.services.account.AccountService;
import com.example.swipingapp.services.account.IAccountService;
import com.example.swipingapp.utils.DialogUtils;
import com.example.swipingapp.viewModels.account.RegisterViewModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    // region Properties

    private IAccountService mAccountService;
    private UserRegisterResponse mUserRegisterResponse = null;
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

        mAccountService = AccountService.getInstance();

        mFullNameView = (EditText) findViewById(R.id.full_name);
        mEmailView = (EditText) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        mConfirmPasswordView = (EditText) findViewById(R.id.confirm_password);
        mSignUpButton = (Button) findViewById(R.id.btn_sign_up);
        mTermsAndConditionsText = (TextView) findViewById(R.id.txt_terms_and_conditions);

        mSignUpButton.setOnClickListener(new SignUpButtonClickListener());
        mTermsAndConditionsText.setOnClickListener(new TermsAndConditionsTextClickListener());

        updateButtonState(true);
    }

    // endregion

    // region Private functions

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

            updateButtonState(false);

            String fullName = mFullNameView.getText().toString();
            String email = mEmailView.getText().toString();
            String password = mPasswordView.getText().toString();
            String confirmPassword = mConfirmPasswordView.getText().toString();

            RegisterViewModel registerViewModel = new RegisterViewModel(fullName, email, password, confirmPassword);

            mUserRegisterResponse = new UserRegisterResponse();
            mAccountService.register(registerViewModel, mUserRegisterResponse);
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

                String title = getString(R.string.activity_register_error_register_failed_title);
                String message = getString(R.string.activity_register_error_register_failed_message);

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