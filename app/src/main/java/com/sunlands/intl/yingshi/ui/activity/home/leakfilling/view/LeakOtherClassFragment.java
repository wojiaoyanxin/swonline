package com.sunlands.intl.yingshi.ui.activity.home.leakfilling.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.oushangfeng.pinnedsectionitemdecoration.PinnedHeaderItemDecoration;
import com.sunlands.comm_core.helper.RxBindingHelper;
import com.sunlands.comm_core.net.BaseModel;
import com.sunlands.comm_core.utils.CommonUtils;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.base.BaseHeaderAdapter;
import com.sunlands.intl.yingshi.bean.multi.PinnedHeaderEntity;
import com.sunlands.intl.yingshi.common.CommonFragment;
import com.sunlands.intl.yingshi.ui.activity.ExamTransitionActivity;
import com.sunlands.intl.yingshi.ui.activity.home.leakfilling.adapter.LeakOtherClassAdapter;
import com.sunlands.intl.yingshi.ui.activity.home.leakfilling.bean.CommonLeakBean;
import com.sunlands.intl.yingshi.ui.activity.home.leakfilling.bean.LeakOtherBean;
import com.sunlands.intl.yingshi.ui.widget.CustomLoadMoreView;
import com.sunlands.intl.yingshi.ui.widget.countdown.CountdownTime;
import com.sunlands.intl.yingshi.util.Utils;

import java.util.ArrayList;
import java.util.List;

import static com.sunlands.intl.yingshi.ui.activity.home.leakfilling.view.LeakClassFragment.monthList;

/**
 * Created by yanxin on 2019/3/11.
 */

@SuppressLint("ValidFragment")
public class LeakOtherClassFragment extends CommonFragment<LeakOtherBean> {

    private String monthData = "全部";
    private LeakOtherClassAdapter mAdapter;
    int type;
    private View view;
    private View cl_no_homework;
    private PinnedHeaderItemDecoration pinnedHeaderItemDecoration;
    private List<LeakOtherBean.ListBean> sevenDays;

    public LeakOtherClassFragment() {

    }

    @SuppressLint("ValidFragment")
    public LeakOtherClassFragment(int i) {
        type = i;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new LeakOtherClassAdapter(null, type);
        mAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        //悬浮置顶
        pinnedHeaderItemDecoration = new PinnedHeaderItemDecoration
                .Builder(BaseHeaderAdapter.TYPE_HEADER).create();
        mRecyclerView.addItemDecoration(pinnedHeaderItemDecoration);
        mAdapter.setLoadMoreView(new CustomLoadMoreView());
        mAdapter.setEnableLoadMore(true);
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

        if (type == 2) {
            mTvEmpty.setText("暂无考试");
        } else if (type == 1) {
            mTvEmpty.setText(R.string.no_quiz);
        } else if (type == 3) {
            mTvEmpty.setText("暂无论文");
        }
        ivEmpty.setImageResource(R.drawable.no_content);
        mAdapter.setEmptyView(mEmptyView);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                PinnedHeaderEntity<LeakOtherBean.ListBean> item = (PinnedHeaderEntity<LeakOtherBean.ListBean>) adapter.getItem(position);
                int isEnd = item.getData().getHasEnd();
                int isRepeat = item.getData().getCanRepeat();
                int isStart = item.getData().getHasBegin();
                int hasJoin = item.getData().getHasJoined();
                String isResult = item.getData().getIsResult();
                if ("没有近七天记录".equals(item.getData().getTitle())
                        | "没有其他".equals(item.getData().getTitle())) {
                    return;
                }
                if (type == 2) {
                    if (isStart == 0) {//未开始
                        //  ToastUtils.showShort("考试未到开始时间");
                    } else if ((isEnd == 1 && hasJoin == 0)) { //已结束未参加
                        //  ToastUtils.showShort("考试已结束");
                    } else if (isStart == 1 && hasJoin == 0) { //已经开始未参加
                        ToastUtils.showShort("请前往思维教育官网参加考试");
                        //   GoH5Utils.showExamTo(mContext, 0, item.getData().getExamId());
                    } else if ((isRepeat == 1 && hasJoin == 1)) { //已参加  支持多次参加
                        ToastUtils.showShort("请前往思维教育官网参加考试");
                    } else if ((isRepeat == 0 && hasJoin == 1)) { //已参加 不 支持多次参加
                        ToastUtils.showShort("请前往尚德官国际网参加考试");
                    } else if ((isEnd == 1 && hasJoin == 1)) { //已结束 已参加
                        ToastUtils.showShort("请前往思维教育官网参加考试");
                    } else {
                        ToastUtils.showShort("请前往思维教育官网参加考试");
                    }
                } else if (type == 1) {

                    ExamTransitionActivity.show(mContext, item.getData().getRid() + "", item.getData().getCourseId() + "");

                } else if (type == 3) {

                    if ((isEnd == 1 && hasJoin == 0)) {
                        //  ToastUtils.showShort("已过论文提交日期，不可提交");
                    } else if ((isEnd == 0 && hasJoin == 0)) {
                        ToastUtils.showShort("请前往思维教育官网提交论文");
                    } else {
                        ToastUtils.showShort("请前往思维教育官网提交论文");
                    }

                }
            }
        });
        mAdapter.setOnClickMonth(new LeakOtherClassAdapter.OnClickMonth() {
            @Override
            public void getView(TextView view) {
                setWheel();
            }
        });

    }


    @Override
    public String getTitleText() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_leak_other_class_home;
    }

    protected void getData() {
        if (!CommonUtils.hasNetWorkConection()) {
            mSwipeRefreshLayout.setRefreshing(false);
            ToastUtils.showShort("请检查网络");
            return;
        }
        reset(true);
        if (mTabs != null) {
            mTabs.clear();
        }
        mAdapter.setEnableLoadMore(false);
        getDataNet(true, type, 2, monthData, mNextPage);
    }

    private void loadMore() {
        reset(false);
        getDataNet(false, type, 2, monthData, mNextPage);
    }


    private void setWheel() {
        if (monthList == null || monthList.size() <= 0) {
            return;
        }
        if (monthList.get(0).contains("全部")) {
            monthList.remove(0);
        }
        if (type == 1) {
            monthList.add(0, "全部作业");
        } else if (type == 2) {
            monthList.add(0, "全部考试");
        } else if (type == 3) {
            monthList.add(0, "全部论文");
        }
        OptionsPickerView pvOptions = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                monthData = monthList.get(options1);
                if (monthData.equals("全部作业") | monthData.equals("全部考试") | monthData.equals("全部论文")) {
                    monthData = "全部";
                }
                getData();
                //  mPresenter.getLeakOtherClass(type, 2, true, monthData, Constants.DEFAULT_PAGE);
            }
        })
                .setTitleText("选择时间")
                .setTitleSize(16)
                .setContentTextSize(18)
                .build();

        pvOptions.setPicker(monthList, null, null);
        if (!pvOptions.isShowing()) {
            pvOptions.show();
        }
    }

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        super.findView(inflateView, savedInstanceState);
        view = FBIF(R.id.view);
        cl_no_homework = FBIF(R.id.cl_no_homework);
    }

    @Override
    public void initListener() {
        super.initListener();
        RxBindingHelper.setOnClickListener(view, this);
        if (type == 1) {
            CountdownTime.onTimeCountdownOverListener = new CountdownTime.OnTimeCountdownOverListener() {
                @Override
                public void onTimeCountdownOver(String id) {
                    getData();
                }
            };
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v == view) {
            if (sevenDays.size() > 0) {
                //setWheel();
            } else {
                setWheel();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            mRecyclerView.scrollToPosition(0);
        } catch (Exception X) {

        }
        getData();
    }

    @Override
    public void onSuccess(LeakOtherBean beans) {
        super.onSuccess(beans);
        List<LeakOtherBean.FutureBean> future = beans.getFuture(); //实际上近7天的
        sevenDays = new ArrayList<>();//初始化近7天的
        List<LeakOtherBean.ListBean> moreEarly = beans.getList();//其他的
        ArrayList<CommonLeakBean> list = new ArrayList<>();
        cl_no_homework.setVisibility(View.GONE);
        if (Utils.isEmpty(future) && Utils.isEmpty(moreEarly) && isRefresh == true && monthData.contains("全部")) {
            onGetLeakOtherClassSuccess(isRefresh, beans.getHasMore() == 1, convertData(null, monthData));
            return;
        }

        if (Utils.isEmpty(future) && Utils.isEmpty(moreEarly) && isRefresh == true && !monthData.contains("全部")) {
            cl_no_homework.setVisibility(View.VISIBLE);
        }

        if (!Utils.isEmpty(future)) {
            for (int i = 0; i < future.size(); i++) {
                LeakOtherBean.ListBean bean = new LeakOtherBean.ListBean();
                LeakOtherBean.FutureBean futureBean = future.get(i);
                bean.setLeft(futureBean.getLeft());
                bean.setCanRepeat(futureBean.getCanRepeat());
                bean.setTitle(futureBean.getTitle());
                bean.setCourseId(futureBean.getCourseId());
                bean.setEndTime(futureBean.getEndTime());
                bean.setEndTimeFormat(futureBean.getEndTimeFormat());
                bean.setHasBegin(futureBean.getHasBegin());
                bean.setHasEnd(futureBean.getHasEnd());
                bean.setHasJoined(futureBean.getHasJoined());
                bean.setPackageName(futureBean.getPackageName());
                bean.setRid(futureBean.getRid());
                bean.setStartTime(futureBean.getStartTime());
                bean.setTitle(futureBean.getTitle());
                bean.setTime(futureBean.getTime());
                bean.setThesis_time(futureBean.getThesis_time());
                bean.setStartTimeFormat(futureBean.getStartTimeFormat());
                if (!sevenDays.contains(bean)) {
                    sevenDays.add(bean);
                }
            }
        } else {
//            LeakOtherBean.ListBean bean = new LeakOtherBean.ListBean();
//            bean.setTitle("没有近七天记录");
//            if (!sevenDays.contains(bean) && isRefresh == true) {
//                sevenDays.add(bean);
//            }
        }

        CommonLeakBean commonLeakBean = new CommonLeakBean();
        if (isRefresh == true) {
            commonLeakBean.setList(sevenDays);
            commonLeakBean.setTab("近7天需完成");
            if (!list.contains(commonLeakBean)) {
                list.add(commonLeakBean);
            }
        }

        if (Utils.isEmpty(moreEarly) && isRefresh == true) {
            LeakOtherBean.ListBean bean = new LeakOtherBean.ListBean();
            bean.setTitle("没有其他");
            if (!moreEarly.contains(bean)) {
                moreEarly.add(bean);
            }
        }

        commonLeakBean = new CommonLeakBean();
        commonLeakBean.setList(moreEarly);
        commonLeakBean.setTab(monthData);
        if (!list.contains(commonLeakBean)) {
            list.add(commonLeakBean);
        }
        onGetLeakOtherClassSuccess(isRefresh, beans.getHasMore() == 1, convertData(list, monthData));
    }

    private void onGetLeakOtherClassSuccess(boolean isRefresh, boolean b, List<PinnedHeaderEntity<LeakOtherBean.ListBean>> leakOtherBean) {

        if (isRefresh) {
            mSwipeRefreshLayout.setRefreshing(false);
            mAdapter.setNewData(leakOtherBean);
            mAdapter.loadMoreComplete();
            mAdapter.setEnableLoadMore(true);

        } else {
            if (!Utils.isEmpty(leakOtherBean)) {
                mAdapter.addData(leakOtherBean);
            }
            mAdapter.setEnableLoadMore(true);

        }
        if (b) {
            mAdapter.loadMoreComplete();
        } else {
            mAdapter.loadMoreEnd(false);
        }


    }

    @Override
    public void onFailed(BaseModel model) {
        super.onFailed(model);
        if (isRefresh) {
            mSwipeRefreshLayout.setRefreshing(false);
            mAdapter.setEnableLoadMore(false);
        } else {
            mAdapter.loadMoreFail();
            mAdapter.setEnableLoadMore(true);
        }
    }

    public List<String> mTabs = new ArrayList<>();

    private List<PinnedHeaderEntity<LeakOtherBean.ListBean>> convertData(ArrayList<CommonLeakBean> list, String yearMonth) {

        List<PinnedHeaderEntity<LeakOtherBean.ListBean>> pinnedHeaderEntities = new ArrayList<>();
        if (Utils.isEmpty(list)) {
            return null;
        }
        for (CommonLeakBean leakBean : list) {
            String tab = leakBean.getTab();
            List<LeakOtherBean.ListBean> list1 = leakBean.getList();
            for (int i = 0; i < list1.size(); i++) {
                if (!mTabs.contains(tab)) {
                    pinnedHeaderEntities.add(new PinnedHeaderEntity<>(
                            list1.get(i), BaseHeaderAdapter.TYPE_HEADER, tab));
                }
                pinnedHeaderEntities.add(new PinnedHeaderEntity<>(
                        list1.get(i), BaseHeaderAdapter.TYPE_DATA, yearMonth));

                //0 全圆角   // 1 上圆角   //2 下圆角   //3 没有圆角
                LeakOtherBean.ListBean bean = list1.get(i);
                if (list1.size() == 1) { //只有一个
                    bean.setLayout(0);
                } else if (i == list1.size() - 1) {  //最后一个
                    bean.setLayout(2);
                } else { //中间位置
                    if (i == 0) {
                        bean.setLayout(1);
                    } else {
                        bean.setLayout(3);
                    }
                }
                mTabs.add(tab);
            }
        }
        return pinnedHeaderEntities;
    }

}

