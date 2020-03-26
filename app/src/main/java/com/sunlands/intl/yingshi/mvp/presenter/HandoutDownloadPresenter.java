package com.sunlands.intl.yingshi.mvp.presenter;

import android.content.Context;

import com.blankj.utilcode.util.ToastUtils;
import com.sunlands.comm_core.utils.CommonUtils;
import com.sunlands.intl.yingshi.base.BasePresenter;
import com.sunlands.intl.yingshi.bean.TempCourseBean;
import com.sunlands.intl.yingshi.greendao.helper.DBSaveUtils;
import com.sunlands.intl.yingshi.mvp.contract.HandoutDownloadContract;
import com.sunlands.intl.yingshi.mvp.model.HandoutDownloadModel;
import com.sunlands.intl.yingshi.ui.widget.CustomProgressDialog;
import com.sunlands.intl.yingshi.util.DLog;
import com.sunlands.intl.yingshi.util.StringUtils;


/**
 * 文件名: HandoutDownloadPresenter
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/10/10
 */
public class HandoutDownloadPresenter extends BasePresenter<HandoutDownloadContract.IHandoutDownloadView>
        implements HandoutDownloadContract.IHandoutDownloadPresenter {

    private HandoutDownloadModel mHandoutDownloadModel;

    public HandoutDownloadPresenter() {

    }

    public HandoutDownloadPresenter(Context context, HandoutDownloadContract.IHandoutDownloadView view) {
        super(context, view);
        mHandoutDownloadModel = new HandoutDownloadModel();
    }


    @Override
    public void detachView() {
        super.detachView();
    }

    @Override
    public void handoutDownload(TempCourseBean tempCourseBean) {
        downloadInternal(tempCourseBean);
    }


    private void downloadInternal(TempCourseBean tempCourseBean) {

        CustomProgressDialog.stopLoading();
        if (!CommonUtils.hasNetWorkConection()) {
            ToastUtils.showShort("请检查网络设置");
            return;
        }
        String handoutUrl = tempCourseBean.getHandout();

        if (StringUtils.isEmpty(handoutUrl)) {
            if (isViewAttached()) {
                getView().showToast("下载地址为空");
                CustomProgressDialog.stopLoading();
            }
            return;
        }

        long l = System.currentTimeMillis();

        String handout_name = tempCourseBean.getHandout_name();
        String courseName = tempCourseBean.getCourseName();
        String courseId = tempCourseBean.getCourseId();
        String date = tempCourseBean.getDate();
        String subjectId = tempCourseBean.getSubjectId();
        String subjectName = tempCourseBean.getSubjectName();

//        DownloadTarget target = Aria.download(mContext).load(handoutUrl);
//        MaterialBean.ListBean listBean = new MaterialBean.ListBean();
//        listBean.setCourseId(courseId);
//        listBean.setUrl(handoutUrl);
//        listBean.setFilePath(Constants.CACHE_DIR + File.separator + handout_name);
//        listBean.setCreated_at(date);
//        listBean.setFile_name(handout_name);
//        listBean.setCourse_name(courseName);
//        listBean.setPackage_id(subjectId);
//        listBean.setPackage_name(subjectName);
//
//        String s = new Gson().toJson(listBean);
//
//        target.getDownloadEntity().setMd5Code(s);
//
//        boolean running = target.isRunning();
//
//        if (running == true) {
//            ToastUtils.showShort("已经添加到下载任务");
//            return;
//        }
//
//        Aria.download(mContext)
//                .load(listBean.getUrl())
//                .setFilePath(Constants.CACHE_DIR + File.separator + listBean.getFile_name())
//                .start();
//
//        ToastUtils.showShort("添加到下载任务成功");

        mHandoutDownloadModel.handoutDownload(mContext, handoutUrl, l + "", new HandoutDownloadContract.IHandoutDownloadModel.HandoutDownloadCallback() {
            @Override
            public void onSuccess(String path) {

                saveHandoutDownloadLog(
                        tempCourseBean.getSubjectId(),
                        tempCourseBean.getSubjectName(),
                        tempCourseBean.getCourseId(),
                        tempCourseBean.getCourseName(),
                        path, tempCourseBean.getDate(),
                        tempCourseBean.getHandout_name(),
                        tempCourseBean.getSid() + "",
                        handoutUrl);
                if (isViewAttached()) {
                    getView().onHandoutDownloadSuccess();
                }
                CustomProgressDialog.stopLoading();
            }

            @Override
            public void onProgress(int progress, long total) {
                getView().onProgress(progress, total);
            }

            @Override
            public void onFailure(int errorCode, String msg) {
                CustomProgressDialog.stopLoading();
                if (isViewAttached()) {
                    DLog.d(errorCode + msg);
                    getView().onHandoutDownloadFailed(errorCode, msg);
                    if("stream was reset: CANCEL" .equals(msg)) {
                        ToastUtils.showShort("请检查网络");
                    }else {
                        getView().showToast(msg);
                    }
                }
            }
        });
    }


    private void saveHandoutDownloadLog(String subjectId, String subjectName,
                                        String courseId, String courseName,
                                        String path, String date,
                                        String mFileName, String sid,
                                        String url) {
        DBSaveUtils.saveDownloadFile(subjectId, subjectName,
                courseId, courseName,
                path, date, mFileName, Integer.parseInt(sid), url);
    }
}
