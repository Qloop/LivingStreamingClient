package com.qloop.orange.presenter;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.qloop.orange.bean.UpdateInfo;
import com.qloop.orange.model.ICheckUpdate;
import com.qloop.orange.model.Impl.CheckUpdateImpl;
import com.qloop.orange.model.OnCheckUpdateListener;
import com.qloop.orange.view.Iview.ISplashView;

/**
 * Created by Qloop on 2017/4/9.
 */

public class CheckUpdatePresenter {
    private ICheckUpdate checkUpdate;
    private ISplashView splashView;
    private Context mContext;

    public CheckUpdatePresenter(ISplashView splashView, Context context) {
        this.splashView = splashView;
        mContext = context;
        checkUpdate = new CheckUpdateImpl();
    }

    public void checkUpdate() {
        checkUpdate.getUpdateInfo(new OnCheckUpdateListener() {
            @Override
            public void isLastVersion() {

            }

            @Override
            public void isOldVersion() {

            }

            @Override
            public void checkVersion(UpdateInfo updateInfo) {
                if (updateInfo.getVersionCode() > getVersionCode()) {//有新版本
                    splashView.showUpdateWindow(updateInfo.getUpdateInfo());
                } else {
                    splashView.toMainPager(2000);
                }
            }
        });
    }

    public void downloadUpdate() {
        //下载更新包
    }

    /**
     * 获取本机的版本号
     */
    private int getVersionCode() {
        PackageManager packageManager = mContext.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(mContext.getPackageName(), 0);
//			System.out.println("版本号" + versionCode);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            //包名没找到
            e.printStackTrace();
        }
        return -1;
    }
}
