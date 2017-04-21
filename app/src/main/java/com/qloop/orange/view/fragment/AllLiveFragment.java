package com.qloop.orange.view.fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.qloop.orange.R;
import com.qloop.orange.adapter.AllLiveAdapter;
import com.qloop.orange.bean.LiveListInfo;
import com.qloop.orange.presenter.AllLivePresenter;
import com.qloop.orange.utils.ToastUtils;
import com.qloop.orange.view.Iview.IAllLiveFragmemt;
import com.qloop.orange.view.PullActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Qloop on 2017/4/13.
 */

public class AllLiveFragment extends BaseFragment implements IAllLiveFragmemt {


    private static final String TAG = "ALL_LIVE";
    @BindView(R.id.rv_live_list)
    RecyclerView rlLiveList;
    @BindView(R.id.srl_all_live)
    SwipeRefreshLayout mRefreshView;

    AllLivePresenter allLivePresenter;
    private AllLiveAdapter adapter;
    private LiveListInfo liveListInfo;
    private Unbinder unbinder;

    @Override
    public View initViews() {
        View rootView = View.inflate(mActivity, R.layout.fragment_all_live, null);
        unbinder = ButterKnife.bind(this, rootView);
        allLivePresenter = new AllLivePresenter(this);
        allLivePresenter.getData();

        mRefreshView.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark);
        mRefreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                allLivePresenter.getData();
            }
        });
        return rootView;
    }


    @Override
    public <T> void createAdapter(T data) {
        Log.i(TAG, "create---------------");
        liveListInfo = (LiveListInfo) data;
        adapter = new AllLiveAdapter(liveListInfo.getLiveList(), mActivity);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity, 2);
        rlLiveList.setLayoutManager(gridLayoutManager);
        rlLiveList.setItemAnimator(new DefaultItemAnimator());
        rlLiveList.setAdapter(adapter);
        adapter.setOnItemClickListener(new AllLiveAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                toLiveRoom();
                ToastUtils.showToastShort(mActivity, position + "");
            }
        });
    }

    @Override
    public void toLiveRoom() {
        startActivity(new Intent(mActivity, PullActivity.class));
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
    }
}
