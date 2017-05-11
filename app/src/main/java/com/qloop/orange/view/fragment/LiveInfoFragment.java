package com.qloop.orange.view.fragment;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.qloop.orange.R;
import com.qloop.orange.bean.UserInfo;
import com.qloop.orange.presenter.CreateLiveRoomPresenter;
import com.qloop.orange.utils.ToastUtils;
import com.qloop.orange.utils.UserCache;
import com.qloop.orange.view.CreateLiveRoomActivity;
import com.qloop.orange.view.HostActivity;
import com.qloop.orange.view.Iview.ICreateLiveRoomView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Qloop on 2017/5/9.
 */

public class LiveInfoFragment extends BaseFragment implements ICreateLiveRoomView {


    @BindView(R.id.et_live_name)
    EditText etLiveName;
    @BindView(R.id.et_live_info)
    EditText etLiveInfo;
    @BindView(R.id.btn_create_room)
    Button btnCreateRoom;
    Unbinder unbinder;
    private CreateLiveRoomPresenter presenter;

    @Override
    public View initViews() {
        View rootView = View.inflate(mActivity, R.layout.fragment_live_info, null);
        unbinder = ButterKnife.bind(this, rootView);
        presenter = new CreateLiveRoomPresenter(this);
        return rootView;
    }


    @OnClick(R.id.btn_create_room)
    public void onViewClicked() {
        if (presenter != null && checkInput()) {
            presenter.createLiveRoom();
        }
    }

    @Override
    public String getNickname() {
        return UserCache.getUserName(mActivity);
    }

    @Override
    public String getMail() {
        return UserCache.getEmail(mActivity);
    }

    @Override
    public String getTitle() {
        return etLiveName.getText().toString();
    }

    @Override
    public String getDescription() {
        return etLiveInfo.getText().toString();
    }

    @Override
    public String getClassify() {
        return ((CreateLiveRoomActivity) mActivity).getChoseClassify();
    }

    @Override
    public void nextPager() {
        startActivity(new Intent(mActivity, HostActivity.class));
    }

    @Override
    public void error() {
        showPromptDialog("网络错误 请检查网络后重试");
    }

    @Override
    public void hasExist() {
        showPromptDialog("您已经有直播间了哦");
    }

    @Override
    public void cachePushUrl(String url) {
        UserCache.setLiveRoom(mActivity, url);
        UserCache.setRoomName(mActivity, getTitle());
        nextPager();
    }

    private boolean checkInput() {
        return !TextUtils.isEmpty(etLiveInfo.getText().toString())
                && !TextUtils.isEmpty(etLiveName.getText().toString());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
