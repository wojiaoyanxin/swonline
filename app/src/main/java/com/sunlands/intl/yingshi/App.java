package com.sunlands.intl.yingshi;

import android.content.Context;
import android.graphics.Color;
import android.os.Environment;
import android.os.Handler;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;
import android.view.Gravity;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.arialyy.annotations.Download;
import com.arialyy.aria.core.Aria;
import com.arialyy.aria.core.download.DownloadTask;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.meituan.android.walle.WalleChannelReader;
import com.sunlands.comm_core.helper.UserInfoHelper;
import com.sunlands.comm_core.statemanager.loader.StateRepository;
import com.sunlands.comm_core.utils.CommonUtils;
import com.sunlands.intl.yingshi.constant.Constants;
import com.sunlands.intl.yingshi.greendao.db.LoginInfo;
import com.sunlands.intl.yingshi.greendao.helper.DBSaveUtils;
import com.sunlands.intl.yingshi.greendao.helper.DbHelper;
import com.sunlands.intl.yingshi.helper.LoginUserInfoHelper;
import com.sunlands.intl.yingshi.helper.state.NoDataState;
import com.sunlands.intl.yingshi.helper.state.NoOrderDataState;
import com.sunlands.intl.yingshi.helper.state.NoSearchDataState;
import com.sunlands.intl.yingshi.util.DLog;
import com.sunlands.sunlands_live_sdk.SunlandsLiveSdk;
import com.sunlands.sunlands_live_sdk.offline.OfflineManager;
import com.sunlands.sunlands_live_sdk.utils.LiveNetEnv;
import com.tencent.liteav.demo.play.bean.MaterialBean;
import com.tencent.liteav.demo.play.im.IMUtils;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.commonsdk.UMConfigure;


/**
 * 文件名: App
 * 描    述: [app总入口]
 * 创建人: duzzi
 * 创建时间: 2018/7/27
 */
public class App extends MultiDexApplication {
    //gradlew compileDebugSources --stacktrace -info
    public static App sLPApplication;
    private static Handler mHandler;
    public static double longitude;
    public static double latitude;
    // APP_ID 替换为你的应用从官方网站申请到的合法appID
    private static final String APP_ID = Constants.APP_ID;
    // IWXAPI 是第三方app和微信通信的openApi接口
    public IWXAPI api;
    public String channel;

    public IWXAPI getApi() {
        return api;
    }

    /**
     * 注册微信sdk
     */

    private void registerWX() {
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, APP_ID, true);
        // 将应用的appId注册到微信
        api.registerApp(APP_ID);
    }

    /**
     * 项目启动初次运行
     */
    @Override
    public void onCreate() {
        super.onCreate();
        sLPApplication = this;
        mHandler = new Handler();
        DLog.setDebuggable(BuildConfig.DEBUG);
        DbHelper.setDatabase();
        initAMapLocation();
        initChannel();
        Aria.download(this).register();
        Aria.get(this).getDownloadConfig().setMaxTaskNum(3);
        Aria.get(this).getDownloadConfig().setMaxSpeed(0);
        registerWX();
        ToastUtils.setGravity(Gravity.CENTER, 0, 0);
        ToastUtils.setBgColor(Color.parseColor("#a0000000"));
        ToastUtils.setMsgColor(CommonUtils.getColor(R.color.cl_ffffff));
        StateRepository.registerState(NoDataState.STATE, NoDataState.class);
        StateRepository.registerState(NoOrderDataState.STATE, NoOrderDataState.class);
        StateRepository.registerState(NoSearchDataState.STATE, NoSearchDataState.class);
        SunlandsLiveSdk.getInstance().setEnvironment(LiveNetEnv.Env.RELEASE);
        SunlandsLiveSdk.getInstance().setLogLevel(1);
        OfflineManager.getInstance().init(this, 3);
        OfflineManager.getInstance().setEnvironment(LiveNetEnv.Env.RELEASE);
        IMUtils.getInstance().initIm(this);
        if (LoginUserInfoHelper.getInstance().isLogin()) {
//            StatistiParameterInfo.getInstance().userBuilder()
//                    .setUserId(LoginUserInfoHelper.getInstance().getUserInfo().getUserId())
//                    .setApi_env(BuildConfig.BUILD_TYPE)
//                    .setStats_id(1003)
//                    .build();
            LoginInfo mUserInfo = LoginUserInfoHelper.getInstance().getUserInfo();
            UserInfoHelper.getInstance().setUserInfo(mUserInfo.getUserId(), mUserInfo.getStuId(), mUserInfo.getSessionKey());
            UserInfoHelper.getInstance().setUserInfo2(mUserInfo.getUsername(), mUserInfo.getAvatar(), mUserInfo.getFullTel());
            OfflineManager.getInstance().setRootFolder(Environment.getExternalStorageDirectory().getPath() + "/sunlands_live" + UserInfoHelper.getInstance().getUserId());
        }
        //  MobSDK.init(this);
    }

    /**
     * 渠道功能注册
     */

    private void initChannel() {
        UMConfigure.setLogEnabled(false);
        channel = WalleChannelReader.getChannel(this.getApplicationContext());
        if (TextUtils.isEmpty(channel)) {
            channel = "test";
        }
        UMConfigure.init(this, null, channel, UMConfigure.DEVICE_TYPE_PHONE, "");
    }

    /**
     * 地图定位sdk注册
     */

    private void initAMapLocation() {

        AMapLocationClient mLocationClient = new AMapLocationClient(getApplicationContext());
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationClient.setLocationOption(mLocationOption);
        mLocationClient.setLocationListener(new AMapLocationListener() {

            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {

                String errorInfo = aMapLocation.getErrorInfo();
                //获取维度
                latitude = aMapLocation.getLatitude();
                //获取经度
                longitude = aMapLocation.getLongitude();
            }
        });
        mLocationClient.startLocation();
    }

    public static App getApplication() {
        return sLPApplication;
    }

    public static Handler getMainHandler() {
        return mHandler;
    }

    public static Context getContext() {
        return sLPApplication;
    }

    @Download.onTaskComplete
    void taskComplete(DownloadTask task) {
        String md5Code = task.getDownloadEntity().getMd5Code();
        MaterialBean.ListBean listBean = new Gson().fromJson(md5Code, MaterialBean.ListBean.class);
        if (listBean == null) {
            return;
        }
        DBSaveUtils.saveDownloadFile(listBean.getPackage_id(), listBean.getPackage_name(),
                listBean.getCourseId(), listBean.getCourse_name(),
                listBean.getFilePath(), listBean.getCreated_at(), listBean.getFile_name(), listBean.getSid(), listBean.getUrl());
    }

}
