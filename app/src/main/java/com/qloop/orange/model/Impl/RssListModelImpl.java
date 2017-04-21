package com.qloop.orange.model.Impl;

import com.qloop.orange.bean.RssListInfo;
import com.qloop.orange.model.IRssModel;
import com.qloop.orange.model.OnRssListListener;
import com.qloop.orange.netInterface.RssNetInterface;
import com.qloop.orange.utils.ToastUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Qloop on 2017/4/20.
 */

public class RssListModelImpl implements IRssModel {
    @Override
    public void getData(final OnRssListListener onRssListListener) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(5000, TimeUnit.SECONDS);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.tzloop.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        RssNetInterface rssNetInterface = retrofit.create(RssNetInterface.class);
        rssNetInterface.getRssListInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RssListInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(RssListInfo rssListInfo) {
                        onRssListListener.setData(rssListInfo);
                    }
                });
    }
}
