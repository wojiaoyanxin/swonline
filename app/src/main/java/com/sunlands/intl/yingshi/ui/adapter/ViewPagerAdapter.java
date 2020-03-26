package com.sunlands.intl.yingshi.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件名: ViewPagerAdapter
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/8/6
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> mFragments = new ArrayList<>();

    public ViewPagerAdapter(List<Fragment> fragments, FragmentManager fm) {
        super(fm);
        mFragments.addAll(fragments);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
