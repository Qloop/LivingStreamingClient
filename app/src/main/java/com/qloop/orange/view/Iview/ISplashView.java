package com.qloop.orange.view.Iview;

/**
 * Created by Qloop on 2017/4/9.
 */

public interface ISplashView {
    void showSplashPic();
    void showUpdateWindow(String updateInfo);
    void hideUpdateWindow();
    void toMainPager(int delay);
}
