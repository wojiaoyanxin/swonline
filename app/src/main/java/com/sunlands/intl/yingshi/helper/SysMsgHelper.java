package com.sunlands.intl.yingshi.helper;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.blankj.utilcode.util.ActivityUtils;
import com.sunlands.comm_core.helper.ApplicationsHelper;
import com.sunlands.intl.yingshi.bean.SysMsgBean;
import com.sunlands.intl.yingshi.ui.activity.home.examarrangement.ExamArrangementActivity;
import com.sunlands.intl.yingshi.ui.my.view.MyApplyActivity;
import com.sunlands.intl.yingshi.util.EnterPlayerUtils;
import com.sunlands.intl.yingshi.util.UrlParse;
import com.sunlands.intl.yingshi.web.WebViewActivity;

import java.util.Map;

/**
 * 当前包名: com.sunlands.intl.yingshi.helper
 * 创 建 人: xueh
 * 创建日期: 2019/2/22 12:15
 * 备注：
 */
public class SysMsgHelper {

    public static final String courseDetailProtocol = "courseDetail";
    public static final String examArrangementTipProtocol = "examArrangementTip";
    public static final String messageSysListProtol = "messageList";
    public static final String applyListProtol = "applyList";
    public static final String zoomProtol = "zoomDetail";
    public static final String INDEX = "INDEX";
    public static void jump(SysMsgBean.ListBean mListBean, Context context) {
        Log.e("SysMsgHelper", "mListBean: "+mListBean.toString() );
        if (!TextUtils.isEmpty(mListBean.getMessage().getApp_url())) {
            Log.e("SysMsgHelper", "jump: " + mListBean.getMessage().getApp_url());
            if (mListBean.getMessage().app_url.startsWith("slmba")) {
                //String mString = getInsideString(mListBean.getMessage().app_url, "//", "?");
                String mString=mListBean.getMessage().app_url;
                Map<String, String> urlParams = UrlParse.getUrlParams(mListBean.getMessage().app_url);
                if (mString.contains(courseDetailProtocol)) {
                    /**
                     *   "courseId" : 课程id
                     *    "courseLiveType" : 课程播放类型
                     *    app_url:  slmba://courseDetail?courseId=78&courseLiveType=3
                     */
                    String courseId = getInsideString(mListBean.getMessage().app_url, "courseId=", "&");

                    String isSYMasterCourse = urlParams.get("isSYMasterCourse");

                    if("1".equals(isSYMasterCourse)) {
                        EnterPlayerUtils.enterBilingClass(context,Integer.valueOf(courseId),0);
                    }else {
                        EnterPlayerUtils.enterClass(context,Integer.valueOf(courseId));
                    }

                } else if (mString.contains(examArrangementTipProtocol)) {
                    /** 考试安排
                     * "controller" : "examArrangementTip"
                     */
                    String type = mListBean.getMessage().app_url.substring(mListBean.getMessage().app_url.indexOf("?type=") + "?type=".length());

                    /**
                     * 0 是随堂考页面，1是考试
                     */
                    Intent intent = new Intent(ApplicationsHelper.context(), ExamArrangementActivity.class);
                    if (type.equals("3")||type.equals("4")) {
                        intent.putExtra(INDEX,1);
                    }else{
                        intent.putExtra(INDEX,0);
                    }
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    ApplicationsHelper.context().startActivity(intent);

                } else if (mString.contains(messageSysListProtol)) {
                    /** 消息中心-系统消息页
                     *"controller" : "messageList"
                     */


                } else if (mString.contains(applyListProtol)) {
                    /** 我的申请列表
                     *"controller" : "applyList"
                     */

                    ActivityUtils.startActivity(MyApplyActivity.class);
                } else if (mString.contains(zoomProtol)) {
                    /**
                     * zoom播放详情
                     *
                     * "controller" : "zoomDetail"
                     * "meetingId" : zoomk会议id,字符串
                     */

                    String courseId = urlParams.get("courseId");
                    EnterPlayerUtils.enterClass(context,Integer.valueOf(courseId));
                }


            } else {
                WebViewActivity.goActivity(mListBean.getMessage().app_url,mListBean.getMessage().title);
            }
        }

    }

    public static String getInsideString(String str, String strStart, String
            strEnd) {
        if (str.indexOf(strStart) < 0) {
            return "";
        }
        if (str.indexOf(strEnd) < 0) {
            return "";
        }
        return str.substring(str.indexOf(strStart) + strStart.length(), str.indexOf(strEnd));
    }
}