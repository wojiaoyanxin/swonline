package com.sunlands.intl.yingshi.ui.home.view;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.LinearLayout;

import com.shangde.lepai.uilib.indicator.ViewPagerHelper;
import com.shangde.lepai.uilib.indicator.commonnavigator.CommonNavigator;
import com.sunlands.comm_core.utils.rx.RxBindingUtils;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.common.MyFragment;
import com.sunlands.intl.yingshi.ui.adapter.ViewPagerAdapter;
import com.sunlands.intl.yingshi.ui.home.adapter.HomeClassIndicatorAdapter;
import com.sunlands.intl.yingshi.ui.my.handout.view.MyHandoutActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yxin
 * @date 2020-03-11 - 14:59
 * @des
 */
public class MyHomeFragment extends MyFragment<Object> {


    CoordinatorLayout cdlHome;
    AppBarLayout ablHome;
    private View cdLianximoshi;
    private View cdKchengziliao;

    public MyHomeFragment() {
    }

    public static MyHomeFragment newInstance() {
        MyHomeFragment fragment = new MyHomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        super.initView(inflateView, savedInstanceState);
        intViewPager();
    }

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        super.findView(inflateView, savedInstanceState);
        cdlHome = FBIF(R.id.cdl_home);
        ablHome = FBIF(R.id.abl_home);
        cdLianximoshi = FBIF(R.id.cd_lianximoshi);
        cdKchengziliao = FBIF(R.id.cd_kechengziliao);
    }

    @Override
    public void initListener() {
        super.initListener();
        RxBindingUtils.setOnClickListener(cdLianximoshi, this);
        RxBindingUtils.setOnClickListener(cdKchengziliao, this);
        ablHome.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    mSwipeRefreshLayout.setEnabled(true);
                } else {
                    mSwipeRefreshLayout.setEnabled(false);
                }
            }
        });
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v == cdLianximoshi) { //练习模式

        } else if (v == cdKchengziliao) { //课程资料
            startActivity(MyHandoutActivity.class);
        }
    }

    private void intViewPager() {
        CommonNavigator commonNavigator = new CommonNavigator(mContext);
        HomeClassIndicatorAdapter indicatorAdapter = new HomeClassIndicatorAdapter(mContext,
                mViewPager, Arrays.asList("活动课", "普通科", "中村总额述职", "只是监狱与能力", "几件事倒计时的和结束"));
        commonNavigator.setAdapter(indicatorAdapter);
        mMagicIndicator.setNavigator(commonNavigator);
        LinearLayout titleContainer = commonNavigator.getTitleContainer(); // must after setNavigator
        titleContainer.setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(HomeClassListFragment.newInstance());
        fragments.add(HomeClassListFragment.newInstance());
        fragments.add(HomeClassListFragment.newInstance());
        fragments.add(HomeClassListFragment.newInstance());
        fragments.add(HomeClassListFragment.newInstance());
        ViewPagerAdapter classroomAdapter = new ViewPagerAdapter(fragments, getChildFragmentManager());
        mViewPager.setAdapter(classroomAdapter);
        ViewPagerHelper.bind(mMagicIndicator, mViewPager);
        mViewPager.setCurrentItem(0);
        mViewPager.setOffscreenPageLimit(4);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_siwei;
    }
}
