package com.qloop.orange.view.fragment;

import android.view.View;

import com.qloop.orange.R;
import com.qloop.orange.view.Iview.IRecommendFragmemt;

/**
 * Created by Qloop on 2017/4/13.
 */

public class RecommendFragment extends BaseFragment implements IRecommendFragmemt {
    @Override
    public View initViews() {
        View rootView = View.inflate(mActivity, R.layout.fragment_recommend, null);
        return rootView;
    }
}
