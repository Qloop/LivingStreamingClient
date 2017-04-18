package com.qloop.orange.presenter;

import com.qloop.orange.bean.LiveListInfo;
import com.qloop.orange.model.ILiveList;
import com.qloop.orange.model.Impl.LiveListImpl;
import com.qloop.orange.model.OnLiveListListener;
import com.qloop.orange.view.Iview.IAllLiveFragmemt;

/**
 * Created by Qloop on 2017/4/15.
 */

public class AllLivePresenter {

    private IAllLiveFragmemt allLiveFragmemt;
    private ILiveList liveList;

    public AllLivePresenter(IAllLiveFragmemt allLiveFragmemt) {
        this.allLiveFragmemt = allLiveFragmemt;
        liveList = new LiveListImpl();
    }

    public void getData() {
        liveList.getLiveList(new OnLiveListListener() {
            @Override
            public void setData(LiveListInfo liveListInfo) {
                allLiveFragmemt.createAdapter(liveListInfo);
            }
        });
    }

}
