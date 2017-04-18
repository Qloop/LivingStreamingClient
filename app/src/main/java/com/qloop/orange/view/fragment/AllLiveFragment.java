package com.qloop.orange.view.fragment;

import android.content.Intent;
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

/**
 * Created by Qloop on 2017/4/13.
 */

public class AllLiveFragment extends BaseFragment implements IAllLiveFragmemt {


    private static final String TAG = "ALL_LIVE";
    @BindView(R.id.rv_live_list)
    RecyclerView rlLiveList;

    AllLivePresenter allLivePresenter;
    private AllLiveAdapter adapter;
    private LiveListInfo liveListInfo;

    @Override
    public View initViews() {
        View rootView = View.inflate(mActivity, R.layout.fragment_all_live, null);
        ButterKnife.bind(this, rootView);
        allLivePresenter = new AllLivePresenter(this);
        allLivePresenter.getData();
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
}
