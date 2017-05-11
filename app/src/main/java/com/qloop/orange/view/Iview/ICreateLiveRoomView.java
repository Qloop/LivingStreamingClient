package com.qloop.orange.view.Iview;

/**
 * Created by Qloop on 2017/5/10.
 */
public interface ICreateLiveRoomView {

    String getNickname();

    String getMail();

    String getTitle();

    String getDescription();

    String getClassify();

    void nextPager();

    void error();

    void hasExist();

    void cachePushUrl(String url);
}
