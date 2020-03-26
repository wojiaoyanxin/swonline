package com.sunlands.intl.yingshi.ui.login.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.shangde.lepai.uilib.view.text.PasswordEditText;
import com.sunlands.comm_core.base.ibase.IBaseView;
import com.sunlands.comm_core.base.mvp.MvpActivity;
import com.sunlands.comm_core.helper.RxBindingHelper;
import com.sunlands.comm_core.utils.CommonUtils;
import com.sunlands.comm_core.utils.rx.RxBindingUtils;
import com.sunlands.comm_core.utils.rx.exception.RxException;
import com.sunlands.comm_core.utils.rx.rxjava.RxJavaUtils;
import com.sunlands.comm_core.utils.rx.subsciber.BaseSubscriber;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.dialog.TwoBtTitleDialog;
import com.sunlands.intl.yingshi.ui.login.ILoginContract;
import com.sunlands.intl.yingshi.ui.login.presenter.ForgePwdResetPresenter;
import com.sunlands.intl.yingshi.util.OtherUtils;
import com.xueh.bg.drawable.DrawableCreator;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

public class ForgePwdResetActivity extends MvpActivity<ForgePwdResetPresenter> implements ILoginContract.IForgePwdResetView {
    public static final String KEY_PHONE = "phone";
    private ImageView mIv_title_back;
    private EditText mEt_forget_pwd_input_code;
    private PasswordEditText mPsd_forget_pwd_et;
    private TextView mTv_et_forget_pwd_code_countdown;
    private TextView mTv_forget_pwd_reset, mTv_title_right;
    private String mPhone;
    Long mTime;

    @Override
    public void onClick(View v) {
        if (v == mIv_title_back) {
            onBackPressed();
        } else if (v == mTv_forget_pwd_reset) {
            getPresenter().resetPwd(mPhone, CommonUtils.getStrFromView(mEt_forget_pwd_input_code), CommonUtils.getStrFromView(mPsd_forget_pwd_et));
        } else if (v == mTv_et_forget_pwd_code_countdown) {
            getPresenter().getCode(0, mPhone);
        } else if (v == mTv_title_right) {
            if (!ObjectUtils.isEmpty(mTime) && mTime > 0) {
                com.blankj.utilcode.util.ToastUtils.showLong("请于秒" + mTime + "后再次获取");
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
        return R.layout.activity_forge_pwd_rest;
    }

    @Override
    public void initDataBeforeView() {

    }

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        TextView tv_title_content = findViewById(R.id.tv_title_content);
        mIv_title_back = findViewById(R.id.iv_title_back);
        tv_title_content.setText("忘记密码");
        mEt_forget_pwd_input_code = findViewById(R.id.et_forget_pwd_input_code);
        mPsd_forget_pwd_et = findViewById(R.id.psd_forget_pwd_et);
        mTv_et_forget_pwd_code_countdown = findViewById(R.id.tv_et_forget_pwd_code_countdown);
        mTv_forget_pwd_reset = findViewById(R.id.tv_forget_pwd_reset);
        mTv_title_right = findViewById(R.id.tv_title_right);
        mTv_title_right.setVisibility(View.VISIBLE);
        mTv_title_right.setText("语音验证码");
        mTv_title_right.setTextColor(CommonUtils.getColor(R.color.cl_D5A96D));
    }

    @Override
    public void initDataAfterView() {
        mPhone = getIntent().getStringExtra(KEY_PHONE);
    }

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        countDown();
    }

    @Override
    public void initListener() {
        RxBindingHelper.setOnClickListener(mTv_title_right, this);
        RxBindingHelper.setOnClickListener(mTv_et_forget_pwd_code_countdown, this);
        RxBindingHelper.setOnClickListener(mIv_title_back, this);
        RxBindingHelper.setOnClickListener(mTv_forget_pwd_reset, this);

        addDisposable(Observable.combineLatest(RxBindingUtils.textChanges(mEt_forget_pwd_input_code), RxBindingUtils.textChanges(mPsd_forget_pwd_et), new BiFunction<CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean apply(CharSequence charSequence, CharSequence charSequence2) throws Exception {
                return charSequence.length() == 6 && charSequence2.length() >= 6 && charSequence2.length() <= 20;
            }
        }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean) {
                    Drawable drawable = new DrawableCreator.Builder().setCornersRadius(CommonUtils.dip2px(22))
                            .setSolidColor(CommonUtils.getColor(R.color.cl_D2AA77))
                            .setStrokeColor(CommonUtils.getColor(R.color.cl_D2AA77))
                            .build();
                    mTv_forget_pwd_reset.setBackground(drawable);
                    mTv_forget_pwd_reset.setClickable(true);
                } else {
                    Drawable drawable = new DrawableCreator.Builder().setCornersRadius(CommonUtils.dip2px(22))
                            .setSolidColor(CommonUtils.getColor(R.color.cl_cccccc))
                            .setStrokeColor(CommonUtils.getColor(R.color.cl_cccccc))
                            .build();
                    mTv_forget_pwd_reset.setClickable(false);
                    mTv_forget_pwd_reset.setBackground(drawable);
                }
            }
        }));
    }

    @Override
    protected ForgePwdResetPresenter createPresenter(IBaseView view) {
        return new ForgePwdResetPresenter(this);
    }

    @Override
    public void onPwdReseSuccess() {
        ToastUtils.showShort("密码设置成功");
        this.finish();
        ActivityUtils.finishActivity(ForgetPwdActivity.class);
    }

    @Override
    public void onCodeSendSuccess() {
        ToastUtils.showShort("发送成功");
        countDown();
    }

    private void countDown() {
        addDisposable(RxJavaUtils.countDown(60, new BaseSubscriber<Long>() {
            @Override
            public void onError(RxException e) {

            }

            @Override
            protected void onStart() {
                super.onStart();
                OtherUtils.setNoClickableView(mTv_et_forget_pwd_code_countdown, 3);
            }

            @Override
            public void onComplete() {
                super.onComplete();
                OtherUtils.setClickableView(mTv_et_forget_pwd_code_countdown, 3);
                mTv_et_forget_pwd_code_countdown.setText("重新获取");
                mTv_et_forget_pwd_code_countdown.setTextColor(CommonUtils.getColor(R.color.cl_ffffff));
            }

            @Override
            public void onSuccess(Long aLong) {
                mTime = aLong;
                mTv_et_forget_pwd_code_countdown.setText(String.format("(%ss)重新获取", aLong));
            }
        }));
    }
}
