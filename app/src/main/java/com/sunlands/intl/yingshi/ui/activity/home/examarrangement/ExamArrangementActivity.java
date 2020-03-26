package com.sunlands.intl.yingshi.ui.activity.home.examarrangement;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;

import com.shangde.lepai.uilib.indicator.ViewPagerHelper;
import com.shangde.lepai.uilib.indicator.commonnavigator.CommonNavigator;

import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.common.CommonActivity;
import com.sunlands.intl.yingshi.helper.SysMsgHelper;
import com.sunlands.intl.yingshi.ui.adapter.ViewPagerAdapter;
import com.sunlands.intl.yingshi.ui.classroom.adapter.ClassroomIndicatorAdapter;

import java.util.ArrayList;
import java.util.List;

//考试安排
public class ExamArrangementActivity<T> extends CommonActivity<T> {

    private int mExtra;


    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        super.initView(inflateView, savedInstanceState);
        mExtra = getIntent().getIntExtra(SysMsgHelper.INDEX, -1);
        intViewPager();
    }

    @Override
    public String getTitleText() {
        return getResources().getString(R.string.exam_schedule);
    }

    private void intViewPager() {
        CommonNavigator commonNavigator = new CommonNavigator(this);
        ClassroomIndicatorAdapter indicatorAdapter = new ClassroomIndicatorAdapter(this,
                mViewPager, R.array.exam_items);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(indicatorAdapter);
        magicIndicator.setNavigator(commonNavigator);
        LinearLayout titleContainer = commonNavigator.getTitleContainer(); // must after setNavigator
        titleContainer.setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeWorkFragment(2));
        fragments.add(new ExamFragment(1));
        ViewPagerAdapter classroomAdapter = new ViewPagerAdapter(fragments, getSupportFragmentManager());
        mViewPager.setAdapter(classroomAdapter);
        ViewPagerHelper.bind(magicIndicator, mViewPager);
        if (mExtra > -1) {
            mViewPager.setCurrentItem(mExtra);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_exam_arrangement;
    }

}
