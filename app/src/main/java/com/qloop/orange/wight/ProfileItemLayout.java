package com.qloop.orange.wight;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qloop.orange.R;

/**
 * Created by Qloop on 2017/4/22.
 */

public class ProfileItemLayout extends RelativeLayout {

    private static final String NAMESPACE = "http://schemas.android.com/apk/res-auto";
    private TextView tvTitle;
    private TextView tvSubtitle;
    private ImageView ivIcon;
    private String title;
    private int icon;
    private String subtitle;
    private boolean showBaseLine;


    public ProfileItemLayout(Context context) {
        this(context, null, 0);
    }


    public ProfileItemLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        Log.i("VIEW", "222222222222222222222222222");
    }

    public ProfileItemLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.i("VIEW", "33333333333333333333333333333333333333333");

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProfileItemLayout, defStyleAttr, 0);
        icon = typedArray.getResourceId(R.styleable.ProfileItemLayout_profile_icon, 0);
        title = typedArray.getString(R.styleable.ProfileItemLayout_profile_title);
        subtitle = typedArray.getString(R.styleable.ProfileItemLayout_profile_subtitle);
        showBaseLine = typedArray.getBoolean(R.styleable.ProfileItemLayout_profile_showBaseLine, false);
        initViews();
    }

    private void initViews() {
        Log.i("VIEW", icon + "");
        View.inflate(getContext(), R.layout.item_profile, this);
        ivIcon = (ImageView) findViewById(R.id.iv_icon);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvSubtitle = (TextView) findViewById(R.id.tv_subTitle);
        View vBaseLine = findViewById(R.id.v_baseline);
        if (icon == 0) {
            ivIcon.setVisibility(View.GONE);
        } else {
            ivIcon.setImageResource(icon);
        }
        if (!TextUtils.isEmpty(title)) {
            tvTitle.setText(title);
        }
        if (!TextUtils.isEmpty(subtitle)) {
            tvSubtitle.setText(subtitle);
        }
        if (showBaseLine) {
            vBaseLine.setVisibility(VISIBLE);
        }
    }

}
