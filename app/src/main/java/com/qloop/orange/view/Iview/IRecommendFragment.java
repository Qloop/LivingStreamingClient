package com.qloop.orange.view.Iview;

/**
 * Created by Qloop on 2017/4/13.
 */

public interface IRecommendFragment {

    <T> void setTopData(T data);

    <T> void createAdapter(T data);

    void toLiveRoom();

}
