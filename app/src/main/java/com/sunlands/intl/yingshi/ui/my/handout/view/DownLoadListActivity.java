package com.sunlands.intl.yingshi.ui.my.handout.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shangde.lepai.uilib.indicator.ViewPagerHelper;
import com.shangde.lepai.uilib.indicator.commonnavigator.CommonNavigator;
import com.sunlands.comm_core.helper.EventBusHelper;
import com.sunlands.comm_core.utils.rx.RxBindingUtils;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.bean.event.DownLoadEvent;
import com.sunlands.intl.yingshi.common.CommonActivity;
import com.sunlands.intl.yingshi.constant.Constants;
import com.sunlands.intl.yingshi.ui.adapter.ViewPagerAdapter;
import com.sunlands.intl.yingshi.ui.my.handout.adapter.DownLoadIndicatorAdapter;
import com.sunlands.intl.yingshi.util.SPHelper;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class DownLoadListActivity extends CommonActivity<Object> {

    TextView tvHeaderRight;
    TextView selectAll;
    TextView delete;
    LinearLayout bottomView;
    int currentPosition = 0;

    public static void show(Context context, String subjectId, String name) {
        Intent intent = new Intent(context, DownLoadListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra(Constants.Key.KEY_SUBJECT_ID, subjectId);
        intent.putExtra(Constants.Key.KEY_SUBJECT_NAME, name);
        context.startActivity(intent);
    }


    @Override
    public void initListener() {
        super.initListener();
        RxBindingUtils.setOnClickListener(tvHeaderRight, this);
        RxBindingUtils.setOnClickListener(selectAll, this);
        RxBindingUtils.setOnClickListener(delete, this);
    }

    @Override
    public void initDataBeforeView() {
        super.initDataBeforeView();
        SPHelper.isSelectAllDownLoad(false);
    }

    private void intViewPager() {
        mViewPager.setOffscreenPageLimit(0);
        CommonNavigator commonNavigator = new CommonNavigator(this);
        DownLoadIndicatorAdapter indicatorAdapter = new DownLoadIndicatorAdapter(this,
                mViewPager, R.array.download_items);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(indicatorAdapter);
        magicIndicator.setNavigator(commonNavigator);
        LinearLayout titleContainer = commonNavigator.getTitleContainer(); // must after setNavigator
        titleContainer.setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        List<Fragment> fragments = new ArrayList<>();
        String courseId = getIntent().getStringExtra(Constants.Key.KEY_SUBJECT_ID);
        fragments.add(new DownLoadListFragment(1, courseId));
        fragments.add(new DownLoadListFragment(2, courseId));
        fragments.add(new DownLoadListFragment(3, courseId));
        fragments.add(new DownLoadListFragment(4, courseId));
        fragments.add(new DownLoadListFragment(5, courseId));
        ViewPagerAdapter adapter = new ViewPagerAdapter(fragments, getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        ViewPagerHelper.bind(magicIndicator, mViewPager);
        mViewPager.setCurrentItem(0);
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                currentPosition = position;
                cancelAll();
                SPHelper.isSelectAllDownLoad(false);
                tvHeaderRight.setText("编辑");
                bottomView.setVisibility(View.GONE);
                EventBusHelper.post(new DownLoadEvent(12, position));
            }
        });
    }

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        super.findView(inflateView, savedInstanceState);
        tvHeaderRight = FBIA(R.id.tv_header_right);
        selectAll = FBIA(R.id.select_all);
        delete = FBIA(R.id.delete);
        bottomView = FBIA(R.id.bottom_view);
        intViewPager();
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        int id = v.getId();
        if (id == R.id.tv_header_right) {
            String rightText = tvHeaderRight.getText().toString();

            if ("编辑".equals(rightText)) {
                tvHeaderRight.setText("取消");
                SPHelper.isSelectAllDownLoad(true);
                bottomView.setVisibility(View.VISIBLE);
            } else {
                SPHelper.isSelectAllDownLoad(false);
                tvHeaderRight.setText("编辑");
                bottomView.setVisibility(View.GONE);
            }

            EventBusHelper.post(new DownLoadEvent(0));
        } else if (id == R.id.select_all) {
            String all = selectAll.getText().toString();
            if (all.equals("全选")) {
                selectAll();
            } else {
                cancelAll();
            }
        } else if (id == R.id.delete) {
            EventBusHelper.post(new DownLoadEvent(7, currentPosition));
        }
    }


    //全选
    public void selectAll() {
        selectAll.setText("取消全选");
        EventBusHelper.post(new DownLoadEvent(1));
    }

    //取消全选
    public void cancelAll() {
        selectAll.setText("全选");
        EventBusHelper.post(new DownLoadEvent(2));
    }


    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        super.initView(inflateView, savedInstanceState);
        tvHeaderRight.setText("编辑");
        cancelAll();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_down_load_list;
    }


    @Override
    public String getTitleText() {
        return getIntent().getStringExtra(Constants.Key.KEY_SUBJECT_NAME);
    }


    //是否置灰删除按钮
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void downLoad(DownLoadEvent event) {
        if (event.getType() == 3) {
            delete.setTextColor(Color.parseColor("#E44747"));
            delete.setBackgroundResource(R.drawable.delete);
        } else if (event.getType() == 4) {
            delete.setTextColor(Color.parseColor("#8d8d8d"));
            delete.setBackgroundResource(R.drawable.delete_un_enable);
        } else if (event.getType() == 5) {
            selectAll.setText("取消全选");
        } else if (event.getType() == 6) {
            selectAll.setText("全选");
        } else if (event.getType() == 8) {
            tvHeaderRight.setVisibility(View.GONE);
            bottomView.setVisibility(View.GONE);
        } else if (event.getType() == 11) {
            tvHeaderRight.setVisibility(View.VISIBLE);
        } else if (event.getType() == 10) {
            cancelAll();
            SPHelper.isSelectAllDownLoad(false);
            tvHeaderRight.setText("编辑");
            bottomView.setVisibility(View.GONE);
        }
    }

}
