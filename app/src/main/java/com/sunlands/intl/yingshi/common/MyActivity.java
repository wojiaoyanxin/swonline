package com.sunlands.intl.yingshi.common;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shangde.lepai.uilib.indicator.MagicIndicator;
import com.sunlands.comm_core.base.BaseViewImpl;
import com.sunlands.comm_core.base.ibase.IBaseView;
import com.sunlands.comm_core.base.mvp.MvpActivity;
import com.sunlands.comm_core.helper.RxBindingHelper;
import com.sunlands.comm_core.net.BaseModel;
import com.sunlands.comm_core.utils.CommonUtils;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.ui.widget.CustomLoadMoreView;

import java.util.List;

/**
 * @author yxin
 * @date 2019-12-10 - 14:15
 * @des
 */
public abstract class MyActivity<T> extends MvpActivity<CommonPresenter<T>> implements CommonContract.ICommonView<T>, BaseViewImpl.OnClickListener {

    public BaseQuickAdapter baseQuickAdapter;
    public RelativeLayout vgBack;
    public TextView tvTitle;
    public MagicIndicator magicIndicator;
    public ViewPager mViewPager;
    public RecyclerView mRecyclerView;
    public SwipeRefreshLayout mSwipeRefreshLayout;
    public boolean isRefresh = true;
    public int mNextPage = 1;


    @Override
    protected CommonPresenter<T> createPresenter(IBaseView view) {
        return new CommonPresenter<T>(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.vg_back) {
            onBackPressed();
        }
    }

    @Override
    public int getCreateViewLayoutId() {
        return getLayoutId();
    }

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        tvTitle = FBIA(R.id.tv_title);
        vgBack = FBIA(R.id.vg_back);
        magicIndicator = FBIA(R.id.magic_indicator);
        mViewPager = FBIA(R.id.view_pager);
        mRecyclerView = FBIA(R.id.recycler_view);
        mSwipeRefreshLayout = FBIA(R.id.swipe_refresh_layout);
    }

    @Override
    public void initDataAfterView() {

    }

    public void getDataOnNet(Object... args) {

        getPresenter().getDataFromNet(getClass().getName(), args);
    }

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        if (tvTitle != null) {
            tvTitle.setText(getTitleText());
        }
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
        if (!CommonUtils.hasNetWorkConection()) {
            if (mSwipeRefreshLayout != null) {
                mSwipeRefreshLayout.setRefreshing(false);
            }
            ToastUtils.showShort("请检查网络设置");
        }
        if (mRecyclerView != null) {
            inflateStateView(getString(R.string.no_content), R.drawable.no_content);
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
        return new LinearLayoutManager(this);
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

    public abstract String getTitleText();

    @Override
    public void initListener() {
        if (vgBack != null) {
            RxBindingHelper.setOnClickListener(vgBack, this);
        }
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

    /**
     * 状态页面
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


    public void setFullScreen(boolean enable) {
        if (enable) {
            WindowManager.LayoutParams attrs = getWindow().getAttributes();
            attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            getWindow().setAttributes(attrs);
        } else {
            WindowManager.LayoutParams attrs = getWindow().getAttributes();
            attrs.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
            getWindow().setAttributes(attrs);
        }
    }

}
