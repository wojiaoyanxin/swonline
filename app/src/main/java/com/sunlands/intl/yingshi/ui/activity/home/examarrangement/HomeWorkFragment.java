package com.sunlands.intl.yingshi.ui.activity.home.examarrangement;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sunlands.comm_core.net.BaseModel;
import com.sunlands.comm_core.utils.CommonUtils;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.common.CommonFragment;
import com.sunlands.intl.yingshi.ui.activity.ExamTransitionActivity;
import com.sunlands.intl.yingshi.ui.widget.CustomLoadMoreView;
import com.sunlands.intl.yingshi.ui.widget.countdown.CountdownTime;
import com.sunlands.intl.yingshi.util.Utils;

import java.util.List;


//考试安排
public class HomeWorkFragment extends CommonFragment<ExamArrangementsResponse> {


    private HomeWorkScheduleAdapter mAdapter;

    int type;

    public HomeWorkFragment() {

    }

    @SuppressLint("ValidFragment")

    public HomeWorkFragment(int type) {
        this.type = type;
    }


    private void loadMore() {
        reset(false);
        getDataNet(false, type, mNextPage);
    }

    @Override
    public void onSuccess(ExamArrangementsResponse bean) {
        super.onSuccess(bean);
        mSwipeRefreshLayout.setRefreshing(false);
        setData(isRefresh, bean.getHasMore() == 1, bean.getExamList());
        mSwipeRefreshLayout.setRefreshing(false);
        if (isRefresh) mAdapter.setEnableLoadMore(true);
    }

    @Override
    public void onFailed(BaseModel model) {
        super.onFailed(model);
        mSwipeRefreshLayout.setRefreshing(false);
        if (isRefresh) mAdapter.setEnableLoadMore(true);
    }

    private void setData(boolean isRefresh, boolean hasMore, List<ExamArrangementsResponse.ExamBean> list) {

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
    public void initView(View view, Bundle savedInstanceState) {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new HomeWorkScheduleAdapter(null);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.addFooterView(getFooterView());
      //  mAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        mAdapter.setLoadMoreView(new CustomLoadMoreView());
        mAdapter.setEnableLoadMore(false);
        inflateEmptyView(mRecyclerView);
        mTvEmpty.setText("暂无作业");
        ivEmpty.setImageResource(R.drawable.no_jilu);
        mAdapter.setEmptyView(mEmptyView);

    }

    @Override
    public void initListener() {
        super.initListener();
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        }, mRecyclerView);
        mSwipeRefreshLayout.setOnRefreshListener(this::getData);
        addOnClickListener();
        CountdownTime.onTimeCountdownOverListener = new CountdownTime.OnTimeCountdownOverListener() {
            @Override
            public void onTimeCountdownOver(String id) {
                getData();
            }
        };
    }

    //statusCode  1 未开始   2  未参加  3  已参加
    private void addOnClickListener() {

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ExamArrangementsResponse.ExamBean item = (ExamArrangementsResponse.ExamBean) adapter.getItem(position);
                ExamTransitionActivity.show(mContext, item.getExamId() + "", item.getCourseId() + "");
            }
        });
    }

    private View getFooterView() {
        View view = getLayoutInflater().inflate(R.layout.layout_exam_arrangement_footer,
                (ViewGroup) mRecyclerView.getParent(), false);
        return view;
    }

    @Override
    public String getTitleText() {
        return "";
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_exam;
    }

    protected void getData() {

        if (!CommonUtils.hasNetWorkConection()) {
            mSwipeRefreshLayout.setRefreshing(false);
            ToastUtils.showShort("请检查网络设置");
            return;
        }
        reset(true);
        getDataNet(true, type, mNextPage);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isDataLoaded) {
            getData();
        }
    }

    @Override
    public void loadData() {
        super.loadData();
        getDataNet(true, type, mNextPage);
    }
}
