package com.qloop.orange.presenter;

import com.qloop.orange.bean.LiveListInfo;
import com.qloop.orange.model.ILiveListModel;
import com.qloop.orange.model.Impl.LiveListModelImpl;
import com.qloop.orange.model.OnLiveListListener;
import com.qloop.orange.utils.ToastUtils;
import com.qloop.orange.view.Iview.IAllLiveFragmemt;

/**
 * Created by Qloop on 2017/4/15.
 */

public class AllLivePresenter {

    private IAllLiveFragmemt allLiveFragmemt;
    private ILiveListModel liveList;

    public AllLivePresenter(IAllLiveFragmemt allLiveFragmemt) {
        this.allLiveFragmemt = allLiveFragmemt;
        liveList = new LiveListModelImpl();
    }

    public void getData() {
        liveList.getLiveList(new OnLiveListListener() {
            @Override
            public void setData(LiveListInfo liveListInfo) {
                allLiveFragmemt.stopRefresh();
                allLiveFragmemt.createAdapter(liveListInfo);
            }

            @Override
            public void accessError() {
                allLiveFragmemt.onError();
            }
        });
    }

}
