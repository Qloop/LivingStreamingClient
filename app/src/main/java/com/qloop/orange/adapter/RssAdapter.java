package com.qloop.orange.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qloop.orange.R;
import com.qloop.orange.bean.RssListInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by Qloop on 2017/4/20.
 */

public class RssAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<?> mData;

    public RssAdapter(Context mContext, List<?> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_rss, parent, false);
        return new RssViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RssListInfo.RssDataBean rssDataBean = (RssListInfo.RssDataBean) mData.get(position);
        ((RssViewHolder) holder).tvRssName.setText(rssDataBean.getRssName());
        ((RssViewHolder) holder).tvAnchor.setText(rssDataBean.getAnchor());
        ((RssViewHolder) holder).tvWatchCountRss.setText(rssDataBean.getWatchCount() + "");
        Glide.with(mContext)
                .load(rssDataBean.getRssThumbnail())
                .centerCrop()
                .placeholder(R.mipmap.home_list_item_bg)
                .crossFade()
                .bitmapTransform(new RoundedCornersTransformation(mContext, 10, 0, RoundedCornersTransformation.CornerType.ALL))
                .into(((RssViewHolder) holder).ivThumbnail);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class RssViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        RelativeLayout rootView;
        @BindView(R.id.iv_thumbnail_rss)
        ImageView ivThumbnail;
        @BindView(R.id.tv_rss_name)
        TextView tvRssName;
        @BindView(R.id.tv_anchor)
        TextView tvAnchor;
        @BindView(R.id.tv_watch_count_rss)
        TextView tvWatchCountRss;

        public RssViewHolder(View itemView) {
            super(itemView);
            rootView = (RelativeLayout) itemView;
            ButterKnife.bind(this, itemView);
            rootView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(v, getLayoutPosition());
            }
        }
    }

    private onItemClickListener onItemClickListener;

    public interface onItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(RssAdapter.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
