package com.qloop.orange.wight;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Qloop on 2017/4/13.
 */

public class MainPagerViewPager extends ViewPager {
    public MainPagerViewPager(Context context) {
        super(context);
    }

    public MainPagerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 父控件不再拦截本控件的事件（解决滑动冲突）
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);
    }


}
