package com.qloop.orange;

import android.app.Activity;
import android.app.Application;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Qloop on 2017/5/5.
 */

public class MyApplication extends Application {

    public static Map<String, Activity> destoryMap = new HashMap<>();

    private MyApplication() {
    }

    /**
     * 添加到销毁队列
     *
     * @param activity 要销毁的activity
     */

    public static void addDestoryActivity(Activity activity, String activityName) {
        destoryMap.put(activityName, activity);
    }


}
