package com.qloop.orange.model.Impl;

import com.qloop.orange.bean.UpdateInfo;
import com.qloop.orange.config.AppConfig;
import com.qloop.orange.model.ICheckUpdateModel;
import com.qloop.orange.model.OnCheckUpdateListener;
import com.qloop.orange.netInterface.UpdateInterface;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Qloop on 2017/4/9.
 */

public class CheckUpdateModelImpl implements ICheckUpdateModel {

    @Override
    public void getUpdateInfo(final OnCheckUpdateListener onCheckUpdateListener) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(5000, TimeUnit.SECONDS);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        UpdateInterface updateInterface = retrofit.create(UpdateInterface.class);
        updateInterface.getCheckUpdateInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UpdateInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onCheckUpdateListener.accessTimeOut();
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(UpdateInfo updateInfo) {
                        onCheckUpdateListener.checkVersion(updateInfo);
                    }
                });

    }
}
