package com.qloop.orange.bean;

import java.util.List;

/**
 * 直播间列表 [全部]
 * Created by Qloop on 2017/4/14.
 */

public class LiveListInfo {


    private List<LiveListBean> liveList;

    public List<LiveListBean> getLiveList() {
        return liveList;
    }

    public void setLiveList(List<LiveListBean> liveList) {
        this.liveList = liveList;
    }

    public static class LiveListBean {
        /**
         * img : http://www.tzloop.com/live/thumbnail/thumbnail1.jpg
         * liveName : KPL王者荣耀职业联赛荣耀职业联荣耀职业联荣耀职业联荣耀职业联荣耀职业联荣耀职业联荣耀职业联荣耀职业联荣耀职业联
         * liveRoomName : KPL职业联赛
         * watchCount : 37.7万
         * livName : KPL王者荣耀职业联赛
         */

        private String img;
        private String liveName;
        private String liveRoomName;
        private String watchCount;
        private String livName;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getLiveName() {
            return liveName;
        }

        public void setLiveName(String liveName) {
            this.liveName = liveName;
        }

        public String getLiveRoomName() {
            return liveRoomName;
        }

        public void setLiveRoomName(String liveRoomName) {
            this.liveRoomName = liveRoomName;
        }

        public String getWatchCount() {
            return watchCount;
        }

        public void setWatchCount(String watchCount) {
            this.watchCount = watchCount;
        }

        public String getLivName() {
            return livName;
        }

        public void setLivName(String livName) {
            this.livName = livName;
        }
    }
}
