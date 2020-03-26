package com.sunlands.intl.yingshi.js;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.webkit.JavascriptInterface;

import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.just.agentweb.AgentWeb;
import com.sunlands.intl.yingshi.ui.activity.home.apply.ApplyUniversityBean;
import com.sunlands.intl.yingshi.ui.activity.home.apply.ApplyActivity;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import java.util.List;

/**
 * js调用android接口
 */
public class AndroidInterface {

    private Handler deliver = new Handler(Looper.getMainLooper());
    private AgentWeb agent;
    private Context context;

    public AndroidInterface(AgentWeb agent, Context context) {
        this.agent = agent;
        this.context = context;
    }

    public AndroidInterface(AgentWeb agent, Context context, JSCallback jsCallback) {
        this.agent = agent;
        this.context = context;
        this.jsCallback = jsCallback;
    }

    /**
     * 立即申请
     *
     * @param msg 选择的院校对象信息
     */
    @JavascriptInterface
    public void apply(final String msg) {

        deliver.post(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                try {
                    ApplyUniversityBean applyUniversityBean = gson.fromJson(msg,
                            ApplyUniversityBean.class);
                    ApplyActivity.show(context, applyUniversityBean);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                    ToastUtils.showShort(e.getMessage());
                }
            }
        });
    }

    /**
     * 开始录音
     * @param id 题目id
     * @param max 最大录音时长
     */
    @JavascriptInterface
    public void H5ToNativeStartRecord(final String id, String max) {

        deliver.post(new Runnable() {
            @Override
            public void run() {
                //检察权限
                AndPermission.with(context)
                        .runtime()
                        .permission(Permission.RECORD_AUDIO
                        )
                        .onGranted(new Action<List<String>>() {
                            @Override
                            public void onAction(List<String> permissions) {

                                jsCallback.H5ToNativeStartRecord(id, max);
                            }
                        })
                        .onDenied(new Action<List<String>>() {
                            @Override
                            public void onAction(@NonNull List<String> permissions) {
                                ToastUtils.showShort("请授予录音权限");
                            }
                        })
                        .start();
            }
        });
    }

    /**
     * 录音完成提交
     * @param id 题目id
     */
    @JavascriptInterface
    public void H5ToNativeSubmit(final String id) {

        deliver.post(new Runnable() {
            @Override
            public void run() {
                jsCallback.H5ToNativeSubmit(id);
            }
        });
    }

    /**
     * 显示原生loading
     * @param id
     */
    @JavascriptInterface
    public void NativeToH5ShowLoading(final String id) {

        deliver.post(new Runnable() {
            @Override
            public void run() {
                //jsCallback.NativeToH5ShowLoading(id);
            }
        });
    }

    /**
     * 隐藏原生loading
     * @param id
     */
    @JavascriptInterface
    public void NativeToH5HideLoading(final String id) {

        deliver.post(new Runnable() {
            @Override
            public void run() {

                //    jsCallback.NativeToH5HideLoading(id);

            }
        });
    }

    /**
     * 关闭当前页面
     */
    @JavascriptInterface
    public void H5ToNativeCloseWebview() {

        deliver.post(new Runnable() {
            @Override
            public void run() {

                jsCallback.H5ToNativeCloseWebview();

            }
        });
    }

    private JSCallback jsCallback;

    public interface JSCallback {

        void H5ToNativeCloseWebview();

        void H5ToNativeStartRecord(String id, String max);//开始录音

        void H5ToNativeSubmit(String id);//提交

        void NativeToH5ShowLoading(String id);//显示

        void NativeToH5HideLoading(String id);//隐藏

    }


}