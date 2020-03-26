package com.sunlands.intl.yingshi.ui.my.view;


import android.os.Bundle;
import android.view.View;

import com.sunlands.comm_core.helper.EventBusHelper;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.ui.my.bean.NameType;

public class PositionNameActivity extends CompanyNameActivity {

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        super.initView(inflateView, savedInstanceState);

        tvTitle.setText("职位名称");
        tvHeaderRight.setText("保存");
        editName.setHint("请输入职位名称");
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        if (id == R.id.tv_header_right) {
            NameType nameType = new NameType(editName.getText().toString(), "2");
            EventBusHelper.post(nameType);
            onBackPressed();
        } else if (id == R.id.vg_back) {
            onBackPressed();
        }
    }

}
