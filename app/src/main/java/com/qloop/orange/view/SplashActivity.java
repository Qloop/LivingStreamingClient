package com.qloop.orange.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.qloop.orange.R;
import com.qloop.orange.presenter.CheckUpdatePresenter;
import com.qloop.orange.view.Iview.ISplashView;

import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Qloop on 2017/4/9.
 */

public class SplashActivity extends AppCompatActivity implements ISplashView {


    private SweetAlertDialog pDialog;
    private CheckUpdatePresenter checkUpdatePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        checkUpdatePresenter = new CheckUpdatePresenter(this, this);
    }


    @Override
    public void showSplashPic() {

    }

    @Override
    public void showUpdateWindow(String updateInfo) {
        pDialog = new SweetAlertDialog(this)
                .setTitleText("新版本")
                .setContentText(updateInfo)
                .setConfirmText("立即更新")
                .setCancelText("我再想想");
        //取消
        pDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                pDialog.dismiss();
                toMainPager(0);
            }
        });

        //立即更新
        pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                pDialog.dismiss();
                checkUpdatePresenter.downloadUpdate();
            }
        });
        pDialog.show();
    }

    @Override
    public void hideUpdateWindow() {

    }

    @Override
    public void toMainPager(int delay) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        }, delay);

    }
}
