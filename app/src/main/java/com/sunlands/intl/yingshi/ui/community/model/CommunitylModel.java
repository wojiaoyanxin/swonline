package com.sunlands.intl.yingshi.ui.community.model;

import android.arch.lifecycle.Lifecycle;

import com.sunlands.comm_core.base.mvp.MvpBaseModel;
import com.sunlands.comm_core.net.MVPModelCallbacks;
import com.sunlands.comm_core.net.ServiceGenerator;
import com.sunlands.intl.yingshi.bean.EmptyBean;
import com.sunlands.intl.yingshi.bean.MainlBean;
import com.sunlands.intl.yingshi.constant.RestApi;
import com.sunlands.intl.yingshi.ui.community.IMessageContract;

import java.util.List;

import io.reactivex.subjects.PublishSubject;

/**
 * 当前包名: com.sunlands.intl.yingshi.ui.activity.home.messagecore.model
 * 创 建 人: xueh
 * 创建日期: 2019/2/25 15:31
 * 备注：
 */
public class CommunitylModel extends MvpBaseModel<RestApi> implements IMessageContract.MailModel {
    @Override
    protected RestApi initApi() {
        return ServiceGenerator.getService(RestApi.class);
    }
    @Override
    public void getMailInfo(int limit,String tab,String keyword,PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<MainlBean> mMVPModelCallbacks) {
        deploy(getApi().getMailListInfo(limit,tab,0,keyword),mPublishSubject,mMVPModelCallbacks);
    }

    @Override
    public void threadLiked(int threadId, PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<EmptyBean> mMVPModelCallbacks) {
        deploy(getApi().threadLiked(threadId),mPublishSubject,mMVPModelCallbacks);
    }

    @Override
    public void unLiked(int threadId, PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<EmptyBean> mMVPModelCallbacks) {
        deploy(getApi().unLiked(threadId),mPublishSubject,mMVPModelCallbacks);
    }

    @Override
    public void collect(int threadId, PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<EmptyBean> mMVPModelCallbacks) {
        deploy(getApi().collect(threadId),mPublishSubject,mMVPModelCallbacks);
    }

    @Override
    public void unCollect(int threadId, PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<EmptyBean> mMVPModelCallbacks) {
        deploy(getApi().un_Collect(threadId),mPublishSubject,mMVPModelCallbacks);
    }

    @Override
    public void report_Submit(int threadId, PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<List<EmptyBean>> mMVPModelCallbacks) {
        deploy(getApi().report_Submit(threadId),mPublishSubject,mMVPModelCallbacks);
    }

    @Override
    public void delete(int threadId, PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<EmptyBean> mMVPModelCallbacks) {
        deploy(getApi().delete(threadId),mPublishSubject,mMVPModelCallbacks);
    }
}
