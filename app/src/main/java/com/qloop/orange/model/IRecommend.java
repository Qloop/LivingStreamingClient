package com.qloop.orange.model;

/**
 * Created by Qloop on 2017/4/17.
 */

public interface IRecommend {

    void getTopData(OnRecommendListener onRecommendListener);
    void getRecommendContentData(OnRecommendListener onRecommendListener);

}
