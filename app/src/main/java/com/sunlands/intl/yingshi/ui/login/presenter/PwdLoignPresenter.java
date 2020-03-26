package com.sunlands.intl.yingshi.ui.login.presenter;

import com.blankj.utilcode.util.ToastUtils;
import com.sunlands.comm_core.base.mvp.MvpBasePresenter;
import com.sunlands.comm_core.net.BaseModel;
import com.sunlands.comm_core.net.MVPModelCallbacks;
import com.sunlands.intl.yingshi.greendao.db.LoginInfo;
import com.sunlands.intl.yingshi.groovy.CheckNet;
import com.sunlands.intl.yingshi.ui.login.ILoginContract;
import com.sunlands.intl.yingshi.ui.login.model.PwdLoginModel;

/**
 * 当前包名: com.sunlands.intl.yingshi.ui.login.presenter
 * 创 建 人: xueh
 * 创建日期: 2019/3/11 15:10
 * 备注：
 */
public class PwdLoignPresenter extends MvpBasePresenter<ILoginContract.IPwdLoginView,PwdLoginModel> {
    public PwdLoignPresenter(ILoginContract.IPwdLoginView iPwdLoginView) {
        super(iPwdLoginView);
    }

    @Override
    protected PwdLoginModel createModel() {
        return new PwdLoginModel();
    }
    @CheckNet
    public void pwdLogin(String phone,String pwd){
        getView().showLoading();
        getModel().pwdLogin(phone, pwd, getView().getLifecycleSubject(), new MVPModelCallbacks<LoginInfo>() {
            @Override
            public void onSuccess(LoginInfo data) {
                getView().hideLoading();
                if(data == null) {
                    ToastUtils.showShort("接口调用失败");
                    return;
                }
                getView().onPwdLoginSuccess(data);

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
