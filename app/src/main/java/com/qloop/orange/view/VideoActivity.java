package com.qloop.orange.view;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dou361.ijkplayer.listener.OnShowThumbnailListener;
import com.dou361.ijkplayer.widget.IjkVideoView;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import com.qloop.orange.R;
import com.qloop.orange.view.fragment.HostInfoFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Qloop on 2017/5/16.
 */

public class VideoActivity extends BaseActivity {


    @BindView(R.id.video_view)
    IjkVideoView videoView;
    @BindView(R.id.vp_video)
    ViewPager vpVideo;
    @BindView(R.id.tablayout)
    TabLayout tabLayout;

    private String mVideoPath = "http://hcxsc4v1myvjiu4uf2x.exp.bcelive.com/lss-hekvvkvt9xq2v11i/live.m3u8";
    private PlayerView player;

    private static final String TAG = "VideoActivity";

    @Override
    protected void initViews() {
        Log.i(TAG, "start");
        setUpViewPager();
        setUpVideoPlayer();
    }

    private void setUpVideoPlayer() {
        /**播放资源*/
//        List<VideoijkBean> list = new ArrayList<>();
//
//        String url1 = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4";
//        String url2 = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f30.mp4";
//        VideoijkBean m1 = new VideoijkBean();
//        m1.setStream("标清");
//        m1.setUrl(url1);
//        VideoijkBean m2 = new VideoijkBean();
//        m2.setStream("高清");
//        m2.setUrl(url2);
//        list.add(m1);
//        list.add(m2);
        /**播放器*/
//        rootView = View.inflate(this, R.layout.activity_video, null);
//        setContentView(rootView);
        player = new PlayerView(this)
                .setTitle("播放器测试")
                .setScaleType(PlayStateParams.fitparent)
                .hideMenu(true)
                .forbidTouch(false)
                .showThumbnail(new OnShowThumbnailListener() {
                    @Override
                    public void onShowThumbnail(ImageView ivThumbnail) {
                        /**加载前显示的缩略图*/
                        Glide.with(VideoActivity.this)
                                .load(R.mipmap.home_list_item_bg)
                                .placeholder(R.color.colorPrimary)
                                .error(R.color.colorRed)
                                .into(ivThumbnail);
                    }
                })
                .setPlaySource(mVideoPath)
                .setShowSpeed(true)
                .startPlay();
    }

    private void setUpViewPager() {
        final List<Fragment> fragmentList = new ArrayList<>();
//        DanmuFragment danmuFragment = new DanmuFragment();
        HostInfoFragment hostInfoFragment = new HostInfoFragment();
//        fragmentList.add(danmuFragment);
        fragmentList.add(hostInfoFragment);
        final ArrayList<String> titleList = new ArrayList<>();
//        titleList.add("聊天");
        titleList.add("主播");
        for (String title :
                titleList) {
            tabLayout.addTab(tabLayout.newTab().setText(title));
        }

        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titleList.get(position);
            }
        };
        vpVideo.setAdapter(adapter);
        tabLayout.setupWithViewPager(vpVideo);
    }

    @Override
    protected int setLayoutResourceID() {
        Log.i(TAG, "viewlayout");
        return R.layout.activity_video;
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (player != null) {
            player.onPause();
        }
        /**demo的内容，恢复系统其它媒体的状态*/
        //MediaUtils.muteAudioFocus(mContext, true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (player != null) {
            player.onResume();
        }
        /**demo的内容，暂停系统其它媒体的状态*/
//        MediaUtils.muteAudioFocus(mContext, false);
        /**demo的内容，激活设备常亮状态*/
        //if (wakeLock != null) {
        //    wakeLock.acquire();
        //}
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.onDestroy();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (player != null) {
            player.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public void onBackPressed() {
        if (player != null && player.onBackPressed()) {
            return;
        }
        super.onBackPressed();
        /**demo的内容，恢复设备亮度状态*/
        //if (wakeLock != null) {
        //    wakeLock.release();
        //}
    }
}
