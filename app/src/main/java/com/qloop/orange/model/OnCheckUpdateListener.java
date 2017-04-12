package com.qloop.orange.model;

import com.qloop.orange.bean.UpdateInfo;

/**
 * Created by Qloop on 2017/4/9.
 */

public interface OnCheckUpdateListener {
    void isLastVersion();
    void isOldVersion();
    void checkVersion(UpdateInfo updateInfo);
    void accessTimeOut();
}
