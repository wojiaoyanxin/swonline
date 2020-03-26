package com.sunlands.intl.yingshi.ui.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.shangde.lepai.uilib.view.vcedittext.VerificationAction;
import com.shangde.lepai.uilib.view.vcedittext.VerificationCodeEditText;
import com.sunlands.comm_core.base.ibase.IBaseView;
import com.sunlands.comm_core.base.mvp.MvpActivity;
import com.sunlands.comm_core.helper.RxBindingHelper;
import com.sunlands.comm_core.utils.CommonUtils;
import com.sunlands.comm_core.utils.rx.exception.RxException;
import com.sunlands.comm_core.utils.rx.rxjava.RxJavaUtils;
import com.sunlands.comm_core.utils.rx.subsciber.BaseSubscriber;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.greendao.db.LoginInfo;
import com.sunlands.intl.yingshi.dialog.TwoBtTitleDialog;
import com.sunlands.intl.yingshi.helper.LoginInOutHelper;
import com.sunlands.intl.yingshi.ui.activity.MainActivity;
import com.sunlands.intl.yingshi.ui.login.ILoginContract;
import com.sunlands.intl.yingshi.ui.login.presenter.VerifiCationPresenter;

public class VerifiCationActivity extends MvpActivity<VerifiCationPresenter> implements ILoginContract.IVerifiCationLoginView {
    private ImageView mIv_title_back;

    public static final String PHONE_KEY = "phone";
    private VerificationCodeEditText mVce_et;
    private TextView mTv_login_send_form_phone;
    private String mPhone;
    private TextView mTv_login_count_down_time;
    private TextView mTv_title_right;
    private Long mTime;

    @Override
    public void onClick(View v) {
        if (v == mIv_title_back) {
            onBackPressed();
        } else if (v == mTv_login_count_down_time) {
            getPresenter().getCode(0, mPhone);
        } else if (v == mTv_title_right) {
            if (!ObjectUtils.isEmpty(mTime) && mTime > 0) {
                ToastUtils.showLong("请于秒" + mTime + "后再次获取");
            } else {
                TwoBtTitleDialog dialog = TwoBtTitleDialog.getInstance("语音验证码", "取消", "现在接听", "若您长时间未能收到短信验证码可通过电话方式获取请注意接听来电");
                dialog.setOnRightClick(new TwoBtTitleDialog.onRightClick() {
                    @Override
                    public void onRight() {
                        getPresenter().getCode(1, mPhone);
                    }
                });
                dialog.show(getSupportFragmentManager(), null);
            }
        }
    }

    @Override
    public int getCreateViewLayoutId() {
        return R.layout.activity_verifi_cation;
    }

    @Override
    public void initDataBeforeView() {
        mPhone = getIntent().getStringExtra(PHONE_KEY);
    }

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        TextView tv_title_content = findViewById(R.id.tv_title_content);
        mIv_title_back = findViewById(R.id.iv_title_back);
        mTv_title_right = FBIA(R.id.tv_title_right);
        mTv_title_right.setVisibility(View.GONE);
        mTv_title_right.setText("语音验证码");
        mTv_title_right.setTextColor(CommonUtils.getColor(R.color.cl_D5A96D));
        mVce_et = findViewById(R.id.vce_et);
        mTv_login_send_form_phone = findViewById(R.id.tv_login_send_form_phone);
        mTv_login_count_down_time = findViewById(R.id.tv_login_count_down_time);
        tv_title_content.setText("短信验证码");

    }

    @Override
    public void initDataAfterView() {

    }

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        mTv_login_send_form_phone.setText(String.format("验证码已发至%s", mPhone));
        countDownt();
    }

    private void countDownt() {
        addDisposable(RxJavaUtils.countDown(60, new BaseSubscriber<Long>() {
            @Override
            public void onError(RxException e) {

            }

            @Override
            protected void onStart() {
                super.onStart();
                mTv_login_count_down_time.setClickable(false);
                mTv_login_count_down_time.setTextColor(CommonUtils.getColor(R.color.cl_999999));
            }

            @Override
            public void onComplete() {
                super.onComplete();
                mTv_login_count_down_time.setText("重新获取");
                mTv_login_count_down_time.setClickable(true);
                mTv_login_count_down_time.setTextColor(CommonUtils.getColor(R.color.cl_D2AA77));
            }

            @Override
            public void onSuccess(Long aLong) {
                mTime = aLong;
                mTv_login_count_down_time.setText(String.format("(%ss)重新获取", aLong));
                mTv_login_count_down_time.setClickable(false);
            }
        }));
    }

    @Override
    public void initListener() {
        RxBindingHelper.setOnClickListener(mTv_title_right, this);
        RxBindingHelper.setOnClickListener(mTv_login_count_down_time, this);
        RxBindingHelper.setOnClickListener(mIv_title_back, this);
        mVce_et.setOnVerificationCodeChangedListener(new VerificationAction.OnVerificationCodeChangedListener() {
            @Override
            public void onVerCodeChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void onInputCompleted(CharSequence s) {
                KeyboardUtils.hideSoftInput(VerifiCationActivity.this);
                getPresenter().smsLogin(mPhone, s.toString());
            }
        });
    }

    @Override
    protected VerifiCationPresenter createPresenter(IBaseView view) {
        return new VerifiCationPresenter(this);
    }

    @Override
    public void onSmsLoginSuccess(LoginInfo data) {
        LoginInOutHelper.loginIn(data);
        startActivity(new Intent(this, MainActivity.class));
        ActivityUtils.finishActivity(PhoneLoginActivity.class);
        this.finish();
    }

    @Override
    public void getCodeSuccess() {
        countDownt();
    }
}
