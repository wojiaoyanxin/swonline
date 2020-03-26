
package com.sunlands.intl.yingshi.helper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.blankj.utilcode.util.AppUtils;
import com.sunlands.intl.yingshi.R;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RequestExecutor;


public class NotifyRationale implements Rationale<Void> {

    @Override
    public void showRationale(Context context, Void data, final RequestExecutor executor) {
        new AlertDialog.Builder(context).setCancelable(false)
                .setMessage("为了更好的用户体验，请允许" + AppUtils.getAppName() + "申请通知权限")
                .setPositiveButton(R.string.setting, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        executor.execute();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        executor.cancel();
                        dialog.dismiss();
                    }
                })
                .show();
    }
}