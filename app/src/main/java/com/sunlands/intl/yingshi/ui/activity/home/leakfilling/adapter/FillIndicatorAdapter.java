package com.sunlands.intl.yingshi.ui.activity.home.leakfilling.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
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
import com.sunlands.intl.yingshi.constant.Constants;
import com.sunlands.intl.yingshi.util.DLog;

import java.util.Arrays;
import java.util.List;

/**
 * 文件名: ClassroomIndicatorAdapter
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/8/6
 */
public class FillIndicatorAdapter extends CommonNavigatorAdapter {
    private final List<String> mDataList;
    Context mContext;
    ViewPager mViewPager;

    public FillIndicatorAdapter(Context context, ViewPager viewPager, int arrayResId) {
        mContext = context;
        mViewPager = viewPager;
        mDataList = Arrays.asList(mContext.getResources().getStringArray(arrayResId));
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public IPagerTitleView getTitleView(Context context, final int index) {
        SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
        simplePagerTitleView.setPadding(UIUtil.dip2px(mContext, 0), 0,
                UIUtil.dip2px(mContext, 0), 0);
        simplePagerTitleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        simplePagerTitleView.setText(mDataList.get(index));
        simplePagerTitleView.setNormalColor(mContext.getResources().getColor(R.color.textNormal));
        simplePagerTitleView.setSelectedColor(mContext.getResources().getColor(R.color.black));
        simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DLog.d("index: " + index);
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
        indicator.setLineWidth(UIUtil.dip2px(context, Constants.WIDTH));
        indicator.setLineHeight(UIUtil.dip2px(context, Constants.HEIGHT));
        indicator.setColors(mContext.getResources().getColor(R.color.colorLabelYellowActive));
        return indicator;
    }

    @Override
    public float getTitleWeight(Context context, int index) {
        return mDataList.size();
    }
}
