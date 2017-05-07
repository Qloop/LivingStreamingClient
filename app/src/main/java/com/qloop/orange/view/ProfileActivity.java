package com.qloop.orange.view;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qloop.orange.R;
import com.qloop.orange.adapter.AllLiveAdapter;
import com.qloop.orange.utils.UserCache;
import com.qloop.orange.view.Iview.IProfileView;
import com.qloop.orange.wight.CircleImageView;

import butterknife.BindView;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by Qloop on 2017/5/6.
 */

public class ProfileActivity extends BaseActivity implements IProfileView {


    @BindView(R.id.tv_avator)
    TextView tvAvator;
    @BindView(R.id.civ_avator)
    CircleImageView civAvator;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.rl_change_avator)
    RelativeLayout rlChangeAvator;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.rl_change_nickname)
    RelativeLayout rlChangeNickname;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.tv_user_mail)
    TextView tvUserMail;
    @BindView(R.id.ll_profile_root)
    LinearLayout mRootView;

    @Override
    protected void initViews() {
        tvName.setText(UserCache.getUserName(this));
        Glide.with(this)
                .load(UserCache.getAvator(this))
                .centerCrop()
                .placeholder(R.mipmap.ic_avatar_default)
                .into(civAvator);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_profile;
    }


    @OnClick({R.id.rl_change_avator, R.id.rl_change_nickname})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_change_avator:
                showPopWindow();
                break;
            case R.id.rl_change_nickname:
                break;
        }
    }

    @Override
    public void showPopWindow() {
        startActivity(new Intent(this, ChangeAvatarActivity.class));
//        finish();
    }

    @Override
    public void error() {

    }

    @Override
    public void setAvator() {

    }

    @Override
    public void takePhoto() {

    }

    @Override
    public void getPicFromPhone() {

    }

    @Override
    public void showPopAnimation() {

    }

    @Override
    public void hidePopAnimation() {

    }
}
