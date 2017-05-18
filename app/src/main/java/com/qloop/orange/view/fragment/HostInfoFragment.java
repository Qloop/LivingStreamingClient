package com.qloop.orange.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.qloop.orange.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Qloop on 2017/5/17.
 */

public class HostInfoFragment extends BaseFragment {


    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.tv_room_id)
    TextView tvRoomId;
    @BindView(R.id.btn_rss)
    Button btnRss;
    @BindView(R.id.tv_live_info)
    TextView tvLiveInfo;
    Unbinder unbinder;

    @Override
    public View initViews() {
        View rootView = View.inflate(mActivity, R.layout.fragment_host_info, null);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }


    @OnClick(R.id.btn_rss)
    public void onViewClicked() {
        //点击订阅
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
