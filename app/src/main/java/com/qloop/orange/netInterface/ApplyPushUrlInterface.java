package com.qloop.orange.netInterface;

import com.qloop.orange.bean.ApplyPushInfo;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 创建直播间时申请推流地址(逻辑暂定)
 * Created by Qloop on 2017/3/24.
 */

public interface ApplyPushUrlInterface {

    @GET("living/apply_push_url")
    Observable<ApplyPushInfo> getApplyPushInfo(@Query("description") String description);
}
