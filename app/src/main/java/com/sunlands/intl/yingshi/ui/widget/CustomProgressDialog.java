package com.sunlands.intl.yingshi.ui.widget;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sunlands.intl.yingshi.R;

import java.lang.ref.WeakReference;

/**
 * Created by yanxin on 2019/3/8.
 */

public class CustomProgressDialog extends Dialog {

    private WeakReference<Context> mContext = new WeakReference<>(null);

    private volatile static CustomProgressDialog sDialog;

    public CustomProgressDialog(Context context, CharSequence message) {

        super(context, R.style.CustomProgressDialog);

        mContext = new WeakReference<>(context);

        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_progressbar, null);
        TextView tvMessage = (TextView) view.findViewById(R.id.tv_show);
        if (!TextUtils.isEmpty(message)) {
            tvMessage.setText(message);
        }
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addContentView(view, lp);
    }


    public static synchronized void showLoading(Context context) {

        try {
            showLoading(context, "加载中...");
        } catch (Exception e) {

        }
    }

    public static synchronized void showLoading(Context context, CharSequence message) {
        showLoading(context, message, true);
    }

    public static synchronized void showLoading(Context context, CharSequence message, boolean cancelable) {
//        if (sDialog != null && sDialog.isShowing()) {
//            sDialog.dismiss();
//        }

        if (context == null || !(context instanceof Activity)) {
            return;
        }
        if (sDialog == null) {
            sDialog = new CustomProgressDialog(context, "加载中...");
            sDialog.setCancelable(true);
        }

        if (sDialog != null && !sDialog.isShowing() && !((Activity) context).isFinishing()) {
            sDialog.show();
        }
    }

    public static synchronized void showLoadingDownLoad(Context context) {

        if (context == null || !(context instanceof Activity)) {
            return;
        }
        if (sDialog == null) {
            sDialog = new CustomProgressDialog(context, "下载中...");
            sDialog.setCancelable(false);
        }
        if (sDialog != null) {
            sDialog.show();
        }
    }

    public static synchronized void stopLoading() {

        try {
            if (sDialog != null && sDialog.isShowing()) {
                sDialog.dismiss();
            }
            sDialog = null;
        } catch (Exception e) {

        }
    }
}
