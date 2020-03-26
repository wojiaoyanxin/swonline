package com.sunlands.intl.yingshi.base;


import com.sunlands.intl.yingshi.greendao.db.LoginInfo;
import com.sunlands.intl.yingshi.greendao.helper.DbHelper;
import com.sunlands.intl.yingshi.util.DLog;

import java.util.HashMap;

/**
 * 文件名: BaseModel
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/6/6
 */
public class BaseModel {
    public static final int SUCCESS = 0;
    public static final int ERROR_UNKNOWN = -1000;
    public static final int ERROR_NULL = -1001;
    public static final int ERROR_JSON = -1002;
    protected HashMap<String, String> mBodyHashMap = new HashMap<>();
    protected HashMap<String, String> mHeaderHashMap = new HashMap<>();
    protected String mUrl;

    protected String mStuId = "18";
    protected String mUserId = "23";
    protected String mSessionKey = "5bbd97edad382";

    public BaseModel() {
        LoginInfo loginInfo = DbHelper.getInstance().getLoginInfoDao().queryBuilder().unique();
        if (loginInfo != null) {
            DLog.d("loginInfo: " + loginInfo);
            mStuId = String.valueOf(loginInfo.getStuId());
            mUserId = String.valueOf(loginInfo.getUserId());
            mSessionKey = String.valueOf(loginInfo.getSessionKey());
        }
    }
}
