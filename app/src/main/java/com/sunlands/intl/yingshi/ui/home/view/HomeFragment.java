package com.sunlands.intl.yingshi.ui.home.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.shangde.lepai.uilib.statusbar.StatusBarHelper;
import com.sunlands.comm_core.helper.EventBusHelper;
import com.sunlands.comm_core.helper.RxBindingHelper;
import com.sunlands.comm_core.net.BaseModel;
import com.sunlands.comm_core.utils.CommonUtils;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.bean.event.EventMessage;
import com.sunlands.intl.yingshi.common.CommonFragment;
import com.sunlands.intl.yingshi.constant.Constants;
import com.sunlands.intl.yingshi.greendao.helper.DbHelper;
import com.sunlands.intl.yingshi.helper.LoginUserInfoHelper;
import com.sunlands.intl.yingshi.ui.activity.ExamTransitionActivity;
import com.sunlands.intl.yingshi.ui.activity.home.biling.BilingDaKaActivity;
import com.sunlands.intl.yingshi.ui.activity.home.examarrangement.ExamArrangementActivity;
import com.sunlands.intl.yingshi.ui.activity.home.leakfilling.view.FillActivity;
import com.sunlands.intl.yingshi.ui.activity.home.mythesisn.MyPapersActivity;
import com.sunlands.intl.yingshi.ui.activity.home.plan.view.PlanActivity;
import com.sunlands.intl.yingshi.ui.activity.home.schoollist.view.SchoolListActivity;
import com.sunlands.intl.yingshi.ui.activity.home.smallclass.MoreSmallClassListActivity;
import com.sunlands.intl.yingshi.ui.activity.home.studystat.view.StudyStatActivity;
import com.sunlands.intl.yingshi.ui.community.view.CommunityActivity;
import com.sunlands.intl.yingshi.ui.community.view.FriendListActivity;
import com.sunlands.intl.yingshi.ui.home.adapter.HomeWorkAdapter;
import com.sunlands.intl.yingshi.ui.home.adapter.ThesisAdapter;
import com.sunlands.intl.yingshi.ui.home.adapter.TodayHotListFromHomeAdapter;
import com.sunlands.intl.yingshi.ui.home.adapter.WillLessonAdapter;
import com.sunlands.intl.yingshi.ui.home.bean.ArticleBean;
import com.sunlands.intl.yingshi.ui.home.bean.ArticleListResponse;
import com.sunlands.intl.yingshi.ui.home.bean.HomeDataResponse;
import com.sunlands.intl.yingshi.ui.home.bean.SmallClassBean;
import com.sunlands.intl.yingshi.ui.my.bean.UserInfo;
import com.sunlands.intl.yingshi.ui.widget.CustomLoadMoreView;
import com.sunlands.intl.yingshi.ui.widget.MyScrollTextView;
import com.sunlands.intl.yingshi.ui.widget.SimplePageChangeListener;
import com.sunlands.intl.yingshi.ui.widget.countdown.CountdownTime;
import com.sunlands.intl.yingshi.util.DensityUtil;
import com.sunlands.intl.yingshi.util.EnterPlayerUtils;
import com.sunlands.intl.yingshi.util.GlideUtils;
import com.sunlands.intl.yingshi.util.Utils;
import com.sunlands.intl.yingshi.web.WebViewActivity;
import com.sunlands.intl.yingshi.ui.activity.home.scorequery.ScoreQueryActivity;
import com.sunlands.intl.yingshi.ui.home.adapter.BilingAdapter;
import com.sunlands.intl.yingshi.ui.home.adapter.ClassAdapter;
import com.sunlands.intl.yingshi.ui.home.adapter.ExamAdapter;
import com.sunlands.intl.yingshi.ui.home.adapter.HomeArticleAdapter;
import com.sunlands.intl.yingshi.ui.activity.home.smallclass.SmallClassAdapter;
import com.sunlands.intl.yingshi.util.OtherUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import cc.ibooker.ztextviewlib.AutoVerticalScrollTextViewUtil;
import cn.bingoogolapple.bgabanner.BGABanner;

import static com.sunlands.intl.yingshi.bean.event.EventMessage.EVENT_CLICK_LESSON_CALENDAR;
import static com.sunlands.intl.yingshi.bean.event.EventMessage.EVENT_RECREATE;

public class HomeFragment extends CommonFragment<Object> {

    ImageView head_Img;
    ImageView imageViewMsg;
    TextView userName;
    LinearLayout userBg;
    RelativeLayout rlMessage;
    TextView messageCount;
    RelativeLayout rlUserInfoBg;

    private HomeArticleAdapter mHomeAdapter;
    //banner
    private RelativeLayout mRlIndicatorContainer;
    private BGABanner mContentBanner;
    //今日课程
    private LinearLayout mLlFunction;

    private TextView text;
    private View willLessonView;
    private View weekPlan;
    private View smallClass;
    private RecyclerView recyclerViewClass;
    private RecyclerView recyclerViewHome;
    private RecyclerView recyclerViewExam;
    private RecyclerView recyclerViewPaper;
    private TextView week;
    private TextView time;
    private TextView count1;
    private TextView count2;
    private TextView count3;
    private TextView count4;
    private WillLessonAdapter lessonAdapter;
    private SmallClassAdapter smallClassAdapter;
    private ClassAdapter classAdapter;
    private HomeWorkAdapter homeWorkAdapter;
    private ExamAdapter examAdapter;
    private ThesisAdapter thesisAdapter;
    private int mCurrentCourseId;
    private RecyclerView recyclerViewWill;
    private ImageView noneImageView;
    private LinearLayout recycleViewLl;
    private BilingAdapter bilingAdapter;
    private View dakaHeadView;
    private RecyclerView smallClassRecyclerView;
    private TodayHotListFromHomeAdapter hotListAdapter;
    private View ll_dakake;
    private View ll_hot;
    private MyScrollTextView autoVerticalScrollTextView;
    private AutoVerticalScrollTextViewUtil aUtil;
    private View ll_hot_scroll;
    private HomeDataResponse.RankBean rank;
    private TextView tv_no1;
    private TextView tv_no2;
    private TextView tv_no3;
    private List<SmallClassBean.PaginationListBean> paginationList;
    private List<SmallClassBean.PaginationListBean> paginationListTemp;
    private View tvRefresh;
    private View moreSmall;

    public HomeFragment() {

    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        super.findView(inflateView, savedInstanceState);
        head_Img = FBIF(R.id.head_img);
        imageViewMsg = FBIF(R.id.image_message);
        userName = FBIF(R.id.user_name);
        userBg = FBIF(R.id.user_bg);
        rlMessage = FBIF(R.id.rl_message);
        messageCount = FBIF(R.id.message_count);
        rlUserInfoBg = FBIF(R.id.rl_userInfo_bg);
    }

    @Override
    public void initListener() {
        super.initListener();
        RxBindingHelper.setOnClickListener(rlMessage, this);
        CountdownTime.onTimeCountdownOverListener = new CountdownTime.OnTimeCountdownOverListener() {
            @Override
            public void onTimeCountdownOver(String id) {
                getData();
            }
        };
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        startActivity(new Intent(mContext, SysMsgActivity.class));
    }

    @Override
    public void onSuccess(Object bean) {
        super.onSuccess(bean);
        setRefresh(false);
        if (bean instanceof HomeDataResponse) {
            HomeDataResponse homeDataResponse = (HomeDataResponse) bean;

            //banner
            List<HomeDataResponse.BannersBean> banners = homeDataResponse.getBanners();
            if (!Utils.isEmpty(banners)) {
                int unreadNum = homeDataResponse.getUnreadNum();
                if (unreadNum > 0) {
                   // messageCount.setVisibility(View.VISIBLE);
                    messageCount.setText(unreadNum + "");
                } else {
                    messageCount.setVisibility(View.GONE);
                }
                updateBannerView(banners);
            }

            //中心模块
            List<HomeDataResponse.FunctionPartsBean> functionParts = homeDataResponse.getFunctionParts();
            if (!Utils.isEmpty(functionParts)) {
                updateFunctionPartsView(functionParts);
            }
            //考研计算器
            rank = homeDataResponse.getRank();
            if (rank != null && rank.getList().size() >= 3) {
                tv_no1.setText(rank.getList().get(0).getName());
                tv_no2.setText(rank.getList().get(1).getName());
                tv_no3.setText(rank.getList().get(2).getName());
            }
            //课程预告
            List<HomeDataResponse.CoursesBean> courses = homeDataResponse.getCourses();
            if (recyclerViewWill != null) {
                recyclerViewWill.scrollToPosition(0);
            }
            updateTodayLessonView(courses);

            //本周计划
            HomeDataResponse.WeekPlanBean weekPlan = homeDataResponse.getWeekPlan();

            updateWeekLessonView(weekPlan);

            //大咖面对面
            List<HomeDataResponse.ListBean> list = homeDataResponse.getList();
            //今日热点
            List<HomeDataResponse.NewBean> news = homeDataResponse.getNews();

            updateBilingView(list, news);

            //资讯列表
            List<ArticleBean> newsBeans = homeDataResponse.getArticles();

            if (newsBeans == null) {
                return;
            }
            if (newsBeans.size() > 0) {
                text.setText(R.string.college_news);
            } else {
                text.setText("");
            }
            mHomeAdapter.setNewData(newsBeans);
        } else if (bean instanceof ArticleListResponse) {
            ArticleListResponse listResponse = (ArticleListResponse) bean;
            setData(isRefresh, listResponse.getHasMore() == 1, listResponse.getArticleList());
        } else if (bean instanceof SmallClassBean) {
            SmallClassBean smallClassBean = (SmallClassBean) bean;
            paginationList = smallClassBean.getPaginationList();
            paginationListTemp = new ArrayList<>(paginationList);
            smallClassAdapter.setNewData(paginationList);
            if (paginationList != null && paginationList.size() < 3) {
                //隐藏 换一批
                tvRefresh.setVisibility(View.GONE);
                moreSmall.setVisibility(View.GONE);
            } else {
                tvRefresh.setVisibility(View.VISIBLE);
                moreSmall.setVisibility(View.VISIBLE);
            }
        }
    }


    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mHomeAdapter = new HomeArticleAdapter();
        mHomeAdapter.setOnLoadMoreListener(this::loadMore, mRecyclerView);
        //  mHomeAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        mHomeAdapter.setLoadMoreView(new CustomLoadMoreView());
        mRecyclerView.setAdapter(mHomeAdapter);
        mHomeAdapter.addHeaderView(getHomeLoopView());
        mHomeAdapter.addHeaderView(getEntryView());
        //考研计算器
        if (LoginUserInfoHelper.getInstance().getUserInfo().getStuId() == 0) {
            mHomeAdapter.addHeaderView(getPostgraduateView());
            smallClass = getSmallClass();
          //  mHomeAdapter.addHeaderView(smallClass);
        }
        willLessonView = getWillLessonView();
        weekPlan = getWeekPlan();
        //新版 即将开始的课程展示
        mHomeAdapter.addHeaderView(willLessonView);
        mHomeAdapter.addHeaderView(weekPlan);
        //大咖面对面 //今日热点列表
        dakaHeadView = getDakaHeadView();
        mHomeAdapter.addHeaderView(dakaHeadView);
        //关于考研
        if (LoginUserInfoHelper.getInstance().getUserInfo().getStuId() == 0) {
            mHomeAdapter.addHeaderView(getAboutPostgraduateView());
        }
        mHomeAdapter.addHeaderView(getNewsHeadView());
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                ArticleBean newsBean = mHomeAdapter.getItem(position);
                if (newsBean != null) {
                    ArticleDetailActivity.show(mContext, newsBean.getUrl());
                }
            }
        });
    }

    //banner
    private View getHomeLoopView() {
        View view = getLayoutInflater().inflate(R.layout.layout_home_loop_view,
                (ViewGroup) mRecyclerView.getParent(), false);
        mContentBanner = view.findViewById(R.id.banner_guide_content);
        mContentBanner.hideIndicator(true);
        mRlIndicatorContainer = view.findViewById(R.id.tl_indicator_container);
        autoVerticalScrollTextView = view.findViewById(R.id.autoVerticalScrollTextView);
        ll_hot_scroll = view.findViewById(R.id.ll_hot_scroll);
        return view;
    }

    private void updateBannerView(List<HomeDataResponse.BannersBean> bannersBeans) {
        final LinearLayout linearLayout = new LinearLayout(mContext);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(DensityUtil.dip2px(mContext, 6), 0,
                DensityUtil.dip2px(mContext, 6), 0);
        if (Utils.isEmpty(bannersBeans)) return;
        ImageView imageView;
        for (int i = 0; i < bannersBeans.size(); i++) {
            imageView = new ImageView(getContext());
            imageView.setLayoutParams(lp);
            imageView.setImageResource(R.drawable.shape_rectangle_indicator);
            imageView.setEnabled(i == 0);
            linearLayout.addView(imageView);
        }
        mRlIndicatorContainer.removeAllViews();
        if (bannersBeans.size() > 1) {
            mContentBanner.setAutoPlayAble(true);
            mRlIndicatorContainer.addView(linearLayout);
        } else {
            mContentBanner.setAutoPlayAble(false);
        }
        mContentBanner.setAdapter(new BGABanner.Adapter<ImageView, HomeDataResponse.BannersBean>() {
            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, HomeDataResponse.BannersBean model, int position) {
                itemView.setScaleType(ImageView.ScaleType.FIT_XY);
                GlideUtils.loadImage(mContext, model.getImgUrl(), itemView);
            }
        });
        mContentBanner.setData(bannersBeans, null);
        mContentBanner.setDelegate(new BGABanner.Delegate<View, HomeDataResponse.BannersBean>() {
            @Override
            public void onBannerItemClick(BGABanner banner, View itemView,
                                          @Nullable HomeDataResponse.BannersBean model, int position) {
                assert model != null;
                if(TextUtils.isEmpty(model.getUrl() )) {
                    return;
                }
                ArticleDetailActivity.show(mContext, model.getUrl());
            }
        });
        mContentBanner.setOnPageChangeListener(new SimplePageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                for (int i = 0; i < linearLayout.getChildCount(); i++) {
                    linearLayout.getChildAt(i).setEnabled(i == position);
                }
            }
        });
    }

    //中心模块//消息中心 我的考勤  ..........
    private View getEntryView() {
        View view = getLayoutInflater().inflate(R.layout.layout_entry,
                (ViewGroup) mRecyclerView.getParent(), false);
        mLlFunction = view.findViewById(R.id.ll_function);
        return view;
    }

    private void updateFunctionPartsView(List<HomeDataResponse.FunctionPartsBean> functionPartsBeans) {
        mLlFunction.removeAllViews();
        for (int i = 0; i < functionPartsBeans.size(); i++) {
            HomeDataResponse.FunctionPartsBean functionPartsBean = functionPartsBeans.get(i);
            View inflate = getLayoutInflater().inflate(R.layout.layout_function_entry,
                    (ViewGroup) mRecyclerView.getParent(), false);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.weight = 1;
            inflate.setLayoutParams(layoutParams);
            GlideUtils.loadImage(mContext, functionPartsBean.getIconUrl(),
                    (ImageView) inflate.findViewById(R.id.iv_function));
            ((TextView) inflate.findViewById(R.id.tv_function)).setText(String.valueOf(
                    functionPartsBean.getName()));
            inflate.setOnClickListener(v -> onClickFunction(functionPartsBean.getLink_type(), functionPartsBean.getLink_url(), Integer.valueOf(functionPartsBean
                    .getType())));
            mLlFunction.addView(inflate);
        }
    }

    public void onClickFunction(int linkType, String url, int type) {
        if (linkType == 1) {
            WebViewActivity.goActivity(url, "");
            return;
        }
        switch (type) {
            case Constants.FunctionType.MY_MESSAGE://消息中心
                ActivityUtils.startActivity(FriendListActivity.class);
                break;
            case Constants.FunctionType.MY_EXAM_SCHEDULE: //考试安排
                ActivityUtils.startActivity(ExamArrangementActivity.class);
                break;
            case Constants.FunctionType.MY_SCORE_QUERY: //成绩查询
                ActivityUtils.startActivity(ScoreQueryActivity.class);
                break;
            case Constants.FunctionType.MY_PAPER: //我的论文
                ActivityUtils.startActivity(MyPapersActivity.class);
                break;
            case Constants.FunctionType.MY_ATTENDANCE://我的考勤
                //  ActivityUtils.startActivity(MyAttendanceActivity.class);
                break;
            case Constants.FunctionType.MY_COMMIUNITY://社区
                ActivityUtils.startActivity(CommunityActivity.class);
                break;
            case 8://学习统计
                ActivityUtils.startActivity(StudyStatActivity.class);
                break;
            case Constants.FunctionType.MY_SCHOOL_LIST://院校列表
                ActivityUtils.startActivity(SchoolListActivity.class);
                break;
            case Constants.FunctionType.MY_LEAK_LIST://查漏补缺
                ActivityUtils.startActivity(FillActivity.class);
                break;
            case 12://真题库
                ActivityUtils.startActivity(QuestionListActivity.class);
                break;
            case 13://考研常识
                ActivityUtils.startActivity(AskAnswerActivity.class);
                break;
            default:
                break;
        }
    }

    //考研计算机  Postgraduate entrance examination calculator
    private View getPostgraduateView() {
        View view = getLayoutInflater().inflate(R.layout.layout_postgraduate_calculator,
                (ViewGroup) mRecyclerView.getParent(), false);
        view.findViewById(R.id.tv_jump_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebViewActivity.goActivity(rank.getTestUrl() + "&userId=" + LoginUserInfoHelper.getInstance().getUserInfo().getId(), "");
            }
        });
        view.findViewById(R.id.tv_tiaojian1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rank != null && rank.getList().size() > 0) {
                    WebViewActivity.goActivity(rank.getList().get(0).getRequirementUrl(), "");
                }

            }
        });
        view.findViewById(R.id.tv_tiaojian2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rank != null && rank.getList().size() > 1) {
                    WebViewActivity.goActivity(rank.getList().get(1).getRequirementUrl(), "");
                }
            }
        });
        view.findViewById(R.id.tv_tiaojian3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rank != null && rank.getList().size() > 2) {
                    WebViewActivity.goActivity(rank.getList().get(2).getRequirementUrl(), "");
                }
            }
        });
        view.findViewById(R.id.tv_look_all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //查看全部院校
                WebViewActivity.goActivity(rank.getMoreUrl(), "");
            }
        });
        tv_no1 = view.findViewById(R.id.tv_no1);
        tv_no2 = view.findViewById(R.id.tv_no2);
        tv_no3 = view.findViewById(R.id.tv_no3);
        return view;
    }

    //大咖面对面//大咖课和今日热点
    private View getDakaHeadView() {

        View inflate = getLayoutInflater().inflate(R.layout.layout_dakamianduimian_head,
                (ViewGroup) mRecyclerView.getParent(), false);

        RecyclerView recyclerViewDaka = inflate.findViewById(R.id.recycler_view);
        RecyclerView recyclerViewHot = inflate.findViewById(R.id.recycler_view_hot);
        ll_dakake = inflate.findViewById(R.id.ll_dakake);
        ll_hot = inflate.findViewById(R.id.ll_hot);
        recyclerViewDaka.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerViewHot.setLayoutManager(new LinearLayoutManager(mContext));
        bilingAdapter = new BilingAdapter();
        hotListAdapter = new TodayHotListFromHomeAdapter();
        recyclerViewDaka.setAdapter(bilingAdapter);
        recyclerViewHot.setAdapter(hotListAdapter);
        inflate.findViewById(R.id.tv_see_more).setOnClickListener(v -> {
            ActivityUtils.startActivity(BilingDaKaActivity.class);
        });
        inflate.findViewById(R.id.tv_more).setOnClickListener(v -> {
            ActivityUtils.startActivity(com.sunlands.intl.yingshi.ui.home.view.TodayHotListActivity.class);
        });
        bilingAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

                HomeDataResponse.ListBean listBean = bilingAdapter.getData().get(i);

                EnterPlayerUtils.enterBilingClass(mContext, listBean.getCourse_id(), listBean.getIs_free());

            }
        });
        return inflate;
    }  //

    private void updateBilingView(List<HomeDataResponse.ListBean> list, List<HomeDataResponse.NewBean> news) {

        bilingAdapter.setNewData(list);
        hotListAdapter.setNewData(news);

        if (Utils.isEmpty(list) && Utils.isEmpty(news)) {
            dakaHeadView.setVisibility(View.GONE);
        } else {
            dakaHeadView.setVisibility(View.VISIBLE);
        }

        if (Utils.isEmpty(list)) {
            ll_dakake.setVisibility(View.GONE);
        } else {
            ll_dakake.setVisibility(View.VISIBLE);
        }
        if (Utils.isEmpty(news)) {
            ll_hot.setVisibility(View.GONE);
            ll_hot_scroll.setVisibility(View.GONE);
        } else {
            ll_hot.setVisibility(View.VISIBLE);
            ll_hot_scroll.setVisibility(View.VISIBLE);
            if (aUtil != null && aUtil.getIsRunning()) {
                return;
            }
            ArrayList arrayList = new ArrayList<>();
            for (HomeDataResponse.NewBean newBean : news) {
                arrayList.add(newBean.getTitle());
            }
            // 初始化
            aUtil = new AutoVerticalScrollTextViewUtil(autoVerticalScrollTextView, arrayList);
            aUtil.setDuration(3000).start();
            aUtil.setOnMyClickListener(new AutoVerticalScrollTextViewUtil.OnMyClickListener() {
                @Override
                public void onMyClickListener(int i, CharSequence charSequence) {
                    WebViewActivity.goActivity(news.get(i).getUrl(), "");
                }
            });
        }
    }

    //关于考研
    private View getAboutPostgraduateView() {
        View inflate = getLayoutInflater().inflate(R.layout.layout_about_postgraduate,
                (ViewGroup) mRecyclerView.getParent(), false);
        inflate.findViewById(R.id.tv_jump_weixin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OtherUtils.jumpToWeixin();
            }
        });

        return inflate;
    }

    //一周计划
    private View getWeekPlan() {
        View view = getLayoutInflater().inflate(R.layout.layout_week_plan,
                (ViewGroup) mRecyclerView.getParent(), false);
        recyclerViewClass = view.findViewById(R.id.recycler_view_class);
        recyclerViewHome = view.findViewById(R.id.recycler_view_homework);
        recyclerViewExam = view.findViewById(R.id.recycler_view_exam);
        recyclerViewPaper = view.findViewById(R.id.recycler_view_paper);
        noneImageView = view.findViewById(R.id.image_none);
        recycleViewLl = view.findViewById(R.id.recycleView_ll);
        recyclerViewClass.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerViewHome.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerViewExam.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerViewPaper.setLayoutManager(new LinearLayoutManager(mContext));
        classAdapter = new ClassAdapter();
        homeWorkAdapter = new HomeWorkAdapter();
        examAdapter = new ExamAdapter();
        thesisAdapter = new ThesisAdapter();
        recyclerViewClass.setAdapter(classAdapter);
        recyclerViewHome.setAdapter(homeWorkAdapter);
        recyclerViewExam.setAdapter(examAdapter);
        recyclerViewPaper.setAdapter(thesisAdapter);
        week = view.findViewById(R.id.week);
        time = view.findViewById(R.id.time);
        count1 = view.findViewById(R.id.count1);
        count2 = view.findViewById(R.id.count2);
        count3 = view.findViewById(R.id.count3);
        count4 = view.findViewById(R.id.count4);
        view.findViewById(R.id.more).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.startActivity(PlanActivity.class);
            }
        });
        classAdapter.setOnItemClickListener(listener);
        homeWorkAdapter.setOnItemClickListener(listener);
        examAdapter.setOnItemClickListener(listener);
        thesisAdapter.setOnItemClickListener(listener);
        Intent intent = new Intent(mContext, PlanActivity.class);

        view.findViewById(R.id.ll_class).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("currentItem", 0);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.ll_homework).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("currentItem", 1);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.ll_exam).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("currentItem", 2);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.ll_thesis).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("currentItem", 3);
                startActivity(intent);
            }
        });

        return view;
    }

    private void updateWeekLessonView(HomeDataResponse.WeekPlanBean coursesBeans) {

        if (coursesBeans == null) {
            weekPlan.setVisibility(View.GONE);
            return;
        }

        if (coursesBeans != null && TextUtils.isEmpty(coursesBeans.getWeek())) {
            weekPlan.setVisibility(View.GONE);
            return;
        } else {
            weekPlan.setVisibility(View.VISIBLE);
        }
        int courseNum = coursesBeans.getCourseNum();
        int taskNum = coursesBeans.getTaskNum();
        int examNum = coursesBeans.getExamNum();
        int thesisNum = coursesBeans.getThesisNum();
        week.setText(coursesBeans.getWeek());
        time.setText(coursesBeans.getTime());
        count1.setText(courseNum + "");
        count2.setText(taskNum + "");
        count3.setText(examNum + "");
        count4.setText(thesisNum + "");
        List<HomeDataResponse.WeekPlanBean.CourseListBean> courseList = coursesBeans.getCourseList();
        List<HomeDataResponse.WeekPlanBean.TaskListBean> taskList = coursesBeans.getTaskList();
        List<HomeDataResponse.WeekPlanBean.ExamListBean> examList = coursesBeans.getExamList();
        List<HomeDataResponse.WeekPlanBean.ThesisListBean> thesisList = coursesBeans.getThesisList();
        classAdapter.setNewData(courseList);
        homeWorkAdapter.setNewData(taskList);
        examAdapter.setNewData(examList);
        thesisAdapter.setNewData(thesisList);
        if (Utils.isEmpty(courseList) && Utils.isEmpty(taskList) &&
                Utils.isEmpty(examList) && Utils.isEmpty(thesisList)) {
            noneImageView.setVisibility(View.VISIBLE);
            recycleViewLl.setVisibility(View.GONE);
        } else {
            noneImageView.setVisibility(View.GONE);
            recycleViewLl.setVisibility(View.VISIBLE);
        }
    }

    private void updateTodayLessonView(List<HomeDataResponse.CoursesBean> coursesBeans) {
        if (Utils.isEmpty(coursesBeans)) {
            willLessonView.setVisibility(View.GONE);
        } else {
            willLessonView.setVisibility(View.VISIBLE);
            lessonAdapter.setNewData(coursesBeans);
        }
    }

    //学院公告头部
    private View getNewsHeadView() {
        View inflate = getLayoutInflater().inflate(R.layout.layout_news_head,
                (ViewGroup) mRecyclerView.getParent(), false);
        text = inflate.findViewById(R.id.school_news);
        inflate.findViewById(R.id.tv_see_more_news).setOnClickListener(v -> {
            //   ActivityUtils.startActivity(ArticlesActivity.class);
        });

        return inflate;
    }


    @Override
    public void initView(View view, Bundle savedInstanceState) {
        mSwipeRefreshLayout.setRefreshing(true);
        if (LoginUserInfoHelper.getInstance().getUserInfo() == null) {
            return;
        }
        if (TextUtils.isEmpty(LoginUserInfoHelper.getInstance().getUserInfo().getUsername())) {
            userName.setText(DbHelper.getInstance().getLoginInfoDao().queryBuilder().unique().getTel());
        } else {
            userName.setText(LoginUserInfoHelper.getInstance().getUserInfo().getUsername());
        }
        GlideUtils.loadRoundImage(mContext, LoginUserInfoHelper.getInstance().getUserInfo().getAvatar(), head_Img);
        initRecyclerView();
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            getData();
        });
        LinearLayoutManager layoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int position = layoutManager.findFirstVisibleItemPosition();
                View firstVisiableChildView = layoutManager.findViewByPosition(position);
                int itemHeight = firstVisiableChildView.getHeight();
                int i = (position) * itemHeight - firstVisiableChildView.getTop();
                if (i > 100) {
                    userBg.setBackgroundResource(0);
                    userName.setTextColor(Color.BLACK);
                    rlUserInfoBg.setBackgroundColor(Color.WHITE);
                    imageViewMsg.setImageResource(R.drawable.ic_message_light);
                    StatusBarHelper.setStatusBarLightMode((Activity) mContext);
                } else {
                    imageViewMsg.setImageResource(R.drawable.ic_message_dark);
                    userName.setTextColor(Color.WHITE);
                    StatusBarHelper.setStatusBarDarkMode((Activity) mContext);
                    userBg.setBackgroundResource(R.drawable.head_home);
                    rlUserInfoBg.setBackgroundColor(Color.TRANSPARENT);
                }
            }
        });
    }

    //小课列表
    private View getSmallClass() {
        View view = getLayoutInflater().inflate(R.layout.layout_small_class,
                (ViewGroup) mRecyclerView.getParent(), false);
        smallClassRecyclerView = view.findViewById(R.id.recycler_view_small_class);
        smallClassRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        smallClassAdapter = new SmallClassAdapter();
        smallClassRecyclerView.setAdapter(smallClassAdapter);
        moreSmall = view.findViewById(R.id.tv_look_more);
        tvRefresh = view.findViewById(R.id.tv_refresh);
        moreSmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查看更多
                startActivity(MoreSmallClassListActivity.class);
            }
        });
        tvRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //刷新
               // smallClassAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
                if (paginationList.size() > 3) {
                    paginationList.remove(0);
                    paginationList.remove(0);
                } else if (paginationList.size() == 3) {
                    paginationList.remove(0);
                } else if (paginationList.size() == 2) {
                    paginationList = new ArrayList<>(paginationListTemp);
                }
                smallClassAdapter.setNewData(paginationList);
            }
        });
        return view;
    }

    //即将开始的课程
    private View getWillLessonView() {
        View view = getLayoutInflater().inflate(R.layout.layout_will_lesson,
                (ViewGroup) mRecyclerView.getParent(), false);
        recyclerViewWill = view.findViewById(R.id.recycler_view);
        recyclerViewWill.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false));
        lessonAdapter = new WillLessonAdapter();
        recyclerViewWill.setAdapter(lessonAdapter);
        lessonAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                HomeDataResponse.CoursesBean item = (HomeDataResponse.CoursesBean) adapter.getItem(position);
//                String courseId = item.getCourseId();
//                mCurrentCourseId = Integer.parseInt(courseId);
//                EnterPlayerUtils.enterClass(mContext, mCurrentCourseId);
                //跳转到课程日历
                EventBusHelper.post(new EventMessage(EVENT_RECREATE));
                EventBusHelper.post(new EventMessage(EVENT_CLICK_LESSON_CALENDAR));
            }
        });
        return view;
    }

    BaseQuickAdapter.OnItemClickListener listener = (adapter, view, position) -> {

        Object item = adapter.getItem(position);
        if (adapter instanceof ClassAdapter) {
            HomeDataResponse.WeekPlanBean.CourseListBean bean =
                    (HomeDataResponse.WeekPlanBean.CourseListBean) item;
            String courseId = bean.getCourseId();
            mCurrentCourseId = Integer.parseInt(courseId);
            EnterPlayerUtils.enterClass(mContext, mCurrentCourseId);

        } else if (adapter instanceof HomeWorkAdapter) {

            HomeDataResponse.WeekPlanBean.TaskListBean task = (HomeDataResponse.WeekPlanBean.TaskListBean) item;
            int isEnd = task.getIsEnd();
            int isRepeat = task.getIsRepeat();
            int isStart = task.getIsStart();
            int hasJoin = task.getHasJoin();
//            GoH5Utils.showHomeWorkTo(mContext, task.getCourseId(),
//                    isStart, hasJoin,
//                    isEnd, isRepeat, task.getIsView() + "");
            ExamTransitionActivity.show(mContext, task.getExamId(), task.getCourseId());

        } else if (adapter instanceof ExamAdapter) {

            HomeDataResponse.WeekPlanBean.ExamListBean task = (HomeDataResponse.WeekPlanBean.ExamListBean) item;
            int isEnd = task.getIsEnd();
            int isRepeat = task.getIsRepeat();
            int isStart = task.getIsStart();
            int hasJoin = task.getHasJoin();
            if (isStart == 0) {//未开始
                // ToastUtils.showShort("考试未到开始时间");
            } else if ((isEnd == 1 && hasJoin == 0)) { //已结束未参加
                //   ToastUtils.showShort("考试已结束");
            } else if (isStart == 1 && hasJoin == 0) { //已经开始未参加
                ToastUtils.showShort("请前往思维教育官网参加考试");
            } else if ((isRepeat == 1 && hasJoin == 1)) { //已参加  支持多次参加
                ToastUtils.showShort("请前往思维教育官网参加考试");
            } else if ((isRepeat == 0 && hasJoin == 1)) { //已参加 不 支持多次参加
                ToastUtils.showShort("请前往思维教育官网参加考试");
            } else if ((isEnd == 1 && hasJoin == 1)) { //已结束 已参加
                ToastUtils.showShort("请前往思维教育官网参加考试");
            } else {
                ToastUtils.showShort("请前往思维教育官网参加考试");
            }
        } else if (adapter instanceof ThesisAdapter) {

            HomeDataResponse.WeekPlanBean.ThesisListBean thesisListBean = (HomeDataResponse.WeekPlanBean.ThesisListBean) item;
            int isEnd = thesisListBean.getIsEnd();
            int hasJoin = thesisListBean.getHasJoin();
            int isStart = thesisListBean.getIsStart();
            if (isStart == 0) {//未开始
                // ToastUtils.showShort("考试未到开始时间");
            } else if ((isEnd == 1 && hasJoin == 0)) {
                //  ToastUtils.showShort("已过论文提交日期，不可提交");
            } else if ((isEnd == 0 && hasJoin == 0)) {
                ToastUtils.showShort("请前往思维教育官网提交论文");
            } else if ((isEnd == 1 && hasJoin == 1)) { //已结束 已参加

            } else if ((isEnd == 0 && hasJoin == 1)) { //未结束 已参加
                ToastUtils.showShort("请前往思维教育官网提交论文");
            } else {
                ToastUtils.showShort("请前往思维教育官网提交论文");
            }
        }

    };

    @Override
    public String getTitleText() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    protected void getData() {

        if (!CommonUtils.hasNetWorkConection()) {
            mSwipeRefreshLayout.setRefreshing(false);
            ToastUtils.showShort("请检查网络设置");
            return;
        }
        reset(true);
        getDataNet(true, "");
        //小课列表请求接口
      //  getDataNet(true, "1", "100");
    }

    private void loadMore() {
        reset(false);
        getDataNet(false, mNextPage);
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
        if (aUtil != null && aUtil.getIsRunning()) {
            aUtil.start();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (aUtil != null && aUtil.getIsRunning()) {
            aUtil.stop();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(UserInfo userInfo) {
        try {
            GlideUtils.loadRoundImage(mContext, userInfo.getHeadUrl(), head_Img);
            userName.setText(TextUtils.isEmpty(userInfo.getName()) ? userInfo.getNickname() : userInfo.getName());
        } catch (Exception X) {

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMain(EventMessage eventMessage) {
        if (eventMessage.getEventType() == EventMessage.EVENT_REFRESH_HOME) {
            getData();
        }
    }

    @Override
    public void onFailed(BaseModel model) {
        super.onFailed(model);
        mSwipeRefreshLayout.setRefreshing(false);
    }


    private void setData(boolean isRefresh, boolean hasMore, List<ArticleBean> articleBeanList) {
        if (isRefresh) {
            mHomeAdapter.setNewData(articleBeanList);
            if (!hasMore) {
                mHomeAdapter.loadMoreEnd();
            }
        } else {
            if (!Utils.isEmpty(articleBeanList)) {
                mHomeAdapter.addData(articleBeanList);
            }
        }
        if (!hasMore) {
            mHomeAdapter.loadMoreEnd();
        } else {
            mHomeAdapter.loadMoreComplete();
        }
    }

    private void setRefresh(boolean refresh) {
        mSwipeRefreshLayout.setRefreshing(refresh);
    }
}
