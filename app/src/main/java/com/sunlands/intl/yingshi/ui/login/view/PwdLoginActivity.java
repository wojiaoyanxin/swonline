package com.sunlands.intl.yingshi.ui.login.view;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.shangde.lepai.uilib.view.text.PasswordEditText;
import com.shangde.lepai.uilib.view.text.PowerfulEditText;
import com.sunlands.comm_core.base.ibase.IBaseView;
import com.sunlands.comm_core.base.mvp.MvpActivity;
import com.sunlands.comm_core.helper.RxBindingHelper;
import com.sunlands.comm_core.utils.CommonUtils;
import com.sunlands.comm_core.utils.rx.RxBindingUtils;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.greendao.db.LoginInfo;
import com.sunlands.intl.yingshi.helper.LoginInOutHelper;
import com.sunlands.intl.yingshi.ui.activity.MainActivity;
import com.sunlands.intl.yingshi.ui.login.ILoginContract;
import com.sunlands.intl.yingshi.ui.login.presenter.PwdLoignPresenter;
import com.sunlands.intl.yingshi.util.SPHelper;
import com.xueh.bg.drawable.DrawableCreator;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

public class PwdLoginActivity extends MvpActivity<PwdLoignPresenter> implements ILoginContract.IPwdLoginView {
    private ImageView mIv_title_back;
    private TextView mTv_title_right, mTv_forget_password, mTv_pw_login;
    private PasswordEditText mPsd_login_et;
    private PowerfulEditText mEt_pw_login_input_phone;

    @Override
    public void onClick(View v) {
        if (mIv_title_back == v) {
            onBackPressed();
        } else if (mTv_forget_password == v) {
            //忘记密码
            startActivity(new Intent(this, ForgetPwdActivity.class));
        } else if (mTv_title_right == v) {
            //免密登录
            startActivity(new Intent(this, PhoneLoginActivity.class));
        } else if (mTv_pw_login == v) {
            getPresenter().pwdLogin(CommonUtils.getStrFromView(mEt_pw_login_input_phone), mPsd_login_et.getTrimmedString());
        }
    }

    @Override
    public int getCreateViewLayoutId() {
        return R.layout.activity_pass_word;
    }

    @Override
    public void initDataBeforeView() {

    }

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        TextView tv_title_content = findViewById(R.id.tv_title_content);
        mIv_title_back = findViewById(R.id.iv_title_back);
        mTv_title_right = findViewById(R.id.tv_title_right);
        mTv_forget_password = findViewById(R.id.tv_forget_password);
        mEt_pw_login_input_phone = findViewById(R.id.et_pw_login_input_phone);
        mTv_pw_login = findViewById(R.id.tv_pw_login);
        mPsd_login_et = findViewById(R.id.psd_login_et);
        tv_title_content.setText("输入手机号");
    }

    @Override
    public void initDataAfterView() {
        mEt_pw_login_input_phone.setText(SPHelper.getUserPhone());
        mEt_pw_login_input_phone.setSelection(SPHelper.getUserPhone().length());
    }

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
//        mTv_title_right.setText("免密登录");
//        mTv_title_right.setTextColor(CommonUtils.getColor(R.color.cl_D2AA77));
//        mTv_title_right.setVisibility(View.VISIBLE);
    }

    @Override
    public void initListener() {
        RxBindingHelper.setOnClickListener(mTv_forget_password, this);
        RxBindingHelper.setOnClickListener(mIv_title_back, this);
        RxBindingHelper.setOnClickListener(mTv_title_right, this);
        RxBindingHelper.setOnClickListener(mTv_pw_login, this);
        addDisposable(Observable.combineLatest(RxBindingUtils.textChanges(mEt_pw_login_input_phone), RxBindingUtils.textChanges(mPsd_login_et), new BiFunction<CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean apply(CharSequence charSequence, CharSequence charSequence2) throws Exception {
                return charSequence.length() == 11 && charSequence2.length() >= 6 && charSequence2.length() <= 20;
            }
        }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean) {
                    mTv_pw_login.setClickable(true);
                    Drawable drawable = new DrawableCreator.Builder().setCornersRadius(CommonUtils.dip2px(22))
                            .setSolidColor(CommonUtils.getColor(R.color.cl_D2AA77))
                            .setStrokeColor(CommonUtils.getColor(R.color.cl_D2AA77))
                            .build();
                    mTv_pw_login.setBackground(drawable);
                } else {
                    Drawable drawable = new DrawableCreator.Builder().setCornersRadius(CommonUtils.dip2px(22))
                            .setSolidColor(CommonUtils.getColor(R.color.cl_cccccc))
                            .setStrokeColor(CommonUtils.getColor(R.color.cl_cccccc))
                            .build();
                    mTv_pw_login.setClickable(false);
                    mTv_pw_login.setBackground(drawable);
                }
            }
        }));
    }

    @Override
    protected PwdLoignPresenter createPresenter(IBaseView view) {
        return new PwdLoignPresenter(this);
    }

    @Override
    public void onPwdLoginSuccess(LoginInfo data) {
        hideLoading();
        LoginInOutHelper.loginIn(data);
        startActivity(new Intent(this, MainActivity.class));
        ActivityUtils.finishActivity(PhoneLoginActivity.class);
        this.finish();
    }
}
