package com.sunlands.intl.yingshi.ui.home.view;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.common.MyActivity;
import com.sunlands.intl.yingshi.ui.home.adapter.TodayHotListFromHomeAdapter;
import com.sunlands.intl.yingshi.ui.home.bean.TodayHotListBean;


public class TodayHotListActivity extends MyActivity<TodayHotListBean> {


    @Override
    public int getLayoutId() {
        return R.layout.activity_today_hot_list;
    }

    @Override
    public String getTitleText() {
        return "今日热点";
    }

    @Override
    public void onSuccess(TodayHotListBean bean) {
        super.onSuccess(bean);
        setData(bean.getList(),bean.getHasMore() == 1);
    }

    @Override
    public BaseQuickAdapter getAdapter() {
        return new TodayHotListFromHomeAdapter();
    }

    @Override
    public void initDataAfterView() {
        super.initDataAfterView();
        getDataOnNet(mNextPage);
    }

    @Override
    public void load() {
        super.load();
        getDataOnNet(mNextPage);
    }
}
