package com.sunlands.intl.yingshi.ui.login.model;

import android.arch.lifecycle.Lifecycle;

import com.sunlands.comm_core.base.mvp.MvpBaseModel;
import com.sunlands.comm_core.net.MVPModelCallbacks;
import com.sunlands.comm_core.net.ServiceGenerator;
import com.sunlands.intl.yingshi.greendao.db.LoginInfo;
import com.sunlands.intl.yingshi.constant.RestApi;
import com.sunlands.intl.yingshi.ui.login.ILoginContract;
import com.sunlands.intl.yingshi.util.AppUtils;

import io.reactivex.subjects.PublishSubject;

/**
 * 当前包名: com.sunlands.intl.yingshi.ui.login.model
 * 创 建 人: xueh
 * 创建日期: 2019/3/11 15:10
 * 备注：
 */
public class PwdLoginModel extends MvpBaseModel<RestApi> implements ILoginContract.IPwdLoginModel {
    @Override
    protected RestApi initApi() {
        return ServiceGenerator.getService(RestApi.class);
    }

    @Override
    public void pwdLogin(String phoneNumber, String pwdStr, PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<LoginInfo> mvpModelCallbacks) {
        deploy(getApi().login(ILoginContract.Type.PWD, phoneNumber, pwdStr, "", 2, AppUtils.getUniquePsuedoID()), mPublishSubject, mvpModelCallbacks);
    }

    @Override
    public void threadLogin(String unionId, PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<LoginInfo> mvpModelCallbacks) {
        deploy(getApi().login(ILoginContract.Type.WX, "", "", unionId, 2, AppUtils.getUniquePsuedoID()), mPublishSubject, mvpModelCallbacks);
    }
}
