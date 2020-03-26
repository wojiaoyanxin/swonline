package com.sunlands.intl.yingshi.common;


import com.blankj.utilcode.util.ToastUtils;
import com.sunlands.comm_core.base.mvp.MvpBasePresenter;
import com.sunlands.comm_core.net.BaseModel;
import com.sunlands.comm_core.net.MVPModelCallbacks;
import com.sunlands.intl.yingshi.groovy.CheckNet;

import javax.annotation.Nullable;

/**
 * Created by yanxin on 2019/5/15.
 */

public class CommonPresenter<T> extends MvpBasePresenter<CommonContract.ICommonView<T>, CommonModel<T>> {

    public CommonPresenter(CommonContract.ICommonView<T> tiCommonView) {
        super(tiCommonView);
    }

    @Override
    protected CommonModel<T> createModel() {
        return new CommonModel<T>();
    }

    @CheckNet
    public void getDataFromNet(@Nullable String type, Boolean isShow, Object... o) {
        if (getView() == null) {
            return;
        }
        getModel().getData(type, isShow, getView().getLifecycleSubject(), new MVPModelCallbacks<T>() {
            @Override
            public void onSuccess(T data) {
                getView().getDataSuccess(data);
                getView().hideLoading();
            }

            @Override
            public void onException(BaseModel model) {
                getView().hideLoading();
                getView().getDataFailed(model);
                ToastUtils.showShort(model.getMsg());
            }

            @Override
            public void onError(Throwable e) {
                getView().getDataFailed(new BaseModel());
                getView().hideLoading();
                //  ToastUtils.showShort(e.getMessage());
            }
        }, o);
    }

    @CheckNet
    public void getDataFromNet(@Nullable String type, Object... o) {
        if (getView() == null) {
            return;
        }
        getModel().getData(type, getView().getLifecycleSubject(), new MVPModelCallbacks<T>() {
            @Override
            public void onSuccess(T data) {
                getView().getDataSuccess(data);
                getView().hideLoading();
            }

            @Override
            public void onException(BaseModel model) {
                getView().hideLoading();
                getView().getDataFailed(model);
                ToastUtils.showShort(model.getMsg());
            }

            @Override
            public void onError(Throwable e) {
                getView().getDataFailed(new BaseModel());
                getView().hideLoading();
                //  ToastUtils.showShort(e.getMessage());
            }
        }, o);
    }
}
