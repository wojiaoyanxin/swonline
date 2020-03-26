package com.sunlands.intl.yingshi.ui.community.presenter;

import com.sunlands.comm_core.base.mvp.MvpBasePresenter;
import com.sunlands.comm_core.net.BaseModel;
import com.sunlands.comm_core.net.MVPModelCallbacks;
import com.sunlands.intl.yingshi.bean.MyCollectBean;
import com.sunlands.intl.yingshi.groovy.CheckNet;
import com.sunlands.intl.yingshi.ui.community.IMessageContract;
import com.sunlands.intl.yingshi.ui.community.model.MyCollectModel;
import com.sunlands.intl.yingshi.ui.community.view.MyCollectActivity;

/**
 * 当前包名: com.sunlands.intl.yingshi.ui.community.presenter
 * 创 建 人: xueh
 * 创建日期: 2019/4/15 14:45
 * 备注：
 */
public class MyCollectPresenter extends MvpBasePresenter<IMessageContract.IMyCollect, MyCollectModel> {
    public MyCollectPresenter(IMessageContract.IMyCollect iMyCollect) {
        super(iMyCollect);
    }

    @Override
    protected MyCollectModel createModel() {
        return new MyCollectModel();
    }

    public void getMine(int limit, String type,String viewId) {
        if (type.equals(MyCollectActivity.MY_COLLECT)) {
            getMineCollect(limit);
        } else if (type.equals(MyCollectActivity.MY_PUBLISH)) {
            getMineThread(limit,viewId);
        }
    }

    @CheckNet
    private void getMineThread(int limit,String viewId ) {
        getView().showLoading();
        getModel().mine_Thread(0, limit,viewId, getView().getLifecycleSubject(), new MVPModelCallbacks<MyCollectBean>() {
            @Override
            public void onSuccess(MyCollectBean data) {
                getView().onMineSuccess(data);
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
    private void getMineCollect(int limit) {
        getView().showLoading();
        getModel().mine_Collect(0, limit, getView().getLifecycleSubject(), new MVPModelCallbacks<MyCollectBean>() {
            @Override
            public void onSuccess(MyCollectBean data) {
                getView().onMineSuccess(data);
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
