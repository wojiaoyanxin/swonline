package com.sunlands.intl.yingshi.ui.community.model;

import android.arch.lifecycle.Lifecycle;

import com.sunlands.comm_core.base.mvp.MvpBaseModel;
import com.sunlands.comm_core.net.MVPModelCallbacks;
import com.sunlands.comm_core.net.ServiceGenerator;
import com.sunlands.intl.yingshi.bean.FriendInfoBean;
import com.sunlands.intl.yingshi.constant.RestApi;
import com.sunlands.intl.yingshi.ui.community.IMessageContract;

import io.reactivex.subjects.PublishSubject;

/**
 * 当前包名: com.sunlands.intl.yingshi.ui.activity.home.messagecore.model
 * 创 建 人: xueh
 * 创建日期: 2019/2/25 11:55
 * 备注：
 */
public class MyFriendModel extends MvpBaseModel<RestApi> implements IMessageContract.FriendModel {
    @Override
    protected RestApi initApi() {
        return ServiceGenerator.getService(RestApi.class);
    }
    @Override
    public void getFriendInfo(String userid,PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<FriendInfoBean> mMVPModelCallbacks) {
        deploy(getApi().getFriendInfo(userid),mPublishSubject,mMVPModelCallbacks);
    }
}
