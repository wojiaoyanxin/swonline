package com.sunlands.intl.yingshi.ui.activity.home.smallclass;

import android.content.Context;
import android.graphics.Typeface;
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
import com.sunlands.intl.yingshi.constant.Constants;
import com.sunlands.intl.yingshi.ui.home.bean.SmallClassBean;

import java.util.List;


public class SmallClassIndicatorAdapter extends CommonNavigatorAdapter {

    private List<SmallClassBean.CatListBean> mDataList;
    Context mContext;
    ViewPager mViewPager;

    public SmallClassIndicatorAdapter(Context context, ViewPager viewPager, List<SmallClassBean.CatListBean> mDataList) {
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
        simplePagerTitleView.setTextSize(16);
        simplePagerTitleView.setTypeface(Typeface.DEFAULT_BOLD);
        simplePagerTitleView.setText(mDataList.get(index).getName());
        simplePagerTitleView.setNormalColor(mContext.getResources().getColor(R.color.cl_999999));
        simplePagerTitleView.setSelectedColor(mContext.getResources().getColor(R.color.cl_333333));
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
