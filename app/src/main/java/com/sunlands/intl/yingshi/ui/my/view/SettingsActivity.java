package com.sunlands.intl.yingshi.ui.my.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.sunlands.comm_core.helper.RxBindingHelper;
import com.sunlands.comm_core.net.BaseModel;
import com.sunlands.comm_core.utils.CommonUtils;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.bean.EmptyBean;
import com.sunlands.intl.yingshi.common.CommonActivity;
import com.sunlands.intl.yingshi.helper.LoginInOutHelper;

public class SettingsActivity extends CommonActivity<EmptyBean> {

    TextView mTvTitle;
    LinearLayout mLlChangePassword;
    TextView mTvLogout;

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        super.findView(inflateView, savedInstanceState);
        mTvTitle = FBIA(R.id.tv_title);
        mLlChangePassword = FBIA(R.id.ll_change_password);
        mTvLogout = FBIA(R.id.tv_logout);
    }

    @Override
    public void initListener() {
        super.initListener();
        RxBindingHelper.setOnClickListener(mTvLogout, this);
        RxBindingHelper.setOnClickListener(mLlChangePassword, this);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        int id = v.getId();
        if (id == R.id.tv_logout) {
            getDataNet(false, "");
        } else if (id == R.id.ll_change_password) {
            ActivityUtils.startActivity(this, ChangePasswordActivity.class);
        }

    }


    @Override
    public String getTitleText() {
        return CommonUtils.getString(R.string.setting);
    }

    @Override
    public void onSuccess(EmptyBean bean) {
        hideLoading();
        LoginInOutHelper.loginOut(SettingsActivity.this);
    }

    @Override
    public void onFailed(BaseModel model) {
        super.onFailed(model);
        hideLoading();
        LoginInOutHelper.loginOut(SettingsActivity.this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_settings;
    }

    public static void show(@NonNull Activity activity, int requestCode) {
        Intent intent = new Intent(activity, SettingsActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }

}
