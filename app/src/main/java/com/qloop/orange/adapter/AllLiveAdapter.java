package com.qloop.orange.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qloop.orange.R;
import com.qloop.orange.bean.LiveListInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by Qloop on 2017/4/14.
 */

public class AllLiveAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<?> mData;
    private Context mContext;
    private OnItemClickListener onItemClickListener;

    public AllLiveAdapter(List<?> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_all_live, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        LiveListInfo.LiveListBean liveList = (LiveListInfo.LiveListBean) mData.get(position);
        ((ViewHolder) holder).tvLiveName.setText(liveList.getLiveName());
        ((ViewHolder) holder).tvLiveRoomName.setText(liveList.getLiveRoomName());
        ((ViewHolder) holder).tvWatchCount.setText(liveList.getWatchCount());
        Glide.with(mContext)
                .load(liveList.getImg())
                .centerCrop()
                .placeholder(R.mipmap.home_list_item_bg)
                .crossFade()
                .bitmapTransform(new RoundedCornersTransformation(mContext, 10, 0, RoundedCornersTransformation.CornerType.ALL))
                .into(((ViewHolder) holder).ivThumbnail);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public RelativeLayout itemRootView;
        @BindView(R.id.iv_thumbnail)
        public ImageView ivThumbnail;
        @BindView(R.id.tv_live_name)
        public TextView tvLiveName;
        @BindView(R.id.tv_live_room_name)
        public TextView tvLiveRoomName;
        @BindView(R.id.tv_watch_count)
        public TextView tvWatchCount;

        public ViewHolder(View itemView) {
            super(itemView);
            itemRootView = (RelativeLayout) itemView;
            ButterKnife.bind(this, itemView);
            itemRootView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(v, getLayoutPosition());
            }
        }

    }
}
