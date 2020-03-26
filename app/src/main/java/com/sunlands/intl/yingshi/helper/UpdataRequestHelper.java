package com.sunlands.intl.yingshi.helper;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.sunlands.comm_core.base.mvp.MvpBaseModel;
import com.sunlands.comm_core.net.BaseModel;
import com.sunlands.comm_core.net.MVPModelSuccessCallbacks;
import com.sunlands.comm_core.net.ServiceGenerator;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.bean.AppUpdataBean;
import com.sunlands.intl.yingshi.bean.InfoStatusBean;
import com.sunlands.intl.yingshi.greendao.db.LoginInfo;
import com.sunlands.intl.yingshi.constant.RestApi;
import com.sunlands.intl.yingshi.dialog.AppUpdataDialog;
import com.sunlands.intl.yingshi.groovy.CheckNet;
import com.sunlands.intl.yingshi.ui.activity.DialogActivity;
import com.sunlands.intl.yingshi.ui.activity.home.biling.ApplyBackBean;

import io.reactivex.subjects.PublishSubject;

/**
 * 创 建 人: xueh
 * 创建日期: 2019/5/6 17:21
 * 备注：
 */

public class UpdataRequestHelper extends MvpBaseModel<RestApi> {
    @Override
    protected RestApi initApi() {
        return ServiceGenerator.getService(RestApi.class);
    }

    public interface IUserInfoStatus {
        void showDialog();
    }

    @CheckNet
    public void getInfoStatus(IUserInfoStatus iUserInfoStatus) {
        deploy(getApi().getUser_Status(), PublishSubject.create(), new MVPModelSuccessCallbacks<InfoStatusBean>() {
            @Override
            public void onSuccess(InfoStatusBean data) {
                //	0-未完善个人信息 1-已完善个人信息
                if (ObjectUtils.isNotEmpty(data) && data.completeInfo == 0) {
                    if (iUserInfoStatus != null) {
                        iUserInfoStatus.showDialog();
                    }
                }

            }
        });
    }

    @CheckNet
    public void getAPPUpdata(FragmentManager fragmentManager) {
        deploy(getApi().getApp_Version(), PublishSubject.create(), new MVPModelSuccessCallbacks<AppUpdataBean>() {

            @Override
            public void onSuccess(AppUpdataBean data) {
                if (ObjectUtils.isNotEmpty(data) && data.hasNew == 1 && !StringUtils.isEmpty(data.getInfo().getUrl())) {
                    AppUpdataDialog.getInstance(data).show(fragmentManager, null);
                }
            }
        });
    }

    @CheckNet
    public void getUserInfoUpdata() {
        deploy(getApi().getUser_Role(), PublishSubject.create(), new MVPModelSuccessCallbacks<LoginInfo>() {
            @Override
            public void onSuccess(LoginInfo data) {
                if (ObjectUtils.isNotEmpty(data)) {
                    LoginUserInfoHelper.getInstance().updataStuid(data.getStuId(), data.getUniversity());
                }
            }
        }, false);
    }

    @CheckNet //预约
    public void order(String courseId, TextView yuyue, TextView counts, int view_num) {
        deploy(getApi().order(courseId), PublishSubject.create(), new MVPModelSuccessCallbacks<Object>() {
            @Override
            public void onSuccess(Object data) {
                yuyue.setText("已预约");
                yuyue.setBackgroundResource(R.drawable.button_common_home_un);
                ToastUtils.showShort("您已成功预约该课程");
                counts.setText(view_num + 1 + "人想看");
                yuyue.setTextColor(Color.parseColor("#999999"));
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                ToastUtils.showShort(e.getMessage());
            }

            @Override
            public void onException(BaseModel model) {
                super.onException(model);
                ToastUtils.showShort(model.getMsg());
            }
        }, true);
    }

    @CheckNet //检查用户是否申请了学校
    public void isApply(Context context) {

        deploy(getApi().isApply(), PublishSubject.create(), new MVPModelSuccessCallbacks<ApplyBackBean>() {
            @Override
            public void onSuccess(ApplyBackBean applyBackBean) {

                Intent intent = new Intent(context, DialogActivity.class);

                if (applyBackBean.getIsApply() == 0) { //没有申请

                    intent.putExtra("type", "2");

                } else {

                    intent.putExtra("type", "1");
                    intent.putExtra("message", applyBackBean.getShowMessage());
                }

                context.startActivity(intent);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                ToastUtils.showShort(e.getMessage());
            }

            @Override
            public void onException(BaseModel model) {
                super.onException(model);
                ToastUtils.showShort(model.getMsg());
            }
        }, true);
    }

    //注册小米 id
    public void registerMiPushId(String id) {

        deploy(getApi().registerMiPushId(id), PublishSubject.create(), new MVPModelSuccessCallbacks<Object>() {
            @Override
            public void onSuccess(Object applyBackBean) {

            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);

            }

            @Override
            public void onException(BaseModel model) {
                super.onException(model);

            }
        }, false);
    }

    //设置消息已读
    public void actionRead(String id) {

        deploy(getApi().actionRead(id), PublishSubject.create(), new MVPModelSuccessCallbacks<Object>() {
            @Override
            public void onSuccess(Object o) {

            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);

            }

            @Override
            public void onException(BaseModel model) {
                super.onException(model);

            }
        }, TextUtils.isEmpty(id) ? true : false);
    }

    //设置进入真题详情
    public void actionJoin(String id) {

        deploy(getApi().actionJoin(id), PublishSubject.create(), new MVPModelSuccessCallbacks<Object>() {
            @Override
            public void onSuccess(Object o) {

            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);

            }

            @Override
            public void onException(BaseModel model) {
                super.onException(model);

            }
        }, false);
    }

}
