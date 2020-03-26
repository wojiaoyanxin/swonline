package com.sunlands.intl.yingshi.common;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shangde.lepai.uilib.indicator.MagicIndicator;
import com.sunlands.comm_core.base.BaseViewImpl;
import com.sunlands.comm_core.base.ibase.IBaseView;
import com.sunlands.comm_core.base.mvp.MvpFragment;
import com.sunlands.comm_core.net.BaseModel;
import com.sunlands.intl.yingshi.R;

/**
 * Created by yanxin on 2019/5/16.
 */

public abstract class CommonFragment<T> extends MvpFragment<CommonPresenter<T>> implements CommonContract.ICommonView<T>, BaseViewImpl.OnClickListener {

    public Context mContext;

    protected View mEmptyView;
    protected TextView mTvEmpty;
    protected ImageView ivEmpty;

    public RelativeLayout mVgBack;
    public TextView mTvTitle;
    public MagicIndicator mMagicIndicator;
    public ViewPager mViewPager;
    public RecyclerView mRecyclerView;
    public SwipeRefreshLayout mSwipeRefreshLayout;

    public boolean isRefresh = true;

    public int mNextPage = 1;

    public boolean isViewCreated; // 界面是否已创建完成
    public boolean isVisibleToUser; // 是否对用户可见
    public boolean isDataLoaded; // 数据是否已请求

    // 实现具体的数据请求逻辑
    public void loadData() {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        tryLoadData();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewCreated = true;
        tryLoadData();
    }

    public void tryLoadData() {
        if (isViewCreated && isVisibleToUser && !isDataLoaded) {
            loadData();
            isDataLoaded = true;
        }
    }

    public void reset(boolean isRefresh) {
        this.isRefresh = isRefresh;
        if (!isRefresh) {
            mNextPage++;
        } else {
            mNextPage = 1;
        }
    }

    protected void inflateEmptyView(ViewGroup viewGroup) {
        mEmptyView = getLayoutInflater().inflate(R.layout.layout_empty, viewGroup, false);
        mTvEmpty = (TextView) mEmptyView.findViewById(R.id.tv_empty);
        ivEmpty = mEmptyView.findViewById(R.id.iv_empty);
    }

    @Override
    public int getCreateViewLayoutId() {
        return getLayoutId();
    }

    public abstract String getTitleText();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public abstract int getLayoutId();

    public View footView(RecyclerView mRecyclerView, int layoutId) {
        View view = getLayoutInflater().inflate(layoutId,
                (ViewGroup) mRecyclerView.getParent(), false);
        return view;
    }

    public View headView(RecyclerView mRecyclerView, int layoutId) {
        View view = getLayoutInflater().inflate(layoutId,
                (ViewGroup) mRecyclerView.getParent(), false);
        return view;
    }

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        mVgBack = FBIF(R.id.vg_back);
        mTvTitle = FBIF(R.id.tv_title);
        mMagicIndicator = FBIF(R.id.magic_indicator);
        mViewPager = FBIF(R.id.view_pager);
        mRecyclerView = FBIF(R.id.recycler_view);
        mSwipeRefreshLayout = FBIF(R.id.swipe_refresh_layout);
    }

    @Override
    public void initDataAfterView() {

    }

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        if (mTvTitle != null) {
            mTvTitle.setText(getTitleText());
        }
        if (mVgBack != null) {
            mVgBack.setVisibility(View.GONE);
        }

    }

    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void getDataSuccess(T bean) {
        onSuccess(bean);
    }


    public void onSuccess(T bean) {

    }

    @Override
    public void getDataFailed(BaseModel model) {

        onFailed(model);
    }


    public void onFailed(BaseModel model) {


    }

    @Override
    protected CommonPresenter<T> createPresenter(IBaseView view) {
        return new CommonPresenter<>(this);
    }

    public void getDataNet(Boolean isShow, Object... args) {
        getPresenter().getDataFromNet(getClass().getName(), isShow, args);
    }
}
