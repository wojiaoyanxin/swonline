package com.sunlands.intl.yingshi.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.arialyy.annotations.Download;
import com.arialyy.aria.core.download.DownloadTask;

/**
 * 后台下载服务
 */
public class DownloadService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Download.onTaskComplete
    void taskComplete(DownloadTask task) {
        //下载完成  存入数据库

    }

}
