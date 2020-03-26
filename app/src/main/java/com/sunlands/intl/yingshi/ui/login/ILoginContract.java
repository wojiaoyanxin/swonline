package com.sunlands.intl.yingshi.ui.login;

import android.arch.lifecycle.Lifecycle;

import com.sunlands.comm_core.base.ibase.IBaseModel;
import com.sunlands.comm_core.base.ibase.IBaseView;
import com.sunlands.comm_core.net.MVPModelCallbacks;
import com.sunlands.intl.yingshi.bean.EmptyBean;
import com.sunlands.intl.yingshi.greendao.db.LoginInfo;

import io.reactivex.subjects.PublishSubject;

/**
 * 当前包名: com.sunlands.intl.yingshi.ui.login
 * 创 建 人: xueh
 * 创建日期: 2019/3/11 11:16
 * 备注：
 */
public class ILoginContract {
    public interface Type {
        String PWD = "pwd";
        String WX = "wx";
        String SMS = "sms";
    }

    public interface IPhoneLoginModel extends IBaseModel {
        //0 短信 1是语音
        void getCode(int type,String phoneNumber, PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<EmptyBean> mvpModelCallbacks);
    }

    public interface IPhoneLoginView extends com.sunlands.comm_core.base.ibase.IBaseView {
        void onGetCodeSuccess(String phone);
    }

    public interface IVerifiCationLoginModel extends IBaseModel {
        void smsLogin(String phoneNumber, String authCode, PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<LoginInfo> mvpModelCallbacks);
    }

    public interface IVerifiCationLoginView extends com.sunlands.comm_core.base.ibase.IBaseView {
        void onSmsLoginSuccess(LoginInfo data);
        void getCodeSuccess();
    }

    public interface IPwdLoginModel extends IBaseModel {
        void pwdLogin(String phoneNumber, String pwdStr, PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<LoginInfo> mvpModelCallbacks);
        void threadLogin(String unionId, PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<LoginInfo> mvpModelCallbacks);
    }

    public interface IPwdLoginView extends com.sunlands.comm_core.base.ibase.IBaseView {
        void onPwdLoginSuccess(LoginInfo data);
    }

    public interface IForgetPwdView extends com.sunlands.comm_core.base.ibase.IBaseView {
        void onSendCodeSuccess(String phone);
    }


    public interface IForgePwdResetModel extends com.sunlands.comm_core.base.ibase.IBaseModel {
        void resetPwd(String tel, String code, String pwdStr, PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<EmptyBean> mvpModelCallbacks);
    }

    public interface IForgePwdResetView extends com.sunlands.comm_core.base.ibase.IBaseView {
        void onPwdReseSuccess();

        void onCodeSendSuccess();
    }


    public interface IRegisterView extends IBaseView {
        void onRegisterSuccess(LoginInfo info);

        void onCodeSendSuccess();
    }

    public interface IRegisterModel extends IBaseModel {
        void pwdRegister(String tel, String code, String pwdStr, PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<LoginInfo> mvpModelCallbacks);
    }


    public interface IBindPhoneView extends IBaseView {
        void onBindPhoneSuccess(LoginInfo info);
        void onCodeSendSuccess();
    }

    public interface IBindPhoneModel extends IBaseModel {
        void bindPhone(String tel, String code,String unionId, String gender, String iconurl,PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<LoginInfo> mvpModelCallbacks);
    }
}
