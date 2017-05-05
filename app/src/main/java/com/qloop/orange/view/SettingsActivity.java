package com.qloop.orange.view;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.qloop.orange.MyApplication;
import com.qloop.orange.R;
import com.qloop.orange.bean.UpdateInfo;
import com.qloop.orange.model.ICheckUpdateModel;
import com.qloop.orange.model.Impl.CheckUpdateModelImpl;
import com.qloop.orange.model.OnCheckUpdateListener;
import com.qloop.orange.utils.UserCache;
import com.qloop.orange.wight.ProfileItemLayout;
import com.qloop.orange.wight.TopBarView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Qloop on 2017/4/25.
 */

public class SettingsActivity extends BaseActivity {
    @BindView(R.id.topbar)
    TopBarView topbar;
    @BindView(R.id.copyright_statement)
    ProfileItemLayout copyrightStatement;
    @BindView(R.id.switch_hardware_decode)
    Switch switchHardwareDecode;
    @BindView(R.id.switch_little_window)
    Switch switchLittleWindow;
    @BindView(R.id.pil_suggestion)
    ProfileItemLayout pilSuggestion;
    @BindView(R.id.pil_check_version)
    ProfileItemLayout pilCheckVersion;
    @BindView(R.id.pil_about)
    ProfileItemLayout pilAbout;
    @BindView(R.id.btn_sign_out)
    Button btnSignOut;
    @BindView(R.id.tv_version)
    TextView tvVersion;

    @Override
    protected void initViews() {
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_settings;
    }

    @OnClick({R.id.copyright_statement, R.id.pil_suggestion, R.id.pil_check_version, R.id.pil_about, R.id.btn_sign_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.copyright_statement:
                startActivity(new Intent(this, CopyRightActivity.class));
                break;
            case R.id.pil_suggestion:
                startActivity(new Intent(this, FeedbackActivity.class));
                break;
            case R.id.pil_check_version:
                checkVersion();
                break;
            case R.id.pil_about:
                startActivity(new Intent(this, CopyRightActivity.class));
                break;
            case R.id.btn_sign_out:
                signOut();
                break;
        }
    }

    private void signOut() {
        UserCache.cleanUserInfo(this);
        MyApplication.destoryMap.get("MainActivity").finish();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void checkVersion() {
        ICheckUpdateModel checkUpdateModel = new CheckUpdateModelImpl();
        checkUpdateModel.getUpdateInfo(new OnCheckUpdateListener() {
            @Override
            public void isLastVersion() {

            }

            @Override
            public void isOldVersion() {

            }

            @Override
            public void checkVersion(UpdateInfo updateInfo) {
                if (updateInfo.getVersionCode() > getVersionCode()) {
                    showUpdateWindow(updateInfo.getUpdateInfo());
                } else {
                    showPromptDialog("已经是最新版本");
                }
            }

            @Override
            public void accessTimeOut() {
                showPromptDialog(NET_ERROR);
            }
        });
    }

    /**
     * 获取本机的版本号
     */
    private int getVersionCode() {
        PackageManager packageManager = getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(this.getPackageName(), 0);
//			System.out.println("版本号" + versionCode);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            //包名没找到
            e.printStackTrace();
        }
        return -1;
    }

    public void showUpdateWindow(String updateInfo) {
        final SweetAlertDialog pDialog = new SweetAlertDialog(this)
                .setTitleText("新版本")
                .setContentText(updateInfo)
                .setConfirmText("立即更新")
                .setCancelText("我再想想");
        //取消
        pDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                pDialog.dismiss();
            }
        });

        //立即更新
        pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                pDialog.dismiss();
//                checkUpdatePresenter.downloadUpdate();
            }
        });
        pDialog.show();
    }
}
