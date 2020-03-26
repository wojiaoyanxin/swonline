package com.sunlands.intl.yingshi.ui.community.model;

import android.arch.lifecycle.Lifecycle;

import com.sunlands.comm_core.base.mvp.MvpBaseModel;
import com.sunlands.comm_core.net.MVPModelCallbacks;
import com.sunlands.comm_core.net.ServiceGenerator;
import com.sunlands.intl.yingshi.bean.MainlBean;
import com.sunlands.intl.yingshi.constant.RestApi;
import com.sunlands.intl.yingshi.ui.community.IMessageContract;

import io.reactivex.subjects.PublishSubject;

/**
 * 当前包名: com.sunlands.intl.yingshi.ui.community.model
 * 创 建 人: xueh
 * 创建日期: 2019/4/12 16:03
 * 备注：
 */
public class MasterModel extends MvpBaseModel<RestApi> implements IMessageContract.IMasterModel {
    @Override
    protected RestApi initApi() {
       return ServiceGenerator.getService(RestApi.class);
    }

    @Override
    public void getThread_Pagination(int limit, String tab, int channelId, String keyword, PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<MainlBean> mMVPModelCallbacks) {
        deploy(getApi().getMailListInfo(limit,tab,channelId,""),mPublishSubject,mMVPModelCallbacks);
    }
}
