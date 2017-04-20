package com.qloop.orange.netInterface;

import com.qloop.orange.bean.RssListInfo;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Qloop on 2017/4/20.
 */

public interface RssNetInterface {

    @GET("live/rss_list.json")
    Observable<RssListInfo> getRssListInfo();
}
