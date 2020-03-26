package com.sunlands.intl.yingshi.dialog;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arialyy.annotations.Download;
import com.arialyy.aria.core.Aria;
import com.arialyy.aria.core.download.DownloadTask;
import com.arialyy.aria.core.inf.IEntity;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.PathUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.sunlands.comm_core.base.BaseViewImpl;
import com.sunlands.comm_core.helper.BaseDialogHelper;
import com.sunlands.comm_core.helper.RxBindingHelper;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.bean.AppUpdataBean;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import java.io.File;

/**
 * 创 建 人: xueh
 * 创建日期: 2019/5/7 16:32
 * 备注：版本更新，分为强制和普通
 */
public class AppUpdataDialog extends BaseDialogHelper implements BaseViewImpl.OnClickListener {
    static boolean mCoerce;
    static String apkUrl = "";
    static String apkFilePath;
    private ProgressBar mProgress;
    static String mContent;
    private TextView mTv_updata_now;

    public static AppUpdataDialog getInstance(AppUpdataBean appUpdataBean) {
        AppUpdataDialog dialogFragment = new AppUpdataDialog();
        mContent = appUpdataBean.getInfo().getSummary();
        mCoerce = appUpdataBean.getInfo().getIsCompel() == 1 ? true : false;
        if (!StringUtils.isEmpty(appUpdataBean.getInfo().getUrl())) {
            apkUrl = appUpdataBean.getInfo().getUrl();
        }
        apkFilePath = PathUtils.getExternalAppCachePath() + File.separator + "version" + appUpdataBean.getInfo().getVersion() + "APP.apk";
        if (mCoerce) {
            dialogFragment.setCanceledBack(false);
            dialogFragment.setCanceledOnTouchOutside(false);
        } else {
            dialogFragment.setCanceledBack(false);
            dialogFragment.setCanceledOnTouchOutside(true);
        }
        dialogFragment.setGravity(Gravity.CENTER);
        return dialogFragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_app_updata_layout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView tv_updata_content = getView().findViewById(R.id.tv_updata_content);
        TextView tv_withhold_updata = getView().findViewById(R.id.tv_withhold_updata);
        mTv_updata_now = getView().findViewById(R.id.tv_updata_now);
        TextView tv_coerce_updata_now = getView().findViewById(R.id.tv_coerce_updata_now);
        mProgress = getView().findViewById(R.id.progress);
        if (!TextUtils.isEmpty(mContent)) {
            tv_updata_content.setText(mContent);
        }
        if (mCoerce) {
            getView().findViewById(R.id.tv_updata_group).setVisibility(View.GONE);
            tv_coerce_updata_now.setVisibility(View.VISIBLE);
        } else {
            getView().findViewById(R.id.tv_updata_group).setVisibility(View.VISIBLE);
            tv_coerce_updata_now.setVisibility(View.GONE);
        }
        RxBindingHelper.setOnClickListener(tv_withhold_updata, this);
        RxBindingHelper.setOnClickListener(mTv_updata_now, this);
        RxBindingHelper.setOnClickListener(tv_coerce_updata_now, this);
        Aria.download(this).register();


    }

    @Override
    public void onDetach() {
        super.onDetach();
        Aria.download(this).unRegister();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_withhold_updata) {
            dismiss();
        } else if (v.getId() == R.id.tv_updata_now) {
            startDown();
        } else if (v.getId() == R.id.tv_coerce_updata_now) {
            startDown();
        }
    }

    private void startDown() {
        if (FileUtils.isFileExists(apkFilePath)) {
            installAPK();
            return;
        }
        if (Aria.download(this).load(apkUrl).getTaskState() == IEntity.STATE_RUNNING) {
            Aria.download(this).load(apkUrl).stop();
        } else {
            Aria.download(this)
                    .load(apkUrl)
                    .useServerFileName(true)
                    .setFilePath(apkFilePath)
                    .start();
        }
    }

    @Download.onTaskStart
    void taskStart(DownloadTask task) {
        if (task.getKey().equals(apkUrl)) {
            mProgress.setVisibility(View.VISIBLE);
            mTv_updata_now.setText("暂停下载");
        }
    }

    @Download.onTaskRunning
    protected void running(DownloadTask task) {
        long len = task.getFileSize();
        if (mProgress.getVisibility() != View.VISIBLE) {
            mProgress.setVisibility(View.VISIBLE);
        }
        if (len == 0) {
            mProgress.setProgress(0);
        } else {
            mProgress.setProgress(task.getPercent());
        }
    }

    @Download.onTaskResume
    void taskResume(DownloadTask task) {
        if (task.getKey().equals(apkUrl)) {
            mTv_updata_now.setText("暂停下载");
        }
    }

    @Download.onTaskStop
    void taskStop(DownloadTask task) {
        if (task.getKey().equals(apkUrl)) {
            mTv_updata_now.setText("继续下载");
        }
    }

    @Download.onTaskCancel
    void taskCancel(DownloadTask task) {
        if (task.getKey().equals(apkUrl)) {
            ToastUtils.showShort("取消下载");
        }
    }

    @Download.onTaskFail
    void taskFail(DownloadTask task) {
        if (task.getKey().equals(apkUrl)) {
            ToastUtils.showShort("下载失败");
        }
    }

    @Download.onTaskComplete
    void taskComplete(DownloadTask task) {
        installAPK();
    }

    private void installAPK() {
        AndPermission.with(getActivity())
                .install()
                .file(FileUtils.getFileByPath(apkFilePath))
                .onDenied(new Action<File>() {
                    @Override
                    public void onAction(File data) {
                        ToastUtils.showShort("未授予权限");
                    }
                })
                .start();
//        AppUtils.installApp(task.getDownloadEntity().getDownloadPath());
        this.dismiss();
    }
}
