package com.sunlands.intl.yingshi.ui.my.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.shangde.lepai.uilib.viewgroup.layoutmanager.SpaceItemDecoration;
import com.sunlands.comm_core.net.BaseModel;
import com.sunlands.comm_core.utils.CommonUtils;
import com.sunlands.intl.yingshi.R;

import com.sunlands.intl.yingshi.common.CommonActivity;
import com.sunlands.intl.yingshi.constant.Constants;
import com.sunlands.intl.yingshi.ui.my.bean.HistoryMoreResponse;
import com.sunlands.intl.yingshi.ui.my.bean.HistoryResponse;
import com.sunlands.intl.yingshi.ui.my.bean.HistorySectionEntity;
import com.sunlands.intl.yingshi.util.EnterPlayerUtils;
import com.sunlands.intl.yingshi.ui.my.adapter.HistoryAdapter;
import com.sunlands.intl.yingshi.ui.my.bean.HistoryBean;
import com.sunlands.intl.yingshi.ui.widget.CustomLoadMoreView;
import com.sunlands.intl.yingshi.util.Utils;


import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends CommonActivity<Object> {

    private HistoryAdapter mHistoryAdapter;

    private HistorySectionEntity mWeekHeader;
    private HistorySectionEntity mEarlierHeader;
    private List<HistorySectionEntity> mHistorySectionEntities = new ArrayList<>();
    private HistorySectionEntity mTodayHeader;

    protected int mNextPage = Constants.DEFAULT_NEXT_PAGE;

     void reset2(boolean isRefresh) {
        if (!isRefresh) {
            mNextPage++;
        } else {
            mNextPage = 1;
            mHistorySectionEntities.clear();
        }
    }

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        super.initView(inflateView, savedInstanceState);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        //悬浮置顶
//        mRecyclerView.addItemDecoration(new PinnedHeaderItemDecoration
//                .Builder(BaseHeaderAdapter.TYPE_HEADER).create());
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(
                CommonUtils.dip2px(7),
                CommonUtils.dip2px(10)));
        mHistoryAdapter = new HistoryAdapter();
        mHistoryAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        mHistoryAdapter.setLoadMoreView(new CustomLoadMoreView());
        inflateEmptyView(mRecyclerView);
        mTvEmpty.setText("暂无记录");
        ivEmpty.setImageResource(R.drawable.no_jilu);
        mHistoryAdapter.setEmptyView(mEmptyView);
        mRecyclerView.setAdapter(mHistoryAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(this::getData);
        mHistoryAdapter.setOnLoadMoreListener(this::loadMore, mRecyclerView);
    }

    @Override
    public String getTitleText() {
        return getResources().getString(R.string.history_record);
    }

    @Override
    public void initDataAfterView() {
        super.initDataAfterView();
        mTodayHeader = new HistorySectionEntity(true, getResources().getString(R.string.today));
        mWeekHeader = new HistorySectionEntity(true, getResources().getString(R.string.in_a_week));
        mEarlierHeader = new HistorySectionEntity(true, getResources().getString(R.string.earlier));
    }

    @Override
    public void initListener() {
        super.initListener();
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                HistorySectionEntity item = mHistoryAdapter.getItem(position);
                if (item != null && item.t != null) {
                    HistoryBean historyBean = item.t;
                    int courseType = historyBean.getCourseType();
                    if (courseType == 1) {
                        EnterPlayerUtils.enterClass(HistoryActivity.this, Integer.parseInt(historyBean.getCourseId()));
                    } else if (courseType == 2) {
                        EnterPlayerUtils.enterBilingClass(HistoryActivity.this, Integer.parseInt(historyBean.getCourseId()),historyBean.getIs_free());
                    }
                }
            }
        });
    }

    private void loadMore() {
        getDataNet( true, mNextPage + "");
    }

    @Override
    public void onSuccess(Object bean) {

        if (bean instanceof HistoryResponse) {
            HistoryResponse historyResponse = (HistoryResponse) bean;
            reset2(true);
            if (!Utils.isEmpty(historyResponse.getToday())) {
                mHistorySectionEntities.add(mTodayHeader);
                for (HistoryBean historyBean : historyResponse.getToday()) {
                    mHistorySectionEntities.add(new HistorySectionEntity(historyBean));
                }
            }
            if (!Utils.isEmpty(historyResponse.getWeek())) {
                mHistorySectionEntities.add(mWeekHeader);
                for (HistoryBean historyBean : historyResponse.getWeek()) {
                    mHistorySectionEntities.add(new HistorySectionEntity(historyBean));
                }
            }

            if (!Utils.isEmpty(historyResponse.getMore())) {
                mHistorySectionEntities.add(mEarlierHeader);
                for (HistoryBean historyBean : historyResponse.getMore()) {
                    mHistorySectionEntities.add(new HistorySectionEntity(historyBean));
                }
            }

            mSwipeRefreshLayout.setRefreshing(false);
            mHistoryAdapter.setEnableLoadMore(true);
            if (Utils.isEmpty(mHistorySectionEntities)) {
                mHistoryAdapter.setNewData(null);
            } else {
                mHistoryAdapter.setNewData(mHistorySectionEntities);
//                if (historyResponse.getHasMore() == 1) {
//                    mHistoryAdapter.loadMoreComplete();
//                } else {
                mHistoryAdapter.loadMoreEnd();
                //               }
            }

        } else if (bean instanceof HistoryMoreResponse) { //cha看更多

            HistoryMoreResponse historyMoreResponse = (HistoryMoreResponse) bean;
            if (historyMoreResponse == null) {
                return;
            }
            boolean hasMore = historyMoreResponse.getHasMore() == 1;
            if (hasMore) {
                reset2(false);
            }
            List<HistorySectionEntity> historySectionEntities = new ArrayList<>();
            if (!Utils.isEmpty(historyMoreResponse.getCourseList())) {
                if (!mHistorySectionEntities.contains(mEarlierHeader)) {
                    historySectionEntities.add(mEarlierHeader);
                }
                for (HistoryBean historyBean : historyMoreResponse.getCourseList()) {
                    HistorySectionEntity historySectionEntity = new HistorySectionEntity(historyBean);
                    if (!mHistorySectionEntities.contains(historySectionEntity)) {
                        historySectionEntities.add(historySectionEntity);
                    }
                }
            }

            mHistoryAdapter.setEnableLoadMore(true);
            mSwipeRefreshLayout.setRefreshing(false);
            if (!Utils.isEmpty(historySectionEntities))
                mHistoryAdapter.addData(historySectionEntities);
            if (hasMore) {
                mHistoryAdapter.loadMoreComplete();
            } else {
                mHistoryAdapter.loadMoreEnd();
            }
        }

    }

    @Override
    public void onFailed(BaseModel model) {
        super.onFailed(model);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_history;
    }

    protected void getData() {

        if (!CommonUtils.hasNetWorkConection()) {
            mSwipeRefreshLayout.setRefreshing(false);
            ToastUtils.showShort("请检查网络设置");
            return;
        }
        getDataNet(true, "");
    }

    @Override
    public void onResume() {
        super.onResume();
        getDataNet(true, "");
    }
}
