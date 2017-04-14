package com.qloop.orange.view.fragment;

import android.view.View;

import com.qloop.orange.R;
import com.qloop.orange.view.Iview.IAllLiveFragmemt;

/**
 * Created by Qloop on 2017/4/13.
 */

public class AllLiveFragment extends BaseFragment implements IAllLiveFragmemt {
    @Override
    public View initViews() {
        View rootView = View.inflate(mActivity, R.layout.fragment_all_live, null);
        return rootView;
    }
}
