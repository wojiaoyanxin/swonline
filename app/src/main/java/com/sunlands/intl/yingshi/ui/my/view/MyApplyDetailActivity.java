package com.sunlands.intl.yingshi.ui.my.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.common.CommonActivity;
import com.sunlands.intl.yingshi.constant.Constants;
import com.sunlands.intl.yingshi.ui.my.adapter.MyApplyDetailAdapter;


public class MyApplyDetailActivity extends CommonActivity<Object> {

    RelativeLayout mVgBack;
    TextView mTvTitle;
    RecyclerView mRecyclerView;

    private MyApplyDetailAdapter mMyApplyDetailAdapter;
    private String mInformationStep;

    public static void show(Context context,String informationStep) {
        Intent intent = new Intent(context, MyApplyDetailActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Constants.Key.KEY_INFORMATION_STEP, informationStep);
        context.startActivity(intent);
    }

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        super.findView(inflateView, savedInstanceState);
        mVgBack = FBIA(R.id.vg_back);
        mTvTitle = FBIA(R.id.tv_title);
        mRecyclerView = FBIA(R.id.recycler_view);
    }

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        super.initView(inflateView, savedInstanceState);
        mInformationStep = getIntent().getStringExtra(Constants.Key.KEY_INFORMATION_STEP);
        mMyApplyDetailAdapter = new MyApplyDetailAdapter(this,mInformationStep);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        mRecyclerView.setAdapter(mMyApplyDetailAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_apply_detail;
    }

    @Override
    public String getTitleText() {
        return "提交资料";
    }

}
