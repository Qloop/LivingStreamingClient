package com.qloop.orange.netInterface;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Qloop on 2017/5/6.
 */

public interface AvatarNetInterface {

    @POST("save_avatar")
    Observable<Response<ResponseBody>> sendNewAvatar(@Query("mail") String mail,
                                                     @Query("avatar") String avatar);

    @GET("get_avatar")
    Observable<Response<ResponseBody>> getAvatar(@Query("mail") String mail);
}
