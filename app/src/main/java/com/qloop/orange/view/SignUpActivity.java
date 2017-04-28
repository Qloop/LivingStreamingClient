package com.qloop.orange.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.qloop.orange.R;

import butterknife.BindView;

/**
 * Created by Qloop on 2017/4/28.
 */

public class SignUpActivity extends BaseActivity {


    @BindView(R.id.et_email)
    EditText mEmail;
    @BindView(R.id.et_nickname)
    EditText mNickname;
    @BindView(R.id.et_password)
    EditText mPassword;
    @BindView(R.id.btn_sign)
    Button btnSign;
    @BindView(R.id.sing_up_progress)
    ProgressBar mSingUpProgress;

    @Override
    protected void initViews() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_sign_up;
    }
}
