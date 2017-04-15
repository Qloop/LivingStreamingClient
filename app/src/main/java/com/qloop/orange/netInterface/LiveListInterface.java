package com.qloop.orange.netInterface;

import com.qloop.orange.bean.LiveListInfo;

import retrofit2.http.GET;
import rx.Observable;

/**
 * [全部]直播间列表
 * Created by Qloop on 2017/4/15.
 */

public interface LiveListInterface {
    @GET("live/live_list.json")
    Observable<LiveListInfo> getLiveListInfo();
}
