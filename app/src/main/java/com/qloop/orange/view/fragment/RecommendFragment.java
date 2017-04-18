package com.qloop.orange.view.fragment;

import android.content.Intent;
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

/**
 * Created by Qloop on 2017/4/13.
 */

public class RecommendFragment extends BaseFragment implements IRecommendFragment {

    private static final String TAG = "RECOMMEND";
    //    @BindView(R.id.rl_recommend)
    RecyclerView mRecyclerView;
    private TopRecommendInfo topRecommendInfo;

    @Override
    public View initViews() {
        View rootView = View.inflate(mActivity, R.layout.fragment_recommend, null);
        View heardView = View.inflate(mActivity, R.layout.header_recommend, null);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rl_recommend);
        ButterKnife.bind(this, rootView);
        RecommendPresenter recommendPresenter = new RecommendPresenter(this);
        recommendPresenter.getData();
        recommendPresenter.getContentData();
        return rootView;
    }

    @Override
    public <T> void setTopData(T data) {
        topRecommendInfo = (TopRecommendInfo) data;
        Log.i(TAG, "topdata+++++++++++++++++++++++");
    }

    @Override
    public <T> void createAdapter(T data) {

        Log.i(TAG, "create+++++++++++++++++++++++");
        LiveListInfo liveListInfo = (LiveListInfo) data;
        if (liveListInfo != null) {
            System.out.println("#############" + liveListInfo.getLiveList().get(0).getLiveName());
        }
        RecommendAdapter adapter = new RecommendAdapter(mActivity, liveListInfo.getLiveList(), topRecommendInfo.getTopData());
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
}
