package com.sunlands.intl.yingshi.util;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.ui.activity.DoHomeworkOrQuizActivity;

/**
 * Created by yanxin on 2019/3/12.
 */

public class GoH5Utils {


    public static void showSchoolTo(Context context, String university_id) {
        int i = 0;
        if (!TextUtils.isEmpty(university_id)) {
            i = Integer.parseInt(university_id);
        }
        DoHomeworkOrQuizActivity.showSchool(context, "school", i);
    }

    //type  2  随堂考和作业      action  0  做题   1  解析
    public static void showHomeTo(Context context, int action, String courseId, String quiz) {
        if (TextUtils.isEmpty(courseId)) {
            ToastUtils.showShort("请前往尚德官国际网参加考试");
            return;
        }
        DoHomeworkOrQuizActivity.showExam(context, 2, Integer.parseInt(courseId), action, quiz);
    }


    // 1.是否有作业
    // 2.是否开始
    // 3.是否参加
    // 4.是否结束
    // 5.是否重复
    public static void showExamTo(Context context, String mCourseId,
                                  String quiz, int isQuizStart,
                                  int join, int isQuizEnd, int isRepeat, int isView) {

        if ("0".equals(quiz) | TextUtils.isEmpty(quiz) | quiz == null) {  //未开始
            mCourseId = "0";
        }


        int i = Integer.parseInt(TextUtils.isEmpty(mCourseId) ? "0" : mCourseId);

        if (i == 0) {
            ToastUtils.showShort("请前往思维教育官网参加考试");
            return;
        }

        if ("0".equals(quiz) | TextUtils.isEmpty(quiz) | quiz == null) {  //未开始
            //  ToastUtils.showShort("暂无作业");
        } else if (isQuizStart == 0) {//作业未到开始时间
            // ToastUtils.showShort("作业未到开始时间");
        } else if (join == 0 && isQuizEnd == 1) {
            //  ToastUtils.showShort("作业已结束");
        } else if (TextUtils.isEmpty(mCourseId)) {
            ToastUtils.showShort("请前往思维教育官网参加考试");
        } else if (isQuizStart == 1 && join == 0) { //已经开始未参加
            DoHomeworkOrQuizActivity.showExam(context, 2, i, 0, quiz);
        } else if ((isRepeat == 1 && join == 1 && isQuizEnd == 0)) { //已参加  支持多次参加
            DoHomeworkOrQuizActivity.showExam(context, 2, i, 0, quiz);
        } else if ((isRepeat == 0 && join == 1)) { //已参加 不 支持多次参加
            if (isView == 1) {
                DoHomeworkOrQuizActivity.showExam(context, 2, i, 1, quiz);
            } else {
                ToastUtils.showShort("暂无解析");
            }
        } else if ((isQuizEnd == 1 && join == 1)) { //已结束 已参加
            if (isView == 1) {
                DoHomeworkOrQuizActivity.showExam(context, 2, i, 1, quiz);
            } else {
                ToastUtils.showShort("暂无解析");
            }
        }
    }


    public static void setViewState(TextView view, TextView enterJiexi, int isEnd, int isRepeat, int isStart, int hasJoin, int isResult) {

        view.setText("进入答题");
        enterJiexi.setVisibility(View.GONE);
        view.setEnabled(true);
        if (isStart == 0) { //未开始
            view.setText("待开始");
            view.setEnabled(false);
            view.setTextColor(Color.parseColor("#999999"));
            view.setBackgroundResource(R.drawable.button_common_home_un);
        } else if ((isEnd == 1 && hasJoin == 0)) { //已结束未参加
            view.setText("已结束");
            view.setEnabled(false);
            view.setTextColor(Color.parseColor("#999999"));
            view.setBackgroundResource(R.drawable.button_common_home_un);
        } else if (isStart == 1 && hasJoin == 0) { //已经开始未参加
            view.setTextColor(Color.parseColor("#333333"));
            view.setBackgroundResource(R.drawable.button_common_home);
        } else if ((isRepeat == 1 && hasJoin == 1 && isEnd == 0)) { //已参加  支持多次参加
            view.setTextColor(Color.parseColor("#333333"));
            view.setBackgroundResource(R.drawable.button_common_home);
            view.setText("再次答题");
            if (isResult == 0) {
                enterJiexi.setVisibility(View.GONE);
            } else {
                enterJiexi.setVisibility(View.VISIBLE);
            }
        } else if (isRepeat == 0 && hasJoin == 1 && isEnd == 0) { //已参加 不 支持多次参加

            view.setTextColor(Color.parseColor("#999999"));
            view.setBackgroundResource(R.drawable.button_common_home_un);
            view.setText("已参加");
            view.setEnabled(false);

            if (isResult == 0) {
                enterJiexi.setVisibility(View.GONE);
            } else {
                enterJiexi.setVisibility(View.VISIBLE);
            }

        } else if ((isEnd == 1 && hasJoin == 1)) { //已结束 已参加

            view.setText("已结束");
            view.setEnabled(false);
            view.setTextColor(Color.parseColor("#999999"));
            view.setBackgroundResource(R.drawable.button_common_home_un);

            if (isResult == 0) {
                enterJiexi.setVisibility(View.GONE);
            } else {
                enterJiexi.setVisibility(View.VISIBLE);
            }

        }

    }
}
