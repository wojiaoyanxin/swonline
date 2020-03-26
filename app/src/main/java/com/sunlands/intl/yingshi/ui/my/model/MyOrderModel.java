package com.sunlands.intl.yingshi.ui.my.model;

import android.arch.lifecycle.Lifecycle;

import com.sunlands.comm_core.base.mvp.MvpBaseModel;
import com.sunlands.comm_core.net.MVPModelCallbacks;
import com.sunlands.comm_core.net.ServiceGenerator;
import com.sunlands.intl.yingshi.bean.MyOrderBean;
import com.sunlands.intl.yingshi.constant.RestApi;
import com.sunlands.intl.yingshi.ui.home.bean.SmallClassOrderBean;
import com.sunlands.intl.yingshi.ui.my.IMyContract;

import io.reactivex.subjects.PublishSubject;

/**
 * 创 建 人: xueh
 * 创建日期: 2019/5/6 13:39
 * 备注：
 */
public class MyOrderModel extends MvpBaseModel<RestApi> implements IMyContract.IMyOrderModel {
    @Override
    protected RestApi initApi() {
        return ServiceGenerator.getService(RestApi.class);
    }

    @Override
    public void getMyOrder(int limit, PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<MyOrderBean> mMVPModelCallbacks) {
        deploy(getApi().order_List(limit), mPublishSubject, mMVPModelCallbacks);
    }

    @Override
    public void getPayResult(int id, PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<SmallClassOrderBean> mMVPModelCallbacks) {
        deploy(getApi().orderSmallClass(id + ""), mPublishSubject, mMVPModelCallbacks);
    }
}
