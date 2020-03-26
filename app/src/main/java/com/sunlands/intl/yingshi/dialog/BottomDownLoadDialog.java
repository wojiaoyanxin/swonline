package com.sunlands.intl.yingshi.dialog;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.arialyy.annotations.Download;
import com.arialyy.aria.core.Aria;
import com.arialyy.aria.core.download.DownloadTask;
import com.sunlands.comm_core.helper.BaseDialogHelper;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.greendao.db.MyDownLoadInfo;
import com.sunlands.intl.yingshi.ui.my.handout.adapter.DownloadAdapter;
import com.sunlands.intl.yingshi.util.Utils;

import java.util.List;

public class BottomDownLoadDialog extends BaseDialogHelper {

    private static List<MyDownLoadInfo> mData;
    private DownloadAdapter mAdapter;

    public static BottomDownLoadDialog getInstance(List<MyDownLoadInfo> listBean) {
        mData = listBean;
        BottomDownLoadDialog dialogFragment = new BottomDownLoadDialog();
        dialogFragment.setCanceledBack(true);
        dialogFragment.setCanceledOnTouchOutside(true);
        dialogFragment.setGravity(Gravity.BOTTOM);
        return dialogFragment;
    }


    @Override
    public int getLayoutId() {
        return R.layout.dialog_bottom_download_layout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();
        if (getDialog().getWindow() != null) {
            WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            params.windowAnimations = R.style.BottomToTopAnim;
            getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }
        view.findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                mData = null;
            }
        });
        Aria.download(this).register();
        RecyclerView recycle_view = view.findViewById(R.id.recycle_view);
        recycle_view.setLayoutManager(new LinearLayoutManager(Utils.getApp()));
        mAdapter = new DownloadAdapter(Utils.getContext(), mData);
        recycle_view.setLayoutManager(new LinearLayoutManager(Utils.getContext()));
        recycle_view.setAdapter(mAdapter);
    }

    @Download.onPre
    void onPre(DownloadTask task) {
        mAdapter.updateState(task.getEntity());
    }

    @Download.onWait
    void onWait(DownloadTask task) {
        mAdapter.updateState(task.getEntity());
    }

    @Download.onTaskStart
    void taskStart(DownloadTask task) {
        mAdapter.updateState(task.getEntity());
    }

    @Download.onTaskResume
    void taskResume(DownloadTask task) {
        mAdapter.updateState(task.getEntity());
    }

    @Download.onTaskStop
    void taskStop(DownloadTask task) {
        mAdapter.updateState(task.getEntity());
    }

    @Download.onTaskCancel
    void taskCancel(DownloadTask task) {
        mAdapter.updateState(task.getEntity());
    }

    @Download.onTaskFail
    void taskFail(DownloadTask task) {
        mAdapter.updateState(task.getEntity());
    }

    @Download.onTaskComplete
    void taskComplete(DownloadTask task) {
        mAdapter.updateState(task.getEntity());
    }

    @Download.onTaskRunning()
    void taskRunning(DownloadTask task) {
        mAdapter.setProgress(task.getEntity());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Aria.download(this).unRegister();
    }

    @Override
    public void onPause() {
        super.onPause();
        dismiss();
    }
}
