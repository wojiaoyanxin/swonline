package com.sunlands.intl.yingshi.util;


import com.sunlands.comm_core.helper.DevelopHelper;
import com.sunlands.intl.yingshi.greendao.db.LoginInfo;
import com.sunlands.intl.yingshi.greendao.helper.DbHelper;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 文件名: StringFormatUtils
 * 描    述: [字符串工具类]
 * 创建人: duzzi
 * 创建时间: 2018/8/28
 */
public class StringFormatUtils {
    public static String getStartEndTime(String startTime, String endTime) {
        return String.format("%s-%s", startTime, endTime);
    }

    public static String getFormatDate(int year, int month) {
        return String.format("%s年%s月", year, month);
    }

    public static String getFormatTodayDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    public static String getFormatDate(int year, int month, int day) {
        return String.format("%s年%s月%s日", year, month, day);
    }

    public static String getFormatDateWithLine(int year, int month, int day) {
        String monthStr = month < 10 ? "0" + String.valueOf(month) : String.valueOf(month);
        String dayStr = day < 10 ? "0" + String.valueOf(day) : String.valueOf(day);
        return String.format("%s-%s-%s", year, monthStr, dayStr);
    }

    public static String getFormatAttendance(int num) {
        return String.format("应出勤：%s节", num);
    }

    public static String getFormatAbsence(int num) {
        return String.format("缺勤：%s节", num);
    }

    public static String getStudyTime(int time) {
        return String.format("学习：%s小时", time);
    }

    public static String secondsToFormatTime(int time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0)
            return "00:00:00";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = "00:" + unitFormat(minute) + ":" + unitFormat(second);
            } else {
                hour = minute / 60;
                if (hour > 99)
                    return "99:59:59";
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }

    private static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }

    public static String getPercent(int i) {
        return MessageFormat.format("{0}%", i);
    }

    public static String getExamUrl(int type, int courseId, int action, String examId) {
        //type=%@&stuId=%@&courseId=%@&userId=%@&sessionKey=%
        LoginInfo loginInfo = DbHelper.getInstance().getLoginInfoDao().queryBuilder().unique();
        if (type == -1 || courseId == -1 || loginInfo == null) {
            return null;
        }
        return DevelopHelper.HOMEWORK_TASK +
                "type=" + type +
                "&courseId=" + courseId +
                "&examId=" + examId +
                "&action=" + action +
                "&stuId=" + loginInfo.getStuId() +
                "&userId=" + loginInfo.getUserId() +
                "&sessionKey=" + loginInfo.getSessionKey();
    }

    public static String getPlanExamUrl(int type, int courseId, int action) {
        //type=%@&stuId=%@&courseId=%@&userId=%@&sessionKey=%
        LoginInfo loginInfo = DbHelper.getInstance().getLoginInfoDao().queryBuilder().unique();
        if (type == -1 || courseId == -1 || loginInfo == null) {
            return null;
        }
        return DevelopHelper.HOMEWORK_EXAM +
                "type=" + type +
                "&courseId=" + courseId +
                "&action=" + action +
                "&stuId=" + loginInfo.getStuId() +
                "&userId=" + loginInfo.getUserId() +
                "&sessionKey=" + loginInfo.getSessionKey();
    }

    public static String getSchoolUrl(int mCourseId) {
        //type=%@&stuId=%@&courseId=%@&userId=%@&sessionKey=%
        LoginInfo loginInfo = DbHelper.getInstance().getLoginInfoDao().queryBuilder().unique();
        if (loginInfo == null) {
            return null;
        }
        return DevelopHelper.HOMEWORK_SCHOOL +
                "insdetail" + mCourseId +
                "&stuId=" + loginInfo.getStuId() +
                "&tel=" + SPHelper.getUserPhone() +
                "&isapp=" + 1 +
                "&userId=" + loginInfo.getUserId() +
                "&sessionKey=" + loginInfo.getSessionKey();
    }
}
