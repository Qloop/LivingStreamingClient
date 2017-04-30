package com.qloop.orange.presenter;

import android.content.Context;

import com.qloop.orange.bean.UserInfo;
import com.qloop.orange.model.IUserModel;
import com.qloop.orange.model.Impl.UserModelImpl;
import com.qloop.orange.model.OnSignUpListener;
import com.qloop.orange.view.Iview.ISignUpView;

/**
 * Created by Qloop on 2017/4/30.
 */

public class SignUpPresenter {

    private ISignUpView signUpView;
    private IUserModel userModel;

    public SignUpPresenter(ISignUpView signUpView) {
        this.signUpView = signUpView;
        this.userModel = new UserModelImpl();
    }

    public void signUp() {
        userModel.signUp(signUpView.getUserName(), signUpView.getEmail(), signUpView.getPassword(),
                new OnSignUpListener() {
                    @Override
                    public void error() {
                        signUpView.error();
                    }

                    @Override
                    public void hasExist() {
                        signUpView.userExits();
                    }

                    @Override
                    public void setUserInfo(UserInfo userInfo) {
                        signUpView.cacheUserInfo(userInfo);
                    }

                    @Override
                    public void hasCompleted() {
                        signUpView.hideProgress();
                    }
                });
    }
}
