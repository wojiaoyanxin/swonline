package com.sunlands.intl.yingshi.ui.community.presenter;

import com.sunlands.comm_core.base.mvp.MvpBasePresenter;
import com.sunlands.comm_core.net.BaseModel;
import com.sunlands.comm_core.net.MVPModelCallbacks;
import com.sunlands.intl.yingshi.bean.MainlBean;
import com.sunlands.intl.yingshi.groovy.CheckNet;
import com.sunlands.intl.yingshi.ui.community.IMessageContract;
import com.sunlands.intl.yingshi.ui.community.model.MasterModel;

/**
 * 当前包名: com.sunlands.intl.yingshi.ui.community.presenter
 * 创 建 人: xueh
 * 创建日期: 2019/4/12 16:04
 * 备注：
 */
public class MasterPresenter extends MvpBasePresenter<IMessageContract .IMasterView,MasterModel> {
    public MasterPresenter(IMessageContract.IMasterView masterView) {
        super(masterView);
    }

    @Override
    protected MasterModel createModel() {
        return new MasterModel();
    }
    @CheckNet
    public void getThread_Pagination(int limit, String tab,int channelId) {
        getView().showLoading();
        getModel().getThread_Pagination(limit, tab, channelId,"",getView().getLifecycleSubject(), new MVPModelCallbacks<MainlBean>() {
            @Override
            public void onSuccess(MainlBean data) {
                getView().getPaginationSuccess(data);
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
