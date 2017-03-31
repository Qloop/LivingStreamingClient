package com.qloop.orange.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.qloop.orange.R;
import com.qloop.orange.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.widget.media.IjkVideoView;

/**
 * Created by Qloop on 2017/3/27.
 */

public class PullActivity extends AppCompatActivity {

    private boolean mBackPressed;
    private String mVideoPath = "http://hcxsc4v1myvjiu4uf2x.exp.bcelive.com/lss-hc0thvhzie13trck/live.m3u8";

    @BindView(R.id.ijkplayer)
    IjkVideoView mVideoView;
//    private AndroidMediaController mMediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull);
        ButterKnife.bind(this);

        //init player
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        //这里使用的是Demo中提供的AndroidMediaController类控制播放相关操作
//        mMediaController = new AndroidMediaController(this, false);
//        mMediaController.setSupportActionBar(actionBar);
//        mVideoView.setMediaController(mMediaController);
        if (mVideoPath != null) {
            mVideoView.setVideoPath(mVideoPath);
        } else {
            ToastUtils.showToastShort(this, "直播地址获取失败");
        }
        mVideoView.start();
    }


    @Override
    public void onBackPressed() {
        mBackPressed = true;
        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mBackPressed || !mVideoView.isBackgroundPlayEnabled()) {
            mVideoView.stopPlayback();
            mVideoView.release(true);
            mVideoView.stopBackgroundPlay();
        } else {
            mVideoView.enterBackground();
        }
        IjkMediaPlayer.native_profileEnd();
    }
}
