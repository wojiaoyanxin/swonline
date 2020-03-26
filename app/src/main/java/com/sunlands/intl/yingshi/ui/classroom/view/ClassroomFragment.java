package com.sunlands.intl.yingshi.ui.classroom.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;

import com.shangde.lepai.uilib.indicator.ViewPagerHelper;
import com.shangde.lepai.uilib.indicator.commonnavigator.CommonNavigator;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.bean.event.EventMessage;
import com.sunlands.intl.yingshi.common.CommonFragment;
import com.sunlands.intl.yingshi.ui.adapter.ViewPagerAdapter;
import com.sunlands.intl.yingshi.ui.classroom.adapter.ClassroomIndicatorAdapter;
import com.sunlands.intl.yingshi.util.DLog;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * 课堂
 * @param <T>
 */
public class ClassroomFragment<T> extends CommonFragment<T> {

    private static final String ARG_PARAM1 = "param1";

    public ClassroomFragment() {
    }

    public static ClassroomFragment newInstance(String param1) {
        ClassroomFragment fragment = new ClassroomFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(EventMessage eventMessage) {
        DLog.d("refreshEvent.getType(): " + eventMessage.getEventType());
        if (eventMessage.getEventType() == EventMessage.EVENT_CLICK_LESSON_CALENDAR) {
            mViewPager.setCurrentItem(0);
        }
    }

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        super.initView(inflateView, savedInstanceState);
        intViewPager();
    }

    private void intViewPager() {
        CommonNavigator commonNavigator = new CommonNavigator(mContext);
        ClassroomIndicatorAdapter indicatorAdapter = new ClassroomIndicatorAdapter(mContext,
                mViewPager, R.array.classroom_items);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(indicatorAdapter);
        mMagicIndicator.setNavigator(commonNavigator);
        LinearLayout titleContainer = commonNavigator.getTitleContainer(); // must after setNavigator
        titleContainer.setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new LessonCalendarFragment());
        fragments.add(new AllLessonFragment());
        ViewPagerAdapter classroomAdapter = new ViewPagerAdapter(fragments, getChildFragmentManager());
        mViewPager.setAdapter(classroomAdapter);
        ViewPagerHelper.bind(mMagicIndicator, mViewPager);
        mViewPager.setCurrentItem(0);
    }

    @Override
    public String getTitleText() {
        return "课堂";
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_class_room;
    }
}
