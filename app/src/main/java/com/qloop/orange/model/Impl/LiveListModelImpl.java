package com.qloop.orange.model.Impl;

import com.qloop.orange.bean.LiveListInfo;
import com.qloop.orange.model.ILiveListModel;
import com.qloop.orange.model.OnLiveListListener;
import com.qloop.orange.netInterface.LiveListInterface;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Qloop on 2017/4/15.
 */

public class LiveListModelImpl implements ILiveListModel {

    @Override
    public void getLiveList(final OnLiveListListener onLiveListListener) {
        System.out.println("getLiveList@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(5000, TimeUnit.SECONDS);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.tzloop.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        LiveListInterface liveListInterface = retrofit.create(LiveListInterface.class);
        liveListInterface.getLiveListInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LiveListInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        System.out.println("error*+*+*+*+*++**+*++**+*+*+");
                    }

                    @Override
                    public void onNext(LiveListInfo liveListInfo) {
                        System.out.println("NEXT -=-=-=-=-===-=-=-=-=-=-=-=-=-=-=-");
                        onLiveListListener.setData(liveListInfo);
                    }
                });
    }
}
