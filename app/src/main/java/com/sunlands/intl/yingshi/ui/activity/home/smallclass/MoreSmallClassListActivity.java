package com.sunlands.intl.yingshi.ui.activity.home.smallclass;

import android.support.v4.app.Fragment;

import com.shangde.lepai.uilib.indicator.ViewPagerHelper;
import com.shangde.lepai.uilib.indicator.commonnavigator.CommonNavigator;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.common.MyActivity;
import com.sunlands.intl.yingshi.ui.adapter.ViewPagerAdapter;
import com.sunlands.intl.yingshi.ui.home.bean.SmallClassBean;

import java.util.ArrayList;
import java.util.List;

public class MoreSmallClassListActivity extends MyActivity<SmallClassBean> {


    @Override
    public int getLayoutId() {
        return R.layout.activity_more_small_class_list;
    }

    @Override
    public String getTitleText() {
        return null;
    }

    private void intViewPager(List<SmallClassBean.CatListBean> catList) {
        mViewPager.setOffscreenPageLimit(catList.size());
        CommonNavigator commonNavigator = new CommonNavigator(this);
        SmallClassIndicatorAdapter indicatorAdapter = new SmallClassIndicatorAdapter(this,
                mViewPager, catList);
        commonNavigator.setAdapter(indicatorAdapter);
        if (catList.size() < 5) {
            commonNavigator.setAdjustMode(true);
        }
        magicIndicator.setNavigator(commonNavigator);
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < catList.size(); i++) {
            fragments.add(new SmallClassFragment(catList.get(i).getCatId()));
        }
        ViewPagerAdapter classroomAdapter = new ViewPagerAdapter(fragments, getSupportFragmentManager());
        mViewPager.setAdapter(classroomAdapter);
        ViewPagerHelper.bind(magicIndicator, mViewPager);
    }

    @Override
    public void initDataAfterView() {
        super.initDataAfterView();
        getDataOnNet("1", "1");
    }

    @Override
    public void onSuccess(SmallClassBean bean) {
        super.onSuccess(bean);
        List<SmallClassBean.CatListBean> catList = bean.getCatList();
        intViewPager(catList);
    }
}
