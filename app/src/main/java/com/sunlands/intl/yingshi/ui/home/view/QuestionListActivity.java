package com.sunlands.intl.yingshi.ui.home.view;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.common.MyActivity;
import com.sunlands.intl.yingshi.ui.home.adapter.QuestionListAdapter;
import com.sunlands.intl.yingshi.ui.home.bean.QuestionBean;

public class QuestionListActivity extends MyActivity<QuestionBean> {


    @Override
    public BaseQuickAdapter getAdapter() {
        return new QuestionListAdapter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_question_list;
    }

    @Override
    public String getTitleText() {
        return "真题库";
    }

    @Override
    public void onSuccess(QuestionBean bean) {
        super.onSuccess(bean);
        setData(bean.getList(), bean.getHasMore() == 1);
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
