package com.sunlands.intl.yingshi.util;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.blankj.utilcode.util.ToastUtils;
import com.sunlands.comm_core.net.BaseModel;
import com.sunlands.comm_core.net.RxSchedulers;
import com.sunlands.comm_core.net.ServiceGenerator;
import com.sunlands.intl.yingshi.App;
import com.sunlands.intl.yingshi.constant.RestApi;
import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;

import java.util.Collection;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 文件名: Utils
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/7/27
 */
public class Utils {


    public static Handler getMainThreadHandler() {
        return App.getMainHandler();
    }

    public static Context getContext() {
        return com.blankj.utilcode.util.Utils.getApp();
    }

    public static Application getApp() {
        return com.blankj.utilcode.util.Utils.getApp();
    }

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    //上传经纬度
    public static void putLocation() {

        ServiceGenerator.getService(RestApi.class)
                .putLocation( App.latitude + "", App.longitude + "")
                .compose(RxSchedulers.compose())
                .subscribe(new Observer<BaseModel<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseModel<String> stringBaseModel) {
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public static void putFirstLoadTime() {
        ServiceGenerator.getService(RestApi.class)
                .putTime(AppUtils.getUniquePsuedoID(),
                        App.getApplication().channel,
                        (System.currentTimeMillis() - SPHelper.getLocalTime()) + "")
                .compose(RxSchedulers.compose())
                .subscribe(new Observer<BaseModel<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseModel<String> stringBaseModel) {
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //跳转到微信小程序

    public static void toMiniProgram() {

        try {
            //    app 吊起小程序
            WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
            req.userName = "gh_9eed60d3c778";
            // 填小程序原始id
            req.path = "pages/guide/guide";
            // 拉起小程序页面的可带参路径，不填默认拉起小程序首页
            req.miniprogramType = WXLaunchMiniProgram.Req.MINIPTOGRAM_TYPE_RELEASE;
            // 可选打开 开发版，体验版和正式版
            App.sLPApplication.api.sendReq(req);
        } catch (Exception e) {
            ToastUtils.showShort("检查到您手机没有安装微信，请安装后使用该功能");
        }

    }
}
