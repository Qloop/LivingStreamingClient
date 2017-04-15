package com.qloop.orange.view.Iview;

import com.qloop.orange.bean.LiveListInfo;

/**
 * Created by Qloop on 2017/4/13.
 */

public interface IAllLiveFragmemt {
    <T> void createAdapter(T data);

    void toLiveRoom();
}
