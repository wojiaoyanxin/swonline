package com.sunlands.intl.yingshi.ui.community.model;

import android.arch.lifecycle.Lifecycle;

import com.sunlands.comm_core.base.mvp.MvpBaseModel;
import com.sunlands.comm_core.net.MVPModelCallbacks;
import com.sunlands.comm_core.net.ServiceGenerator;
import com.sunlands.intl.yingshi.bean.MyCollectBean;
import com.sunlands.intl.yingshi.constant.RestApi;
import com.sunlands.intl.yingshi.ui.community.IMessageContract;

import io.reactivex.subjects.PublishSubject;

/**
 * 当前包名: com.sunlands.intl.yingshi.ui.community.model
 * 创 建 人: xueh
 * 创建日期: 2019/4/15 14:46
 * 备注：
 */
public class MyCollectModel extends MvpBaseModel<RestApi> implements IMessageContract.IMyCollectModel {
    @Override
    protected RestApi initApi() {
        return ServiceGenerator.getService(RestApi.class);
    }

    @Override
    public void mine_Thread(int start, int limit,String viewId ,PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<MyCollectBean> mMVPModelCallbacks) {
        deploy(getApi().mine_Thread(start,limit,viewId),mPublishSubject,mMVPModelCallbacks);
    }

    @Override
    public void mine_Collect(int start, int limit, PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<MyCollectBean> mMVPModelCallbacks) {
        deploy(getApi().mine_Collect(start,limit),mPublishSubject,mMVPModelCallbacks);
    }
}
