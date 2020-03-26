package com.sunlands.intl.yingshi.ui.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.sunlands.comm_core.helper.RxBindingHelper;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.common.CommonActivity;
import com.sunlands.intl.yingshi.helper.LoginInOutHelper;
import com.sunlands.intl.yingshi.ui.activity.home.apply.ApplyActivity;

/**
 * 弹窗activity
 */

public class DialogActivity extends CommonActivity<Object> {

    private TextView yesBtn;
    private TextView noBtn;
    private View viewLine;
    private TextView message;
    private String type;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_dialog;
    }

    @Override
    public String getTitleText() {
        return null;
    }


    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        super.initView(inflateView, savedInstanceState);
        String mMessage = getIntent().getStringExtra("message");
        type = getIntent().getStringExtra("type");

        if ("1".equals(type)) { //已经申请
            message.setText(mMessage);
        } else if ("2".equals(type)) { //没有申请  前往成为学员
            message.setText("该课程仅学员可观看，是否申请成为学员");
            yesBtn.setText("前往");
            viewLine.setVisibility(View.VISIBLE);
            noBtn.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        super.findView(inflateView, savedInstanceState);
        yesBtn = FBIA(R.id.yes_btn);
        noBtn = FBIA(R.id.no_btn);
        viewLine = FBIA(R.id.view_line);
        message = FBIA(R.id.message);
    }

    @Override
    public void initListener() {
        super.initListener();
        RxBindingHelper.setOnClickListener(yesBtn, this);
        RxBindingHelper.setOnClickListener(noBtn, this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        if (v.getId() == R.id.no_btn) {
            onBackPressed();
            overridePendingTransition(0, 0);
        } else if (v.getId() == R.id.yes_btn) {
            if ("2".equals(type)) {
                onBackPressed();
                ActivityUtils.startActivity(ApplyActivity.class);
            } else if ("1".equals(type)) {
                onBackPressed();
                overridePendingTransition(0, 0);
            } else {
                LoginInOutHelper.loginOut(this);
            }
        }

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return true;
    }
}
