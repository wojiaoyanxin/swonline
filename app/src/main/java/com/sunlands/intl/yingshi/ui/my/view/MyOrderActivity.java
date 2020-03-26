package com.sunlands.intl.yingshi.ui.my.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.SpanUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.sunlands.comm_core.base.ibase.IBaseView;
import com.sunlands.comm_core.base.mvp.MvpActivity;
import com.sunlands.comm_core.helper.RxBindingHelper;
import com.sunlands.comm_core.rvadapter.CommonAdapter;
import com.sunlands.comm_core.rvadapter.ViewHolder;
import com.sunlands.comm_core.statemanager.StateLayout;
import com.sunlands.comm_core.statemanager.state.CoreState;
import com.sunlands.comm_core.utils.CommonUtils;
import com.sunlands.comm_core.weight.RecycleViewItemLine;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.bean.MyOrderBean;
import com.sunlands.intl.yingshi.helper.state.NoOrderDataState;
import com.sunlands.intl.yingshi.ui.home.bean.SmallClassOrderBean;
import com.sunlands.intl.yingshi.ui.activity.home.smallclass.PayWebViewActivity;
import com.sunlands.intl.yingshi.ui.my.IMyContract;
import com.sunlands.intl.yingshi.ui.my.presenter.MyOrderPresenter;

public class MyOrderActivity extends MvpActivity<MyOrderPresenter> implements IMyContract.IMyOrderView {

    private ImageView mIv_title_back;
    private TextView mTv_title_content;
    private RecyclerView mRv_order_list;
    private CommonAdapter<MyOrderBean.ListBean> mCommonAdapter;
    private SmartRefreshLayout mSrl_order;
    public int limit = 20;

    @Override
    public void onClick(View v) {
        if (mIv_title_back == v) {
            onBackPressed();
        }
    }

    @Override
    public int getCreateViewLayoutId() {
        return R.layout.activity_my_order;
    }

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        mIv_title_back = FBIA(R.id.iv_title_back);
        mTv_title_content = FBIA(R.id.tv_title_content);
        mRv_order_list = FBIA(R.id.rv_order_list);
        mSrl_order = FBIA(R.id.srl_order);
    }

    @Override
    public void initDataAfterView() {
        getPresenter().getOrderList(limit);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().getOrderList(limit);
    }

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        mTv_title_content.setText("我的订单");
        mRv_order_list.setLayoutManager(new LinearLayoutManager(this));
        mCommonAdapter = new CommonAdapter<MyOrderBean.ListBean>(this, null, R.layout.item_order_layout) {
            @Override
            public void convert(ViewHolder holder, MyOrderBean.ListBean listBean) {
                holder.setText(R.id.tv_order_name_content, listBean.getName());
                SpanUtils.with(holder.getView(R.id.tv_order_money)).append("订单金额    ").append(listBean.getAmount() + "").setForegroundColor(Color.parseColor("#D0021B")).create();
                holder.setText(R.id.tv_order_number, "订单编号    " + listBean.getOrderNo());
                holder.setText(R.id.tv_order_time, "下单时间    " + listBean.getCreatedAt());
                holder.setText(R.id.tv_order_state, "已完成");

            }
        };
        mRv_order_list.addItemDecoration(new RecycleViewItemLine(this, LinearLayoutManager.VERTICAL, 10, CommonUtils.getColor(R.color.cl_EAEAEA)));
        mRv_order_list.setAdapter(mCommonAdapter);
    }

    @Override
    public void initListener() {
        RxBindingHelper.setOnClickListener(mIv_title_back, this);
        mSrl_order.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                getPresenter().getOrderList(limit += 20);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getPresenter().getOrderList(limit);
            }
        });
    }

    @Override
    protected MyOrderPresenter createPresenter(IBaseView view) {
        return new MyOrderPresenter(this);
    }


    @Override
    public void getMyOrderSuccess(MyOrderBean bean) {
        mSrl_order.finishRefresh();
        mSrl_order.finishLoadMore();
        if (ObjectUtils.isNotEmpty(bean) && bean.getPaginationList().size() > 0) {
            if (bean.getHasMore() == 0 || bean.getPaginationList().size() == 0) {
                mSrl_order.finishLoadMoreWithNoMoreData();
            }
            ((StateLayout) FBIA(R.id.sl_order)).showState(CoreState.STATE);
            mCommonAdapter.setDatas(bean.getPaginationList());
        } else {
            ((StateLayout) FBIA(R.id.sl_order)).showState(NoOrderDataState.STATE);
        }
    }

    @Override
    public void getPayResultSuccess(SmallClassOrderBean order) {
        String orderNumber = order.getOrderNumber();
        String payUrl = order.getPayUrl();
        Intent mIntent = new Intent(this, PayWebViewActivity.class);
        mIntent.putExtra("url", payUrl);
        mIntent.putExtra("title", "收银台");
        ActivityUtils.startActivity(mIntent);
    }


}
