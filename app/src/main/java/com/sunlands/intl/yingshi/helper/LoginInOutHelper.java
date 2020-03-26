package com.sunlands.intl.yingshi.helper;

import android.content.Context;
import android.os.Environment;

import com.arialyy.aria.core.Aria;
import com.blankj.utilcode.util.ActivityUtils;
import com.example.statisti_lib.entity.StatistiParameterInfo;
import com.sunlands.comm_core.helper.UserInfoHelper;
import com.sunlands.intl.yingshi.BuildConfig;
import com.sunlands.intl.yingshi.greendao.db.LoginInfo;
import com.sunlands.intl.yingshi.greendao.helper.DbHelper;
import com.sunlands.intl.yingshi.ui.activity.MainActivity;
import com.sunlands.intl.yingshi.ui.login.view.NewLoginActivity;
import com.sunlands.intl.yingshi.ui.my.view.SettingsActivity;
import com.sunlands.intl.yingshi.util.SPHelper;
import com.sunlands.sunlands_live_sdk.offline.OfflineManager;
import com.tencent.liteav.demo.play.im.IMUtils;

/**
 * 当前包名: com.sunlands.intl.yingshi.helper
 * 创 建 人: xueh
 * 创建日期: 2019/3/2 14:38
 * 备注：
 */
public class LoginInOutHelper {

    public LoginInOutHelper() {

    }

    public static void loginOut(Context context) {
        Aria.download(context).stopAllTask();
        DbHelper.getInstance().getLoginInfoDao().deleteAll();
        StatistiParameterInfo.reset();
        UserInfoHelper.getInstance().reset();
        IMUtils.getInstance().logout();
        ActivityUtils.startActivity(NewLoginActivity.class);
        ActivityUtils.finishActivity(MainActivity.class);
        ActivityUtils.finishActivity(SettingsActivity.class);
    }

    public static void loginIn(LoginInfo data) {
        DbHelper.getInstance().getLoginInfoDao().deleteAll();
        DbHelper.getInstance().getLoginInfoDao().insert(data);
        SPHelper.setUserPhone(data.getFullTell());
        UserInfoHelper.getInstance().setUserInfo(data.getUserId(), data.getStuId(), data.getSessionKey());
        UserInfoHelper.getInstance().setUserInfo2(data.getUsername(), data.getAvatar(), data.getFullTel());
        StatistiParameterInfo.getInstance().userBuilder()
                .setUserId(data.getUserId())
                .setApi_env(BuildConfig.BUILD_TYPE)
                .build();
        OfflineManager.getInstance().setRootFolder(Environment.getExternalStorageDirectory().getPath() + "/sunlands_live" + UserInfoHelper.getInstance().getUserId());
    }
}
