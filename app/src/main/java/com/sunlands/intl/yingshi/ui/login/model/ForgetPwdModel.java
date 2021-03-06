package com.sunlands.intl.yingshi.ui.login.model;

import android.arch.lifecycle.Lifecycle;

import com.sunlands.comm_core.base.mvp.MvpBaseModel;
import com.sunlands.comm_core.net.MVPModelCallbacks;
import com.sunlands.comm_core.net.ServiceGenerator;
import com.sunlands.intl.yingshi.bean.EmptyBean;
import com.sunlands.intl.yingshi.constant.RestApi;
import com.sunlands.intl.yingshi.ui.login.ILoginContract;

import io.reactivex.subjects.PublishSubject;

/**
 * 当前包名: com.sunlands.intl.yingshi.ui.login.model
 * 创 建 人: xueh
 * 创建日期: 2019/3/11 17:37
 * 备注：
 */
public class ForgetPwdModel extends MvpBaseModel<RestApi> implements ILoginContract.IPhoneLoginModel {
    @Override
    protected RestApi initApi() {
        return ServiceGenerator.getService(RestApi.class);
    }
    //1 注册  2  登录  3  更改密码   4 更改手机号   5  忘记密码     6 绑定
    @Override
    public void getCode(int type,String phoneNumber, PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<EmptyBean> mvpModelCallbacks) {
        if (type == 1) {
            //1 是语音
            deploy(getApi().getVoiceCode(phoneNumber), mPublishSubject, mvpModelCallbacks);
        } else {
            deploy(getApi().getVerifyCode(phoneNumber, "5"), mPublishSubject, mvpModelCallbacks);
        }
    }
}
