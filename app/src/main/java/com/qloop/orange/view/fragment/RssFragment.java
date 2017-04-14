package com.qloop.orange.view.fragment;

import android.view.View;

import com.qloop.orange.R;
import com.qloop.orange.view.Iview.IRssFragment;

/**
 * Created by Qloop on 2017/4/13.
 */

public class RssFragment extends BaseFragment implements IRssFragment {
    @Override
    public View initViews() {
        View view = View.inflate(mActivity, R.layout.fragment_rss, null);
        return view;
    }
}
