package com.qloop.orange.netInterface;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Qloop on 2017/5/4.
 */

public interface FeedbackNetInterface {

    @GET("feedback")
    Observable<Response<ResponseBody>> submitFeedback(@Query("content") String content,
                                                      @Query("mail") String mail);
}
