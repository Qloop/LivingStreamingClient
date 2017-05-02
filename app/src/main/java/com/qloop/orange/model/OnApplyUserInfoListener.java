package com.qloop.orange.model;

/**
 * Created by Qloop on 2017/5/2.
 */

public interface OnApplyUserInfoListener {

    void error();

    <T> void setUserInfo(T data);
}
