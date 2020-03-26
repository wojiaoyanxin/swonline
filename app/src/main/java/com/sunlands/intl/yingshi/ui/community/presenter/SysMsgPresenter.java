package com.sunlands.intl.yingshi.ui.community.presenter;

import com.sunlands.comm_core.base.mvp.MvpBasePresenter;
import com.sunlands.comm_core.net.BaseModel;
import com.sunlands.comm_core.net.MVPModelCallbacks;
import com.sunlands.intl.yingshi.bean.SysMsgBean;
import com.sunlands.intl.yingshi.groovy.CheckNet;
import com.sunlands.intl.yingshi.ui.community.IMessageContract;
import com.sunlands.intl.yingshi.ui.community.model.SysMsgModel;

/**
 * 当前包名: com.sunlands.intl.yingshi.ui.activity.home.messagecore.presenter
 * 创 建 人: xueh
 * 创建日期: 2019/2/21 16:03
 * 备注：
 */
public class SysMsgPresenter extends MvpBasePresenter<IMessageContract.SysView,SysMsgModel>  {
    public SysMsgPresenter(IMessageContract.SysView mSysView) {
        super(mSysView);
    }

    @Override
    protected SysMsgModel createModel() {
        return new SysMsgModel();
    }
    @CheckNet
    public void getSysMsg(int page){
        getView().showLoading();
        getModel().getSysMsg(page,getView().getLifecycleSubject(), new MVPModelCallbacks<SysMsgBean>() {
            @Override
            public void onSuccess(SysMsgBean data) {
                getView().onSysMsgListSuccess(data); getView().hideLoading();
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
    public void clearMsg(){
        getView().showLoading();
        getModel().getClearMsg(getView().getLifecycleSubject(), new MVPModelCallbacks<String>() {
            @Override
            public void onSuccess(String data) {
                getView().onClearMsgSuccess();
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
