package com.sunlands.intl.yingshi.ui.activity.home.leakfilling.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;
import com.shangde.lepai.uilib.indicator.ViewPagerHelper;
import com.shangde.lepai.uilib.indicator.commonnavigator.CommonNavigator;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.common.CommonActivity;
import com.sunlands.intl.yingshi.ui.activity.home.leakfilling.adapter.FillIndicatorAdapter;
import com.sunlands.intl.yingshi.ui.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;


public class FillActivity<T> extends CommonActivity<T> {

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        super.initView(inflateView, savedInstanceState);
        intViewPager();
    }

    private void intViewPager() {
        mViewPager.setOffscreenPageLimit(4);
        CommonNavigator commonNavigator = new CommonNavigator(this);
        FillIndicatorAdapter indicatorAdapter = new FillIndicatorAdapter(this,
                mViewPager, R.array.fill_items);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(indicatorAdapter);
        magicIndicator.setNavigator(commonNavigator);
        LinearLayout titleContainer = commonNavigator.getTitleContainer(); // must after setNavigator
        titleContainer.setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(LeakClassFragment.newInstance());
        fragments.add(new LeakOtherClassFragment(2));
        fragments.add(new LeakOtherClassFragment(1));
        fragments.add(new LeakOtherClassFragment(3));
        ViewPagerAdapter classroomAdapter = new ViewPagerAdapter(fragments, getSupportFragmentManager());
        mViewPager.setAdapter(classroomAdapter);
        ViewPagerHelper.bind(magicIndicator, mViewPager);
        mViewPager.setCurrentItem(0);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fill;
    }

    @Override
    public String getTitleText() {
        return "任务提醒";
    }


    @Override
    public void onResume() {
        super.onResume();
        hideBottomUIMenu();
    }
}
