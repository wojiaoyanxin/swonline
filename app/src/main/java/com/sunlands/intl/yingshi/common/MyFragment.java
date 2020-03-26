package com.sunlands.intl.yingshi.common;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shangde.lepai.uilib.indicator.MagicIndicator;
import com.sunlands.comm_core.base.BaseViewImpl;
import com.sunlands.comm_core.base.ibase.IBaseView;
import com.sunlands.comm_core.base.mvp.MvpFragment;
import com.sunlands.comm_core.net.BaseModel;
import com.sunlands.comm_core.utils.CommonUtils;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.ui.widget.CustomLoadMoreView;

import java.util.List;

/**
 * @author yxin
 * @date 2019-12-12 - 11:09
 * @des
 */
public abstract class MyFragment<T> extends MvpFragment<CommonPresenter<T>> implements CommonContract.ICommonView<T>, BaseViewImpl.OnClickListener {

    public BaseQuickAdapter baseQuickAdapter;
    public ViewPager mViewPager;
    public RecyclerView mRecyclerView;
    public SwipeRefreshLayout mSwipeRefreshLayout;
    public MagicIndicator mMagicIndicator;
    public boolean isRefresh = true;
    public int mNextPage = 1;
    public Context mContext;
    public boolean isViewCreated; // 界面是否已创建完成
    public boolean isVisibleToUser; // 是否对用户可见
    public boolean isDataLoaded; // 数据是否已请求


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        tryLoadData();
    }

    public void tryLoadData() {
        if (isViewCreated && isVisibleToUser && !isDataLoaded) {
            loadData();
            isDataLoaded = true;
        }
    }

    // 实现具体的数据请求逻辑
    public void loadData() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewCreated = true;
        tryLoadData();
    }

    @Override
    protected CommonPresenter<T> createPresenter(IBaseView view) {
        return new CommonPresenter<T>(this);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public int getCreateViewLayoutId() {
        return getLayoutId();
    }

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        mViewPager = FBIF(R.id.view_pager);
        mRecyclerView = FBIF(R.id.recycler_view);
        mSwipeRefreshLayout = FBIF(R.id.swipe_refresh_layout);
        mMagicIndicator = FBIF(R.id.magic_indicator);
    }

    @Override
    public void initDataAfterView() {

    }

    public void getDataOnNet(Object... args) {
        getPresenter().getDataFromNet(getClass().getName(), args);
    }

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {


        if (mRecyclerView != null) {
            mRecyclerView.setLayoutManager(getLinearLayout());
        }
        baseQuickAdapter = getAdapter();
        if (mRecyclerView != null) {
            mRecyclerView.setAdapter(baseQuickAdapter);
        }
        if (baseQuickAdapter != null) {
            baseQuickAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    reset(false);
                    load();
                }
            }, mRecyclerView);
            // baseQuickAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
            baseQuickAdapter.setLoadMoreView(new CustomLoadMoreView());
        }
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    reset(true);
                    load();
                }
            });
        }
        if (mRecyclerView != null) {
            setEmptyView(getString(R.string.no_content), R.drawable.no_content);
        }
        if (!CommonUtils.hasNetWorkConection()) {
            if (mSwipeRefreshLayout != null) {
                mSwipeRefreshLayout.setRefreshing(false);
            }
            ToastUtils.showShort("请检查网络设置");
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

    //刷新,加载更多
    public void load() {
        if (!CommonUtils.hasNetWorkConection()) {
            if (mSwipeRefreshLayout != null) {
                mSwipeRefreshLayout.setRefreshing(false);
            }
            ToastUtils.showShort("请检查网络设置");
            return;
        }
    }


    public RecyclerView.LayoutManager getLinearLayout() {
        return new LinearLayoutManager(mContext);
    }

    /**
     * 设置数据
     */

    public <T> void setData(List<T> list, boolean hasMore) {
        if (isRefresh) {
            baseQuickAdapter.setNewData(list);
            baseQuickAdapter.loadMoreComplete();
            if (!hasMore) {
                baseQuickAdapter.loadMoreEnd();
            }
        } else {
            baseQuickAdapter.addData(list);
            baseQuickAdapter.loadMoreComplete();
            if (!hasMore) {
                baseQuickAdapter.loadMoreEnd();
            }
        }
    }

    public abstract int getLayoutId();

    @Override
    public void initListener() {

    }

    @Override
    public void getDataSuccess(T bean) {
        if (baseQuickAdapter != null) {
            baseQuickAdapter.setEnableLoadMore(true);
        }
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
        onSuccess(bean);
    }


    @Override
    public void getDataFailed(BaseModel model) {

        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
        onFailed(model);

    }

    public void onSuccess(T bean) {

    }

    public void onFailed(BaseModel model) {


    }

    public void setEmptyView(String tv, int imageId) {
        inflateStateView(tv, imageId);
    }

    /**
     * 默认状态页面
     */

    public void inflateStateView(String tv, int imageId) {

        View inflate = getLayoutInflater().inflate(R.layout.state_no_data_layout, (ViewGroup) mRecyclerView.getParent(), false);
        TextView tv_state = inflate.findViewById(R.id.tv_state);
        tv_state.setText(tv);
        ImageView iv = inflate.findViewById(R.id.iv_state);
        iv.setImageResource(imageId);
        if (baseQuickAdapter != null) {
            baseQuickAdapter.setEmptyView(inflate);
        }
    }


    /**
     * adapter 初始化
     *
     * @return
     */
    public BaseQuickAdapter getAdapter() {
        return null;
    }


}
