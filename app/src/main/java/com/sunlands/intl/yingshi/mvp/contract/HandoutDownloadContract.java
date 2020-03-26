package com.sunlands.intl.yingshi.mvp.contract;

import android.content.Context;

import com.sunlands.intl.yingshi.base.BaseErrorCallback;
import com.sunlands.intl.yingshi.base.IBaseView;
import com.sunlands.intl.yingshi.bean.TempCourseBean;

public interface HandoutDownloadContract {

    interface IHandoutDownloadView extends IBaseView {
        void onHandoutDownloadSuccess();
        void onProgress(int progress, long total);
        void onHandoutDownloadFailed(int code, String msg);
    }

    public interface IHandoutDownloadPresenter {
        void handoutDownload(TempCourseBean tempCourseBean);
    }

    interface IHandoutDownloadModel {

        void handoutDownload(Context mContext, String url, String name, HandoutDownloadCallback callback);

        interface HandoutDownloadCallback extends BaseErrorCallback {
            void onSuccess(String path);
            void onProgress(int progress, long total);
        }
    }
}