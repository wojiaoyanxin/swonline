package com.sunlands.intl.yingshi.ui.classroom.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.blankj.utilcode.util.SpanUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.shangde.lepai.uilib.view.text.QMUITouchableSpan;
import com.sunlands.comm_core.helper.RxBindingHelper;
import com.sunlands.comm_core.net.BaseModel;
import com.sunlands.comm_core.utils.CommonUtils;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.bean.TempCourseBean;
import com.sunlands.intl.yingshi.bean.event.EventMessage;
import com.sunlands.intl.yingshi.bean.multi.ReplayLessonItem;
import com.sunlands.intl.yingshi.bean.multi.ReplaySubjectItem;
import com.sunlands.intl.yingshi.common.CommonFragment;
import com.sunlands.intl.yingshi.constant.RestApi;
import com.sunlands.intl.yingshi.helper.LoginUserInfoHelper;
import com.sunlands.intl.yingshi.mvp.contract.HandoutDownloadContract;
import com.sunlands.intl.yingshi.mvp.presenter.HandoutDownloadPresenter;
import com.sunlands.intl.yingshi.ui.activity.ExamTransitionActivity;
import com.sunlands.intl.yingshi.ui.classroom.adapter.ReplayLessonAdapter;
import com.sunlands.intl.yingshi.ui.classroom.bean.AllCourseResponse;
import com.sunlands.intl.yingshi.ui.widget.CustomLoadMoreView;
import com.sunlands.intl.yingshi.util.EnterPlayerUtils;
import com.sunlands.intl.yingshi.util.StringUtils;
import com.sunlands.intl.yingshi.util.Utils;
import com.sunlands.intl.yingshi.web.WebViewActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


/**
 * 全部课程列表
 */

public class AllLessonFragment extends CommonFragment<Object> {


    TextView classCounts;
    LinearLayout shaixuan_ll;
    View rl_search;

    private ReplayLessonAdapter mReplayLessonAdapter;
    private HandoutDownloadPresenter mHandoutDownloadPresenter;

    public AllLessonFragment() {

    }

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        super.findView(inflateView, savedInstanceState);
        classCounts = FBIF(R.id.class_counts);
        shaixuan_ll = FBIF(R.id.shaixuan_ll);
        rl_search = FBIF(R.id.rl_search);
    }

    @Override
    public void initListener() {
        super.initListener();
        RxBindingHelper.setOnClickListener(shaixuan_ll, this);
        initPresenter();
        mReplayLessonAdapter.setOnCourseClickListener(new ReplayLessonAdapter.CourseClickListener() {
            @Override
            public void onClickCourse(AllCourseResponse.ListBean.CourseListBean replayCourseBean) {
                if (mSwipeRefreshLayout.isRefreshing() == true) {
                    return;
                }
                int mCourseId = replayCourseBean.getSid();
                EnterPlayerUtils.enterClass(mContext, mCourseId);
            }

            @Override
            public void onClickDownloadHandout(AllCourseResponse.ListBean.CourseListBean replayCourseBean) {
                if (mSwipeRefreshLayout.isRefreshing() == true) {
                    return;
                }

                if (!StringUtils.isEmpty(replayCourseBean.getHandout())) {
                    requestPermission(replayCourseBean.convertToTempCourseBean());
                }
            }

            @Override
            public void onClickQuiz(AllCourseResponse.ListBean.CourseListBean item) {

                if (mSwipeRefreshLayout.isRefreshing() == true) {
                    return;
                }

                if (item.getQuiz() == 0) {
                    return;
                }

                ExamTransitionActivity.show(mContext, item.getQuiz() + "", item.getSid() + "");

            }
        });
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });
        mReplayLessonAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        }, mRecyclerView);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        onViewClicked();
    }

    @Override
    public void onSuccess(Object bean) {
        super.onSuccess(bean);
        mSwipeRefreshLayout.setRefreshing(false);
        if (bean instanceof AllCourseResponse) {

            AllCourseResponse allCourseResponse = (AllCourseResponse) bean;
            classCounts.setText("课程数量 (" + allCourseResponse.getTotal() + ")");
            mSwipeRefreshLayout.setRefreshing(false);
            List<MultiItemEntity> multiItemEntities = convertData(allCourseResponse.getList());

            if (isRefresh) {
                mSwipeRefreshLayout.setRefreshing(false);
                mReplayLessonAdapter.setNewData(multiItemEntities);
                mReplayLessonAdapter.loadMoreComplete();
            } else {
                if (!Utils.isEmpty(multiItemEntities))
                    mReplayLessonAdapter.addData(multiItemEntities);
            }
            if (allCourseResponse.getHasMore() == 0) {
                mReplayLessonAdapter.loadMoreEnd();
            } else {
                mReplayLessonAdapter.loadMoreComplete();
            }
            rl_search.setVisibility((multiItemEntities == null && type == -1) ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public void onFailed(BaseModel model) {
        super.onFailed(model);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    public static AllLessonFragment newInstance() {
        AllLessonFragment fragment = new AllLessonFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    protected void initPresenter() {

        mHandoutDownloadPresenter = new HandoutDownloadPresenter(mContext, handoutDownloadView);
    }


    HandoutDownloadContract.IHandoutDownloadView handoutDownloadView = new HandoutDownloadContract.IHandoutDownloadView() {
        @Override
        public void onHandoutDownloadSuccess() {
            showToast("下载成功，前往我的文件中查看");
        }

        @Override
        public void onProgress(int progress, long total) {

        }

        @Override
        public void onHandoutDownloadFailed(int code, String msg) {
            showToast(msg);
        }

        @Override
        public void showToast(String t) {
            ToastUtils.showShort(t);
        }
    };

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        mReplayLessonAdapter = new ReplayLessonAdapter(null);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mReplayLessonAdapter);
        mReplayLessonAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        mReplayLessonAdapter.setLoadMoreView(new CustomLoadMoreView());
        mReplayLessonAdapter.setEnableLoadMore(false);
        inflateEmptyView(mRecyclerView);
        ivEmpty.setImageResource(R.drawable.no_class);
        mReplayLessonAdapter.setEmptyView(mEmptyView);
        SpanUtils.with(mTvEmpty)
                .append("暂无课程")
                .append("\n\n")
                .append(LoginUserInfoHelper.getInstance().getUserInfo().getIsVip() == 0 ? "我要报名" : "")
                .setForegroundColor(CommonUtils.getColor(R.color.cl_4A90E2))
                .setClickSpan(new QMUITouchableSpan(CommonUtils.getColor(R.color.cl_4A90E2), CommonUtils.getColor(R.color.cl_4A90E2), 0, 0) {
                    @Override
                    public void onSpanClick(View widget) {
                        WebViewActivity.goActivity(RestApi.M_SITE,"");
                    }
                })
                .create();
    }

    private void requestPermission(TempCourseBean tempCourseBean) {
        mHandoutDownloadPresenter.handoutDownload(tempCourseBean);
    }

    @Override
    public String getTitleText() {
        return "";
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_replay_lesson;
    }

    int type = -1;

    private void loadMore() {
        reset(false);
        getDataNet(false, type, mNextPage);
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(EventMessage eventMessage) {
        if (eventMessage.getEventType() == EventMessage.EVENT_REFRESH_REPLAY_LESSON) {
            getData();
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    ArrayList<String> arrayList;

    public void onViewClicked() {

        if (arrayList == null) {
            arrayList = new ArrayList<>();
            arrayList.add("全部");
            arrayList.add("未出勤");
            arrayList.add("已出勤");
        }

        setWheel();

    }

    private void setWheel() {

        OptionsPickerView pvOptions = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                String s = arrayList.get(options1);

                if (s.equals("全部")) {
                    type = -1;
                } else if (s.equals("未出勤")) {
                    type = 0;
                } else if (s.equals("已出勤")) {
                    type = 1;
                }
                getData();
            }
        })
                .setTitleText("")
                .setTitleSize(16)
                .setContentTextSize(18)
                .build();

        pvOptions.setPicker(arrayList, null, null);
        pvOptions.show();
    }

    private List<MultiItemEntity> convertData(List<AllCourseResponse.ListBean> replayList) {
        if (Utils.isEmpty(replayList)) {
            return null;
        }
        List<MultiItemEntity> multiItemEntities = new ArrayList<>();

        for (AllCourseResponse.ListBean replayListBean : replayList) {

            ReplaySubjectItem replaySubjectItem = new ReplaySubjectItem(
                    replayListBean.getPackage_name()
                    , replayListBean.getCourse_num(),
                    replayListBean.getQuiz_num());

            List<AllCourseResponse.ListBean.CourseListBean> courseList = replayListBean.getCourse_list();

            if (!Utils.isEmpty(courseList)) {
                for (AllCourseResponse.ListBean.CourseListBean replayCourseBean : courseList) {
                    replayCourseBean.setPackage_id(replayListBean.getPackage_id());
                    replayCourseBean.setPackage_name(replayListBean.getPackage_name());
                    replaySubjectItem.addSubItem(new ReplayLessonItem(replayCourseBean));
                }
            }
            multiItemEntities.add(replaySubjectItem);
        }
        return multiItemEntities;
    }

}
