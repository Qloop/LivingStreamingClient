package com.qloop.orange.presenter;

import android.content.Context;

import com.qloop.orange.bean.RssListInfo;
import com.qloop.orange.model.IRssModel;
import com.qloop.orange.model.Impl.RssListModelImpl;
import com.qloop.orange.model.OnRssListListener;
import com.qloop.orange.view.Iview.IRssFragment;

/**
 * Created by Qloop on 2017/4/20.
 */

public class RssPresenter {

    private IRssFragment rssFragment;
    private IRssModel rssModel;

    public RssPresenter(IRssFragment rssFragment) {
        this.rssFragment = rssFragment;
        this.rssModel = new RssListModelImpl();
    }

    public void getData() {
        rssModel.getData(new OnRssListListener() {
            @Override
            public void setData(RssListInfo rssListInfo) {
                rssFragment.stopRefresh();
                rssFragment.createAdapter(rssListInfo);
            }

            @Override
            public void accessError() {
                rssFragment.onError();
            }
        });
    }
}
