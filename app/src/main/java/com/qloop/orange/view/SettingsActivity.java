package com.qloop.orange.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.qloop.orange.R;
import com.qloop.orange.wight.ProfileItemLayout;
import com.qloop.orange.wight.TopBarView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
                break;
            case R.id.pil_about:
                break;
            case R.id.btn_sign_out:
                break;
        }
    }
}
