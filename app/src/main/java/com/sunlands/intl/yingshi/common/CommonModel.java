package com.sunlands.intl.yingshi.common;

import android.arch.lifecycle.Lifecycle;
import android.text.TextUtils;

import com.blankj.utilcode.util.ToastUtils;
import com.sunlands.comm_core.base.mvp.MvpBaseModel;
import com.sunlands.comm_core.net.MVPModelCallbacks;
import com.sunlands.comm_core.net.ServiceGenerator;
import com.sunlands.intl.yingshi.constant.RestApi;
import com.sunlands.intl.yingshi.ui.activity.DoHomeworkOrQuizActivity;
import com.sunlands.intl.yingshi.ui.activity.ExamTransitionActivity;
import com.sunlands.intl.yingshi.ui.activity.home.apply.ApplyActivity;
import com.sunlands.intl.yingshi.ui.activity.home.apply.ApplyParam;
import com.sunlands.intl.yingshi.ui.activity.home.biling.BilingDaKaActivity;
import com.sunlands.intl.yingshi.ui.activity.home.examarrangement.ExamFragment;
import com.sunlands.intl.yingshi.ui.activity.home.examarrangement.HomeWorkFragment;
import com.sunlands.intl.yingshi.ui.activity.home.leakfilling.view.LeakClassFragment;
import com.sunlands.intl.yingshi.ui.activity.home.leakfilling.view.LeakOtherClassFragment;
import com.sunlands.intl.yingshi.ui.activity.home.mythesisn.MyPapersActivity;
import com.sunlands.intl.yingshi.ui.activity.home.plan.view.ClassFragment;
import com.sunlands.intl.yingshi.ui.activity.home.schoollist.view.SchoolListActivity;
import com.sunlands.intl.yingshi.ui.activity.home.scorequery.ScoreQueryActivity;
import com.sunlands.intl.yingshi.ui.activity.home.smallclass.MoreSmallClassListActivity;
import com.sunlands.intl.yingshi.ui.activity.home.smallclass.SmallClasListActivity;
import com.sunlands.intl.yingshi.ui.activity.home.smallclass.SmallClassDetailsActivity;
import com.sunlands.intl.yingshi.ui.activity.home.smallclass.SmallClassFragment;
import com.sunlands.intl.yingshi.ui.activity.home.smallclass.SmallPlayActivity;
import com.sunlands.intl.yingshi.ui.classroom.view.AllLessonFragment;
import com.sunlands.intl.yingshi.ui.classroom.view.LessonCalendarFragment;
import com.sunlands.intl.yingshi.ui.home.view.AskAnswerActivity;
import com.sunlands.intl.yingshi.ui.home.view.AskAnswerDetailsActivity;
import com.sunlands.intl.yingshi.ui.home.view.HomeFragment;
import com.sunlands.intl.yingshi.ui.home.view.QuestionListActivity;
import com.sunlands.intl.yingshi.ui.home.view.SysMsgActivity;
import com.sunlands.intl.yingshi.ui.home.view.TodayHotListActivity;
import com.sunlands.intl.yingshi.ui.my.view.ChangePasswordActivity;
import com.sunlands.intl.yingshi.ui.my.handout.view.DownLoadActivity;
import com.sunlands.intl.yingshi.ui.my.view.HistoryActivity;
import com.sunlands.intl.yingshi.ui.my.view.MyApplyActivity;
import com.sunlands.intl.yingshi.ui.my.view.MyFragment;
import com.sunlands.intl.yingshi.ui.my.view.MyPrivateActivity;
import com.sunlands.intl.yingshi.ui.my.view.OrderDetailsActivity;
import com.sunlands.intl.yingshi.ui.my.view.SettingsActivity;
import com.sunlands.intl.yingshi.ui.my.view.UserInfoActivity;
import com.sunlands.intl.yingshi.ui.my.view.UserInfoSettingActivity;

import java.io.File;

import io.reactivex.subjects.PublishSubject;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by yanxin on 2019/5/15.
 */

public class CommonModel<T> extends MvpBaseModel<RestApi> implements CommonContract.ICommonModel<T> {

    @Override
    protected RestApi initApi() {
        return ServiceGenerator.getService(RestApi.class);
    }

    @Override
    public void getData(String type, boolean isShow, PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<T> mMVPModelCallbacks, Object... o) {
        getDeploy(type, isShow, mPublishSubject, mMVPModelCallbacks, o);
    }

    @Override
    public void getData(String type, PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<T> mMVPModelCallbacks, Object... o) {
        getDeploy(type, mPublishSubject, mMVPModelCallbacks, o);
    }

    public void getDeploy(String type, Boolean isShow, PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<T> mMVPModelCallbacks, Object... args) {
        if (type.equals(ScoreQueryActivity.class.getName())) {
            if (TextUtils.isEmpty(args[0].toString())) {
                deploy(getApi().getPackAgeList(), mPublishSubject, mMVPModelCallbacks, isShow);
            } else {
                deploy(getApi().scoreQuery(args[0].toString(), args[1].toString()), mPublishSubject, mMVPModelCallbacks, isShow);
            }
        } else if (type.equals(HomeFragment.class.getName())) {
            if (TextUtils.isEmpty(args[0].toString())) {
                deploy(getApi().getHomeData(), mPublishSubject, mMVPModelCallbacks, isShow);
            } else {
                if (args.length == 2) {
                    deploy(getApi().getSmallClassList(args[0].toString(), args[1].toString()), mPublishSubject, mMVPModelCallbacks, isShow);
                } else {
                    deploy(getApi().getArticleList(args[0].toString()), mPublishSubject, mMVPModelCallbacks, isShow);
                }
            }
        } else if (type.equals(ApplyActivity.class.getName())) {
            if (args[0] instanceof ApplyParam) {
                ApplyParam applyParam = (ApplyParam) args[0];
                deploy(getApi().apply(applyParam.getType(),
                        applyParam.getUniversityId(),
                        applyParam.getName(),
                        applyParam.getTel(),
                        String.valueOf(applyParam.getIndustry()),
                        String.valueOf(applyParam.getEducational()),
                        String.valueOf(applyParam.getIndustry()),
                        String.valueOf(applyParam.getPositionType())
                ), mPublishSubject, mMVPModelCallbacks, isShow);
            } else {
                deploy(getApi().getUniversityList(), mPublishSubject, mMVPModelCallbacks, isShow);
            }
        } else if (DoHomeworkOrQuizActivity.class.getName().equals(type)) {

            if (args[0] instanceof File) {
                File file = (File) args[0];
                RequestBody requestBody =
                        RequestBody.create(MediaType.parse("application/octet-stream"), file);
                MultipartBody.Part body = MultipartBody.Part.createFormData("sound", file.getAbsolutePath(), requestBody);
                RequestBody bodyjson = RequestBody.create(MediaType.parse("text/plain"), args[1].toString());
                deploy(getApi().upload_recordFiles(body, bodyjson), mPublishSubject, mMVPModelCallbacks, isShow);
            }
        } else if (AllLessonFragment.class.getName().equals(type)) {
            deploy(getApi().getReplayLessons((int) args[0] == -1 ? "" : (int) args[0] + "", (int) args[1], 8), mPublishSubject, mMVPModelCallbacks, isShow);
        } else if (ExamFragment.class.getName().equals(type)) {
            deploy(getApi().getExamArrangements(args[1].toString(), args[0].toString()), mPublishSubject, mMVPModelCallbacks, isShow);
        } else if (HomeWorkFragment.class.getName().equals(type)) {
            deploy(getApi().getExamArrangements(args[1].toString(), args[0].toString()), mPublishSubject, mMVPModelCallbacks, isShow);
        } else if (LessonCalendarFragment.class.getName().equals(type)) {
            if (args[0].toString().equals("")) {
                deploy(getApi().getCourseDateList(), mPublishSubject, mMVPModelCallbacks, isShow);
            } else {
                deploy(getApi().getLessonCalendar(args[0].toString(), "99"), mPublishSubject, mMVPModelCallbacks, isShow);
            }
        } else if (MyPapersActivity.class.getName().equals(type)) {//我的论文列表
            deploy(getApi().getPagers(), mPublishSubject, mMVPModelCallbacks, isShow);
        } else if (DownLoadActivity.class.getName().equals(type)) {//我的下载
            deploy(getApi().getMaterial(args[0].toString()), mPublishSubject, mMVPModelCallbacks, isShow);
        } else if (ExamTransitionActivity.class.getName().equals(type)) {//试卷信息接口
            deploy(getApi().getExamInfo(args[0].toString()), mPublishSubject, mMVPModelCallbacks, isShow);
        } else if (SchoolListActivity.class.getName().equals(type)) {//院校列表
            deploy(getApi().getSchoolList(args[0].toString(), args[1].toString(), args[2].toString(), "5"), mPublishSubject, mMVPModelCallbacks, isShow);
        } else if (LeakClassFragment.class.getName().equals(type)) {//任务提醒课程列表
            deploy(getApi().getLeakClass(args[0].toString(), args[1].toString(), args[2].toString()), mPublishSubject, mMVPModelCallbacks, isShow);
        } else if (LeakOtherClassFragment.class.getName().equals(type)) {//任务提醒考试论文列表
            if (args[0].toString().equals("3")) {
                deploy(getApi().getLeakOtherClass2("1", args[3].toString(), args[2].toString().equals("全部") ? "" : args[2].toString(), args[1].toString()), mPublishSubject, mMVPModelCallbacks, isShow);
            } else {
                deploy(getApi().getLeakOtherClass1(args[0].toString(), args[3].toString(), args[2].toString().equals("全部") ? "" : args[2].toString(), args[1].toString()), mPublishSubject, mMVPModelCallbacks, isShow);
            }
        } else if (UserInfoSettingActivity.class.getName().equals(type)) {

            if ((TextUtils.isEmpty(args[0].toString()))) {
                deploy(getApi().getUserInfo2(""), mPublishSubject, mMVPModelCallbacks);  //获取个人信息
                return;
            }
            if (args.length == 1) { //上传头像
                File file = (File) args[0];
                RequestBody requestBody =
                        RequestBody.create(MediaType.parse("image/png"), file);
                MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getAbsolutePath(), requestBody);
                deploy(getApi().upload_headFiles(body), mPublishSubject, mMVPModelCallbacks);
            } else if (args.length == 6) {//更新个人信息
                String s = args[4].equals("男") ? "2" : args[4].equals("女") ? "1" : "0";
                deploy(getApi().updateUserInfo(args[0].toString(), args[1].toString(), args[2].toString(), args[3].toString(), s, args[5].toString()), mPublishSubject, mMVPModelCallbacks);
            }
        } else if (MyPrivateActivity.class.getName().equals(type)) {
            if ((TextUtils.isEmpty(args[0].toString()))) {
                deploy(getApi().getGetAllPrivate(), mPublishSubject, mMVPModelCallbacks);  //全部隐私
            } else {
                deploy(getApi().getGetSinglePrivate(args[0].toString()), mPublishSubject, mMVPModelCallbacks); //修改隐私
            }
        } else if (SettingsActivity.class.getName().equals(type)) {  //退出登录
            deploy(getApi().logout(), mPublishSubject, mMVPModelCallbacks);
        } else if (UserInfoActivity.class.getName().equals(type)) {  //个人主页信息
            deploy(getApi().getUserInfo2(""), mPublishSubject, mMVPModelCallbacks);
        } else if (ChangePasswordActivity.class.getName().equals(type)) {  //修改密码
            deploy(getApi().changePassword(args[0].toString(), args[1].toString()), mPublishSubject, mMVPModelCallbacks);
        } else if (MyApplyActivity.class.getName().equals(type)) {  //我的申请
            deploy(getApi().getMyApply(), mPublishSubject, mMVPModelCallbacks);
        } else if (HistoryActivity.class.getName().equals(type)) {
            if ((TextUtils.isEmpty(args[0].toString()))) {
                deploy(getApi().getHistory(), mPublishSubject, mMVPModelCallbacks); //历史记录
            } else {
                deploy(getApi().getMoreHistory(args[0].toString()), mPublishSubject, mMVPModelCallbacks); //历史记录更多
            }
        } else if (MyFragment.class.getName().equals(type)) {   //我的页面
            deploy(getApi().getUserInfo(), mPublishSubject, mMVPModelCallbacks);
        } else if (SysMsgActivity.class.getName().equals(type)) {   //系统消息
            if ((TextUtils.isEmpty(args[0].toString()))) {
                deploy(getApi().messageEmpty(), mPublishSubject, mMVPModelCallbacks);
            } else {
                deploy(getApi().getSysMsgList((int) args[0]), mPublishSubject, mMVPModelCallbacks);
            }

        } else if (SmallClassDetailsActivity.class.getName().equals(type)) {
            if (args.length == 2) {
                deploy(getApi().orderSmallClass(args[0].toString()), mPublishSubject, mMVPModelCallbacks);
            } else if (args.length == 3) {
                deploy(getApi().orderSmallClassUnits(args[0].toString()), mPublishSubject, mMVPModelCallbacks);
            } else {
                deploy(getApi().getSmallClassDetails(args[0].toString()), mPublishSubject, mMVPModelCallbacks);
            }
        } else if (OrderDetailsActivity.class.getName().equals(type)) {
            deploy(getApi().orderSmallClass(args[0].toString()), mPublishSubject, mMVPModelCallbacks);
        } else if (TodayHotListActivity.class.getName().equals(type)) {
            deploy(getApi().getTodayHotList(args[0].toString()), mPublishSubject, mMVPModelCallbacks);
        } else if (QuestionListActivity.class.getName().equals(type)) {
            deploy(getApi().getQuestionList(args[0].toString()), mPublishSubject, mMVPModelCallbacks);
        } else if (AskAnswerActivity.class.getName().equals(type)) {
            deploy(getApi().getAskAnswerList(args[0].toString()), mPublishSubject, mMVPModelCallbacks);
        } else if (AskAnswerDetailsActivity.class.getName().equals(type)) {
            deploy(getApi().getAskAnswerDetails(args[0].toString()), mPublishSubject, mMVPModelCallbacks);
        } else {
            ToastUtils.showShort("没有当前类的model数据");
        }
    }

    public void getDeploy(String type, PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<T> mMVPModelCallbacks, Object... args) {
        if (TodayHotListActivity.class.getName().equals(type)) {
            deploy(getApi().getTodayHotList(args[0].toString()), mPublishSubject, mMVPModelCallbacks);
        } else if (ClassFragment.class.getName().equals(type)) {
            deploy(getApi().getPlanData(args[0].toString()), mPublishSubject, mMVPModelCallbacks);
        } else if (QuestionListActivity.class.getName().equals(type)) {
            deploy(getApi().getQuestionList(args[0].toString()), mPublishSubject, mMVPModelCallbacks);
        } else if (AskAnswerActivity.class.getName().equals(type)) {
            deploy(getApi().getAskAnswerList(args[0].toString()), mPublishSubject, mMVPModelCallbacks);
        } else if (AskAnswerDetailsActivity.class.getName().equals(type)) {
            deploy(getApi().getAskAnswerDetails(args[0].toString()), mPublishSubject, mMVPModelCallbacks);
        } else if (SysMsgActivity.class.getName().equals(type)) {   //系统消息
            deploy(getApi().getSysMsgList((int) args[0]), mPublishSubject, mMVPModelCallbacks);
        } else if (BilingDaKaActivity.class.getName().equals(type)) {//大咖面对面
            deploy(getApi().getBilingList(args[0].toString(), 500 + ""), mPublishSubject, mMVPModelCallbacks);
        } else if (SmallPlayActivity.class.getName().equals(type)) {  //尚直播观看
            if (args.length > 2) {
                deploy(getApi().reportCoursePlayLog(args[0].toString(), args[1].toString(), args[2].toString(), args[3].toString()), mPublishSubject, mMVPModelCallbacks, false);
            } else {
                deploy(getApi().smallEnter(args[0].toString()), mPublishSubject, mMVPModelCallbacks);
            }
        } else if (OrderDetailsActivity.class.getName().equals(type)) {//小课下单
            if (args.length > 1) {
                deploy(getApi().orderSmallDetailsClass(args[0].toString()), mPublishSubject, mMVPModelCallbacks);
            } else {
                deploy(getApi().orderSmallClass(args[0].toString()), mPublishSubject, mMVPModelCallbacks);
            }
        } else if (SmallClasListActivity.class.getName().equals(type)) {  //小课列表
            deploy(getApi().orderSmallClassUnits(args[0].toString()), mPublishSubject, mMVPModelCallbacks);
        } else if (MoreSmallClassListActivity.class.getName().equals(type)) {  //小课列表
            deploy(getApi().getSmallClassList(args[0].toString(), args[1].toString()), mPublishSubject, mMVPModelCallbacks);
        } else if (SmallClassFragment.class.getName().equals(type)) {  //小课fragment列表
            deploy(getApi().getSmallClassList(args[0].toString(), "10", args[1].toString()), mPublishSubject, mMVPModelCallbacks);
        } else {
            ToastUtils.showShort("没有当前类的model数据");
        }
    }

}
