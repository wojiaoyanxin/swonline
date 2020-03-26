package com.sunlands.intl.yingshi.ui.activity.home.plan.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;

import com.shangde.lepai.uilib.indicator.ViewPagerHelper;
import com.shangde.lepai.uilib.indicator.commonnavigator.CommonNavigator;
import com.sunlands.intl.yingshi.R;

import com.sunlands.intl.yingshi.common.CommonActivity;

import com.sunlands.intl.yingshi.ui.activity.home.plan.adapter.PlanIndicatorAdapter;
import com.sunlands.intl.yingshi.ui.adapter.ViewPagerAdapter;


import java.util.ArrayList;
import java.util.List;

public class PlanActivity<T> extends CommonActivity<T> {


    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        super.initView(inflateView, savedInstanceState);
        intViewPager();
    }

    @Override
    public String getTitleText() {
        return "一周计划";
    }


    private void intViewPager() {

        mViewPager.setOffscreenPageLimit(4);
        CommonNavigator commonNavigator = new CommonNavigator(this);
        PlanIndicatorAdapter indicatorAdapter = new PlanIndicatorAdapter(this,
                mViewPager, R.array.plan_items);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(indicatorAdapter);
        magicIndicator.setNavigator(commonNavigator);
        LinearLayout titleContainer = commonNavigator.getTitleContainer(); // must after setNavigator
        titleContainer.setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new ClassFragment(1));
        fragments.add(new ClassFragment(2));
        fragments.add(new ClassFragment(3));
        fragments.add(new ClassFragment(4));
        ViewPagerAdapter classroomAdapter = new ViewPagerAdapter(fragments, getSupportFragmentManager());
        mViewPager.setAdapter(classroomAdapter);
        ViewPagerHelper.bind(magicIndicator, mViewPager);
        mViewPager.setCurrentItem(getIntent().getIntExtra("currentItem", 0));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_plan;
    }

}
