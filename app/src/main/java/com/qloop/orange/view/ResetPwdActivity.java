package com.qloop.orange.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.qloop.orange.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Qloop on 2017/4/28.
 */

public class ResetPwdActivity extends BaseActivity {


    @BindView(R.id.et_lost_pwd)
    EditText mEmail;
    @BindView(R.id.btn_email_login)
    Button btnEmailLogin;
    @BindView(R.id.pb_reset)
    ProgressBar pbReset;

    @Override
    protected void initViews() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_lost_pwd;
    }
}
