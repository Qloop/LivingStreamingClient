package com.qloop.orange.presenter;

import com.qloop.orange.bean.LiveListInfo;
import com.qloop.orange.bean.TopRecommendInfo;
import com.qloop.orange.model.IRecommend;
import com.qloop.orange.model.Impl.RecommendImpl;
import com.qloop.orange.model.OnRecommendListener;
import com.qloop.orange.view.Iview.IRecommendFragment;

/**
 * Created by Qloop on 2017/4/17.
 */

public class RecommendPresenter {

    private IRecommendFragment recommendFragment;
    private IRecommend recommendModel;


    public RecommendPresenter(IRecommendFragment recommendFragment) {
        this.recommendFragment = recommendFragment;
        recommendModel = new RecommendImpl();
    }

    public void getData() {
        recommendModel.getTopData(new OnRecommendListener() {
            @Override
            public void setTopData(TopRecommendInfo topRecommendInfo) {
                recommendFragment.setTopData(topRecommendInfo);
            }

            @Override
            public void setContentData(LiveListInfo liveListInfo) {

            }

        });
    }

    public void getContentData(){
        recommendModel.getRecommendContentData(new OnRecommendListener() {
            @Override
            public void setTopData(TopRecommendInfo topRecommendInfo) {

            }

            @Override
            public void setContentData(LiveListInfo liveListInfo) {
                recommendFragment.createAdapter(liveListInfo);
            }
        });
    }
}
