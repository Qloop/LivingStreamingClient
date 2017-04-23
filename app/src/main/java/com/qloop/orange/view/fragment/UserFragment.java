package com.qloop.orange.view.fragment;

import android.util.Log;
import android.view.View;

import com.qloop.orange.R;
import com.qloop.orange.utils.ToastUtils;
import com.qloop.orange.view.Iview.IUserFragment;

/**
 * Created by Qloop on 2017/4/13.
 */

public class UserFragment extends BaseFragment implements IUserFragment {
    @Override
    public View initViews() {
        View view = View.inflate(mActivity, R.layout.fragment_user, null);
        Log.i("VIEW", "USER************-///////////////////////////////////////////");
        return view;
    }

    @Override
    public void onError() {
        ToastUtils.showToastShort(mActivity, "数据错误");
    }
}
