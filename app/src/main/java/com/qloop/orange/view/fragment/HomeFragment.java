package com.qloop.orange.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qloop.orange.R;
import com.qloop.orange.view.Iview.IHomeFragment;
import com.qloop.orange.wight.MainPagerViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Qloop on 2017/4/13.
 */

public class HomeFragment extends BaseFragment implements IHomeFragment {


    Unbinder unbinder;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tablayout)
    TabLayout mTablayout;
    @BindView(R.id.appbar)
    AppBarLayout mAppbar;
    @BindView(R.id.vp_main_pager)
    MainPagerViewPager vpMainPager;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mRefreshView;

    @Override
    public View initViews() {
        View view = View.inflate(mActivity, R.layout.fragment_home, null);
        unbinder = ButterKnife.bind(this, view);
        setUpViewPager();
        mRefreshView.setColorSchemeColors(new int[]{R.color.colorPrimary});
        mRefreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setUpViewPager() {
        final List<String> titles = new ArrayList<>();
        titles.add("推荐");
        titles.add("全部");
        for (String title :
                titles) {
            mTablayout.addTab(mTablayout.newTab().setText(title));
        }
        RecommendFragment recommendFragment = new RecommendFragment();
        AllLiveFragment allLiveFragment = new AllLiveFragment();
        final List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(recommendFragment);
        fragmentList.add(allLiveFragment);

        FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles.get(position);
            }
        };
        vpMainPager.setAdapter(fragmentPagerAdapter);
        mTablayout.setupWithViewPager(vpMainPager);

    }

    @Override
    public void refreshData() {

    }
}
