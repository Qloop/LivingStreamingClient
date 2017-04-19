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
import com.qloop.orange.bean.LiveListInfo;
import com.qloop.orange.view.Iview.IRecommendFragment;
import com.qloop.orange.wight.TopViewPager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * 首页推荐列表适配器
 * Created by Qloop on 2016/12/11.
 */

public class RecommendAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM_TYPE_HEADER = 0;
    private static final int ITEM_TYPE_CONTENT = 1;
    private static final int ITEM_TYPE_FOOTER = 2;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<?> mContentData;
    private List<?> mHeaderData;
    private int mHeaderCount = 1;
    private int mBottomCount = 0;

    private IRecommendFragment recommendFragment;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    public interface OnTopItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    private OnTopItemClickListener mOnTopItemClickListener;

    public void setOnTopItemClickListener(OnTopItemClickListener listener) {
        mOnTopItemClickListener = listener;
    }

    public RecommendAdapter(Context mContext, List<?> mContentData, List<?> mHeaderData, IRecommendFragment recommendFragment) {
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        this.mContext = mContext;
        this.mContentData = mContentData;
        this.mHeaderData = mHeaderData;
        mLayoutInflater = LayoutInflater.from(mContext);
        this.recommendFragment = recommendFragment;
    }

    //内容长度
    public int getContentItemCount() {
        return mContentData.size();
    }

    //判断当前item是否是HeadView
    public boolean isHeaderView(int position) {
        return mHeaderCount != 0 && position < mHeaderCount;
    }

    //判断当前item是否是FooterView
    public boolean isBottomView(int position) {
        return mBottomCount != 0 && position >= (mHeaderCount + getContentItemCount());
    }


    //判断当前item类型
    @Override
    public int getItemViewType(int position) {
        int dataItemCount = getContentItemCount();
        if (mHeaderCount != 0 && position < mHeaderCount) {
            //头部View
            return ITEM_TYPE_HEADER;
        } else if (mBottomCount != 0 && position >= (mHeaderCount + dataItemCount)) {
            //底部View
            return ITEM_TYPE_FOOTER;
        } else {
            //内容View
            return ITEM_TYPE_CONTENT;
        }
    }

    //内容 ViewHolder
    public class RecommendViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        RelativeLayout itemRootView;
        @BindView(R.id.iv_thumbnail)
        ImageView ivThumbnail;
        @BindView(R.id.tv_live_name)
        TextView tvLiveName;
        @BindView(R.id.tv_live_room_name)
        TextView tvLiveRoomName;
        @BindView(R.id.tv_watch_count)
        TextView tvWatchCount;
//        TextView tvLiveRoomName;
//        TextView tvLiveName;
//        ImageView ivThumbnail;
//        TextView tvWatchCount;

        public RecommendViewHolder(View itemView) {
            super(itemView);
            itemRootView = (RelativeLayout) itemView;
            ButterKnife.bind(this, itemView);
//            ivThumbnail = (ImageView) itemView.findViewById(R.id.iv_thumbnail);
//            tvLiveName = (TextView) itemView.findViewById(R.id.tv_live_name);
//            tvLiveRoomName = (TextView) itemView.findViewById(R.id.tv_live_room_name);
//            tvWatchCount = (TextView) itemView.findViewById(R.id.tv_watch_count);
            itemRootView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(v, getLayoutPosition());
                mOnItemClickListener.onItemLongClick(v, getLayoutPosition());
            }
        }
    }

    //头部 ViewHolder
    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.vp_top_carousel)
        TopViewPager heardViewPager;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

    //底部 ViewHolder
    public static class BottomViewHolder extends RecyclerView.ViewHolder {

        public BottomViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_HEADER) {
            return new HeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.header_recommend, parent, false));
        } else if (viewType == ITEM_TYPE_CONTENT) {
            System.out.println("--------------------------------------");
            return new RecommendViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_all_live, parent, false));
        } else if (viewType == ITEM_TYPE_FOOTER) {
//            return new BottomViewHolder(mLayoutInflater.inflate(R.layout.rv_footer, parent, false));
        }
        System.out.println("null null null null null null null null null ");
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof HeaderViewHolder) {
            if (mHeaderData != null) {
                System.out.println("viewpager ========================================");
                ((HeaderViewHolder) holder).heardViewPager.setAdapter(new TopViewPagerAdapter(mHeaderData, mContext));
                recommendFragment.autoToNextViewPager(((HeaderViewHolder) holder).heardViewPager);
            }
            //设置item点击事件
            if (mOnTopItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnTopItemClickListener.onItemClick(holder.itemView, position);
                    }
                });
                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        mOnTopItemClickListener.onItemLongClick(holder.itemView, position);
                        return false;
                    }
                });
            }
        } else if (holder instanceof RecommendViewHolder) {
            LiveListInfo.LiveListBean liveListBean = (LiveListInfo.LiveListBean) mContentData.get(position - mHeaderCount);
            ((RecommendViewHolder) holder).tvLiveName.setText(liveListBean.getLiveName());
            ((RecommendViewHolder) holder).tvLiveRoomName.setText(liveListBean.getLiveRoomName());
            ((RecommendViewHolder) holder).tvWatchCount.setText(liveListBean.getWatchCount());
            Glide.with(mContext)
                    .load(liveListBean.getImg())
                    .centerCrop()
                    .placeholder(R.mipmap.home_list_item_bg)
                    .crossFade()
                    .bitmapTransform(new RoundedCornersTransformation(mContext, 10, 0, RoundedCornersTransformation.CornerType.ALL))
                    .into(((RecommendViewHolder) holder).ivThumbnail);

            //设置item点击事件
            if (mOnItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onItemClick(holder.itemView, position);
                    }
                });
                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        mOnItemClickListener.onItemLongClick(holder.itemView, position);
                        return false;
                    }
                });
            }

        }
//        else if (holder instanceof BottomViewHolder) {
//
//        }
    }

    @Override
    public int getItemCount() {
        System.out.println("adapter" + mHeaderCount + getContentItemCount() + mBottomCount + "~~~~~~~~~~~~~~~~~~~~~~~");
        return mHeaderCount + getContentItemCount() + mBottomCount;
    }

}
