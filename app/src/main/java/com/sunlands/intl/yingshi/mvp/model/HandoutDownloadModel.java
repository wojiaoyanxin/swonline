package com.sunlands.intl.yingshi.mvp.model;

import android.content.Context;

import com.sunlands.comm_core.utils.rx.subsciber.FileDownLoadObserver;
import com.sunlands.intl.yingshi.base.BaseModel;
import com.sunlands.intl.yingshi.constant.Constants;
import com.sunlands.intl.yingshi.mvp.contract.HandoutDownloadContract;
import com.sunlands.intl.yingshi.ui.widget.CustomProgressDialog;
import com.sunlands.intl.yingshi.util.DownLoadUtil;

import java.io.File;


/**
 * 文件名: HandoutDownloadModel
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/10/10
 */
public class HandoutDownloadModel extends BaseModel implements HandoutDownloadContract
        .IHandoutDownloadModel {

    @Override
    public void handoutDownload(Context mContext, String url, String name, HandoutDownloadCallback callback) {

        CustomProgressDialog.showLoadingDownLoad(mContext);

        DownLoadUtil.downloadFile(url, Constants.CACHE_DIR, name, new FileDownLoadObserver<File>() {
            @Override
            public void onDownLoadSuccess(File file) {
                callback.onSuccess(file.getAbsolutePath());
            }

            @Override
            public void onDownLoadFail(Throwable throwable) {
                callback.onFailure(throwable.hashCode(), throwable.getMessage());
            }

            @Override
            public void onProgress(int progress, long total) {
                callback.onProgress(progress,total);
            }
        });

    }

}
