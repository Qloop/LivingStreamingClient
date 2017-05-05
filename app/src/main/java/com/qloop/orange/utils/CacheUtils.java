package com.qloop.orange.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

/**
 * Created by Qloop on 2017/4/25.
 */

public class CacheUtils {

    private static final String CACHE_NAME = "config";

    public static void setString(Context context, String key, String value, @Nullable String configName) {
        if (configName == null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(CACHE_NAME, Context.MODE_PRIVATE);
            sharedPreferences.edit().putString(key, value).apply();
        } else {
            SharedPreferences sharedPreferences = context.getSharedPreferences(configName, Context.MODE_PRIVATE);
            sharedPreferences.edit().putString(key, value).apply();
        }
    }

    public static void setBoolen(Context context, String key, boolean value, @Nullable String configName) {
        if (configName == null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(CACHE_NAME, Context.MODE_PRIVATE);
            sharedPreferences.edit().putBoolean(key, value).apply();
        } else {
            SharedPreferences sharedPreferences = context.getSharedPreferences(configName, Context.MODE_PRIVATE);
            sharedPreferences.edit().putBoolean(key, value).apply();
        }
    }

    public static String getString(Context context, String key, String defaultValue, @Nullable String configName) {
        if (configName == null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(CACHE_NAME, Context.MODE_PRIVATE);
            return sharedPreferences.getString(key, defaultValue);
        } else {
            SharedPreferences sharedPreferences = context.getSharedPreferences(configName, Context.MODE_PRIVATE);
            return sharedPreferences.getString(key, defaultValue);
        }

    }

    public static boolean getBoolen(Context context, String key, boolean defaultValue, @Nullable String configName) {
        if (configName == null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(CACHE_NAME, Context.MODE_PRIVATE);
            return sharedPreferences.getBoolean(key, defaultValue);
        } else {
            SharedPreferences sharedPreferences = context.getSharedPreferences(configName, Context.MODE_PRIVATE);
            return sharedPreferences.getBoolean(key, defaultValue);
        }
    }

    public static void cleanCache(Context context, String[] keyList, @Nullable String configName) {
        if (configName == null) {
            SharedPreferences sp = context.getSharedPreferences(CACHE_NAME, Context.MODE_PRIVATE);
            for (String key :
                    keyList) {
                sp.edit().remove(key).apply();
            }
        } else {
            SharedPreferences sp = context.getSharedPreferences(configName, Context.MODE_PRIVATE);
            for (String key :
                    keyList) {
                sp.edit().remove(key).apply();
            }
        }
    }

}
