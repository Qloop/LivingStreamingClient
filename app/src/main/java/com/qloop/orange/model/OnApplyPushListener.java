package com.qloop.orange.model;

/**
 * Created by Qloop on 2017/5/10.
 */

public interface OnApplyPushListener {

    void error();

    void hasExist();

    void setPushUrl(String url);
}
