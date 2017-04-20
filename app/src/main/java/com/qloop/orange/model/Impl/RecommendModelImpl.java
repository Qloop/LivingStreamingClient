package com.qloop.orange.model.Impl;

import android.util.Log;

import com.qloop.orange.bean.LiveListInfo;
import com.qloop.orange.bean.TopRecommendInfo;
import com.qloop.orange.model.IRecommendModel;
import com.qloop.orange.model.OnRecommendListener;
import com.qloop.orange.netInterface.RecommendNetInterface;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Qloop on 2017/4/17.
 */

public class RecommendModelImpl implements IRecommendModel {


    private static final String TAG = "RECOMMEND";

    @Override
    public void getTopData(final OnRecommendListener onRecommendListener) {
        Log.i(TAG, "getdata+++++++++++++++++++++++");

        //获取top数据
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(5000, TimeUnit.SECONDS);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.tzloop.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        RecommendNetInterface recommendNetInterface = retrofit.create(RecommendNetInterface.class);
        recommendNetInterface.getTopRecommendInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<TopRecommendInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(TopRecommendInfo topRecommendInfo) {
                        onRecommendListener.setTopData(topRecommendInfo);
                    }
                });

    }

    @Override
    public void getRecommendContentData(final OnRecommendListener onRecommendListener) {
        //内容数据
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(5000, TimeUnit.SECONDS);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.tzloop.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        RecommendNetInterface recommendNetInterface = retrofit.create(RecommendNetInterface.class);
        recommendNetInterface.getLiveListInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LiveListInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(LiveListInfo liveListInfo) {
                        onRecommendListener.setContentData(liveListInfo);
                    }
                });
    }
}
