package com.qloop.orange.bean;

/**
 * Created by Qloop on 2017/4/9.
 */

public class UpdateInfo {

    /**
     * updateInfo : 测试版本更新
     * downloadUrl : http://118.89.112.50/live/apk/app-debug.apk
     * versionName : 1.1
     * versionCode : 1.1
     */

    private String updateInfo;
    private String downloadUrl;
    private String versionName;
    private double versionCode;

    public String getUpdateInfo() {
        return updateInfo;
    }

    public void setUpdateInfo(String updateInfo) {
        this.updateInfo = updateInfo;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public double getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(double versionCode) {
        this.versionCode = versionCode;
    }
}
