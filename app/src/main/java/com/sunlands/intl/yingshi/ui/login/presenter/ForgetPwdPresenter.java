package com.sunlands.intl.yingshi.ui.login.presenter;

import com.blankj.utilcode.util.ToastUtils;
import com.sunlands.comm_core.base.mvp.MvpBasePresenter;
import com.sunlands.comm_core.net.BaseModel;
import com.sunlands.comm_core.net.MVPModelCallbacks;
import com.sunlands.intl.yingshi.bean.EmptyBean;
import com.sunlands.intl.yingshi.groovy.CheckNet;
import com.sunlands.intl.yingshi.ui.login.ILoginContract;
import com.sunlands.intl.yingshi.ui.login.model.ForgetPwdModel;

/**
 * 当前包名: com.sunlands.intl.yingshi.ui.login.presenter
 * 创 建 人: xueh
 * 创建日期: 2019/3/11 17:37
 * 备注：
 */
public class ForgetPwdPresenter extends MvpBasePresenter<ILoginContract.IForgetPwdView, ForgetPwdModel> {
    public ForgetPwdPresenter(ILoginContract.IForgetPwdView iForgetPwdView) {
        super(iForgetPwdView);
    }

    @Override
    protected ForgetPwdModel createModel() {
        return new ForgetPwdModel();
    }

    @CheckNet
    public void forgetPwdSendCode(int type,String phone) {
        if (phone.length() != 11) {
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
                ToastUtils.showShort("发送成功");
                getView().onSendCodeSuccess(phone);
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
