package com.sunlands.intl.yingshi.ui.community.presenter;

import com.sunlands.comm_core.base.mvp.MvpBasePresenter;
import com.sunlands.comm_core.net.BaseModel;
import com.sunlands.comm_core.net.MVPModelCallbacks;
import com.sunlands.intl.yingshi.bean.MsgListBean;
import com.sunlands.intl.yingshi.groovy.CheckNet;
import com.sunlands.intl.yingshi.ui.community.IMessageContract;
import com.sunlands.intl.yingshi.ui.community.model.MessageCoreModel;

/**
 * 当前包名: com.sunlands.intl.yingshi.ui.activity.home.messagecore
 * 创 建 人: xueh
 * 创建日期: 2019/2/18 17:19
 * 备注：
 */
public class MessageCorePresenter extends MvpBasePresenter<IMessageContract.View,MessageCoreModel> {

    public MessageCorePresenter(IMessageContract.View mView) {
        super(mView);
    }
    @Override
    protected MessageCoreModel createModel() {
        return new MessageCoreModel();
    }
    @CheckNet
    public void getMsgList(){
        getView().showLoading();
        getModel().getMsg_List(getView().getLifecycleSubject(), new MVPModelCallbacks<MsgListBean>() {
            @Override
            public void onSuccess(MsgListBean data) {
                getView().onMsgListSuccess(data);
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
