package com.sunlands.intl.yingshi.ui.login.presenter;

import com.blankj.utilcode.util.ToastUtils;
import com.sunlands.comm_core.base.mvp.MvpBasePresenter;
import com.sunlands.comm_core.net.BaseModel;
import com.sunlands.comm_core.net.MVPModelCallbacks;
import com.sunlands.intl.yingshi.bean.EmptyBean;
import com.sunlands.intl.yingshi.greendao.db.LoginInfo;
import com.sunlands.intl.yingshi.groovy.CheckNet;
import com.sunlands.intl.yingshi.ui.login.ILoginContract;
import com.sunlands.intl.yingshi.ui.login.model.RegisterModel;
import com.sunlands.intl.yingshi.util.SPHelper;

/**
 * 当前包名: com.sunlands.intl.yingshi.ui.login.presenter
 * 创 建 人: xueh
 * 创建日期: 2019/3/12 11:30
 * 备注：
 */
public class RegisterPresenter extends MvpBasePresenter<ILoginContract.IRegisterView,RegisterModel> {
    public RegisterPresenter(ILoginContract.IRegisterView iRegisterView) {
        super(iRegisterView);
    }

    @Override
    protected RegisterModel createModel() {
        return new RegisterModel();
    }
    @CheckNet
    public void pwdRegister(String tel,String code,String pwd){
        getView().showLoading();
        getModel().pwdRegister(tel, code, pwd, getView().getLifecycleSubject(), new MVPModelCallbacks<LoginInfo>() {
            @Override
            public void onSuccess(LoginInfo data) {
                SPHelper.setUserPhone(tel);
                getView().onRegisterSuccess(data);
                getView().hideLoading();

            }

            @Override
            public void onException(BaseModel model) {
                ToastUtils.showShort(model.getMsg() );
                getView().hideLoading();
            }

            @Override
            public void onError(Throwable e) {
                getView().hideLoading();
            }
        });

    }
    @CheckNet
    public void pwdSendCode(int type,String tel){
        if (tel.length()!=11) {
            ToastUtils.showShort("请输入11位手机号码");
            return;
        }else if (!tel.startsWith("1")) {
            ToastUtils.showShort("请输入正确的手机号格式");
            return;
        }
        getView().showLoading();
        getModel().getCode(type,tel,getView().getLifecycleSubject(), new MVPModelCallbacks<EmptyBean>() {
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
