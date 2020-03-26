package com.sunlands.intl.yingshi.constant;

import android.os.Environment;

import com.sunlands.intl.yingshi.helper.LoginUserInfoHelper;

/**
 * 文件名: Constants
 * 描    述: [常量]
 * 创建人: duzzi
 * 创建时间: 2018/7/27
 */
public class Constants {
    public static final int SEND_VERIFY_CODE_INTERVAL = 60;
    public static final int VERIFY_CODE_LENGTH = 4;
    public static final int MIN_PASSWORD_LENGTH = 6;
    public static final int MAX_PASSWORD_LENGTH = 12;

    public static final int REPORT_INTERVAL = 10 * 1000;


    public static final String CACHE_DIR = Environment.getExternalStorageDirectory().getPath()
            + "/mba" + LoginUserInfoHelper.getInstance().getUserInfo().getId();
    public static final String RECORD_FILE = Environment.getExternalStorageDirectory().getPath()
            + "/mba_record";
    //微信 appid
    public static final String APP_ID = "wxd76627236f07b7a9";

    public static int DEFAULT_PAGE = 1;
    public static int DEFAULT_NEXT_PAGE = 2;


    public static int HAS_MORE = 1;
    public static int WIDTH = 34;
    public static int HEIGHT = 4;

    public static class FunctionType {
        public static final int MY_LESSON_SCHEDULE = 1;
        public static final int MY_EXAM_SCHEDULE = 2;
        public static final int MY_SCORE_QUERY = 3;
        public static final int MY_PAPER = 4;
        public static final int MY_ATTENDANCE = 5;
        public static final int MY_COMMIUNITY = 9;
        public static final int MY_MESSAGE = 6;
        public static final int MY_SCHOOL_LIST = 7;
        public static final int MY_LEAK_LIST = 10;
    }

    public static class Key {
        public static final String KEY_ACCOUNT_INFO = "KEY_ACCOUNT_INFO";
        public static final String KEY_LOGIN_INFO = "KEY_LOGIN_INFO";
        public static final String KEY_MAIN_TAB_INDEX = "KEY_MAIN_TAB_INDEX";
        public static final String KEY_VERIFY_CODE = "KEY_VERIFY_CODE";
        public static final String KEY_PHONE_NUMBER = "KEY_PHONE_NUMBER";
        public static final String KEY_LESSON_UPCOMING = "KEY_LESSON_UPCOMING";
        public static final String KEY_USER_INFO = "KEY_USER_INFO";
        public static final String KEY_USER_INFO2 = "KEY_USER_INFO2";
        public static final String KEY_PAGER_BEAN = "KEY_PAGER_BEAN";
        public static final String KEY_ARTICLE_URL = "KEY_ARTICLE_URL";
        public static final String KEY_COURSE_ID = "KEY_COURSE_ID";
        public static final String KEY_APPLY_UNIVERSITY = "KEY_APPLY_UNIVERSITY";
        public static final String KEY_TYPE = "KEY_TYPE";
        public static final String KEY_INFORMATION_STEP = "KEY_INFORMATION_STEP";
        public static final String KEY_FILE_PATH = "KEY_FILE_PATH";
        public static final String KEY_IS_VIP = "KEY_IS_VIP";
        public static final String KEY_BEAN = "BEAN";
        public static final String KEY_ACTION = "ACTION";
        public static final String KEY_FROM = "from";
        public static final String KEY_FILE_NAME = "file_title";
        public static final String EXAM_ID = "examid";
        public static final String KEY_SUBJECT_ID = "subject_id";
        public static final String KEY_SUBJECT_NAME = "subject_name";
    }

    public static class Error {
        public static final int ERR_ILLEGAL_PHONE = -0x1001;
        //1	普通异常
        //3001	未绑定手机号
        //3002	有必传参数为空
        //3003	尚未注册
        //3004	参数验证错误
        //3005	提醒
        //3006	登录超时
        public static final int ERR_NORMAL = 1;
        public static final int ERR_NOT_BIND = 3001;
        public static final int ERR_NOT_REGISTER = 3002;
        public static final int ERR_SOME_PARAM_LOST = 3003;
        public static final int ERR_PARAMS_VERIFY = 3004;
        public static final int ERR_NOTICE = 3005;
        public static final int ERR_LOGIN_EXPIRE = 3006;

    }

    public static class Course {

        public static final int TYPE_DO_HOMEWORK = 1;
        public static final int TYPE_QUIZ = 2;

        public static final int TYPE_LIVE_COURSE = 1;
        public static final int TYPE_VIDEO_COURSE = 2;

        public static final int ORDER_STATUS_NOT_ORDER = 0;
        public static final int ORDER_STATUS_HAS_ORDER = 1;

        public static final int PLAY_STATUS_NOT_START = 0;
        public static final int PLAY_STATUS_LIVING = 1;
        public static final int PLAY_STATUS_LIVE_END = 2;
        public static final int PLAY_STATUS_REPLAY = 3;

        public static final int HOMEWORK_NOT_FINISHED = 0;
        public static final int HOMEWORK_FINISHED = 1;

        public static final int NO_QUIZ = 0;
        public static final int NO_HOMEWORK = 0;
        public static final int HAS_QUIZ = 1;

    }

    public static class MiPush {

        public static final String APP_ID = "2882303761517963201";
        public static final String APP_KEY = "5281796372201";

        public static final int MSG_TYPE_INTENT = 1001;//指定页面
    }

    public static class Bugly {
        public static final String APP_ID = "6ec4722d83";
        public static final String APP_KEY = "c703c129-7b51-42cb-bcfe-be31030cc36a";
    }

    public static class Channel {
        public static final String KEY_SITE_ID = "site_id";
        public static final String KEY_MESSAGE_TYPE = "message_type";
        public static final String KEY_PROVINCE = "province";
    }



    public static final int QUES_TYPE_TIANKONG = 1;//填空
    public static final int QUES_TYPE_DANXUAN= 2;//

}
