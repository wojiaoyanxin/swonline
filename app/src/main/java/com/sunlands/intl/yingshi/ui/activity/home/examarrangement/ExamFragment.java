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
import com.sunlands.intl.yingshi.ui.widget.CustomLoadMoreView;
import com.sunlands.intl.yingshi.util.GoH5Utils;
import com.sunlands.intl.yingshi.util.Utils;

import java.util.List;


//考试安排
public class ExamFragment extends CommonFragment<ExamArrangementsResponse> {


    private ExamScheduleAdapter mAdapter;

    int type;

    public ExamFragment() {

    }

    @SuppressLint("ValidFragment")

    public ExamFragment(int type) {
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
        mAdapter = new ExamScheduleAdapter(null);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.addFooterView(getFooterView());
        mAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        mAdapter.setLoadMoreView(new CustomLoadMoreView());
        mAdapter.setEnableLoadMore(false);
        inflateEmptyView(mRecyclerView);
        mTvEmpty.setText("暂无考试");
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
    }

    //statusCode  1 未开始   2  未参加  3  已参加
    private void addOnClickListener() {

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                ExamArrangementsResponse.ExamBean item = (ExamArrangementsResponse.ExamBean) adapter.getItem(position);

                int isView = item.getIs_result();

                if (item.getStatusCode() == 1) {
                    ToastUtils.showShort("考试未到开始时间");
                } else {
                    if (item.getCourseId() != 0) {
                        int courseId = item.getCourseId();
                        if (item.getStatusCode() == 3) {
                            if (isView == 0) {
                                ToastUtils.showShort("暂无解析");
                                return;
                            }
                            GoH5Utils.showHomeTo(mContext, 1, courseId + "", item.getExamId());

                        } else {
                            GoH5Utils.showHomeTo(mContext, 0, courseId + "", item.getExamId());
                        }
                    } else {
                        ToastUtils.showShort("请前往思维教育官网参加考试");
                    }
                }

            }
        });

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                ExamArrangementsResponse.ExamBean item = (ExamArrangementsResponse.ExamBean) adapter.getItem(position);

                int isView = item.getIs_result();

                if (view.getId() == R.id.join_exam) {
                    if (item.getStatusCode() == 1) {
                        ToastUtils.showShort("考试未到开始时间");
                    } else {
                        if (item.getCourseId() != 0) {
                            int courseId = item.getCourseId();
                            GoH5Utils.showHomeTo(mContext, 0, courseId + "", item.getExamId());
                        } else {
                            ToastUtils.showShort("请前往思维教育官网参加考试");
                        }
                    }
                } else if (view.getId() == R.id.look_jiexi) {
                    if (item.getStatusCode() == 1) {
                        ToastUtils.showShort("考试未到开始时间");
                    } else if (item.getStatusCode() == 2) {
                        ToastUtils.showShort("请先参加考试");
                    } else {
                        if (isView == 0) {
                            ToastUtils.showShort("暂无解析");
                        } else {
                            ToastUtils.showShort("请前往思维教育官网参加考试");
                        }
                    }
                }
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
            getDataNet(true, type, mNextPage);
        }
    }

    @Override
    public void loadData() {
        super.loadData();
        getDataNet(true, type, mNextPage);
    }

}
