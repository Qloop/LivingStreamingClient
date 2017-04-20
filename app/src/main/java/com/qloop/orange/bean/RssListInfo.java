package com.qloop.orange.bean;

import java.util.List;

/**
 * Created by Qloop on 2017/4/20.
 */

public class RssListInfo {


    private List<RssDataBean> rssData;

    public List<RssDataBean> getRssData() {
        return rssData;
    }

    public void setRssData(List<RssDataBean> rssData) {
        this.rssData = rssData;
    }

    public static class RssDataBean {
        /**
         * rssThumbnail : http://www.tzloop.com/live/thumbnail/thumbnail1.jpg
         * rssName : 教父: 第一全能
         * anchor : 漫步
         * watchCount : 256
         */

        private String rssThumbnail;
        private String rssName;
        private String anchor;
        private int watchCount;

        public String getRssThumbnail() {
            return rssThumbnail;
        }

        public void setRssThumbnail(String rssThumbnail) {
            this.rssThumbnail = rssThumbnail;
        }

        public String getRssName() {
            return rssName;
        }

        public void setRssName(String rssName) {
            this.rssName = rssName;
        }

        public String getAnchor() {
            return anchor;
        }

        public void setAnchor(String anchor) {
            this.anchor = anchor;
        }

        public int getWatchCount() {
            return watchCount;
        }

        public void setWatchCount(int watchCount) {
            this.watchCount = watchCount;
        }
    }
}
