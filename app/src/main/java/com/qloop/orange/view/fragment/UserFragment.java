package com.qloop.orange.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.qloop.orange.R;
import com.qloop.orange.utils.ToastUtils;
import com.qloop.orange.utils.UserCache;
import com.qloop.orange.view.Iview.IUserFragment;
import com.qloop.orange.view.LoginActivity;
import com.qloop.orange.view.SettingsActivity;
import com.qloop.orange.wight.CircleImageView;
import com.qloop.orange.wight.ProfileItemLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Qloop on 2017/4/13.
 */

public class UserFragment extends BaseFragment implements IUserFragment {
    @BindView(R.id.civ_avatar)
    CircleImageView civAvatar;
    @BindView(R.id.ptl_apply_host)
    ProfileItemLayout ptlApplyHost;
    @BindView(R.id.ptl_rss)
    ProfileItemLayout ptlRss;
    @BindView(R.id.ptl_history)
    ProfileItemLayout ptlHistory;
    @BindView(R.id.ptl_privateLetter)
    ProfileItemLayout ptlPrivateLetter;
    @BindView(R.id.ptl_setting)
    ProfileItemLayout ptlSetting;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.btn_login)
    Button btnLogin;

    Unbinder unbinder;

    private static final String DEFAULT_USER_NAME = "未登陆";

    @Override
    public View initViews() {
        View view = View.inflate(mActivity, R.layout.fragment_user, null);
        unbinder = ButterKnife.bind(this, view);
        setUserInfo();
        return view;
    }


    private void setUserInfo() {
        String userName = UserCache.getUserName(mActivity);
        if (userName.equals(DEFAULT_USER_NAME)) {
            tvUserName.setVisibility(View.GONE);
            btnLogin.setVisibility(View.VISIBLE);
        } else {
            tvUserName.setVisibility(View.VISIBLE);
            btnLogin.setVisibility(View.GONE);
            tvUserName.setText(userName);
        }
        //头像

    }

    @Override
    public void onError() {
        ToastUtils.showToastShort(mActivity, "数据错误");
    }


    @OnClick(R.id.btn_login)
    public void login() {
        startActivity(new Intent(mActivity, LoginActivity.class));
//        mActivity.finish();
    }

    @OnClick(R.id.civ_avatar)
    public void changeUserInfo() {

    }

    @OnClick(R.id.ptl_apply_host)
    public void applyHost() {

    }

    @OnClick(R.id.ptl_rss)
    public void rss() {

    }

    @OnClick(R.id.ptl_history)
    public void watchHistory() {

    }

    @OnClick(R.id.ptl_privateLetter)
    public void privateLetter() {

    }

    @OnClick(R.id.ptl_setting)
    public void settings() {
        Intent intent = new Intent(mActivity, SettingsActivity.class);
        startActivity(intent);
//        mActivity.finish();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
