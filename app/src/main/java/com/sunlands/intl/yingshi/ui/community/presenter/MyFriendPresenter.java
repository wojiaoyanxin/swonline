package com.sunlands.intl.yingshi.ui.community.presenter;

import com.sunlands.comm_core.base.mvp.MvpBasePresenter;
import com.sunlands.comm_core.net.BaseModel;
import com.sunlands.comm_core.net.MVPModelCallbacks;
import com.sunlands.intl.yingshi.bean.FriendInfoBean;
import com.sunlands.intl.yingshi.groovy.CheckNet;
import com.sunlands.intl.yingshi.ui.community.IMessageContract;
import com.sunlands.intl.yingshi.ui.community.model.MyFriendModel;

/**
 * 当前包名: com.sunlands.intl.yingshi.ui.activity.home.messagecore.presenter
 * 创 建 人: xueh
 * 创建日期: 2019/2/25 11:54
 * 备注：
 */
public class MyFriendPresenter extends MvpBasePresenter<IMessageContract.FriendView, MyFriendModel> {

    public MyFriendPresenter(IMessageContract.FriendView mFriendView) {
        super(mFriendView);
    }

    @Override
    protected MyFriendModel createModel() {
        return new MyFriendModel();
    }
    @CheckNet
    public void getFriendInfo(String userid) {
        getModel().getFriendInfo(userid, getView().getLifecycleSubject(), new MVPModelCallbacks<FriendInfoBean>() {
            @Override
            public void onSuccess(FriendInfoBean data) {
                getView().onFriendInfoSuccess(data);
            }

            @Override
            public void onException(BaseModel model) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }
}
