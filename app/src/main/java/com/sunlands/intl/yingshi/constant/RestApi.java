package com.sunlands.intl.yingshi.constant;

import com.sunlands.comm_core.net.BaseModel;
import com.sunlands.intl.yingshi.bean.AppUpdataBean;
import com.sunlands.intl.yingshi.bean.ChannelListBean;
import com.sunlands.intl.yingshi.bean.CommThreadBean;
import com.sunlands.intl.yingshi.bean.CourseEnterResponse;
import com.sunlands.intl.yingshi.bean.EmptyBean;
import com.sunlands.intl.yingshi.bean.ExamInfoBean;
import com.sunlands.intl.yingshi.bean.FileBean;
import com.sunlands.intl.yingshi.bean.FriendGroupBean;
import com.sunlands.intl.yingshi.bean.FriendInfoBean;
import com.sunlands.intl.yingshi.bean.IMBean;
import com.sunlands.intl.yingshi.bean.InfoStatusBean;
import com.sunlands.intl.yingshi.bean.LogBean;
import com.sunlands.intl.yingshi.bean.MainlBean;
import com.sunlands.intl.yingshi.bean.MsgListBean;
import com.sunlands.intl.yingshi.bean.MyCollectBean;
import com.sunlands.intl.yingshi.bean.MyOrderBean;
import com.sunlands.intl.yingshi.bean.PackageListBean;
import com.sunlands.intl.yingshi.bean.PaginationBean;
import com.sunlands.intl.yingshi.bean.PostSubmitBean;
import com.sunlands.intl.yingshi.bean.StudyStatBean;
import com.sunlands.intl.yingshi.bean.SysMsgBean;
import com.sunlands.intl.yingshi.bean.TestBean;
import com.sunlands.intl.yingshi.bean.ThreadInfoBean;
import com.sunlands.intl.yingshi.bean.UploadBean;
import com.sunlands.intl.yingshi.greendao.db.LoginInfo;
import com.sunlands.intl.yingshi.ui.activity.home.apply.UniversityListResponse;
import com.sunlands.intl.yingshi.ui.activity.home.biling.ApplyBackBean;
import com.sunlands.intl.yingshi.ui.activity.home.biling.BilingBean;
import com.sunlands.intl.yingshi.ui.activity.home.examarrangement.ExamArrangementsResponse;
import com.sunlands.intl.yingshi.ui.activity.home.leakfilling.bean.LeakClassBean;
import com.sunlands.intl.yingshi.ui.activity.home.leakfilling.bean.LeakOtherBean;
import com.sunlands.intl.yingshi.ui.activity.home.mythesisn.PapersResponse;
import com.sunlands.intl.yingshi.ui.activity.home.plan.bean.PlanBean;
import com.sunlands.intl.yingshi.ui.activity.home.schoollist.bean.SchoolBean;
import com.sunlands.intl.yingshi.ui.activity.home.scorequery.ScoreResponse;
import com.sunlands.intl.yingshi.ui.classroom.bean.AllCourseResponse;
import com.sunlands.intl.yingshi.ui.classroom.bean.CourseDateListResponse;
import com.sunlands.intl.yingshi.ui.classroom.bean.LessonCalendarResponse;
import com.sunlands.intl.yingshi.ui.home.bean.ArticleListResponse;
import com.sunlands.intl.yingshi.ui.home.bean.AskAnswerBean;
import com.sunlands.intl.yingshi.ui.home.bean.AskAnswerDetailsBean;
import com.sunlands.intl.yingshi.ui.home.bean.HomeDataResponse;
import com.sunlands.intl.yingshi.ui.home.bean.QuestionBean;
import com.sunlands.intl.yingshi.ui.home.bean.SmallClassBean;
import com.sunlands.intl.yingshi.ui.home.bean.SmallClassDetailsBean;
import com.sunlands.intl.yingshi.ui.home.bean.SmallClassOrderBean;
import com.sunlands.intl.yingshi.ui.home.bean.SmallClassOrderDetailsBean;
import com.sunlands.intl.yingshi.ui.home.bean.SmallClassUnitsBean;
import com.sunlands.intl.yingshi.ui.home.bean.SmallClassWatchBean;
import com.sunlands.intl.yingshi.ui.home.bean.TodayHotListBean;
import com.sunlands.intl.yingshi.ui.my.bean.ApplyResponse;
import com.sunlands.intl.yingshi.ui.my.bean.HistoryMoreResponse;
import com.sunlands.intl.yingshi.ui.my.bean.HistoryResponse;
import com.sunlands.intl.yingshi.ui.my.bean.PrivateBean;
import com.sunlands.intl.yingshi.ui.my.bean.UploadAvatarResponse;
import com.sunlands.intl.yingshi.ui.my.bean.UserInfo;
import com.tencent.liteav.demo.play.bean.MaterialBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * 当前包名: com.sunlands.intl.yingshi.constant
 * 创 建 人: xueh
 * 创建日期: 2019/2/19 11:58
 * 备注：
 */
public interface RestApi {


    String M_SITE = "https://smallprogram.sunlands.com/fe-activities/180927/index.html";
    String PRIVACY = "http://www.yingteach.com/static/privacy.html";
    String PROTOCAL = "http://www.yingteach.com/static/protocal.html";

    @GET(ResConstans.test)
    Observable<BaseModel<TestBean>> test(
    );

    @FormUrlEncoded
    @POST(ResConstans.VERIFY_CODE)
    Observable<BaseModel<EmptyBean>> getVerifyCode(@Field("tel") String phoneNumber, @Field("type") String type);

    @FormUrlEncoded
    @POST(ResConstans.SMS_VOICE)
    Observable<BaseModel<EmptyBean>> getVoiceCode(@Field("tel") String phoneNumber);

    //消息中心列表
    @GET(ResConstans.MESSAGE_LIST)
    Observable<BaseModel<MsgListBean>> getMsgList();

    //首页新闻列表
    @GET(ResConstans.ACTION_ARTICLE_LIST)
    Observable<BaseModel<ArticleListResponse>> getArticleList(@Query(Api.KEY_PAGE) String s);

    //首页小课
    @GET(ResConstans.SMALL_CLASS_LIST)
    Observable<BaseModel<SmallClassBean>> getSmallClassList(@Query(Api.KEY_PAGE) String s, @Query(Api.KEY_PAGESIZE) String s1);

    //首页小课
    @GET(ResConstans.SMALL_CLASS_LIST)
    Observable<BaseModel<SmallClassBean>> getSmallClassList(@Query(Api.KEY_PAGE) String s, @Query(Api.KEY_PAGESIZE) String s1, @Query("catId") String s2);

    //考试安排列表
    @GET(ResConstans.ACTION_EXAM_LIST)
    Observable<BaseModel<ExamArrangementsResponse>> getExamArrangements(@Query(Api.KEY_PAGE) String s, @Query(Api.KEY_TYPE) String valueOf);

    //一周计划

    @GET(ResConstans.ACTION_WEEL_PLAN)
    Observable<BaseModel<PlanBean>> getPlanData(@Query(Api.KEY_TYPE) String valueOf);

    //课程日历

    @GET(ResConstans.ACTION_LESSON_CALENDAR_DAY)
    Observable<BaseModel<LessonCalendarResponse>> getLessonCalendar(@Query(Api.KEY_DATE) String of, @Query(Api.KEY_TYPE) String valueOf);


    //论文列表
    @GET(ResConstans.ACTION_THESIS_LIST)
    Observable<BaseModel<PapersResponse>> getPagers();

    //成绩查询
    @GET(ResConstans.ACTION_SUBJECT_SCORE)
    Observable<BaseModel<ScoreResponse>> scoreQuery(@Query(Api.KEY_PAGE) String s, @Query("packageId") String s1);

    //首页数据
    @GET(ResConstans.ACTION_HOME)
    Observable<BaseModel<HomeDataResponse>> getHomeData();


    //上传经纬度
    @GET(ResConstans.ACTION_LOCATION)
    Observable<BaseModel<String>> putLocation(@Query("lat") String s, @Query("lon") String s1);

    //系统消息
    @GET(ResConstans.MESSAGE_PAGINATION)
    Observable<BaseModel<SysMsgBean>> getSysMsgList(
            @Query("page") int page
    );

    //今日热点列表
    @GET(ResConstans.TODAY_HOT_LIST)
    Observable<BaseModel<TodayHotListBean>> getTodayHotList(
            @Query("page") String start
    );

    //真题列表
    @GET(ResConstans.QUESTION_LIST)
    Observable<BaseModel<QuestionBean>> getQuestionList(
            @Query("page") String start
    );

    //考研常识
    @GET(ResConstans.ASK_ANSWER)
    Observable<BaseModel<AskAnswerBean>> getAskAnswerList(
            @Query("page") String start
    );

    //考研常识详情
    @GET(ResConstans.ASK_ANSWER_DETAILS)
    Observable<BaseModel<AskAnswerDetailsBean>>  getAskAnswerDetails(
            @Query("answerId") String id
    );


    @GET(ResConstans.SMALL_COURED_ORDER)
        //小课下单
    Observable<BaseModel<SmallClassOrderBean>> orderSmallClass(
            @Query("id") String id
    );
    @GET(ResConstans.SMALL_COURED_ORDER_DETAILS)
    //小课订单详情
    Observable<BaseModel<SmallClassOrderDetailsBean>> orderSmallDetailsClass(
            @Query("orderNo") String orderNo
    );


    @GET(ResConstans.SMALL_COURED_UNITS)
        //小课单元
    Observable<BaseModel<SmallClassUnitsBean>> orderSmallClassUnits(
            @Query("courseId") String id
    );

    @GET(ResConstans.SMALL_COURED_INFO)
    Observable<BaseModel<SmallClassDetailsBean>> getSmallClassDetails(
            @Query("id") String id
    );

    //个人信息
    @GET(ResConstans.ACTION_USER_INFO)
    Observable<BaseModel<UserInfo>> getUserInfo();

    //个人主页
    @GET(ResConstans.ACTION_USER_HOME)
    Observable<BaseModel<UserInfo>> getUserInfo2(@Query("viewId") String viewId);

    //历史记录
    @GET(ResConstans.ACTION_HISTORY)
    Observable<BaseModel<HistoryResponse>> getHistory();

    //更多历史记录
    @GET(ResConstans.ACTION_HISTORY_MORE)
    Observable<BaseModel<HistoryMoreResponse>> getMoreHistory(@Query(Api.KEY_PAGE) String page);

    //退出登录
    @GET(ResConstans.ACTION_LOGOUT)
    Observable<BaseModel<EmptyBean>> logout();

    @FormUrlEncoded
    @POST(ResConstans.ACTION_FIRST_LOAD)
    Observable<BaseModel<String>> putTime(@Field(Api.KEY_UNIQUEID) String id, @Field(Api.KEY_CHANNEL) String channel, @Field(Api.KEY_LOCAL_TIME) String time);

    //个人隐私设置
    @FormUrlEncoded
    @POST(ResConstans.ACTION_USER_PRIVATE_SET)
    Observable<BaseModel<String>> getGetSinglePrivate(@Field("privacy") String privacy);

    //修改个人信息
    @FormUrlEncoded
    @POST(ResConstans.ACTION_USER_INFO_MODIFY)
    Observable<BaseModel<EmptyBean>> updateUserInfo(@Field(Api.KEY_HEAD_URL) String headUrl,
                                                    @Field("industry") String industry,
                                                    @Field("company") String company,
                                                    @Field("position") String position,
                                                    @Field("sex") String s,
                                                    @Field(Api.KEY_CITY) String city);

    //个人隐私查询
    @GET(ResConstans.ACTION_USER_PRIVATE)
    Observable<BaseModel<PrivateBean>> getGetAllPrivate();

    //我的申请
    @GET(ResConstans.ACTION_MY_APPLY)
    Observable<BaseModel<ApplyResponse>> getMyApply();

    //系统消息
    @GET(ResConstans.FRIEND_INFO)
    Observable<BaseModel<FriendInfoBean>> getFriendInfo(
            @Query("user_id") String user_id
    );

    //站内信
    @GET(ResConstans.THREAD_PAGINATION)
    Observable<BaseModel<MainlBean>> getMailListInfo(
            @Query("limit") int limit,
            @Query("tab") String tab,
            @Query("channelId") int channelId,
            @Query("keyword") String keyword
    );

    //我的回复
    @FormUrlEncoded
    @POST(ResConstans.POST_SUBMIT)
    Observable<BaseModel<PostSubmitBean>> postSubmit(
            @Field("threadId") int threadId,
            @Field("postId") int postId,
            @Field("rePostId") int rePostId,
            @Field("content") String content

    );

    //进入帖子详情获取所有评论
    @GET(ResConstans.POST_PAGINATION)
    Observable<BaseModel<PaginationBean>> getPagination(
            @Query("start") int start,
            @Query("limit") int limit,
            @Query("postId") int postId,
            @Query("threadId") int threadId
    );

    //新建帖子
    @FormUrlEncoded
    @POST(ResConstans.THREAD_SUBMIT)
    Observable<BaseModel<CommThreadBean>> threadSubmit(
            @Field("content") String content,
            @Field("title") String title,
            @Field("toGroupId") String toGroupId,
            @Field("channelId") String channelId,
            @Field("image[]") List<String> image
    );


    //手机号登录
    @FormUrlEncoded
    @POST(ResConstans.LOGIN_LOGIN)
    Observable<BaseModel<LoginInfo>> phoneLogin(
            @Field("type") String type,
            @Field("tel") String tel,
            @Field("authCode") String authCode,
            @Field("plat") int plat,
            @Field(Api.KEY_UNIQUEID) String deviceId//deviceId//login/login
    );

    //手机号登录
    @FormUrlEncoded
    @POST(ResConstans.LOGIN_LOGIN)
    Observable<BaseModel<LoginInfo>> login(
            @Field("type") String type,
            @Field("tel") String tel,
            @Field("pwd") String pwd,
            @Field("unionId") String unionId,
            @Field("plat") int plat,
            @Field(Api.KEY_UNIQUEID) String deviceId
    );

    //重置密码
    @FormUrlEncoded
    @POST(ResConstans.USER_RESET)
    Observable<BaseModel<EmptyBean>> resetPwd(
            @Field("tel") String tel,
            @Field("authCode") String authCode,
            @Field("pwd") String newPwd
    );

    //密码注册
    @FormUrlEncoded
    @POST(ResConstans.USER_REGIST)
    Observable<BaseModel<LoginInfo>> pwdRegist(
            @Field("type") String type,
            @Field("tel") String tel,
            @Field("authCode") String authCode,
            @Field("pwd") String pwd,
            @Field(Api.KEY_UNIQUEID) String deviceId
    );

    //密码注册
    @FormUrlEncoded
    @POST(ResConstans.USER_REGIST)
    Observable<BaseModel<LoginInfo>> bindPhone(
            @Field("type") String type,
            @Field("tel") String tel,
            @Field("authCode") String authCode,
            @Field("unionId") String unionId,
            @Field("gender") String gender,
            @Field("iconurl") String iconurl,
            @Field("plat") int plat,
            @Field(Api.KEY_UNIQUEID) String deviceId
    );

    //学习记录接口

    @GET(ResConstans.RECORDS_STAT)
    Observable<BaseModel<StudyStatBean>> getRecordsStat(
            @Query("type") String type
    );

    //清空学习记录
    @GET(ResConstans.MESSAGE_EMPTY)
    Observable<BaseModel<String>> messageEmpty(
    );

    //帖子点赞
    @GET(ResConstans.THREAD_LIKED)
    Observable<BaseModel<EmptyBean>> threadLiked(
            @Query("threadId") int threadId
    );

    //帖子点赞
    @GET(ResConstans.UN_LIKED)
    Observable<BaseModel<EmptyBean>> unLiked(
            @Query("threadId") int threadId
    );

    //帖子收藏
    @GET(ResConstans.COLLECT)
    Observable<BaseModel<EmptyBean>> collect(
            @Query("threadId") int threadId
    );

    //帖子取消收藏
    @GET(ResConstans.UN_COLLECT)
    Observable<BaseModel<EmptyBean>> un_Collect(
            @Query("threadId") int threadId
    );

    //举报提交
    @GET(ResConstans.REPORT_SUBMIT)
    Observable<BaseModel<List<EmptyBean>>> report_Submit(
            @Query("threadId") int threadId
    );

    //贴子删除
    @GET(ResConstans.DELETE)
    Observable<BaseModel<EmptyBean>> delete(
            @Query("threadId") int threadId
    );

    //上传图片
    @Multipart
    @POST(ResConstans.UPLOAD_FILES)
    Observable<BaseModel<UploadBean>> upload_Files(
            @Part MultipartBody.Part file
    );

    //

    //上传头像图片
    @Multipart
    @POST(ResConstans.ACTION_AVATAR)
    Observable<BaseModel<UploadAvatarResponse>> upload_headFiles(
            @Part MultipartBody.Part file
    );

    //频道列表
    @GET(ResConstans.CHANNEL_LIST)
    Observable<BaseModel<ChannelListBean>> channel_List(
    );


    //分组列表
    @GET(ResConstans.FRIEND_GROUP)
    Observable<BaseModel<FriendGroupBean>> friend_Group(
    );


    //分组列表
    @GET(ResConstans.THREAD_INFO)
    Observable<BaseModel<ThreadInfoBean>> thread_Info(
            @Query("threadId") int threadId
    );

    ////我的帖子
    @GET(ResConstans.MINE_THREAD)
    Observable<BaseModel<MyCollectBean>> mine_Thread(
            @Query("start") int start,
            @Query("limit") int limit,
            @Query("viewId") String viewId
    );

    ////我的收藏
    @GET(ResConstans.MINE_COLLECT)
    Observable<BaseModel<MyCollectBean>> mine_Collect(
            @Query("start") int start,
            @Query("limit") int limit
    );

    ////我的订单
    @GET(ResConstans.ORDER_LIST)
    Observable<BaseModel<MyOrderBean>> order_List(
            @Query("count") int count
    );

    ////用户信息是否完善
    @GET(ResConstans.USER_INFO_STATUS)
    Observable<BaseModel<InfoStatusBean>> getUser_Status(
    );

    ////app更新
    @GET(ResConstans.APP_VERSION)
    Observable<BaseModel<AppUpdataBean>> getApp_Version(
    );

    //有课程的日期
    @GET(ResConstans.ACTION_COURSE_CALENDAR)
    Observable<BaseModel<CourseDateListResponse>> getCourseDateList();

    //有课程的日期
    @GET(ResConstans.ACTION_REPLAY_LESSON)
    Observable<BaseModel<AllCourseResponse>> getReplayLessons(@Query("hasJoin") String hasJoin,
                                                              @Query(Api.KEY_PAGE) int page,
                                                              @Query("count") int count);

    //课程待办事项/查漏补缺列表接口
    @GET(ResConstans.ACTION_LEAK_CLASS_LIST)
    Observable<BaseModel<LeakClassBean>> getLeakClass(@Query(Api.KEY_TYPE) String valueOf,
                                                      @Query(Api.KEY_PAGE) String s,
                                                      @Query(Api.KEY_YEAR_MONTH) String yearMonth);

    //考试待办事项/查漏补缺/学习计划接口
    @GET(ResConstans.ACTION_LEAK_OTHER_CLASS_LIST)
    Observable<BaseModel<LeakOtherBean>> getLeakOtherClass1(@Query(Api.KEY_TYPE) String s,
                                                            @Query(Api.KEY_PAGE) String s1,
                                                            @Query(Api.KEY_YEAR_DATE) String s2,
                                                            @Query(Api.KEY_TAB) String s3);

    //论文待办事项/查漏补缺接口
    @GET(ResConstans.ACTION_LEAK_THESIS_CLASS_LIST)
    Observable<BaseModel<LeakOtherBean>> getLeakOtherClass2(@Query(Api.KEY_TYPE) String s,
                                                            @Query(Api.KEY_PAGE) String s1,
                                                            @Query(Api.KEY_YEAR_MONTH) String s2,
                                                            @Query(Api.KEY_TAB) String s3);

    //修改密码
    @FormUrlEncoded
    @POST(ResConstans.ACTION_CHANGE_PASSWORD)
    Observable<BaseModel<EmptyBean>> changePassword(@Field(Api.KEY_OLD_PWD) String oldPwd, @Field(Api.KEY_NEW_PWD) String pwd);

    //im登录
    @GET(ResConstans.ACTION_IMLOGIN)
    Observable<BaseModel<IMBean>> loginIm();

    //院校申请列表
    @GET(ResConstans.ACTION_UNIVERSITY_LIST)
    Observable<BaseModel<UniversityListResponse>> getUniversityList();

    //学院列表
    @GET(ResConstans.ACTION_SCHOOL_LIST)
    Observable<BaseModel<SchoolBean>> getSchoolList(@Query(Api.KEY_PAGE) String s,
                                                    @Query(Api.KEY_AREA) String area,
                                                    @Query(Api.KEY_SORT) String s1,
                                                    @Query(Api.KEY_PAGESIZE) String s2);

    //pdf下载
    @Streaming
    @GET
    Observable<ResponseBody> downLoadpdf(@Url String url);

    ////更新用户信息
    @GET(ResConstans.USER_ROLE)
    Observable<BaseModel<LoginInfo>> getUser_Role();

    ////申请操作
    @FormUrlEncoded
    @POST(ResConstans.ACTION_APPLY)
    Observable<BaseModel<EmptyBean>> apply(@Field(Api.KEY_TYPE) String s1,
                                           @Field(Api.KEY_UNIVERSITY_ID) String s2,
                                           @Field(Api.KEY_NAME) String s3,
                                           @Field(Api.KEY_TEL) String s4,
                                           @Field(Api.KEY_PLACE) String s5,
                                           @Field(Api.KEY_EDUCATIONAL) String s6,
                                           @Field(Api.KEY_INDUSTRY) String s7,
                                           @Field(Api.KEY_POSITION_TYPE) String s8);

    //上传录音文件
    @Multipart
    @POST(ResConstans.UPLOAD_RECORDFILE)
    Observable<BaseModel<FileBean>> upload_recordFiles(
            @Part MultipartBody.Part file, @Part("duration") RequestBody jsonBody
    );


    //获取大咖面对面列表
    @GET(ResConstans.BILING_LIST)
    Observable<BaseModel<BilingBean>> getBilingList(@Query("start") String page, @Query("limit") String limit);

    //预约报名
    @FormUrlEncoded
    @POST(ResConstans.BILING_ORDER)
    Observable<BaseModel<Object>> order(@Field(Api.KEY_COURSE_ID) String s);

    //是否申请
    @GET(ResConstans.IS_APPLY)
    Observable<BaseModel<ApplyBackBean>> isApply();

    @FormUrlEncoded
    @POST(ResConstans.ACTION_REGISTER_MI_PUSH)
    Observable<BaseModel<Object>> registerMiPushId(@Field(Api.KEY_REG_ID) String id);


    //获取课程资料
    @GET(com.tencent.liteav.demo.play.constant.ResConstans.COURSE_MATERIAL)
    Observable<BaseModel<MaterialBean>> getMaterial(@Query(com.tencent.liteav.demo.play.constant.Api.KEY_COURSE_ID) String s);


    //试卷信息接口
    @GET(ResConstans.EXAM_INFO)
    Observable<BaseModel<ExamInfoBean>> getExamInfo(@Query("examId") String s);

    //设置消息已读
    @GET(ResConstans.ACTION_READ)
    Observable<BaseModel<Object>> actionRead(@Query("inboxId") String s);

    @GET(ResConstans.PACKAGE_LIST)
    Observable<BaseModel<PackageListBean>> getPackAgeList();

    //设置消息已读
    @GET(ResConstans.QUESTION_INFO)
    Observable<BaseModel<Object>> actionJoin(@Query("id") String s);

    //小课观看
    @GET(ResConstans.SMALL_ENTER)
    Observable<BaseModel<SmallClassWatchBean>> smallEnter(@Query("courseId") String s);

    //播放页面日志
    @GET(ResConstans.ACTION_COURSE_PLAY_LOG)
    Observable<BaseModel<LogBean>> reportCoursePlayLog(@Query(Api.KEY_CURRENT_PROGRESS) String s,
                                                       @Query(Api.KEY_LIVE) String valueOf,
                                                       @Query(Api.KEY_COURSE_ID) String of,
                                                       @Query("rate") String rate);

    //进入播放页面
    @GET(ResConstans.ACTION_COURSE_ENTER)
    Observable<BaseModel<CourseEnterResponse>> courseEnter(@Query(Api.KEY_COURSE_ID) String s);

}

