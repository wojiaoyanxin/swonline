package com.sunlands.intl.yingshi.ui.classroom.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arialyy.aria.core.Aria;
import com.arialyy.aria.core.download.DownloadEntity;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarLayout;
import com.haibin.calendarview.CalendarView;
import com.shangde.lepai.uilib.viewgroup.layoutmanager.SpaceItemDecoration;
import com.sunlands.comm_core.net.BaseModel;
import com.sunlands.comm_core.utils.CommonUtils;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.bean.DownLoadInfo;
import com.sunlands.intl.yingshi.bean.TempCourseBean;
import com.sunlands.intl.yingshi.bean.event.EventMessage;
import com.sunlands.intl.yingshi.common.CommonFragment;
import com.sunlands.intl.yingshi.dialog.BottomDownLoadDialog;
import com.sunlands.intl.yingshi.dialog.BottomStudyContentDialog;
import com.sunlands.intl.yingshi.greendao.db.MyDownLoadInfo;
import com.sunlands.intl.yingshi.helper.LoginUserInfoHelper;
import com.sunlands.intl.yingshi.mvp.contract.HandoutDownloadContract;
import com.sunlands.intl.yingshi.mvp.presenter.HandoutDownloadPresenter;
import com.sunlands.intl.yingshi.ui.activity.ExamTransitionActivity;
import com.sunlands.intl.yingshi.ui.classroom.adapter.LessonCalendarAdapter;
import com.sunlands.intl.yingshi.ui.classroom.bean.CourseDateListResponse;
import com.sunlands.intl.yingshi.ui.classroom.bean.LessonCalendarResponse;
import com.sunlands.intl.yingshi.ui.widget.calendar.MyCalendarView;
import com.sunlands.intl.yingshi.util.DLog;
import com.sunlands.intl.yingshi.util.DensityUtil;
import com.sunlands.intl.yingshi.util.EnterPlayerUtils;
import com.sunlands.intl.yingshi.util.GetJsonDataUtil;
import com.sunlands.intl.yingshi.util.StringFormatUtils;
import com.sunlands.intl.yingshi.util.Utils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 课程日历
 */
public class LessonCalendarFragment extends CommonFragment<Object> {


    TextView mTvDateMonth;
    MyCalendarView mCalendarView;
    CalendarLayout mCalendarLayout;
    ImageView mIvPreMonth;
    TextView mTvSeeToday;
    ImageView mIvNextMonth;

    int first = 0;
    private TextView mTvDate;
    private LessonCalendarAdapter mLessonCalendarAdapter;
    private TextView mTvNoMoreData;
    private HandoutDownloadPresenter mHandoutDownloadPresenter;


    public int mCourseId;
    private Map<String, Calendar> map;


    public LessonCalendarFragment() {

    }

    public static LessonCalendarFragment newInstance() {
        LessonCalendarFragment fragment = new LessonCalendarFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    protected void initPresenter() {
        mHandoutDownloadPresenter = new HandoutDownloadPresenter(mContext, handoutDownloadView);
    }


    protected void markAbsenceDate(List<String> dateList) {
        if (Utils.isEmpty(dateList)) {
            mCalendarView.update(false);
            mCalendarView.setSchemeDate(new HashMap<>());
            return;
        }
        map = new HashMap<>();
        try {
            for (String s : dateList) {
                DLog.d(s);
                String[] split = s.split("-");
                Integer year = Integer.valueOf(split[0]);
                Integer month = Integer.valueOf(split[1]);
                Integer day = Integer.valueOf(split[2]);
                map.put(getSchemeCalendar(year, month, day, 0xFFFF0000, "1").toString(),
                        getSchemeCalendar(year, month, day, 0xFFFF0000, "1"));
            }
        } catch (Exception e) {
            DLog.e(e.getMessage());
            e.printStackTrace();
        }
        mCalendarView.setSchemeDate(map);
        String currentDay = mCalendarView.getCurYear() + "-" +
                (mCalendarView.getCurMonth() < 10 ? "0" + mCalendarView.getCurMonth() : mCalendarView.getCurMonth()) + "-" +
                (mCalendarView.getCurDay() < 10 ? "0" + mCalendarView.getCurDay() : mCalendarView.getCurDay());

        if (dateList.contains(currentDay)) {
            mCalendarView.update(true);
        } else {
            mCalendarView.update(false);
        }
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
    public void onSuccess(Object bean) {
        super.onSuccess(bean);

        if (bean instanceof CourseDateListResponse) {
            CourseDateListResponse courseDateListResponse = (CourseDateListResponse) bean;
            markAbsenceDate(courseDateListResponse.getCourselist());
        } else if (bean instanceof LessonCalendarResponse) {
            LessonCalendarResponse lessonCalendarResponse = (LessonCalendarResponse) bean;
            mSwipeRefreshLayout.setRefreshing(false);
            List<LessonCalendarResponse.LessonCalendarBean> courseList = lessonCalendarResponse.getCourseList();
            showEmptyView(Utils.isEmpty(courseList));
            mLessonCalendarAdapter.setNewData(courseList);
        }
    }

    @Override
    public void onFailed(BaseModel model) {
        super.onFailed(model);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    private void showEmptyView(boolean show) {
        mTvNoMoreData.setVisibility(show ? View.VISIBLE : View.GONE);
        mTvDate.setVisibility(show ? View.GONE : View.VISIBLE);
    }

    private Calendar getSchemeCalendar(int year, int month, int day, int color, String text) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setSchemeColor(color);//如果单独标记颜色、则会使用这个颜色
        calendar.setScheme(text);
        return calendar;
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(
                DensityUtil.dip2px(mContext, 7),
                DensityUtil.dip2px(mContext, 0),
                false));
        mLessonCalendarAdapter = new LessonCalendarAdapter(null);
        mLessonCalendarAdapter.openLoadAnimation();
        mLessonCalendarAdapter.addFooterView(footView(mRecyclerView, R.layout.view_load_more_end));
        mLessonCalendarAdapter.addHeaderView(getHeadView());
        mRecyclerView.setAdapter(mLessonCalendarAdapter);
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        initPresenter();
        initRecyclerView();
    }

    @Override
    public void initListener() {
        super.initListener();
        mIvPreMonth.setOnClickListener(onClickListener);
        mTvSeeToday.setOnClickListener(onClickListener);
        mIvNextMonth.setOnClickListener(onClickListener);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // mTvDate.setText(R.string.today_lesson);
                getData();
            }
        });
        mCalendarView.setOnDateSelectedListener(onDateSelectedListener);
        mCalendarView.setOnMonthChangeListener(onMonthChangeListener);
        mLessonCalendarAdapter.setOnItemChildClickListener(onItemChildClickListener);
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                LessonCalendarResponse.LessonCalendarBean item = mLessonCalendarAdapter.getItem(position);
                mCourseId = Integer.parseInt(item.getCourseId());
                EnterPlayerUtils.enterClass(mContext, Integer.parseInt(item.getCourseId()));
            }
        });
    }

    CalendarView.OnDateSelectedListener onDateSelectedListener = new CalendarView.OnDateSelectedListener() {
        @Override
        public void onDateSelected(Calendar calendar, boolean isClick) {
            DLog.d("onDateSelected: " + calendar.getYear() + " " + calendar.getMonth() + " " +
                    calendar.getDay());
            mTvDateMonth.setText(StringFormatUtils.getFormatDate(calendar.getYear(),
                    calendar.getMonth()));
            if (first == 1) {
                mTvDate.setText(StringFormatUtils.getFormatDate(calendar.getYear(), calendar.getMonth(),
                        calendar.getDay()));
            }
            first = 1;
            if (calendar.isCurrentDay()) {
                mTvDate.setText(R.string.today_lesson);
            }
            getDataNet(true, StringFormatUtils.getFormatDateWithLine(
                    calendar.getYear(), calendar.getMonth(), calendar.getDay()));
        }
    };

    CalendarView.OnMonthChangeListener onMonthChangeListener = new CalendarView.OnMonthChangeListener() {
        @Override
        public void onMonthChange(int year, int month) {
            mTvDateMonth.setText(StringFormatUtils.getFormatDate(year, month));
        }
    };

    public ArrayList<DownLoadInfo> parseData(String result) {//Gson 解析

        ArrayList<DownLoadInfo> detail = new ArrayList<>();

        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                DownLoadInfo entity = gson.fromJson(data.optJSONObject(i).toString(), DownLoadInfo.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }


    BaseQuickAdapter.OnItemChildClickListener onItemChildClickListener = new BaseQuickAdapter.OnItemChildClickListener() {
        @Override
        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            LessonCalendarResponse.LessonCalendarBean item = mLessonCalendarAdapter.getItem(position);
            assert item != null;
            int id = view.getId();
            if (id == R.id.ll_handout_download) {
                List<MyDownLoadInfo> mData = new ArrayList<>();
                mData.clear();
                String JsonData = new GetJsonDataUtil()
                        .getJson(mContext, "downLoadInfo.json");//获取assets目录下的json文件数据

                if (position == 1) {
                    JsonData = new GetJsonDataUtil()
                            .getJson(mContext, "downLoadInfo2.json");//获取assets目录下的json文件数据
                }

                ArrayList<DownLoadInfo> jsonBean = parseData(JsonData);//用Gson 转成实体
                for (DownLoadInfo downLoadInfo : jsonBean) {
                    DownloadEntity downloadEntity = Aria.download(this).getDownloadEntity(downLoadInfo.getUrl());
                    MyDownLoadInfo myDownLoadInfo = new MyDownLoadInfo();
                    myDownLoadInfo.setUserId(LoginUserInfoHelper.getInstance().getUserInfo().getUserId());
                    myDownLoadInfo.setCourseId(downLoadInfo.getCourseId());
                    myDownLoadInfo.setCourseName(downLoadInfo.getCourseName());
                    myDownLoadInfo.setProduceId(downLoadInfo.getProduceId());
                    myDownLoadInfo.setProductName(downLoadInfo.getProductName());
                    myDownLoadInfo.setSchoolName(downLoadInfo.getSchoolName());
                    myDownLoadInfo.setSchoolId(downLoadInfo.getSchoolId());
                    myDownLoadInfo.setFileType(downLoadInfo.getType());
                    myDownLoadInfo.setDownloadEntity(downloadEntity);
                    myDownLoadInfo.setCourseId(downLoadInfo.getCourseId());
                    myDownLoadInfo.setFileId(downLoadInfo.getFileId());

                    if (downloadEntity == null) {
                        downloadEntity = new DownloadEntity();
                        downloadEntity.setFileName(downLoadInfo.getFileName());
                        downloadEntity.setUrl(downLoadInfo.getUrl());
                        myDownLoadInfo.setDownloadEntity(downloadEntity);
                    }
                    mData.add(myDownLoadInfo);
                    myDownLoadInfo = null;
                }
                BottomDownLoadDialog.getInstance(mData).show(getFragmentManager(), null);
                if (item.getHasHandout() == 0) {
                    //   ToastUtils.showToast(mStringNoHandout);
                } else {
                    // requestPermission(item.convertToTempCourseBean());
                    //底部弹出对话框
                }
            } else if (id == R.id.ll_quiz) {
                BottomStudyContentDialog.getInstance(null).show(getFragmentManager(), null);

                if (TextUtils.isEmpty(item.getQuiz()) | "0".equals(item.getQuiz())) { //暂无作业
                    Aria.download(this).removeAllTask(true);
                    return;
                }
                ExamTransitionActivity.show(mContext, item.getQuiz() + "", item.getCourseId());
            }
        }
    };

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.iv_pre_month) {
                mCalendarView.scrollToPre(true);
            } else if (id == R.id.tv_see_today) {
                mCalendarView.scrollToCurrent(true);
                mTvDate.setText(R.string.today_lesson);
            } else if (id == R.id.iv_next_month) {
                mCalendarView.scrollToNext(true);
            }
        }
    };

    private void requestPermission(TempCourseBean tempCourseBean) {
        mHandoutDownloadPresenter.handoutDownload(tempCourseBean);
    }

    private View getHeadView() {
        View view = getLayoutInflater().inflate(R.layout.head_lesson_calendar,
                (ViewGroup) mRecyclerView.getParent(), false);
        mTvDateMonth = view.findViewById(R.id.tv_date_month);
        mTvDate = view.findViewById(R.id.tv_date);
        mIvPreMonth = view.findViewById(R.id.iv_pre_month);
        mTvSeeToday = view.findViewById(R.id.tv_see_today);
        mIvNextMonth = view.findViewById(R.id.iv_next_month);
        mCalendarView = view.findViewById(R.id.calendarView);
        mCalendarLayout = view.findViewById(R.id.calendarLayout);
        mTvNoMoreData = view.findViewById(R.id.tv_no_more_data);
        return view;
    }

    @Override
    public String getTitleText() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_lesson_calendar;
    }


    protected void getData() {
        if (!CommonUtils.hasNetWorkConection()) {
            mSwipeRefreshLayout.setRefreshing(false);
            ToastUtils.showShort("请检查网络设置");
            return;
        }
        Calendar calendar = mCalendarView.getSelectedCalendar();
        String date = StringFormatUtils.getFormatDateWithLine(calendar.getYear(),
                calendar.getMonth(), calendar.getDay());
        getDataNet(true, date);
        getDataNet(false, "");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(EventMessage eventMessage) {
        DLog.d("refreshEvent.getType(): " + eventMessage.getEventType());
        if (eventMessage.getEventType() == EventMessage.EVENT_CLICK_LESSON_CALENDAR) {
            refresh();
        }
    }

    public void refresh() {
        String date = StringFormatUtils.getFormatDateWithLine(mCalendarView.getCurYear(),
                mCalendarView.getCurMonth(), mCalendarView.getCurDay());
        getDataNet(true, date);
        getDataNet(false, "");
        //查看今天
        mCalendarView.scrollToCurrent(true);
        mTvDate.setText(R.string.today_lesson);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isOnResume == true) {
            getData();
        }
        isOnResume = true;
    }

    boolean isOnResume;

    @Override
    public void initDataAfterView() {
        super.initDataAfterView();
        refresh();
    }
}
