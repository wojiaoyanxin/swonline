package com.sunlands.intl.yingshi.ui.login.presenter;

import com.blankj.utilcode.util.ToastUtils;
import com.sunlands.comm_core.base.mvp.MvpBasePresenter;
import com.sunlands.comm_core.net.BaseModel;
import com.sunlands.comm_core.net.MVPModelCallbacks;
import com.sunlands.intl.yingshi.bean.EmptyBean;
import com.sunlands.intl.yingshi.groovy.CheckNet;
import com.sunlands.intl.yingshi.ui.login.ILoginContract;
import com.sunlands.intl.yingshi.ui.login.model.ForgePwdResetModel;

/**
 * 当前包名: com.sunlands.intl.yingshi.ui.login.presenter
 * 创 建 人: xueh
 * 创建日期: 2019/3/11 18:28
 * 备注：
 */
public class ForgePwdResetPresenter extends MvpBasePresenter<ILoginContract.IForgePwdResetView, ForgePwdResetModel> {

    public ForgePwdResetPresenter(ILoginContract.IForgePwdResetView iForgePwdResetView) {
        super(iForgePwdResetView);
    }

    @Override
    protected ForgePwdResetModel createModel() {
        return new ForgePwdResetModel();
    }

    @CheckNet
    public void resetPwd(String phone, String code, String newPwd) {
        if (code.length() != 6) {
            ToastUtils.showShort("请输入6位验证码");
            return;
        }
        getView().showLoading();
        getModel().resetPwd(phone, code, newPwd, getView().getLifecycleSubject(), new MVPModelCallbacks<EmptyBean>() {
            @Override
            public void onSuccess(EmptyBean data) {
                getView().onPwdReseSuccess();
                getView().hideLoading();
            }

            @Override
            public void onException(BaseModel model) {
                ToastUtils.showShort(model.getMsg());
                getView().hideLoading();
            }

            @Override
            public void onError(Throwable e) {
                getView().hideLoading();
            }
        });
    }

    @CheckNet
    public void getCode(int type,String tel) {
        getView().showLoading();
        getModel().getCode(type,tel, getView().getLifecycleSubject(), new MVPModelCallbacks<EmptyBean>() {
            @Override
            public void onSuccess(EmptyBean data) {
                ToastUtils.showShort("发送成功");
                getView().onCodeSendSuccess();
                getView().hideLoading();
            }

            @Override
            public void onException(BaseModel model) {
                ToastUtils.showShort(model.getMsg());
                getView().hideLoading();
            }

            @Override
            public void onError(Throwable e) {
                getView().hideLoading();
            }
        });
    }
}
