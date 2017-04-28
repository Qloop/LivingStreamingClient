package com.qloop.orange.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.qloop.orange.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Qloop on 2017/4/28.
 */

public class LoginActivity extends BaseActivity {


    @BindView(R.id.email)
    AutoCompleteTextView mEmail;
    @BindView(R.id.password)
    EditText mPassword;
    @BindView(R.id.tv_lost_password)
    TextView tvLostPassword;
    @BindView(R.id.btn_email_login)
    Button btnEmailLogin;
    @BindView(R.id.btn_sign_up)
    Button btnSignUp;
    @BindView(R.id.login_progress)
    ProgressBar mLoginProgress;

    @Override
    protected void initViews() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_login;
    }

    @OnClick({R.id.tv_lost_password, R.id.btn_email_login, R.id.btn_sign_up})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_lost_password:
                startActivity(new Intent(this, ResetPwdActivity.class));
                break;
            case R.id.btn_email_login:
                break;
            case R.id.btn_sign_up:
                startActivity(new Intent(this, SignUpActivity.class));
                break;
        }
    }
}
