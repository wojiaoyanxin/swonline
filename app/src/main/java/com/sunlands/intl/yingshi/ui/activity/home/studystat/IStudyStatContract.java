package com.sunlands.intl.yingshi.ui.activity.home.studystat;

import android.arch.lifecycle.Lifecycle;

import com.sunlands.comm_core.base.ibase.IBaseModel;
import com.sunlands.comm_core.base.ibase.IBaseView;
import com.sunlands.comm_core.net.MVPModelCallbacks;
import com.sunlands.intl.yingshi.bean.StudyStatBean;

import io.reactivex.subjects.PublishSubject;

/**
 * 当前包名: com.sunlands.intl.yingshi.ui.activity.home.studystat
 * 创 建 人: xueh
 * 创建日期: 2019/3/14 15:08
 * 备注：
 */
public class IStudyStatContract {
    public interface type{
        String week="week";
        String year="year";
    }
    public interface IStudyStatView extends IBaseView {
        void onRecordsStatSuccess(StudyStatBean data);
    }

    public interface IStudyStatModel extends IBaseModel {
        void getRecordsStat(String type, PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<StudyStatBean> mMVPModelCallbacks);
    }
}
