package com.qloop.orange.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.qloop.orange.R;
import com.qloop.orange.bean.ApplyPushInfo;
import com.qloop.orange.config.AppConfig;
import com.qloop.orange.netInterface.ApplyPushUrlInterface;
import com.qloop.orange.utils.ToastUtils;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 创建直播间
 * Created by Qloop on 2017/3/24.
 */

public class CreateRoomActivity extends Activity {

    @BindView(R.id.et_home_name)
    EditText mHomeName;
    @BindView(R.id.btn_create_room)
    Button mCreateRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_room);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_create_room)
    public void initData() {
        if (TextUtils.isEmpty(mHomeName.getText().toString())) {
            ToastUtils.showToastLong(this, "请输入直播间名字");
            return;
        }
//        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        builder.connectTimeout(5000, TimeUnit.SECONDS);
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(AppConfig.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build();
//        ApplyPushUrlInterface applyPushUrlInterface = retrofit.create(ApplyPushUrlInterface.class);
//        applyPushUrlInterface.getApplyPushInfo(mHomeName.getText().toString())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<ApplyPushInfo>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(ApplyPushInfo applyPushInfo) {
//                        ToastUtils.showToastLong(CreateRoomActivity.this, applyPushInfo.getRtmp());
//                        Intent intent = new Intent(CreateRoomActivity.this, PushActivity.class);
//                        intent.putExtra("rtmp", applyPushInfo.getRtmp());
//                        startActivity(intent);
//                    }
//                });
    }
}
