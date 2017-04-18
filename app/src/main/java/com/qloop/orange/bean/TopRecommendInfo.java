package com.qloop.orange.bean;

import java.util.List;

/**
 * Created by Qloop on 2017/4/17.
 */

public class TopRecommendInfo {


    private List<TopDataBean> topData;

    public List<TopDataBean> getTopData() {
        return topData;
    }

    public void setTopData(List<TopDataBean> topData) {
        this.topData = topData;
    }

    public static class TopDataBean {
        /**
         * picUrl : http://www.tzloop.com/live/pic_top/top1.jpeg
         */

        private String picUrl;

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }
    }
}
