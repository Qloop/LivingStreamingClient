package com.qloop.orange.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.moxun.tagcloudlib.view.TagsAdapter;
import com.qloop.orange.R;

import java.util.List;

/**
 * Created by Qloop on 2017/5/9.
 */

public class TagCloudAdapter extends TagsAdapter {


    private List<String>  mList;

    public TagCloudAdapter(List<String> mList) {
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public View getView(Context context, int position, ViewGroup parent) {
        TextView textView = (TextView) View.inflate(context, R.layout.item_tagcloudview, null);
        textView.setText(getItem(position));
        return textView;
    }

    @Override
    public String getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getPopularity(int position) {
        return 1;
    }

    @Override
    public void onThemeColorChanged(View view, int themeColor) {

    }
}
