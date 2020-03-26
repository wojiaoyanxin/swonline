package com.sunlands.intl.yingshi.ui.activity.home.studystat.model;

import android.arch.lifecycle.Lifecycle;

import com.sunlands.comm_core.base.mvp.MvpBaseModel;
import com.sunlands.comm_core.net.MVPModelCallbacks;
import com.sunlands.comm_core.net.ServiceGenerator;
import com.sunlands.intl.yingshi.bean.StudyStatBean;
import com.sunlands.intl.yingshi.constant.RestApi;
import com.sunlands.intl.yingshi.ui.activity.home.studystat.IStudyStatContract;

import io.reactivex.subjects.PublishSubject;

/**
 * 当前包名: com.sunlands.intl.yingshi.ui.activity.home.studystat.model
 * 创 建 人: xueh
 * 创建日期: 2019/3/14 15:10
 * 备注：
 */
public class StudyStatModel extends MvpBaseModel<RestApi> implements IStudyStatContract.IStudyStatModel {
    @Override
    protected RestApi initApi() {
        return ServiceGenerator.getService(RestApi.class);
    }

    @Override
    public void getRecordsStat(String type,PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<StudyStatBean> mMVPModelCallbacks) {
        deploy(getApi().getRecordsStat(type),mPublishSubject,mMVPModelCallbacks);
    }
}
