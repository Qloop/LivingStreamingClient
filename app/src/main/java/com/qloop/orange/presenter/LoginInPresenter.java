package com.qloop.orange.presenter;

import com.qloop.orange.model.Impl.UserModelImpl;
import com.qloop.orange.model.OnApplyUserInfoListener;
import com.qloop.orange.model.OnLoginInListener;
import com.qloop.orange.view.Iview.ILoginView;

/**
 * Created by Qloop on 2017/5/2.
 */

public class LoginInPresenter {

    private static final String LOGIN_SUCCESS = "success";
    private static final String LOGIN_FAILED = "failed";
    private ILoginView loginView;
    private UserModelImpl userModel;

    public LoginInPresenter(ILoginView loginView) {
        this.loginView = loginView;
        userModel = new UserModelImpl();
    }

    public void login() {
        userModel.loginIn(loginView.getEmail(), loginView.getPassword(), new OnLoginInListener() {
            @Override
            public void error() {
                loginView.error();
            }

            @Override
            public <T> void setUserInfo(T data) {
                if (LOGIN_SUCCESS.equals(data.toString())) {
                    loginView.loginSuccess();
                } else {
                    loginView.loginFailed();
                }
            }
        });
    }

    public void applyUserInfo() {
        userModel.applyUserInfo(loginView.getEmail(), new OnApplyUserInfoListener() {
            @Override
            public void error() {
                loginView.error();
            }

            @Override
            public <T> void setUserInfo(T data) {
                loginView.cacheUserInfo(data);
            }

        });
    }
}
