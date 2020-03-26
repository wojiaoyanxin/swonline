package com.sunlands.intl.yingshi.ui.widget;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.bean.event.EventMessage;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by yanxin on 2019/3/8.
 */

public class CompleteUserInfoDialog extends Dialog {


    private volatile static CompleteUserInfoDialog sDialog;

    public CompleteUserInfoDialog(Context context) {

        super(context, R.style.BottomDialog2);

        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_complete_userinfo, null);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addContentView(view, lp);
        view.findViewById(R.id.jump).setOnClickListener(v -> hidden());
        view.findViewById(R.id.sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hidden();
                EventBus.getDefault().post(new EventMessage(EventMessage.EVENT_TO_ACTIVITY));
            }
        });
    }


    public static synchronized void show(Context context) {

        if (context == null || !(context instanceof Activity)) {
            return;
        }
        if (sDialog == null) {
            sDialog = new CompleteUserInfoDialog(context);
            sDialog.setCancelable(true);
        }

        if (sDialog != null && !sDialog.isShowing() && !((Activity) context).isFinishing()) {
            sDialog.show();
        }
    }

    public static synchronized void hidden() {

        try {
            if (sDialog != null && sDialog.isShowing()) {
                sDialog.dismiss();
            }
            sDialog = null;
        } catch (Exception e) {

        }
    }
}
