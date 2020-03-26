package com.sunlands.intl.yingshi.ui.activity.home.biling;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.common.MyActivity;
import com.sunlands.intl.yingshi.util.EnterPlayerUtils;

/**
 * 大咖面对面
 */
public class BilingDaKaActivity extends MyActivity<BilingBean> {


    @Override
    public int getLayoutId() {
        return R.layout.activity_biling_da_ka;
    }

    @Override
    public String getTitleText() {
        return "大咖面对面";
    }

    @Override
    public BaseQuickAdapter getAdapter() {
        return new BilingListAdapter();
    }

    @Override
    public void onSuccess(BilingBean bean) {
        super.onSuccess(bean);
        setData(bean.getList(), false);
    }

    @Override
    public void initListener() {
        super.initListener();
        baseQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                BilingBean.ListBean listBean = (BilingBean.ListBean) baseQuickAdapter.getData().get(i);
                int is_free = listBean.getIs_free();
                EnterPlayerUtils.enterBilingClass(BilingDaKaActivity.this, listBean.getCourse_id(), is_free);
            }
        });
    }


    @Override
    public void load() {
        super.load();
        getDataOnNet(0);
    }

    @Override
    public void initDataAfterView() {
        super.initDataAfterView();
        getDataOnNet(0);
    }
}
