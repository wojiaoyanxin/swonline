package com.sunlands.intl.yingshi.ui.activity.home.smallclass;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.common.MyActivity;
import com.sunlands.intl.yingshi.constant.Constants;
import com.sunlands.intl.yingshi.ui.home.bean.SmallClassUnitsBean;

public class SmallClasListActivity extends MyActivity<SmallClassUnitsBean> {


    @Override
    public BaseQuickAdapter getAdapter() {
        return new SmallClassListAdapter();
    }

    public static void show(Context context, String courseId,String name) {
        Intent intent = new Intent(context, SmallClasListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Constants.Key.KEY_COURSE_ID, courseId);
        intent.putExtra(Constants.Key.KEY_ACTION, name);
        context.startActivity(intent);
    }

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        super.findView(inflateView, savedInstanceState);
        TextView name = FBIA(R.id.tv_class_name);
        name.setText(getIntent().getStringExtra(Constants.Key.KEY_ACTION));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_small_clas_list;
    }

    @Override
    public String getTitleText() {
        return "所有课程";
    }

    @Override
    public void initDataAfterView() {
        super.initDataAfterView();
        getDataOnNet(getIntent().getStringExtra(Constants.Key.KEY_COURSE_ID));
    }

    @Override
    public void onSuccess(SmallClassUnitsBean bean) {
        super.onSuccess(bean);
        setData(bean.getList(),false);
    }
}
