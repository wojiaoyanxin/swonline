package com.sunlands.intl.yingshi.ui.activity.home.studystat.presenter;

import com.sunlands.comm_core.base.mvp.MvpBasePresenter;
import com.sunlands.comm_core.net.BaseModel;
import com.sunlands.comm_core.net.MVPModelCallbacks;
import com.sunlands.intl.yingshi.bean.StudyStatBean;
import com.sunlands.intl.yingshi.groovy.CheckNet;
import com.sunlands.intl.yingshi.ui.activity.home.studystat.IStudyStatContract;
import com.sunlands.intl.yingshi.ui.activity.home.studystat.model.StudyStatModel;

/**
 * 当前包名: com.sunlands.intl.yingshi.ui.activity.home.studystat.presenter
 * 创 建 人: xueh
 * 创建日期: 2019/3/14 15:17
 * 备注：
 */
public class StudyStatPresenter extends MvpBasePresenter<IStudyStatContract.IStudyStatView, StudyStatModel> {

    public StudyStatPresenter(IStudyStatContract.IStudyStatView iStudyStatView) {
        super(iStudyStatView);
    }

    @Override
    protected StudyStatModel createModel() {
        return new StudyStatModel();
    }

    @CheckNet
    public void getRecordsStat(String type) {
        getView().showLoading();
        getModel().getRecordsStat(type, getView().getLifecycleSubject(), new MVPModelCallbacks<StudyStatBean>() {
            @Override
            public void onSuccess(StudyStatBean data) {
                getView().onRecordsStatSuccess(data);
                getView().hideLoading();
            }

            @Override
            public void onException(BaseModel model) {
                getView().hideLoading();
            }

            @Override
            public void onError(Throwable e) {
                getView().hideLoading();
            }
        });
    }
}
