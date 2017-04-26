package com.qloop.orange.utils;

import android.content.Context;

/**
 * Created by Qloop on 2017/4/25.
 */

public class UserCache {

    private static final String USER_NAME = "user_name";
    private static final String DEFAULT_NAME = "未登陆";

    public static void setUserName(Context context, String userName) {
        CacheUtils.setString(context, USER_NAME, userName);
    }

    public static String getUserName(Context context) {
        return CacheUtils.getString(context, USER_NAME, DEFAULT_NAME);
    }
}
