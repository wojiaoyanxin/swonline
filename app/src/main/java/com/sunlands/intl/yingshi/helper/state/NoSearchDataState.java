package com.sunlands.intl.yingshi.helper.state;

import android.view.View;

import com.sunlands.comm_core.statemanager.state.BaseState;
import com.sunlands.intl.yingshi.R;

/**
 * 当前包名: com.sunlands.comm_core.statemanager.ui
 * 创 建 人: xueh
 * 创建日期: 2019/3/18 10:26
 * 备注：
 */
public class NoSearchDataState extends BaseState {

    public final static String STATE = "NoSearchDataState";

    @Override
    protected int getLayoutId() {
        return R.layout.state_no_search_data_layout;
    }

    @Override
    protected void onViewCreated(View stateView) {

    }

    @Override
    public String getState() {
        return STATE;
    }
}
