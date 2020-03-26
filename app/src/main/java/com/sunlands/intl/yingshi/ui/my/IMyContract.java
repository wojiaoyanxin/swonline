package com.sunlands.intl.yingshi.ui.my;

import android.arch.lifecycle.Lifecycle;

import com.sunlands.comm_core.base.ibase.IBaseModel;
import com.sunlands.comm_core.base.ibase.IBaseView;
import com.sunlands.comm_core.net.MVPModelCallbacks;
import com.sunlands.intl.yingshi.bean.MyOrderBean;
import com.sunlands.intl.yingshi.ui.home.bean.SmallClassOrderBean;

import io.reactivex.subjects.PublishSubject;

/**
 * 创 建 人: xueh
 * 创建日期: 2019/5/6 13:36
 * 备注：
 */
public interface IMyContract {

    interface IMyOrderView extends IBaseView {
            void getMyOrderSuccess(MyOrderBean bean);
            void getPayResultSuccess(SmallClassOrderBean order);
    }
    interface IMyOrderModel extends IBaseModel {
        void getMyOrder(int limit, PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<MyOrderBean> mMVPModelCallbacks);
        void getPayResult(int id, PublishSubject<Lifecycle.Event> mPublishSubject, MVPModelCallbacks<SmallClassOrderBean> mMVPModelCallbacks);
    }
}
