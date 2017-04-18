package com.qloop.orange.netInterface;

import com.qloop.orange.bean.LiveListInfo;
import com.qloop.orange.bean.TopRecommendInfo;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Qloop on 2017/4/17.
 */

public interface RecommendNetInterface {

    @GET("live/top_recommend.json")
    Observable<TopRecommendInfo> getTopRecommendInfo();

    @GET("live/live_list.json")
    Observable<LiveListInfo> getLiveListInfo();
}
