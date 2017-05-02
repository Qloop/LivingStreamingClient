package com.qloop.orange.view;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.qloop.orange.R;
import com.qloop.orange.bean.UserInfo;
import com.qloop.orange.presenter.SignUpPresenter;
import com.qloop.orange.utils.ToastUtils;
import com.qloop.orange.utils.UserCache;
import com.qloop.orange.view.Iview.ISignUpView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Qloop on 2017/4/28.
 */

public class SignUpActivity extends BaseActivity implements ISignUpView {


    @BindView(R.id.et_email)
    EditText mEmail;
    @BindView(R.id.et_nickname)
    EditText mNickname;
    @BindView(R.id.et_password)
    EditText mPassword;
    @BindView(R.id.btn_sign)
    Button btnSign;
    @BindView(R.id.sign_up_progress)
    ProgressBar mSignUpProgress;
    private SignUpPresenter signUpPresenter;

    private static final String ERROR = "注册失败";
    private static final String USER_EXIST = "用户已存在";
    private UserInfo userInfo;
    private SweetAlertDialog pDialog;

    @Override
    protected void initViews() {
        signUpPresenter = new SignUpPresenter(this);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_sign_up;
    }

    @OnClick(R.id.btn_sign)
    public void signUpClick() {
        hideKeybord();
        if (signUpPresenter != null && checkInput()) {
            showProgress();
            signUpPresenter.signUp();
        }
    }

    @Override
    public void error() {
        ToastUtils.showToastShort(this, ERROR);
    }

    @Override
    public void userExits() {
        ToastUtils.showToastShort(this, USER_EXIST);
    }

    @Override
    public void showProgress() {
        mSignUpProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mSignUpProgress.setVisibility(View.GONE);
    }

    @Override
    public <T> void cacheUserInfo(T data) {
        userInfo = (UserInfo) data;
        if (USER_EXIST.equals(userInfo.getResult())) {
            userExits();
        } else {
            UserCache.cacheUserInfo(this, userInfo.getNickname(), userInfo.getMail(),
                    userInfo.getAvatar() == null ? "" : userInfo.getAvatar().toString(), null);
            hideProgress();
            showSuccessDialog();
        }
    }

    @Override
    public String getUserName() {
        return mNickname.getText().toString();
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
    public boolean checkInput() {
        return !TextUtils.isEmpty(mEmail.getText().toString())
                && !TextUtils.isEmpty(mNickname.getText().toString())
                && !TextUtils.isEmpty(mPassword.getText().toString());
    }

    @Override
    public void toNextPager() {
        finish();
    }

    @Override
    public void showSuccessDialog() {
        pDialog = new SweetAlertDialog(this)
                .setTitleText("Congratulation!")
                .setContentText("注册成功,请及时登陆邮箱验证")
                .setConfirmText("确定")
                .setCancelText("好的");
        //取消
        pDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                pDialog.dismiss();
                toNextPager();
            }
        });

        //立即更新
        pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                pDialog.dismiss();
                toNextPager();
            }
        });
        pDialog.show();
    }

}
