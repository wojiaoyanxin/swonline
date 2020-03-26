package com.sunlands.intl.yingshi.ui.my.presenter;

import com.blankj.utilcode.util.ToastUtils;
import com.sunlands.comm_core.base.mvp.MvpBasePresenter;
import com.sunlands.comm_core.net.BaseModel;
import com.sunlands.comm_core.net.MVPModelCallbacks;
import com.sunlands.intl.yingshi.bean.MyOrderBean;
import com.sunlands.intl.yingshi.groovy.CheckNet;
import com.sunlands.intl.yingshi.ui.home.bean.SmallClassOrderBean;
import com.sunlands.intl.yingshi.ui.my.IMyContract;
import com.sunlands.intl.yingshi.ui.my.model.MyOrderModel;

/**
 * 创 建 人: xueh
 * 创建日期: 2019/5/6 13:39
 * 备注：
 */
public class MyOrderPresenter extends MvpBasePresenter<IMyContract.IMyOrderView,MyOrderModel> {

    public MyOrderPresenter(IMyContract.IMyOrderView iMyOrderView) {
        super(iMyOrderView);
    }

    @Override
    protected MyOrderModel createModel() {
        return new MyOrderModel();
    }

    @CheckNet
    public void getOrderList(int limit){
        getView().showLoading();
        getModel().getMyOrder(limit,getView().getLifecycleSubject(), new MVPModelCallbacks<MyOrderBean>() {
            @Override
            public void onSuccess(MyOrderBean data) {
                getView().getMyOrderSuccess(data);
                getView().hideLoading();
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
    public void pay(int id){
        getView().showLoading();
        getModel().getPayResult(id,getView().getLifecycleSubject(), new MVPModelCallbacks<SmallClassOrderBean>() {
            @Override
            public void onSuccess(SmallClassOrderBean data) {
                getView().getPayResultSuccess(data);
                getView().hideLoading();
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
