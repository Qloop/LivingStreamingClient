package com.qloop.orange.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Qloop on 2017/3/24.
 */

public class ToastUtils {

    public static void showToastLong(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

    public static void showToastShort(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}
