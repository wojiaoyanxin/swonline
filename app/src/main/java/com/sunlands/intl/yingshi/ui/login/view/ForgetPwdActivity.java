package com.sunlands.intl.yingshi.ui.login.view;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shangde.lepai.uilib.view.text.PowerfulEditText;
import com.sunlands.comm_core.base.ibase.IBaseView;
import com.sunlands.comm_core.base.mvp.MvpActivity;
import com.sunlands.comm_core.helper.RxBindingHelper;
import com.sunlands.comm_core.utils.CommonUtils;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.ui.login.ILoginContract;
import com.sunlands.intl.yingshi.ui.login.presenter.ForgetPwdPresenter;
import com.xueh.bg.drawable.DrawableCreator;

public class ForgetPwdActivity extends MvpActivity<ForgetPwdPresenter> implements ILoginContract.IForgetPwdView {
    private ImageView mIv_title_back;
    private TextView mTv_title_right;
    private TextView mTv_forget_password_next;
    private PowerfulEditText mEt_forget_pwd_input_phone;

    @Override
    public void onClick(View v) {
        if (mIv_title_back == v) {
            onBackPressed();
        } else if (mTv_forget_password_next == v) {
            getPresenter().forgetPwdSendCode(0,CommonUtils.getStrFromView(mEt_forget_pwd_input_phone));
        }
    }

    @Override
    public int getCreateViewLayoutId() {
        return R.layout.activity_forget_pwd;
    }

    @Override
    public void initDataBeforeView() {

    }

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        TextView tv_title_content = findViewById(R.id.tv_title_content);
        mTv_title_right = findViewById(R.id.tv_title_right);
        mIv_title_back = findViewById(R.id.iv_title_back);
        tv_title_content.setText("忘记密码");
        mTv_forget_password_next = findViewById(R.id.tv_forget_password_next);
        mEt_forget_pwd_input_phone = findViewById(R.id.et_forget_pwd_input_phone);
    }

    @Override
    public void initDataAfterView() {

    }

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {

    }

    @Override
    public void initListener() {
        RxBindingHelper.setOnClickListener(mTv_forget_password_next, this);
        RxBindingHelper.setOnClickListener(mIv_title_back, this);
        mEt_forget_pwd_input_phone.addTextListener(new PowerfulEditText.TextListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {
                if (s.length() == 11) {
                    Drawable drawable = new DrawableCreator.Builder().setCornersRadius(CommonUtils.dip2px(22))
                            .setSolidColor(CommonUtils.getColor(R.color.cl_D2AA77))
                            .setStrokeColor(CommonUtils.getColor(R.color.cl_D2AA77))
                            .build();
                    mTv_forget_password_next.setBackground(drawable);
                    mTv_forget_password_next.setClickable(true);
                } else {
                    Drawable drawable = new DrawableCreator.Builder().setCornersRadius(CommonUtils.dip2px(22))
                            .setSolidColor(CommonUtils.getColor(R.color.cl_cccccc))
                            .setStrokeColor(CommonUtils.getColor(R.color.cl_cccccc))
                            .build();
                    mTv_forget_password_next.setClickable(false);
                    mTv_forget_password_next.setBackground(drawable);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }


    @Override
    protected ForgetPwdPresenter createPresenter(IBaseView view) {
        return new ForgetPwdPresenter(this);
    }

    @Override
    public void onSendCodeSuccess(String phone) {
        Intent intent = new Intent(this, ForgePwdResetActivity.class);
        intent.putExtra(ForgePwdResetActivity.KEY_PHONE, phone);
        startActivity(intent);
    }
}
