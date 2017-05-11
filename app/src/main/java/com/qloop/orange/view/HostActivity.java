package com.qloop.orange.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.Space;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qloop.orange.R;
import com.qloop.orange.utils.UserCache;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by Qloop on 2017/5/10.
 */

public class HostActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {

    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR = 0.9f;
    private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS = 0.3f;
    private static final int ALPHA_ANIMATIONS_DURATION = 200;

    private boolean mIsTheTitleVisible = false;
    private boolean mIsTheTitleContainerVisible = true;

    @BindView(R.id.main_linearlayout_title)
    LinearLayout mTitleContainer;
    @BindView(R.id.main_textview_title)
    TextView mTitle;
    @BindView(R.id.main_appbar)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.main_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.iv_avatar)
    ImageView mAvatar;
    @BindView(R.id.tv_nickname)
    TextView mNickname;
    @BindView(R.id.tv_live_room_name)
    TextView mLiveRoomName;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.tv_horizental)
    TextView tvHorizental;
    @BindView(R.id.tv_vertical)
    TextView tvVertical;
    private Unbinder unbinder;

    private int DIRECTION = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorHostStatusBar));
        setContentView(R.layout.activity_host);
        unbinder = ButterKnife.bind(this);

        if (!TextUtils.isEmpty(UserCache.getAvator(this))) {
            Glide.with(this)
                    .load(UserCache.getAvator(this))
                    .bitmapTransform(new CropCircleTransformation(this))
                    .crossFade(1000)
                    .into(mAvatar);
            mNickname.setText(UserCache.getUserName(this));
            mTitle.setText(UserCache.getUserName(this));
            mLiveRoomName.setText(UserCache.getRoomName(this));

        }
        mAppBarLayout.addOnOffsetChangedListener(this);
        startAlphaAnimation(mTitle, 0, View.INVISIBLE);

        horizentalModel();
    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(offset) / (float) maxScroll;

        handleAlphaOnTitle(percentage);
        handleToolbarTitleVisibility(percentage);
    }

    private void handleToolbarTitleVisibility(float percentage) {
        if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR) {
            if (!mIsTheTitleVisible) {
                startAlphaAnimation(mTitle, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleVisible = true;
                mToolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorHostStatusBar));
                view.setVisibility(View.VISIBLE);
            }

        } else {
            if (mIsTheTitleVisible) {
                startAlphaAnimation(mTitle, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleVisible = false;
                mToolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorTransparent));
                view.setVisibility(View.GONE);
            }
        }
    }

    private void handleAlphaOnTitle(float percentage) {
        if (percentage >= PERCENTAGE_TO_HIDE_TITLE_DETAILS) {
            if (mIsTheTitleContainerVisible) {
                startAlphaAnimation(mTitleContainer, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleContainerVisible = false;
//                mToolbar.setBackgroundColor(getResources().getColor(R.color.colorTransparent));
            }

        } else {
            if (!mIsTheTitleContainerVisible) {
                startAlphaAnimation(mTitleContainer, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleContainerVisible = true;
//                mToolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            }
        }
    }

    public static void startAlphaAnimation(View v, long duration, int visibility) {
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }

    @OnClick(R.id.tv_horizental)
    public void horizentalModel() {
        DIRECTION = 1;
        tvHorizental.setBackground(ContextCompat.getDrawable(this, R.drawable.shape_btn_live_selected));
        tvHorizental.setTextColor(ContextCompat.getColor(this, R.color.colorHostStatusBar));
        tvVertical.setBackground(ContextCompat.getDrawable(this, R.drawable.shape_btn_live));
        tvVertical.setTextColor(ContextCompat.getColor(this, R.color.colorLightBlack));
    }

    @OnClick(R.id.tv_vertical)
    public void veticalModel() {
        DIRECTION = 2;
        tvHorizental.setBackground(ContextCompat.getDrawable(this, R.drawable.shape_btn_live));
        tvHorizental.setTextColor(ContextCompat.getColor(this, R.color.colorLightBlack));
        tvVertical.setBackground(ContextCompat.getDrawable(this, R.drawable.shape_btn_live_selected));
        tvVertical.setTextColor(ContextCompat.getColor(this, R.color.colorHostStatusBar));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    public void startPush(View view) {
        if (DIRECTION == 1) {
            Log.i("LIVE", "start1");
            Intent intent = new Intent(this, PushHorizentalActivity.class);
            startActivity(intent);
        } else {
            Log.i("LIVE", "start");
            Intent intent = new Intent(this, PushActivity.class);
            startActivity(intent);
        }

    }
}
