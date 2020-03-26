package com.sunlands.intl.yingshi.ui.widget;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.sunlands.intl.yingshi.util.Utils;

import cc.ibooker.ztextviewlib.AutoVerticalScrollTextView;

/**
 * @author yxin
 * @date 2019-12-04 - 13:17
 * @des
 */
public class MyScrollTextView extends AutoVerticalScrollTextView {


    public MyScrollTextView(Context context) {
        super(context);
    }

    public MyScrollTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public View makeView() {
        TextView textView =new TextView(Utils.getApp());
        textView.setGravity(Gravity.START);
        textView.setTextSize(11);
        textView.setSingleLine(true);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setTextColor(Color.parseColor("#666666"));
        return textView;
    }
}
