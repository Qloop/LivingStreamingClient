package com.qloop.orange.view.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.moxun.tagcloudlib.view.TagCloudView;
import com.qloop.orange.R;
import com.qloop.orange.adapter.TagCloudAdapter;
import com.qloop.orange.utils.ToastUtils;
import com.qloop.orange.view.CreateLiveRoomActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Qloop on 2017/5/9.
 */

public class ChoseClassifyFragment extends BaseFragment {


    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.cloudView)
    TagCloudView cloudView;
    @BindView(R.id.tv_classify)
    TextView tvClassify;
    Unbinder unbinder;

    public static List<String> classifyList;

    @Override
    public View initViews() {
        View rootView = View.inflate(mActivity, R.layout.fragment_chose_classify, null);
        unbinder = ButterKnife.bind(this, rootView);

        classifyList = new ArrayList<>();
        addClassify();
        setUpTagCloudView();
        return rootView;
    }


    /**
     * 这里直接写死了, 应该由后台来提供分类的
     */
    private void addClassify() {
        classifyList.add("户外直播");
        classifyList.add("影评专区");
        classifyList.add("音乐");
        classifyList.add("科技教育");
        classifyList.add("萌宠乐园");
        classifyList.add("狼人杀");
        classifyList.add("火影忍者");
        classifyList.add("龙之谷");
        classifyList.add("王者荣耀");
        classifyList.add("皇室战争");
        classifyList.add("综合手游");
        classifyList.add("天天狼人杀");
        classifyList.add("网络游戏");
        classifyList.add("CS:GO");
        classifyList.add("体育竞技");
        classifyList.add("剑网3");
        classifyList.add("DATA2");
        classifyList.add("英雄联盟");
    }

    private void setUpTagCloudView() {
        TagCloudAdapter tagCloudAdapter = new TagCloudAdapter(classifyList);
        cloudView.setAdapter(tagCloudAdapter);
        cloudView.setOnTagClickListener(new TagCloudView.OnTagClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, int position) {
                ((CreateLiveRoomActivity) mActivity).setChoseClassify(classifyList.get(position));
                tvClassify.setText(classifyList.get(position));
            }
        });
    }

    @OnClick(R.id.btn_next)
    public void onViewClicked() {
        if (TextUtils.isEmpty(((CreateLiveRoomActivity) mActivity).getChoseClassify())) {
            ToastUtils.showToastShort(mActivity, "请先选择分类");
        } else {
            ViewPager viewPager = (ViewPager) mActivity.findViewById(R.id.vp_create_route);
            viewPager.setCurrentItem(1);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
