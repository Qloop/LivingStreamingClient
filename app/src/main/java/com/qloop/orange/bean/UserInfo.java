package com.qloop.orange.bean;

/**
 * Created by Qloop on 2017/4/30.
 */

public class UserInfo {


    /**
     * result : 注册成功 (/注册失败/用户已存在) || "success"/ "failed" getUserInfo
     * password :
     * mail : liutaizhu617@gmail.com
     * nickname : 测试菌
     * id : 2
     * liveRoom
     * avatar : null
     */

    private String result;
    private String password;
    private String mail;
    private String nickname;
    private String liveRoom;
    private int id;
    private Object avatar;


    public String getLiveRoom() {
        return liveRoom;
    }

    public void setLiveRoom(String liveRoom) {
        this.liveRoom = liveRoom;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getAvatar() {
        return avatar;
    }

    public void setAvatar(Object avatar) {
        this.avatar = avatar;
    }
}
