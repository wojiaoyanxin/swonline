package com.sunlands.intl.yingshi.ui.activity.home.leakfilling.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sunlands.comm_core.helper.RxBindingHelper;
import com.sunlands.comm_core.net.BaseModel;
import com.sunlands.comm_core.utils.CommonUtils;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.common.CommonFragment;
import com.sunlands.intl.yingshi.ui.activity.home.leakfilling.adapter.LeakClassAdapter;
import com.sunlands.intl.yingshi.ui.activity.home.leakfilling.bean.LeakClassBean;
import com.sunlands.intl.yingshi.ui.widget.CustomLoadMoreView;
import com.sunlands.intl.yingshi.util.EnterPlayerUtils;
import com.sunlands.intl.yingshi.util.Utils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by yanxin on 2019/3/11.
 */

public class LeakClassFragment extends CommonFragment<LeakClassBean> {

    TextView time;
    TextView findbymounth;
    View rl_search;
    private LeakClassAdapter mAdapter;
    public static List<String> monthList;
    public CardView cd_view;

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        super.findView(inflateView, savedInstanceState);
        findbymounth = FBIF(R.id.findbymounth);
        time = FBIF(R.id.time);
        rl_search = FBIF(R.id.rl_search);
        cd_view = FBIF(R.id.cd_view);
    }

    @Override
    public void initDataAfterView() {
        super.initDataAfterView();
        getData();
    }

    @Override
    public void initListener() {
        super.initListener();
        RxBindingHelper.setOnClickListener(findbymounth, this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v == findbymounth) {
            setWheel();
        }
    }

    {
        monthList = new ArrayList<>();
    }

    public LeakClassFragment() {

    }

    public static LeakClassFragment newInstance() {
        return new LeakClassFragment();
    }

    @Override
    public void onSuccess(LeakClassBean bean) {
        super.onSuccess(bean);
        if (monthList != null) {
            if (monthList.size() == 0) {
                monthList = bean.getMonthList();
            }
        }
        setData(isRefresh, bean.getHasMore() == 1, bean.getCourseList());
        mSwipeRefreshLayout.setRefreshing(false);
        if (isRefresh) mAdapter.setEnableLoadMore(true);
    }

    @Override
    public void onFailed(BaseModel model) {
        super.onFailed(model);
        mSwipeRefreshLayout.setRefreshing(false);
        if (isRefresh) mAdapter.setEnableLoadMore(true);
    }

    private String monthData = "";

    private void setData(boolean isRefresh, boolean hasMore, List<LeakClassBean.CourseListBean> list) {

        if (TextUtils.isEmpty(monthData) && Utils.isEmpty(list)) {
            rl_search.setVisibility(View.GONE);
        } else {
            rl_search.setVisibility(View.VISIBLE);
        }

        if (Utils.isEmpty(list)) {
            cd_view.setBackgroundColor(0);
        } else {
            cd_view.setBackgroundColor(Color.WHITE);
        }
        if (isRefresh) {
            mAdapter.setNewData(list);
        } else {
            if (!Utils.isEmpty(list)) {
                mAdapter.addData(list);
            }
        }
        if (!hasMore) {
            mAdapter.loadMoreEnd();
        } else {
            mAdapter.loadMoreComplete();
        }

    }


    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        super.initView(inflateView, savedInstanceState);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new LeakClassAdapter(null);
        mAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        mAdapter.setLoadMoreView(new CustomLoadMoreView());
        mAdapter.setEnableLoadMore(false);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        }, mRecyclerView);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });
        inflateEmptyView(mRecyclerView);
        mTvEmpty.setText("暂无课程");
        ivEmpty.setImageResource(R.drawable.no_class);
        mAdapter.setEmptyView(mEmptyView);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                LeakClassBean.CourseListBean item = (LeakClassBean.CourseListBean) adapter.getItem(position);
                int courseId = Integer.parseInt(item.getCourseId());
                EnterPlayerUtils.enterClass(mContext, courseId);
            }
        });
    }

    private void loadMore() {
        reset(false);
        getDataNet(false, 1, mNextPage, monthData);
    }

    @Override
    public String getTitleText() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_leakclass_home;
    }

    protected void getData() {

        if (!CommonUtils.hasNetWorkConection()) {
            mSwipeRefreshLayout.setRefreshing(false);
            ToastUtils.showShort("请检查网络");
            return;
        }
        reset(true);
        mAdapter.setEnableLoadMore(false);
        getDataNet(true, 1, mNextPage, monthData);
    }

    private void setWheel() {
        if (monthList == null || monthList.size() <= 0) {
            return;
        }
        if (monthList.get(0).contains("全部")) {
            monthList.remove(0);
        }
        monthList.add(0, "全部");
        OptionsPickerView pvOptions = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                monthData = monthList.get(options1);
                time.setText(monthData);
                if (monthData.equals("全部")) {
                    monthData = "";
                }
                getData();
                //  mPresenter.getLeakClass(1, true, monthData, Constants.DEFAULT_PAGE);
            }
        })
                .setTitleText("选择时间")
                .setTitleSize(16)
                .setContentTextSize(18)
                .build();

        pvOptions.setPicker(monthList, null, null);
        pvOptions.show();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (monthList != null) {
            monthList.clear();
        }
    }
}
