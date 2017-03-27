package com.qloop.orange.bean;

/**
 * Created by Qloop on 2017/3/24.
 */

public class ApplyPushInfo {


    /**
     * rtmp : rtmp://push.bcelive.com/live/st09ztrqe7f8u2xqqh
     */

    private String rtmp;
    private int errorCode;


    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getRtmp() {
        return rtmp;
    }

    public void setRtmp(String rtmp) {
        this.rtmp = rtmp;
    }
}
