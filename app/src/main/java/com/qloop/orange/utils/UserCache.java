package com.qloop.orange.utils;

import android.content.Context;

/**
 * Created by Qloop on 2017/4/25.
 */

public class UserCache {

    private static final String USER_NAME = "user_name";
    private static final String EMAIL = "email";
    private static final String AVATOR = "avator";
    private static final String LIVE_ROOM = "live_room";
    private static final String DEFAULT_NAME = "未登陆";
    private static final String CONFIG_NAME = "user_cache";

    public static void setUserName(Context context, String userName) {
        CacheUtils.setString(context, USER_NAME, userName, CONFIG_NAME);
    }

    public static String getUserName(Context context) {
        return CacheUtils.getString(context, USER_NAME, DEFAULT_NAME, CONFIG_NAME);
    }

    public static void setEmail(Context context, String email) {
        CacheUtils.setString(context, EMAIL, email, CONFIG_NAME);
    }

    public static String getEmail(Context context) {
        return CacheUtils.getString(context, EMAIL, null, CONFIG_NAME);
    }

    public static void setAvator(Context context, String avator) {
        CacheUtils.setString(context, AVATOR, avator, CONFIG_NAME);
    }

    public static String getAvator(Context context) {
        return CacheUtils.getString(context, AVATOR, null, CONFIG_NAME);
    }

    public static void setLiveRoom(Context context, String liveRoom) {
        CacheUtils.setString(context, LIVE_ROOM, liveRoom, CONFIG_NAME);
    }

    public static String getLiveRoom(Context context) {
        return CacheUtils.getString(context, LIVE_ROOM, null, CONFIG_NAME);
    }

    public static void cacheUserInfo(Context context, String userName, String email, String avator, String liveRoom) {
        setUserName(context, userName);
        setEmail(context, email);
        setAvator(context, avator);
        setLiveRoom(context,liveRoom);
    }
}
