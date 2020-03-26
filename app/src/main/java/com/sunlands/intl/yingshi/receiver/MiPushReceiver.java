package com.sunlands.intl.yingshi.receiver;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.blankj.utilcode.util.ActivityUtils;
import com.google.gson.Gson;
import com.sunlands.comm_core.utils.CommonUtils;
import com.sunlands.intl.yingshi.bean.CustomPushMessage;
import com.sunlands.intl.yingshi.constant.Api;
import com.sunlands.intl.yingshi.constant.Constants;
import com.sunlands.intl.yingshi.helper.UpdataRequestHelper;
import com.sunlands.intl.yingshi.ui.activity.MainActivity;
import com.sunlands.intl.yingshi.ui.activity.SplashActivity;
import com.sunlands.intl.yingshi.ui.activity.home.examarrangement.ExamArrangementActivity;
import com.sunlands.intl.yingshi.ui.home.view.SysMsgActivity;
import com.sunlands.intl.yingshi.ui.my.view.MyApplyActivity;
import com.sunlands.intl.yingshi.util.DLog;
import com.sunlands.intl.yingshi.util.EnterPlayerUtils;
import com.sunlands.intl.yingshi.util.StringUtils;
import com.sunlands.intl.yingshi.util.UrlParse;
import com.sunlands.intl.yingshi.web.WebViewActivity;
import com.xiaomi.mipush.sdk.ErrorCode;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;

import java.util.List;
import java.util.Map;

/**
 * 文件名: MiPushReceiver
 * 描    述: [极光推送服务]
 * 创建人: duzzi
 * 创建时间: 2018/10/15
 */
public class MiPushReceiver extends PushMessageReceiver {

    private final static String TAG = MiPushReceiver.class.getSimpleName();
    public static String sRegId;
    private String mTopic;
    private String mAlias;
    private String mAccount;
    private String mStartTime;
    private String mEndTime;

    /**
     * 透传消息
     *
     * @param context
     * @param message
     */
    @Override
    public void onReceivePassThroughMessage(Context context, MiPushMessage message) {
        Log.v(TAG, "onReceivePassThroughMessage is called. " + message.toString());
    }

    /**
     * 点击通知栏消息
     *
     * @param context
     * @param message
     */
    @Override
    public void onNotificationMessageClicked(Context context, MiPushMessage message) {

        Log.d(TAG, "onNotificationMessageClicked is called. " + message.toString());

        if (CommonUtils.isBackground(context) == true) {
            ActivityUtils.startActivity(MainActivity.class);
        }
        String content = message.getContent();
        if (!StringUtils.isEmpty(content)) {
            try {
                CustomPushMessage customPushMessage = new Gson().fromJson(content, CustomPushMessage.class);
                if (customPushMessage.getMessageType() == Constants.MiPush.MSG_TYPE_INTENT) {
                    jumpToActivity(context, customPushMessage);
                } else {
                    //启动app
                    ActivityUtils.startActivity(SplashActivity.class);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 跳转指定页面
     *
     * @param context
     * @param customPushMessage
     */
    private void jumpToActivity(Context context, CustomPushMessage customPushMessage) {

        String linkUrl = customPushMessage.getLinkUrl();
        if (!StringUtils.isEmpty(linkUrl)) {
            Map<String, String> urlParams = UrlParse.getUrlParams(linkUrl);
            if (linkUrl.contains("courseDetail")) {
                //普通课程和大咖课详情
                String courseIdInt = urlParams.get(Api.KEY_COURSE_ID);

                int courseId = Integer.parseInt(courseIdInt);

                String s = urlParams.get("isSYMasterCourse");

                if ("1".equals(s)) { //大咖课

                    String is_free = urlParams.get("is_free");
                    EnterPlayerUtils.enterBilingClass(context, courseId, Integer.parseInt(is_free));

                } else if ("0".equals(s)) {  //普通课程
                    EnterPlayerUtils.enterClass(context, courseId);
                }

            } else if (linkUrl.contains("zoomDetail")) {
                //zoom课
                String courseIdInt = urlParams.get(Api.KEY_COURSE_ID);
                EnterPlayerUtils.enterClass(context, Integer.parseInt(courseIdInt));

            } else if (linkUrl.contains("examArrangementTip")) {
                //考试安排
                Intent intent = new Intent(context, ExamArrangementActivity.class);

                String type = urlParams.get("type");

                if (type.equals("3") || type.equals("4")) {

                    intent.putExtra("INDEX", 1);
                } else {
                    intent.putExtra("INDEX", 0);
                }
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            } else if (linkUrl.contains("applyList")) {
                //申请列表
                ActivityUtils.startActivity(MyApplyActivity.class);

            } else if (linkUrl.contains("messageList")) {
                //系统消息
                ActivityUtils.startActivity(SysMsgActivity.class);

            } else if (linkUrl.contains("webDetail")) {
                String url = urlParams.get("url");
                if (TextUtils.isEmpty(url)) {
                    return;
                }

                if (url.contains("&title")) {
                    String url1 = url.substring(0, url.indexOf("&title="));
                    String title = url.substring(url.indexOf("&title="));
                    WebViewActivity.goActivity(url1, title);
                } else {
                    WebViewActivity.goActivity(url, "");
                }

            }
        }
    }

    /**
     * 收到通知栏消息
     *
     * @param context
     * @param message
     */
    @Override
    public void onNotificationMessageArrived(Context context, MiPushMessage message) {
        Log.v(TAG, "onNotificationMessageArrived is called. " + message.toString());
    }

    @Override
    public void onCommandResult(Context context, MiPushCommandMessage message) {
        Log.d(TAG, "onCommandResult is called. " + message.toString());
        String command = message.getCommand();
        List<String> arguments = message.getCommandArguments();
        String cmdArg1 = ((arguments != null && arguments.size() > 0) ? arguments.get(0) : null);
        String cmdArg2 = ((arguments != null && arguments.size() > 1) ? arguments.get(1) : null);
        String log;
        if (MiPushClient.COMMAND_REGISTER.equals(command)) {
            if (message.getResultCode() == ErrorCode.SUCCESS) {
                sRegId = cmdArg1;
                reportRegId(context, sRegId);
//                MiPushClient.setAlias(Utils.getContext(), "15071343418", null);
//                log = context.getString(R.string.register_success);
            } else {
//                log = context.getString(R.string.register_fail);
            }
        } else if (MiPushClient.COMMAND_SET_ALIAS.equals(command)) {
            if (message.getResultCode() == ErrorCode.SUCCESS) {
                mAlias = cmdArg1;
                DLog.d("mAlias: " + mAlias);
//                log = context.getString(R.string.set_alias_success, mAlias);
            } else {
//                log = context.getString(R.string.set_alias_fail, message.getReason());
            }
        }
// else if (MiPushClient.COMMAND_UNSET_ALIAS.equals(command)) {
//            if (message.getResultCode() == ErrorCode.SUCCESS) {
//                mAlias = cmdArg1;
//                log = context.getString(R.string.unset_alias_success, mAlias);
//            } else {
//                log = context.getString(R.string.unset_alias_fail, message.getReason());
//            }
//        } else if (MiPushClient.COMMAND_SET_ACCOUNT.equals(command)) {
//            if (message.getResultCode() == ErrorCode.SUCCESS) {
//                mAccount = cmdArg1;
//                log = context.getString(R.string.set_account_success, mAccount);
//            } else {
//                log = context.getString(R.string.set_account_fail, message.getReason());
//            }
//        } else if (MiPushClient.COMMAND_UNSET_ACCOUNT.equals(command)) {
//            if (message.getResultCode() == ErrorCode.SUCCESS) {
//                mAccount = cmdArg1;
//                log = context.getString(R.string.unset_account_success, mAccount);
//            } else {
//                log = context.getString(R.string.unset_account_fail, message.getReason());
//            }
//        } else if (MiPushClient.COMMAND_SUBSCRIBE_TOPIC.equals(command)) {
//            if (message.getResultCode() == ErrorCode.SUCCESS) {
//                mTopic = cmdArg1;
//                log = context.getString(R.string.subscribe_topic_success, mTopic);
//            } else {
//                log = context.getString(R.string.subscribe_topic_fail, message.getReason());
//            }
//        } else if (MiPushClient.COMMAND_UNSUBSCRIBE_TOPIC.equals(command)) {
//            if (message.getResultCode() == ErrorCode.SUCCESS) {
//                mTopic = cmdArg1;
//                log = context.getString(R.string.unsubscribe_topic_success, mTopic);
//            } else {
//                log = context.getString(R.string.unsubscribe_topic_fail, message.getReason());
//            }
//        } else if (MiPushClient.COMMAND_SET_ACCEPT_TIME.equals(command)) {
//            if (message.getResultCode() == ErrorCode.SUCCESS) {
//                mStartTime = cmdArg1;
//                mEndTime = cmdArg2;
//                log = context.getString(R.string.set_accept_time_success, mStartTime, mEndTime);
//            } else {
//                log = context.getString(R.string.set_accept_time_fail, message.getReason());
//            }
//        } else {
//            log = message.getReason();
//        }
//        MainActivity.logList.add(0, getSimpleDate() + "    " + log);
//
//        Message msg = Message.obtain();
//        msg.obj = log;
//        getHandler().sendMessage(msg);
    }

    @Override
    public void onReceiveRegisterResult(Context context, MiPushCommandMessage message) {
        Log.v(TAG, "onReceiveRegisterResult is called. " + message.toString());
        String command = message.getCommand();
        List<String> arguments = message.getCommandArguments();
        String cmdArg1 = ((arguments != null && arguments.size() > 0) ? arguments.get(0) : null);
        String log;
        if (MiPushClient.COMMAND_REGISTER.equals(command)) {
            if (message.getResultCode() == ErrorCode.SUCCESS) {
                sRegId = cmdArg1;
                reportRegId(context, sRegId);
//                MiPushClient.setAlias(Utils.getContext(), "15071343418", null);
//                log = context.getString(R.string.register_success);
            } else {
//                log = context.getString(R.string.register_fail);
            }
        } else {
            log = message.getReason();
        }
//
//        Message msg = Message.obtain();
//        msg.obj = log;
//        getHandler().sendMessage(msg);
    }

    /**
     * 上报regId
     *
     * @param context
     * @param regId
     */

    private void reportRegId(Context context, String regId) {
        new UpdataRequestHelper().registerMiPushId(regId);
    }


    @Override
    public void onRequirePermissions(Context context, String[] permissions) {
        super.onRequirePermissions(context, permissions);
        Log.e(TAG, "onRequirePermissions is called. need permission" + arrayToString(permissions));
//        if (Build.VERSION.SDK_INT >= 23 && context.getApplicationInfo().targetSdkVersion >= 23) {
//            Intent intent = new Intent();
//            intent.putExtra("permissions", permissions);
//            intent.setComponent(new ComponentName(context.getPackageName(), PermissionActivity.class.getCanonicalName()));
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
//            context.startActivity(intent);
//        }
    }


    public String arrayToString(String[] strings) {
        StringBuilder result = new StringBuilder(" ");
        for (String str : strings) {
            result.append(str).append(" ");
        }
        return result.toString();
    }

}
