package com.qloop.orange.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qloop.orange.R;
import com.qloop.orange.utils.ToastUtils;
import com.qloop.orange.utils.UserCache;
import com.qloop.orange.view.Iview.IUserFragment;
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

    Unbinder unbinder;

    @Override
    public View initViews() {
        View view = View.inflate(mActivity, R.layout.fragment_user, null);
        unbinder = ButterKnife.bind(this, view);
        setUserInfo();
        return view;
    } 


    private void setUserInfo() {
        String userName = UserCache.getUserName(mActivity);
        tvUserName.setText(userName);
        //头像

    }

    @Override
    public void onError() {
        ToastUtils.showToastShort(mActivity, "数据错误");
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
        startActivity(new Intent(mActivity, SettingsActivity.class));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
