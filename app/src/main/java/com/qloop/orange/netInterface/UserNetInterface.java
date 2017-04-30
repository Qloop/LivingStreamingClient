package com.qloop.orange.netInterface;

import com.qloop.orange.bean.UserInfo;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Qloop on 2017/4/30.
 */

public interface UserNetInterface {

    @POST("user/create")
    Observable<UserInfo> signUpAccess(@Query("name") String name, @Query("mail") String mail,
                                      @Query("password") String password);

    /**
     * @return "success" / "failed"
     */
    @GET("user/login")
    Observable<String> loginAccess(@Query("email") String email, @Query("password") String password);

    @GET("user/reset_mail")
    Observable<Boolean> reSetPwdAccess(@Query("mail") String mail);

}
