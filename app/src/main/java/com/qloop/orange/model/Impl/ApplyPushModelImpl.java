package com.qloop.orange.model.Impl;

import com.qloop.orange.bean.ApplyPushInfo;
import com.qloop.orange.config.AppConfig;
import com.qloop.orange.model.IApplyPush;
import com.qloop.orange.model.OnApplyPushListener;
import com.qloop.orange.model.OnApplyUserInfoListener;
import com.qloop.orange.netInterface.ApplyPushUrlInterface;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Qloop on 2017/5/10.
 */

public class ApplyPushModelImpl implements IApplyPush {

    @Override
    public void applyPush(String title, String mail, String description, String nickname, String classify,
                          final OnApplyPushListener onApplyPushListener) {
        OkHttpClient builder = new OkHttpClient.Builder()
                .connectTimeout(5000, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConfig.BASE_URL)
                .client(builder)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ApplyPushUrlInterface applyPushUrlInterface = retrofit.create(ApplyPushUrlInterface.class);
        applyPushUrlInterface.getApplyPushInfo(description, mail, nickname, title, classify)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ApplyPushInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onApplyPushListener.error();
                    }

                    @Override
                    public void onNext(ApplyPushInfo applyPushInfo) {
                        if (applyPushInfo.getErrorCode() == 1) {
                            onApplyPushListener.error();
                        } else if (applyPushInfo.getErrorCode() == 2) {
                            onApplyPushListener.hasExist();
                        } else {
                            onApplyPushListener.setPushUrl(applyPushInfo.getRtmp());
                        }

                    }
                });
    }
}
