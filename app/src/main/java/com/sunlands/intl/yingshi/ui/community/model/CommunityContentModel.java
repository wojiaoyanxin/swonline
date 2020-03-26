package com.sunlands.intl.yingshi.ui.community.model;

import android.arch.lifecycle.Lifecycle;

import com.sunlands.comm_core.base.mvp.MvpBaseModel;
import com.sunlands.comm_core.net.MVPModelCallbacks;
import com.sunlands.comm_core.net.ServiceGenerator;
import com.sunlands.intl.yingshi.bean.PaginationBean;
import com.sunlands.intl.yingshi.bean.PostSubmitBean;
import com.sunlands.intl.yingshi.bean.ThreadInfoBean;
import com.sunlands.intl.yingshi.constant.RestApi;
import com.sunlands.intl.yingshi.ui.community.IMessageContract;

import io.reactivex.subjects.PublishSubject;

/**
 * 当前包名: com.sunlands.intl.yingshi.ui.activity.home.messagecore.model
 * 创 建 人: xueh
 * 创建日期: 2019/3/6 14:32
 * 备注：
 */
public class CommunityContentModel extends MvpBaseModel<RestApi> implements IMessageContract.MailContentModel {
    @Override
    protected RestApi initApi() {
        return ServiceGenerator.getService(RestApi.class);
    }

    @Override
    public void postSubmit(int threadId, int postId, int rePostId, String content, PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<PostSubmitBean> mMVPModelCallbacks) {
        deploy(getApi().postSubmit(threadId,postId,rePostId,content),mPublishSubject,mMVPModelCallbacks);
    }

    @Override
    public void getThreadInfo( int threadId, PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<ThreadInfoBean> mMVPModelCallbacks) {
        deploy(getApi().thread_Info(threadId),mPublishSubject,mMVPModelCallbacks);
    }

    @Override
    public void getPagination(int start, int limit, int postId, int threadId, PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<PaginationBean> mMVPModelCallbacks) {
        deploy(getApi().getPagination(start,limit,postId,threadId),mPublishSubject,mMVPModelCallbacks);
    }
}
