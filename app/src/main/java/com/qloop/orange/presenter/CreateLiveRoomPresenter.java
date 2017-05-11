package com.qloop.orange.presenter;

import com.qloop.orange.bean.ApplyPushInfo;
import com.qloop.orange.model.IApplyPush;
import com.qloop.orange.model.Impl.ApplyPushModelImpl;
import com.qloop.orange.model.OnApplyPushListener;
import com.qloop.orange.view.Iview.ICreateLiveRoomView;

/**
 * Created by Qloop on 2017/5/10.
 */

public class CreateLiveRoomPresenter {
    private IApplyPush applyPushModel;
    private ICreateLiveRoomView createLiveRoomView;

    public CreateLiveRoomPresenter(ICreateLiveRoomView createLiveRoomView) {
        this.createLiveRoomView = createLiveRoomView;
        applyPushModel = new ApplyPushModelImpl();
    }

    public void createLiveRoom() {
        applyPushModel.applyPush(createLiveRoomView.getTitle(), createLiveRoomView.getMail(),
                createLiveRoomView.getDescription(), createLiveRoomView.getNickname(), createLiveRoomView.getClassify(),
                new OnApplyPushListener() {
                    @Override
                    public void error() {
                        createLiveRoomView.error();
                    }

                    @Override
                    public void hasExist() {
                        createLiveRoomView.hasExist();
                    }

                    @Override
                    public void setPushUrl(String url) {
                        createLiveRoomView.cachePushUrl(url);
                    }
                });
    }
}
