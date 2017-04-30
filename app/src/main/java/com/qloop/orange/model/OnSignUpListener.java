package com.qloop.orange.model;

import com.qloop.orange.bean.UserInfo;

/**
 * Created by Qloop on 2017/4/30.
 */

public interface OnSignUpListener {
    void error();
    void hasExist();
    void setUserInfo(UserInfo userInfo);
    void hasCompleted();
}
