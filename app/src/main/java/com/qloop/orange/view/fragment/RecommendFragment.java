package com.qloop.orange.view.fragment;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.qloop.orange.R;
import com.qloop.orange.adapter.RecommendAdapter;
import com.qloop.orange.adapter.TopViewPagerAdapter;
import com.qloop.orange.bean.LiveListInfo;
import com.qloop.orange.bean.TopRecommendInfo;
import com.qloop.orange.presenter.RecommendPresenter;
import com.qloop.orange.utils.ToastUtils;
import com.qloop.orange.view.Iview.IRecommendFragment;
import com.qloop.orange.view.PullActivity;
import com.qloop.orange.wight.TopViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Qloop on 2017/4/13.
 */

public class RecommendFragment extends BaseFragment implements IRecommendFragment {

    private static final String TAG = "RECOMMEND";
    @BindView(R.id.rl_recommend)
    RecyclerView mRecyclerView;
    @BindView(R.id.srl_recommend)
    SwipeRefreshLayout mRefreshView;
    //    @BindView(R.id.vp_top_carousel)
    TopViewPager mViewPager;
    private TopRecommendInfo topRecommendInfo;
    private Unbinder unbinder;
    private Handler mHandler;
    private int currentItem = 0;

    @Override
    public View initViews() {
        View rootView = View.inflate(mActivity, R.layout.fragment_recommend, null);
        View heardView = View.inflate(mActivity, R.layout.header_recommend, null);
        unbinder = ButterKnife.bind(this, rootView);
        mViewPager = (TopViewPager) heardView.findViewById(R.id.vp_top_carousel);
        final RecommendPresenter recommendPresenter = new RecommendPresenter(this);
        recommendPresenter.getData();
        recommendPresenter.getContentData();

        mRefreshView.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark);
        mRefreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recommendPresenter.getData();
                recommendPresenter.getContentData();
            }
        });
        return rootView;
    }

    @Override
    public <T> void setTopData(T data) {
        topRecommendInfo = (TopRecommendInfo) data;
    }

    @Override
    public <T> void createAdapter(T data) {
        LiveListInfo liveListInfo = (LiveListInfo) data;
        if (liveListInfo != null) {
            System.out.println("#############" + liveListInfo.getLiveList().get(0).getLiveName());
        }
        RecommendAdapter adapter = new RecommendAdapter(mActivity, liveListInfo.getLiveList(), topRecommendInfo.getTopData(), this);
        System.out.println("())))))))))))((((" + adapter.getItemCount());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity, 2);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0) {
                    return 2;
                } else {
                    return 1;
                }
            }
        });
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);
        System.out.println("COUT" + "-=-=-=-=-=-=-=-=-=-=.");
        adapter.setOnItemClickListener(new RecommendAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                toLiveRoom();
                ToastUtils.showToastShort(mActivity, position + "");
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });

        //头部轮播item click
        adapter.setOnTopItemClickListener(new RecommendAdapter.OnTopItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                toLiveRoom();
                ToastUtils.showToastShort(mActivity, position + "");
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    @Override
    public void toLiveRoom() {
        startActivity(new Intent(mActivity, PullActivity.class));
    }

    @Override
    public void autoToNextViewPager(final TopViewPager topViewPager) {
        if (mHandler == null) {
            mHandler = new Handler() {
                public void handleMessage(android.os.Message msg) {
                    currentItem = topViewPager.getCurrentItem();
                    if (currentItem < topViewPager.getAdapter().getCount() - 1) { //这里写死了  其实应该根据数据size来判断
                        currentItem++;
                        System.out.println(",,,,,,,,,,," + currentItem);
                        topViewPager.setCurrentItem(currentItem);// 切换到下一个页面
                    } else {
                        System.out.println("。。。。。。。" + currentItem);
                        currentItem = 0;
                        topViewPager.setCurrentItem(currentItem, false);// 切换到下一个页面
                    }

                    mHandler.sendEmptyMessageDelayed(0, 3000);// 继续延时3秒发消息, 形成循环
                }

            };

            mHandler.sendEmptyMessageDelayed(0, 3000);// 延时3秒后发消息
        }
    }

    @Override
    public void onError() {
        ToastUtils.showToastShort(mActivity, "数据错误");
    }

    @Override
    public void stopRefresh() {
        mRefreshView.setRefreshing(false);
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
//        unbinderHeader.unbind();
    }
}
