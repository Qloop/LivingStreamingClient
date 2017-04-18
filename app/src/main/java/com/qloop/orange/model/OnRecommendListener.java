package com.qloop.orange.model;

import com.qloop.orange.bean.LiveListInfo;
import com.qloop.orange.bean.TopRecommendInfo;

/**
 * Created by Qloop on 2017/4/15.
 */

public interface OnRecommendListener {
    void setTopData(TopRecommendInfo topRecommendInfo);

    void setContentData(LiveListInfo liveListInfo);
}
