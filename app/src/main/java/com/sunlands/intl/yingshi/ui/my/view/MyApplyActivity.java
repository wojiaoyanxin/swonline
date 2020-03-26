package com.sunlands.intl.yingshi.ui.my.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SpanUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shangde.lepai.uilib.view.text.QMUITouchableSpan;
import com.shangde.lepai.uilib.viewgroup.layoutmanager.SpaceItemDecoration;
import com.sunlands.comm_core.utils.CommonUtils;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.common.CommonActivity;
import com.sunlands.intl.yingshi.ui.activity.home.apply.ApplyActivity;
import com.sunlands.intl.yingshi.ui.dialog.ApplyExpireNoticeDialog;
import com.sunlands.intl.yingshi.ui.my.adapter.MyApplyAdapter;
import com.sunlands.intl.yingshi.ui.my.bean.ApplyResponse;
import com.sunlands.intl.yingshi.util.Utils;

import java.util.List;


/**
 * 我的申请
 */
public class MyApplyActivity extends CommonActivity<ApplyResponse> {

    RecyclerView mRecyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    private MyApplyAdapter mMyApplyAdapter;

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        super.findView(inflateView, savedInstanceState);
        mRecyclerView = FBIA(R.id.recycler_view);
        mSwipeRefreshLayout = FBIA(R.id.swipe_refresh_layout);
    }

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        super.initView(inflateView, savedInstanceState);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(
                CommonUtils.dip2px(7),
                CommonUtils.dip2px(12)
        ));
        mMyApplyAdapter = new MyApplyAdapter(this);
        mRecyclerView.setAdapter(mMyApplyAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(this::getData);
        inflateEmptyView(mRecyclerView);
        ivEmpty.setImageResource(R.drawable.no_jilu);
        SpanUtils.with(mTvEmpty)
                .append("暂无申请")
                .append("\n\n")
                .append("点我立即申请")
                .setForegroundColor(CommonUtils.getColor(R.color.cl_4A90E2))
                .setClickSpan(new QMUITouchableSpan(CommonUtils.getColor(R.color.cl_4A90E2), CommonUtils.getColor(R.color.cl_4A90E2), 0, 0) {
                    @Override
                    public void onSpanClick(View widget) {
                        ActivityUtils.startActivity(ApplyActivity.class);
                    }
                })
                .create();
        mMyApplyAdapter.setEmptyView(mEmptyView);
    }

    @Override
    public String getTitleText() {
        return "院校申请";
    }

    @Override
    public void initListener() {
        super.initListener();
        mMyApplyAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.cl_submit_data) {
                    ApplyResponse.ApplyBean item = mMyApplyAdapter.getItem(position);
                    if (item != null)
                        MyApplyDetailActivity.show(MyApplyActivity.this, item.getInfomationStep());
                }
            }
        });
    }

    @Override
    public void initDataAfterView() {
        super.initDataAfterView();
        getDataNet(true, "");
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        onBackPressed();
    }

    @Override
    public void onSuccess(ApplyResponse bean) {
        List<ApplyResponse.ApplyBean> applications = bean.getApplications();
        mSwipeRefreshLayout.setRefreshing(false);
        mMyApplyAdapter.setNewData(applications);
        if (!Utils.isEmpty(applications)) {
            for (ApplyResponse.ApplyBean application : applications) {
                if (application.getHasExpired() == 1) {
                    ApplyExpireNoticeDialog applyExpireNoticeDialog = ApplyExpireNoticeDialog.newInstance();
                    applyExpireNoticeDialog.show(getSupportFragmentManager(), "ApplyExpireNoticeDialog");
                    break;
                }
            }
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_apply;
    }

    protected void getData() {
        if (!CommonUtils.hasNetWorkConection()) {
            mSwipeRefreshLayout.setRefreshing(false);
            ToastUtils.showShort("请检查网络设置");
            return;
        }
        getDataNet(true, "");
    }

}
