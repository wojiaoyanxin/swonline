package com.sunlands.intl.yingshi.ui.community.model;

import android.arch.lifecycle.Lifecycle;

import com.sunlands.comm_core.base.mvp.MvpBaseModel;
import com.sunlands.comm_core.net.MVPModelCallbacks;
import com.sunlands.comm_core.net.ServiceGenerator;
import com.sunlands.intl.yingshi.bean.SysMsgBean;
import com.sunlands.intl.yingshi.constant.RestApi;
import com.sunlands.intl.yingshi.ui.community.IMessageContract;

import io.reactivex.subjects.PublishSubject;

/**
 * 当前包名: com.sunlands.intl.yingshi.ui.activity.home.messagecore.model
 * 创 建 人: xueh
 * 创建日期: 2019/2/21 16:04
 * 备注：
 */
public class SysMsgModel extends MvpBaseModel<RestApi> implements IMessageContract.SysModel {
    @Override
    protected RestApi initApi() {
        return ServiceGenerator.getService(RestApi.class);
    }

    @Override
    public void getClearMsg(PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<String> mMVPModelCallbacks) {
        deploy(getApi().messageEmpty(), mPublishSubject, mMVPModelCallbacks);
    }

    @Override
    public void getSysMsg(int page, PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<SysMsgBean> mMVPModelCallbacks) {
        deploy(getApi().getSysMsgList(page), mPublishSubject, mMVPModelCallbacks);
    }
}
