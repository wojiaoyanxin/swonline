package com.sunlands.intl.yingshi.ui.community.model;

import android.arch.lifecycle.Lifecycle;

import com.sunlands.comm_core.base.mvp.MvpBaseModel;
import com.sunlands.comm_core.net.MVPModelCallbacks;
import com.sunlands.comm_core.net.ServiceGenerator;
import com.sunlands.intl.yingshi.bean.MsgListBean;
import com.sunlands.intl.yingshi.constant.RestApi;
import com.sunlands.intl.yingshi.ui.community.IMessageContract;

import io.reactivex.subjects.PublishSubject;

/**
 * 当前包名: com.sunlands.intl.yingshi.ui.activity.home.messagecore
 * 创 建 人: xueh
 * 创建日期: 2019/2/18 17:20
 * 备注：
 */
public class MessageCoreModel extends MvpBaseModel<RestApi> implements IMessageContract.Model {
    @Override
    protected RestApi initApi() {
        return ServiceGenerator.getService(RestApi.class);
    }
    @Override
    public void getMsg_List(PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<MsgListBean> mMVPModelCallbacks) {
        deploy(getApi().getMsgList(),mPublishSubject,mMVPModelCallbacks);
    }
}
