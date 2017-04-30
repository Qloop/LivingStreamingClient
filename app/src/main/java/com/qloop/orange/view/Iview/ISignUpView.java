package com.qloop.orange.view.Iview;

/**
 * Created by Qloop on 2017/4/30.
 */

public interface ISignUpView {

    void error();

    void userExits();

    void showProgress();

    void hideProgress();

    boolean checkInput();

    <T> void cacheUserInfo(T data);

    String getUserName();

    String getEmail();

    String getPassword();

    void toNextPager();

    void showSuccessDialog();
}
