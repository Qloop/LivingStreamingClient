package com.qloop.orange.model.Impl;

import com.qloop.orange.bean.UserInfo;
import com.qloop.orange.config.AppConfig;
import com.qloop.orange.model.IUserModel;
import com.qloop.orange.model.OnSignUpListener;
import com.qloop.orange.netInterface.UserNetInterface;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Qloop on 2017/4/30.
 */

public class UserModelImpl implements IUserModel {

    @Override
    public void signUp(String userName, String email, String pwd, final OnSignUpListener onSignUpListener) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(5000, TimeUnit.SECONDS);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        UserNetInterface userNetInterface = retrofit.create(UserNetInterface.class);
        userNetInterface.signUpAccess(userName, email, pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserInfo>() {
                    @Override
                    public void onCompleted() {
                        onSignUpListener.hasCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        onSignUpListener.hasExist();
                    }

                    @Override
                    public void onNext(UserInfo userInfo) {
                        onSignUpListener.setUserInfo(userInfo);
                    }
                });
    }
}
