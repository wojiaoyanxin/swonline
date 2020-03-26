package com.sunlands.intl.yingshi.ui.home.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.shangde.lepai.uilib.indicator.UIUtil;
import com.shangde.lepai.uilib.indicator.commonnavigator.abs.CommonNavigatorAdapter;
import com.shangde.lepai.uilib.indicator.commonnavigator.abs.IPagerIndicator;
import com.shangde.lepai.uilib.indicator.commonnavigator.abs.IPagerTitleView;
import com.shangde.lepai.uilib.indicator.commonnavigator.indicators.LinePagerIndicator;
import com.shangde.lepai.uilib.indicator.commonnavigator.titles.ColorTransitionPagerTitleView;
import com.shangde.lepai.uilib.indicator.commonnavigator.titles.SimplePagerTitleView;
import com.sunlands.intl.yingshi.R;

import java.util.List;


public class HomeClassIndicatorAdapter extends CommonNavigatorAdapter {

    private List<Object> mDataList;
    Context mContext;
    ViewPager mViewPager;

    public HomeClassIndicatorAdapter(Context context, ViewPager viewPager, List<Object> mDataList) {
        mContext = context;
        mViewPager = viewPager;
        this.mDataList = mDataList;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public IPagerTitleView getTitleView(Context context, final int index) {
        SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
        simplePagerTitleView.setPadding(UIUtil.dip2px(mContext, 7), 0,
                UIUtil.dip2px(mContext, 7), 0);
        simplePagerTitleView.setTextSize(14);
        simplePagerTitleView.setBoldTextOnSelected(true);
        simplePagerTitleView.setText(mDataList.get(index).toString() + "");
        simplePagerTitleView.setNormalColor(mContext.getResources().getColor(R.color.cl_282828));
        simplePagerTitleView.setSelectedColor(mContext.getResources().getColor(R.color.cl_282828));
        simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(index);
            }
        });
        return simplePagerTitleView;
    }

    @Override
    public IPagerIndicator getIndicator(Context context) {
        LinePagerIndicator indicator = new LinePagerIndicator(context);
        indicator.setStartInterpolator(new AccelerateInterpolator());
        indicator.setEndInterpolator(new DecelerateInterpolator(1.6f));
        indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
        indicator.setGravity(Gravity.CENTER);
        indicator.setLineWidth(UIUtil.dip2px(context, 15));
        indicator.setLineHeight(UIUtil.dip2px(context, 2));
        indicator.setColors(mContext.getResources().getColor(R.color.cl_FF7224));
        return indicator;
    }

    @Override
    public float getTitleWeight(Context context, int index) {
        return mDataList.size();
    }
}
