package com.qloop.orange.view;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;

import com.qloop.orange.R;

import butterknife.BindView;

/**
 * Created by Qloop on 2017/4/12.
 */

public class MainActivity extends BaseActivity {

    @BindView(R.id.tv_main)
    TextView tvMainPager;
    private double exitTime = 0.1;

    @Override
    protected void init() {
    }

    @Override
    protected void initViews() {
//        tvMainPager.setTextColor(R.color.colorGray);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {

            if ((System.currentTimeMillis() - exitTime) > 2000)  //System.currentTimeMillis()无论何时调用，肯定大于2000
            {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
