package com.sunlands.intl.yingshi.common;

import android.arch.lifecycle.Lifecycle;

import com.sunlands.comm_core.base.ibase.IBaseModel;
import com.sunlands.comm_core.base.ibase.IBaseView;
import com.sunlands.comm_core.net.BaseModel;
import com.sunlands.comm_core.net.MVPModelCallbacks;

import io.reactivex.subjects.PublishSubject;

/**
 * Created by yanxin on 2019/5/15.
 */

public class CommonContract {

    public interface ICommonView<T> extends IBaseView {

        void getDataSuccess(T bean);
        void getDataFailed(BaseModel model);
    }

    public interface ICommonModel<T> extends IBaseModel {
        void getData(String type,boolean isShow, PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<T> mMVPModelCallbacks, Object ...o);
        void getData(String type, PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<T> mMVPModelCallbacks, Object ...o);
    }

}
