package com.sunlands.intl.yingshi.ui.community.presenter;

import com.blankj.utilcode.util.ToastUtils;
import com.sunlands.comm_core.base.mvp.MvpBasePresenter;
import com.sunlands.comm_core.net.BaseModel;
import com.sunlands.comm_core.net.MVPModelCallbacks;
import com.sunlands.intl.yingshi.bean.PaginationBean;
import com.sunlands.intl.yingshi.bean.PostSubmitBean;
import com.sunlands.intl.yingshi.bean.ThreadInfoBean;
import com.sunlands.intl.yingshi.groovy.CheckNet;
import com.sunlands.intl.yingshi.ui.community.IMessageContract;
import com.sunlands.intl.yingshi.ui.community.model.CommunityContentModel;

/**
 * 当前包名: com.sunlands.intl.yingshi.ui.activity.home.messagecore.presenter
 * 创 建 人: xueh
 * 创建日期: 2019/3/6 14:32
 * 备注：
 */
public class CommunityContentPresenter extends MvpBasePresenter<IMessageContract.MailContentView, CommunityContentModel> {

    public CommunityContentPresenter(IMessageContract.MailContentView mailContentView) {
        super(mailContentView);
    }

    @Override
    protected CommunityContentModel createModel() {
        return new CommunityContentModel();
    }

    @CheckNet
    public void getThreadInfo(int threadId) {
        getView().showLoading();
        getModel().getThreadInfo(threadId, getView().getLifecycleSubject(), new MVPModelCallbacks<ThreadInfoBean>() {
            @Override
            public void onSuccess(ThreadInfoBean data) {
                getView().onThreadInfoSuccess(data);
                getView().hideLoading();
            }

            @Override
            public void onException(BaseModel model) {
                getView().hideLoading();
                if(model.getCode() == 4001) {
                    ToastUtils.showShort(model.getMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                getView().hideLoading();
            }
        });
    }

    @CheckNet
    public void getPagination(int start, int limit, int postId, int threadId) {
        getView().showLoading();
        getModel().getPagination(start, limit, postId, threadId, getView().getLifecycleSubject(), new MVPModelCallbacks<PaginationBean>() {
            @Override
            public void onSuccess(PaginationBean data) {
                getView().onGetPaginationSuccess(data);
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

    @CheckNet
    public void postSubmit(int threadId, int postId, int rePostId, String content) {
        getView().showLoading();
        getModel().postSubmit(threadId, postId, rePostId, content, getView().getLifecycleSubject(), new MVPModelCallbacks<PostSubmitBean>() {
            @Override
            public void onSuccess(PostSubmitBean data) {
                ToastUtils.showShort("回复成功");
                getView().onPostSubmitSuccess("");
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
