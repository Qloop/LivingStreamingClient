package com.qloop.orange.view.Iview;

/**
 * Created by Qloop on 2017/5/2.
 */

public interface ILoginView {

    void error();

    void showProgress();

    void hideProgress();

    <T> void cacheUserInfo(T data);

    boolean checkInput();

    String getEmail();

    String getPassword();

    void loginSuccess();

    void loginFailed();

    void toNextPager();

}
