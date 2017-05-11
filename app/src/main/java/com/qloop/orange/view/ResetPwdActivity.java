package com.qloop.orange.view;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.qloop.orange.R;
import com.qloop.orange.bean.UserInfo;
import com.qloop.orange.config.AppConfig;
import com.qloop.orange.netInterface.UserNetInterface;
import com.qloop.orange.utils.SnackBarUtils;
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
 * Created by Qloop on 2017/4/28.
 */

public class ResetPwdActivity extends BaseActivity {


    private static final String RESET_SUCCESS = "请查收重置密码邮件(注意垃圾箱哦)";
    private static final String RESET_FAILED = "请求失败 请检查网络";
    @BindView(R.id.et_lost_pwd)
    EditText mEmail;
    @BindView(R.id.btn_reset)
    Button btnReset;
    @BindView(R.id.pb_reset)
    ProgressBar pbReset;
    @BindView(R.id.ll_reset_root_view)
    LinearLayout rootView;

    @Override
    protected void initViews() {

    }

    @OnClick(R.id.btn_reset)
    public void reset(){
        hideKeybord();
        if (checkInput()) {
            resetPwd(mEmail.getText().toString());
        }
    }

    private void resetPwd(String mail) {
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
        userNetInterface.reSetPwdAccess(mail)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
//                        Snackbar snackbar = Snackbar.make(rootView, RESET_FAILED, Snackbar.LENGTH_SHORT);
//                        SnackBarUtils.setSnackbarColor(snackbar, R.color.colorWhite, R.color.colorPrimary);
//                        snackbar.show();
                        SnackBarUtils.makeShort(rootView, RESET_FAILED).danger();
                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        ToastUtils.showToastShort(ResetPwdActivity.this,RESET_SUCCESS);
//                        Snackbar snackbar = Snackbar.make(rootView, RESET_SUCCESS, Snackbar.LENGTH_LONG);
//                        SnackBarUtils.setSnackbarColor(snackbar, R.color.colorWhite, R.color.colorPrimary);
////                        snackbar.setAction("知道啦~~", new View.OnClickListener() {
////                            @Override
////                            public void onClick(View v) {
////                                finish();
////                            }
////                        });
//                        snackbar.show();
                        SnackBarUtils.makeLong(rootView, RESET_SUCCESS).success();
                        finish();
                    }
                });
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_lost_pwd;
    }

    public boolean checkInput() {
        return !TextUtils.isEmpty(mEmail.getText().toString());
    }
}
