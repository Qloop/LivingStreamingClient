package com.qloop.orange.netInterface;

import com.qloop.orange.bean.UpdateInfo;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Qloop on 2017/4/10.
 */

public interface UpdateInterface {
    @GET("update/check")
    Observable<UpdateInfo> getCheckUpdateInfo();
}
