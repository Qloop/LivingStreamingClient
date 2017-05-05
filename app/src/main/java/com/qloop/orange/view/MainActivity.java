package com.qloop.orange.view;

import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;

import com.qloop.orange.MyApplication;
import com.qloop.orange.R;
import com.qloop.orange.view.Iview.IMainView;
import com.qloop.orange.view.fragment.HomeFragment;
import com.qloop.orange.view.fragment.RssFragment;
import com.qloop.orange.view.fragment.UserFragment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Qloop on 2017/4/12.
 */

public class MainActivity extends BaseActivity implements IMainView {

    @BindView(R.id.tv_main)
    TextView tvMainPager;
    @BindView(R.id.tv_rss)
    TextView tvRssPager;
    @BindView(R.id.tv_user)
    TextView tvUserPager;
    @BindView(R.id.vp_main)
    ViewPager vpMain;

    private double exitTime = 0.1;


    @Override
    protected void init() {
    }

    @Override
    protected void initViews() {
        MyApplication.addDestoryActivity(this,"MainActivity");
        setSelectedPager(0);
        Fragment homeFragment = new HomeFragment();
        Fragment rssFragment = new RssFragment();
        Fragment userFragment = new UserFragment();
        final List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(homeFragment);
        fragmentList.add(rssFragment);
        fragmentList.add(userFragment);
        FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }
        };
        vpMain.setAdapter(fragmentPagerAdapter);
        vpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setSelectedPager(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_main;
    }

    @Override
    public void setSelectedPager(int position) {
        switch (position) {
            case 0:
                tvMainPager.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                tvRssPager.setTextColor(ContextCompat.getColor(this, R.color.colorBlack));
                tvUserPager.setTextColor(ContextCompat.getColor(this, R.color.colorBlack));

                setDrawableTop(tvMainPager, R.mipmap.home_pressed);
                setDrawableTop(tvRssPager, R.mipmap.rss);
                setDrawableTop(tvUserPager, R.mipmap.user);
                break;
            case 1:
                tvMainPager.setTextColor(ContextCompat.getColor(this, R.color.colorBlack));
                tvRssPager.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                tvUserPager.setTextColor(ContextCompat.getColor(this, R.color.colorBlack));

                setDrawableTop(tvMainPager, R.mipmap.home);
                setDrawableTop(tvRssPager, R.mipmap.rss_pressed);
                setDrawableTop(tvUserPager, R.mipmap.user);
                break;
            case 2:
                tvMainPager.setTextColor(ContextCompat.getColor(this, R.color.colorBlack));
                tvRssPager.setTextColor(ContextCompat.getColor(this, R.color.colorBlack));
                tvUserPager.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));

                setDrawableTop(tvMainPager, R.mipmap.home);
                setDrawableTop(tvRssPager, R.mipmap.rss);
                setDrawableTop(tvUserPager, R.mipmap.user_pressed);
                break;
            case 3:
                break;
        }
    }

    @Override
    public void setDrawableTop(TextView textView, int drawable) {
        Drawable topDrawable = ContextCompat.getDrawable(this, drawable);
        topDrawable.setBounds(0, 0, topDrawable.getMinimumWidth(), topDrawable.getMinimumHeight());
        textView.setCompoundDrawables(null, topDrawable, null, null);
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

    @OnClick(R.id.tv_main)
    public void home() {
        vpMain.setCurrentItem(0, false);
        setSelectedPager(0);
    }

    @OnClick(R.id.tv_rss)
    public void rss() {
        vpMain.setCurrentItem(1, false);
        setSelectedPager(1);
    }

    @OnClick(R.id.tv_user)
    public void user() {
        vpMain.setCurrentItem(2, false);
        setSelectedPager(2);
    }
}
