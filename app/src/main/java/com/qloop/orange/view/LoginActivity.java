package com.qloop.orange.view;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.qloop.orange.MyApplication;
import com.qloop.orange.R;
import com.qloop.orange.bean.UserInfo;
import com.qloop.orange.presenter.LoginInPresenter;
import com.qloop.orange.utils.SnackBarUtils;
import com.qloop.orange.utils.UserCache;
import com.qloop.orange.view.Iview.ILoginView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Qloop on 2017/4/28.
 */

public class LoginActivity extends BaseActivity implements ILoginView {


    private static final String INPUT_PROMOT = "请检查输入";
    private static final String LOGIN_ERROR = "登录异常，检查网络重试";
    private static final String LOGIN_FAILED = "登陆失败，请检查用户名密码";
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
    @BindView(R.id.ll_rootview)
    LinearLayout rootView;
    private LoginInPresenter loginInPresenter;

    @Override
    protected void initViews() {
        loginInPresenter = new LoginInPresenter(this);
//        mEmail.setAdapter();  //突然觉得很啰嗦  不写了-、-  想实现需要修改UserCache里对每一个账户缓存
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

    @OnClick(R.id.btn_email_login)
    public void loginIn() {
        hideKeybord();
        if (loginInPresenter != null && checkInput()) {
            showProgress();
            loginInPresenter.login();
        } else {
            SnackBarUtils.makeLong(rootView, INPUT_PROMOT).info();
        }
    }

    @OnClick(R.id.tv_lost_password)
    public void toResetPwd() {
        startActivity(new Intent(this, ResetPwdActivity.class));
        finish();
    }

//    private void showSnackBar(String msg) {
//        Snackbar snackbar = Snackbar.make(rootView, msg, Snackbar.LENGTH_SHORT);
//        SnackBarUtils.setSnackbarColor(snackbar, R.color.colorWhite, R.color.colorPrimary);
//        snackbar.show();
//    }

    @Override
    public void error() {
        SnackBarUtils.makeLong(rootView, LOGIN_ERROR).danger();
    }

    @Override
    public void showProgress() {
        mLoginProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mLoginProgress.setVisibility(View.GONE);
    }

    @Override
    public <T> void cacheUserInfo(T data) {
        UserInfo userInfo = (UserInfo) data;
        UserCache.cacheUserInfo(this, userInfo.getNickname(), userInfo.getMail(),
                userInfo.getAvatar() == null ? "" : userInfo.getAvatar().toString(),
                userInfo.getLiveRoom() == null ? "" : userInfo.getLiveRoom());
        hideProgress();
        toNextPager();
    }


    @Override
    public boolean checkInput() {
        return !TextUtils.isEmpty(mEmail.getText().toString())
                && !TextUtils.isEmpty(mPassword.getText().toString());
    }

    @Override
    public String getEmail() {
        return mEmail.getText().toString();
    }

    @Override
    public String getPassword() {
        return mPassword.getText().toString();
    }

    @Override
    public void loginSuccess() {
        loginInPresenter.applyUserInfo();
    }

    @Override
    public void loginFailed() {
        SnackBarUtils.makeLong(rootView, LOGIN_FAILED).danger();
    }

    @Override
    public void toNextPager() {
        MyApplication.destoryMap.get("MainActivity").finish();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }


}
