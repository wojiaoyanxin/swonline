package com.sunlands.intl.yingshi.ui.my.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.sunlands.comm_core.helper.RxBindingHelper;
import com.sunlands.comm_core.utils.CommonUtils;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.bean.EmptyBean;
import com.sunlands.intl.yingshi.common.CommonActivity;
import com.sunlands.intl.yingshi.ui.activity.MainActivity;
import com.sunlands.intl.yingshi.ui.login.view.PhoneLoginActivity;
import com.sunlands.intl.yingshi.util.StringUtils;


/**
 * 修改密码
 */
public class ChangePasswordActivity extends CommonActivity<EmptyBean> {

    EditText mEtOldPwd;
    EditText mEtNewPwd;
    EditText mEtEnsurePwd;
    Button mBtnSave;

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        super.findView(inflateView, savedInstanceState);
        mEtOldPwd = FBIA(R.id.et_old_pwd);
        mEtNewPwd = FBIA(R.id.et_new_pwd);
        mEtEnsurePwd = FBIA(R.id.et_ensure_pwd);
        mBtnSave = FBIA(R.id.btn_save);
    }

    @Override
    public void initListener() {
        super.initListener();
        RxBindingHelper.setOnClickListener(mBtnSave, this);
    }


    @Override
    public String getTitleText() {
        return CommonUtils.getString(R.string.change_password);
    }

    @Override
    public void onSuccess(EmptyBean bean) {
        ActivityUtils.finishActivity(MainActivity.class);
        ActivityUtils.finishActivity(SettingsActivity.class);
        ToastUtils.showShort(getString(R.string.change_pwd_success));
        ActivityUtils.startActivity(ChangePasswordActivity.this, PhoneLoginActivity.class);
        finish();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_password;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        if (v.getId() == R.id.btn_save) {
            String oldPwd = mEtOldPwd.getText().toString();
            String newPwd = mEtNewPwd.getText().toString();
            String ensurePwd = mEtEnsurePwd.getText().toString();
            if (StringUtils.isEmpty(oldPwd) || StringUtils.isEmpty(newPwd) || StringUtils.isEmpty(ensurePwd)) {
                ToastUtils.showShort(R.string.password_can_not_be_empty);
                return;
            }
            if (!StringUtils.isPasswordValid(oldPwd) || !StringUtils.isPasswordValid(newPwd)
                    || !StringUtils.isPasswordValid(ensurePwd)) {
                ToastUtils.showShort(R.string.please_input_6_to_12_password);
                return;
            }
            if (!newPwd.equals(ensurePwd)) {
                ToastUtils.showShort(R.string.input_password_not_equals);
                return;
            }
            getDataNet(true, oldPwd, newPwd, ensurePwd);
        }

    }
}
