package com.qloop.orange.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qloop.orange.R;
import com.qloop.orange.bean.TopRecommendInfo;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Qloop on 2017/4/17.
 */

public class TopViewPagerAdapter extends PagerAdapter {

    private List<?> mData;
    private Context mContext;
    private LinkedList<View> mViewCache;


    public TopViewPagerAdapter(List<?> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
        mViewCache = new LinkedList<>();
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        TopRecommendInfo.TopDataBean topDataBean = (TopRecommendInfo.TopDataBean) mData.get(position);
        View rootView = null;
        if (mViewCache.isEmpty()) {
            rootView = View.inflate(mContext, R.layout.item_top_viewpager, null);

        } else {
            rootView = mViewCache.getFirst();
            mViewCache.removeFirst();
        }
        ImageView imageView = (ImageView) rootView.findViewById(R.id.iv_top);
        Glide.with(mContext)
                .load(topDataBean.getPicUrl())
                .centerCrop()
                .placeholder(R.mipmap.home_list_item_bg)
                .crossFade()
                .into(imageView);
        if (onItemClickListener != null) {
            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(v, position);
                }
            });
        }
        container.addView(rootView);
        return rootView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        if (object != null) {
            mViewCache.addLast((View) object);
        }
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
