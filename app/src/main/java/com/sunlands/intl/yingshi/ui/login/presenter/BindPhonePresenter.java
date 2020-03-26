package com.sunlands.intl.yingshi.ui.login.presenter;

import com.blankj.utilcode.util.ToastUtils;
import com.sunlands.comm_core.base.mvp.MvpBasePresenter;
import com.sunlands.comm_core.net.BaseModel;
import com.sunlands.comm_core.net.MVPModelCallbacks;
import com.sunlands.intl.yingshi.bean.EmptyBean;
import com.sunlands.intl.yingshi.greendao.db.LoginInfo;
import com.sunlands.intl.yingshi.groovy.CheckNet;
import com.sunlands.intl.yingshi.ui.login.ILoginContract;
import com.sunlands.intl.yingshi.ui.login.model.BindPhoneModel;

/**
 * 当前包名: com.sunlands.intl.yingshi.ui.login.presenter
 * 创 建 人: xueh
 * 创建日期: 2019/3/13 14:39
 * 备注：
 */
public class BindPhonePresenter extends MvpBasePresenter<ILoginContract.IBindPhoneView, BindPhoneModel> {

    public BindPhonePresenter(ILoginContract.IBindPhoneView iBindPhoneView) {
        super(iBindPhoneView);
    }

    @Override
    protected BindPhoneModel createModel() {
        return new BindPhoneModel();
    }

    @CheckNet
    public void sendCode(int type,String phone) {
        if (phone.length()!=11) {
            ToastUtils.showShort("请输入11位手机号码");
            return;
        }else if (!phone.startsWith("1")) {
            ToastUtils.showShort("请输入正确的手机号格式");
            return;
        }

        getView().showLoading();
        getModel().getCode(type,phone, getView().getLifecycleSubject(), new MVPModelCallbacks<EmptyBean>() {
            @Override
            public void onSuccess(EmptyBean data) {
                getView().hideLoading();
                getView().onCodeSendSuccess();
                ToastUtils.showShort("发送成功");
            }

            @Override
            public void onException(BaseModel model) {
                getView().hideLoading();
                ToastUtils.showShort(model.getMsg());
            }

            @Override
            public void onError(Throwable e) {
                getView().hideLoading();
            }
        });
    }

    @CheckNet
    public void bindPhone(String tel, String code, String unionId,String gender, String iconurl) {
        if (tel.length()!=11) {
            ToastUtils.showShort("请输入11位手机号码");
            return;
        }
        getView().showLoading();
        getModel().bindPhone(tel, code, unionId,gender,iconurl, getView().getLifecycleSubject(), new MVPModelCallbacks<LoginInfo>() {
            @Override
            public void onSuccess(LoginInfo data) {
                getView().hideLoading();
                getView().onBindPhoneSuccess(data);
            }

            @Override
            public void onException(BaseModel model) {
                getView().hideLoading();
                ToastUtils.showShort(model.getMsg());
            }

            @Override
            public void onError(Throwable e) {
                getView().hideLoading();
            }
        });
    }
}
