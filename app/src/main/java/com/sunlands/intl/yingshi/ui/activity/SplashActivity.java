package com.sunlands.intl.yingshi.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.View;

import com.blankj.utilcode.util.SpanUtils;
import com.shangde.lepai.uilib.view.text.QMUITouchableSpan;
import com.sunlands.comm_core.base.DActivity;
import com.sunlands.comm_core.utils.CommonUtils;
import com.sunlands.comm_core.utils.rx.rxjava.RxJavaUtils;
import com.sunlands.intl.yingshi.App;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.constant.RestApi;
import com.sunlands.intl.yingshi.dialog.TwoBtTitleDialog;
import com.sunlands.intl.yingshi.helper.LoginUserInfoHelper;
import com.sunlands.intl.yingshi.helper.NotifyRationale;
import com.sunlands.intl.yingshi.helper.UpdataRequestHelper;
import com.sunlands.intl.yingshi.ui.login.view.NewLoginActivity;
import com.sunlands.intl.yingshi.util.SPHelper;
import com.sunlands.intl.yingshi.web.WebViewActivity;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * 启动页面
 */

public class SplashActivity extends DActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.SunLandsApp);
        super.onCreate(savedInstanceState);
    }

    private void toMain() {
        MainActivity.show(this);
        finish();
    }

    private void toLogin() {
        NewLoginActivity.Companion.show(this);
        finish();
    }

    private void requestNotification() {
        AndPermission.with(this)
                .notification()
                .permission()
                .rationale(new NotifyRationale())
                .onGranted(new Action<Void>() {
                    @Override
                    public void onAction(Void data) {
                        SPHelper.isFirstLoad(false);
                        if (LoginUserInfoHelper.getInstance().isLogin()) {
                            toMain();
                        } else {
                            toLogin();
                        }
                    }
                })
                .onDenied(new Action<Void>() {
                    @Override
                    public void onAction(Void data) {
                        SplashActivity.this.finish();
                    }
                })
                .start();
    }

    private void requestPermission() {
        AndPermission.with(this)
                .runtime()
                .permission(Permission.Group.STORAGE, Permission.Group.LOCATION)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        requestNotification();
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(@NonNull List<String> permissions) {
                        SplashActivity.this.finish();
                    }
                })
                .start();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public int getCreateViewLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
    }

    @Override
    public void initDataAfterView() {
    }

    @Override
    public void onResume() {
        super.onResume();
        mImmersionBar.fitsSystemWindows(false).autoDarkModeEnable(true).init();
    }

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        new UpdataRequestHelper().getUserInfoUpdata();
        RxJavaUtils.delay(1, new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                TwoBtTitleDialog dialog = TwoBtTitleDialog.getInstance("服务协议和隐私政策", "暂不使用", "同意", "");
                dialog.setOnLeftClick(new TwoBtTitleDialog.onLeftClick() {
                    @Override
                    public void onLeft() {
                        finish();
                    }
                });
                dialog.setOnRightClick(new TwoBtTitleDialog.onRightClick() {
                    @Override
                    public void onRight() {
                        requestPermission();
                    }
                });
                if (App.sLPApplication.channel.equals("tencent")) {
                    if (SPHelper.getFirstLoad()) {
                        dialog.show(getSupportFragmentManager(), "");
                    }else {
                        requestPermission();
                    }
                } else {
                    requestPermission();
                }
            }
        });
    }

    @Override
    public void initDataBeforeView() {
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }
        if (SPHelper.getFirstLoad() == true) {
            SPHelper.setLocalTime(System.currentTimeMillis());
        }
        super.initDataBeforeView();
    }

    @Override
    public void initListener() {

    }
}
