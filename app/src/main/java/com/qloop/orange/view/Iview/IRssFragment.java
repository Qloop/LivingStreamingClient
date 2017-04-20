package com.qloop.orange.view.Iview;

/**
 * Created by Qloop on 2017/4/13.
 */

public interface IRssFragment {

    <T> void createAdapter(T data);

    void stopRefresh();

    void toLiveRoom();
}
