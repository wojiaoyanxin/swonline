package com.sunlands.intl.yingshi.ui.my.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.sunlands.comm_core.helper.EventBusHelper;
import com.sunlands.comm_core.helper.RxBindingHelper;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.bean.EmptyBean;
import com.sunlands.intl.yingshi.common.CommonActivity;
import com.sunlands.intl.yingshi.ui.my.bean.NameType;


public class CompanyNameActivity extends CommonActivity<EmptyBean> {

    TextView tvHeaderRight;
    EditText editName;
    TextView counts;

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        super.findView(inflateView, savedInstanceState);

        tvHeaderRight = FBIA(R.id.tv_header_right);
        editName = FBIA(R.id.edit_name);
        counts = FBIA(R.id.counts);

    }

    @Override
    public void initListener() {
        super.initListener();
        editName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                counts.setText(s.length() + "/20");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        RxBindingHelper.setOnClickListener(tvHeaderRight,this);
        RxBindingHelper.setOnClickListener(editName,this);
        RxBindingHelper.setOnClickListener(counts,this);
    }

    @Override
    public void initDataAfterView() {
        super.initDataAfterView();
        String name = getIntent().getStringExtra("name");
        if (!TextUtils.isEmpty(name)) {
            editName.setText(name);
            counts.setText(name.length() + "/20");
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_company_name;
    }

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        super.initView(inflateView, savedInstanceState);
        tvHeaderRight.setText("保存");
    }

    @Override
    public String getTitleText() {
        return "公司名称";
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);

        int id = v.getId();
        if (id == R.id.tv_header_right) {
            NameType nameType = new NameType(editName.getText().toString(), "1");
            EventBusHelper.post(nameType);
            onBackPressed();
        } else if (id == R.id.vg_back) {
            onBackPressed();
        }
    }
}
