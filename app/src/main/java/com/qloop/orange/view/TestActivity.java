package com.qloop.orange.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.qloop.orange.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Qloop on 2017/3/27.
 */

public class TestActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_pull)
    public void enterPull() {
        startActivity(new Intent(this, VideoActivity.class));
    }

    @OnClick(R.id.btn_push)
    public void enterPush() {
        startActivity(new Intent(this, CreateRoomActivity.class));
    }

}
