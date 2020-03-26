package com.sunlands.intl.yingshi.helper;

import com.sunlands.intl.yingshi.greendao.db.LoginInfo;
import com.sunlands.intl.yingshi.greendao.helper.DbHelper;

/**
 * @author: xuehao create time: 2019/2/14 10:11
 * tag: class//
 * description:
 */
public class LoginUserInfoHelper {

    private volatile static LoginUserInfoHelper singleton;

    private LoginUserInfoHelper() {
    }

    public static LoginUserInfoHelper getInstance() {
        if (singleton == null) {
            synchronized (LoginUserInfoHelper.class) {
                if (singleton == null) {
                    singleton = new LoginUserInfoHelper();
                }
            }
        }
        return singleton;
    }

    public LoginInfo getUserInfo() {
        return DbHelper.getInstance().getLoginInfoDao().queryBuilder().unique();
    }

    public void updataStuid(int stuId, String university) {
        LoginInfo userInfo = getUserInfo();
        userInfo.setStuId(stuId);
        userInfo.setUniversity(university);
        DbHelper.getInstance().getLoginInfoDao().update(userInfo);
    }

    public void updataAvatar(String url) {
        LoginInfo userInfo = getUserInfo();
        userInfo.setAvatar(url);
        DbHelper.getInstance().getLoginInfoDao().update(userInfo);
    }

    public boolean isLogin() {
        if (DbHelper.getInstance().getLoginInfoDao().queryBuilder().unique() != null && DbHelper.getInstance().getLoginInfoDao().queryBuilder().unique().getUserId() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
