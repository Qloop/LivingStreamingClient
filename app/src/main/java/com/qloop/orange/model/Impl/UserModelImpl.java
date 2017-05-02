package com.qloop.orange.model.Impl;

import android.util.Log;

import com.google.gson.Gson;
import com.qloop.orange.bean.UserInfo;
import com.qloop.orange.config.AppConfig;
import com.qloop.orange.model.IUserModel;
import com.qloop.orange.model.OnApplyUserInfoListener;
import com.qloop.orange.model.OnLoginInListener;
import com.qloop.orange.model.OnSignUpListener;
import com.qloop.orange.netInterface.UserNetInterface;

import java.io.IOException;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Response;
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
    public void signUp(final String userName, String email, String pwd, final OnSignUpListener onSignUpListener) {
        OkHttpClient builder = new OkHttpClient.Builder()
                .connectTimeout(5000, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConfig.BASE_URL)
                .client(builder)
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
//                        onSignUpListener.hasExist();
                    }

                    @Override
                    public void onNext(UserInfo userInfo) {
                        onSignUpListener.setUserInfo(userInfo);
                    }
                });
    }

    @Override
    public void loginIn(String email, String pwd, final OnLoginInListener onLoginInListener) {
        OkHttpClient builder = new OkHttpClient.Builder()
                .connectTimeout(5000, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConfig.BASE_URL)
                .client(builder)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        UserNetInterface userNetInterface = retrofit.create(UserNetInterface.class);
        userNetInterface.loginAccess(email, pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<ResponseBody>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        onLoginInListener.error();
                    }

                    @Override
                    public void onNext(Response<ResponseBody> responseBodyResponse) {
                        try {
                            onLoginInListener.setUserInfo(responseBodyResponse.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                            onLoginInListener.error();
                        }
                    }
                });
    }

    @Override
    public void applyUserInfo(String email, final OnApplyUserInfoListener onApplyUserInfoListener) {
        OkHttpClient builder = new OkHttpClient.Builder()
                .connectTimeout(5000, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        UserNetInterface userNetInterface = retrofit.create(UserNetInterface.class);
        userNetInterface.getUserInfo(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        onApplyUserInfoListener.error();
                    }

                    @Override
                    public void onNext(UserInfo userInfo) {
                        onApplyUserInfoListener.setUserInfo(userInfo);
                    }
                });

    }
}
