package com.sunlands.intl.yingshi.ui.community.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;

/**
 * @author yxin
 * @date 2019-11-01 - 18:23
 * @des
 */
public class MyRecycleview extends LinearLayoutManager {

    private boolean isScrollEnabled = true;

    public MyRecycleview(Context context) {
        super(context);
    }

    public MyRecycleview(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public MyRecycleview(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean canScrollVertically() {
        return isScrollEnabled && super.canScrollVertically();
    }


}