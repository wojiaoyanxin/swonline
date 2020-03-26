package com.sunlands.intl.yingshi.ui.home.view;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.common.MyActivity;
import com.sunlands.intl.yingshi.ui.home.adapter.AskAnswerListAdapter;
import com.sunlands.intl.yingshi.ui.home.bean.AskAnswerBean;
public class AskAnswerActivity extends MyActivity<AskAnswerBean> {


    @Override
    public BaseQuickAdapter getAdapter() {
        return new AskAnswerListAdapter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_ask_answer;
    }

    @Override
    public String getTitleText() {
        return "考研常识";
    }

    @Override
    public void initDataAfterView() {
        super.initDataAfterView();
        getDataOnNet(mNextPage);
    }
    @Override
    public void onSuccess(AskAnswerBean bean) {
        super.onSuccess(bean);
        setData(bean.getList(), bean.getHasMore() == 1);
    }


    @Override
    public void load() {
        super.load();
        getDataOnNet(mNextPage);
    }
}
