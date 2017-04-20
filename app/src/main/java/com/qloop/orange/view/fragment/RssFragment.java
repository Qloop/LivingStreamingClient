package com.qloop.orange.view.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.qloop.orange.R;
import com.qloop.orange.adapter.RssAdapter;
import com.qloop.orange.bean.RssListInfo;
import com.qloop.orange.presenter.RssPresenter;
import com.qloop.orange.utils.ToastUtils;
import com.qloop.orange.view.Iview.IRssFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Qloop on 2017/4/13.
 */

public class RssFragment extends BaseFragment implements IRssFragment {

    private Unbinder unbinder;

    @BindView(R.id.rl_rss)
    RecyclerView mRecyclerView;
    @BindView(R.id.srl_rss)
    SwipeRefreshLayout mRefreshLayout;
    private RssPresenter rssPresenter;

    @Override
    public View initViews() {
        View view = View.inflate(mActivity, R.layout.fragment_rss, null);
        unbinder = ButterKnife.bind(this, view);
        rssPresenter = new RssPresenter(this);
        rssPresenter.getData();

        mRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
        return view;
    }

    @Override
    public <T> void createAdapter(T data) {
        RssListInfo rssListInfo = (RssListInfo) data;
        final RssAdapter rssAdapter = new RssAdapter(mActivity, rssListInfo.getRssData());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(rssAdapter);
        rssAdapter.setOnItemClickListener(new RssAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ToastUtils.showToastShort(mActivity, rssAdapter.getItemId(position) + "");
                //
            }
        });
    }

    @Override
    public void stopRefresh() {
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void toLiveRoom() {

    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
    }
}
