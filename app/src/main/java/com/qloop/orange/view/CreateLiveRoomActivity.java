package com.qloop.orange.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.qloop.orange.R;
import com.qloop.orange.view.fragment.ChoseClassifyFragment;
import com.qloop.orange.view.fragment.LiveInfoFragment;
import com.qloop.orange.wight.NoScrollViewPager;
import com.qloop.orange.wight.TopBarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Qloop on 2017/5/8.
 */

public class CreateLiveRoomActivity extends BaseActivity {
    @BindView(R.id.top_create_room)
    TopBarView topCreateRoom;
    @BindView(R.id.vp_create_route)
    NoScrollViewPager mViewPager;

    private List<Fragment> fragmentList;
    private String choseClassify;

    @Override
    protected void initViews() {
        setUpViewPager();
    }

    private void setUpViewPager() {
        ChoseClassifyFragment choseClassifyFragment =  new ChoseClassifyFragment();
        LiveInfoFragment liveInfoFragment = new LiveInfoFragment();
        fragmentList = new ArrayList<>();
        fragmentList.add(choseClassifyFragment);
        fragmentList.add(liveInfoFragment);
        final FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }
        };

        mViewPager.setAdapter(fragmentPagerAdapter);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_create_live_room;
    }


    public String getChoseClassify() {
        return choseClassify;
    }

    public void setChoseClassify(String choseClassify) {
        this.choseClassify = choseClassify;
    }
}
