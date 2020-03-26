package com.sunlands.intl.yingshi.ui.login.view;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.sunlands.intl.yingshi.ui.login.presenter.PhoneLoginPresenter;
import com.sunlands.intl.yingshi.util.SPHelper;
import com.xueh.bg.drawable.DrawableCreator;

public class PhoneLoginActivity extends MvpActivity<PhoneLoginPresenter> implements ILoginContract.IPhoneLoginView {

    private ImageView mIv_title_back;
    private TextView mTv_title_right;
    private PowerfulEditText mEt_login_input_phone;
    private TextView mTv_login_get_code;

    @Override
    public int getCreateViewLayoutId() {
        return R.layout.activity_phone_login;
    }

    @Override
    public void initDataBeforeView() {

    }

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        TextView tv_title_content = findViewById(R.id.tv_title_content);
        mTv_title_right = findViewById(R.id.tv_title_right);
        mIv_title_back = findViewById(R.id.iv_title_back);
        tv_title_content.setText("输入手机号");
        mEt_login_input_phone = findViewById(R.id.et_login_input_phone);
        mTv_login_get_code = findViewById(R.id.tv_login_get_code);
    }

    @Override
    public void initDataAfterView() {
        mEt_login_input_phone.setText(SPHelper.getUserPhone());
        mEt_login_input_phone.setSelection(SPHelper.getUserPhone().length());
    }

    @Override
    public void onClick(View v) {
        if (mIv_title_back == v) {
            onBackPressed();
        } else if (mTv_title_right == v) {
            //密码登录
            startActivity(new Intent(this,PwdLoginActivity.class));
        } else if (mTv_login_get_code == v) {
            //获取验证吗
            getPresenter().getCode(0,CommonUtils.getStrFromView(mEt_login_input_phone));
        }

    }

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        mTv_title_right.setVisibility(View.GONE);
        mTv_title_right.setText("密码登录");
        mTv_title_right.setTextColor(CommonUtils.getColor(R.color.cl_D5A96D));
    }

    @Override
    public void initListener() {
        RxBindingHelper.setOnClickListener(mIv_title_back, this);
        RxBindingHelper.setOnClickListener(mTv_title_right, this);
        RxBindingHelper.setOnClickListener(mTv_login_get_code, this);
        mEt_login_input_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 11) {
                    mTv_login_get_code.setClickable(true);
                    Drawable drawable = new DrawableCreator.Builder().setCornersRadius(CommonUtils.dip2px(22))
                            .setSolidColor(CommonUtils.getColor(R.color.cl_D2AA77))
                            .setStrokeColor(CommonUtils.getColor(R.color.cl_D2AA77))
                            .build();
                    mTv_login_get_code.setBackground(drawable);
                } else {
                    Drawable drawable = new DrawableCreator.Builder().setCornersRadius(CommonUtils.dip2px(22))
                            .setSolidColor(CommonUtils.getColor(R.color.cl_cccccc))
                            .setStrokeColor(CommonUtils.getColor(R.color.cl_cccccc))
                            .build();
                    mTv_login_get_code.setClickable(false);
                    mTv_login_get_code.setBackground(drawable);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    protected PhoneLoginPresenter createPresenter(IBaseView view) {
        return new PhoneLoginPresenter(this);
    }

    @Override
    public void onGetCodeSuccess(String phone) {
        Intent intent = new Intent(this, VerifiCationActivity.class);
        intent.putExtra(VerifiCationActivity.PHONE_KEY, phone);
        startActivity(intent);
    }
}
