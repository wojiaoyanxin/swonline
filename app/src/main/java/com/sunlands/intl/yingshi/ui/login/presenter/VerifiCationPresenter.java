package com.sunlands.intl.yingshi.ui.login.presenter;

import com.blankj.utilcode.util.ToastUtils;
import com.sunlands.comm_core.base.mvp.MvpBasePresenter;
import com.sunlands.comm_core.net.BaseModel;
import com.sunlands.comm_core.net.MVPModelCallbacks;
import com.sunlands.intl.yingshi.bean.EmptyBean;
import com.sunlands.intl.yingshi.greendao.db.LoginInfo;
import com.sunlands.intl.yingshi.groovy.CheckNet;
import com.sunlands.intl.yingshi.ui.login.ILoginContract;
import com.sunlands.intl.yingshi.ui.login.model.VerifiCationModel;

/**
 * 当前包名: com.sunlands.intl.yingshi.ui.login.presenter
 * 创 建 人: xueh
 * 创建日期: 2019/3/11 13:11
 * 备注：
 */
public class VerifiCationPresenter extends MvpBasePresenter<ILoginContract.IVerifiCationLoginView,VerifiCationModel> {
    public VerifiCationPresenter(ILoginContract.IVerifiCationLoginView iVerifiCationLoginView) {
        super(iVerifiCationLoginView);
    }

    @Override
    protected VerifiCationModel createModel() {
        return new VerifiCationModel();
    }
    @CheckNet
    public void smsLogin(String phone,String authCode){
        getView().showLoading();
        getModel().smsLogin(phone, authCode, getView().getLifecycleSubject(), new MVPModelCallbacks<LoginInfo>() {
            @Override
            public void onSuccess(LoginInfo data) {
                getView().onSmsLoginSuccess(data);
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
    public void getCode(int type,String phone) {
        if (phone.length() != 11) {
            ToastUtils.showShort("请输入11位手机号");
            return;
        }else if (!phone.startsWith("1")) {
            ToastUtils.showShort("请输入正确的手机号格式");
            return;
        }
        getView().showLoading();
        getModel().getCode(type,phone, getView().getLifecycleSubject(), new MVPModelCallbacks<EmptyBean>() {
            @Override
            public void onSuccess(EmptyBean data) {
                ToastUtils.showShort("发送成功");
                getView().getCodeSuccess();
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
