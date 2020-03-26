package com.sunlands.intl.yingshi.ui.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.shangde.lepai.uilib.view.text.PasswordEditText;
import com.shangde.lepai.uilib.view.text.PowerfulEditText;
import com.sunlands.comm_core.base.ibase.IBaseView;
import com.sunlands.comm_core.base.mvp.MvpActivity;
import com.sunlands.comm_core.helper.RxBindingHelper;
import com.sunlands.comm_core.utils.CommonUtils;
import com.sunlands.comm_core.utils.rx.RxBindingUtils;
import com.sunlands.comm_core.utils.rx.exception.RxException;
import com.sunlands.comm_core.utils.rx.rxjava.RxJavaUtils;
import com.sunlands.comm_core.utils.rx.subsciber.BaseSubscriber;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.greendao.db.LoginInfo;
import com.sunlands.intl.yingshi.dialog.TwoBtTitleDialog;
import com.sunlands.intl.yingshi.ui.login.ILoginContract;
import com.sunlands.intl.yingshi.ui.login.presenter.RegisterPresenter;
import com.sunlands.intl.yingshi.util.OtherUtils;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function3;

public class RegisterActivity extends MvpActivity<RegisterPresenter> implements ILoginContract.IRegisterView {
    private ImageView mIv_title_back;
    private PowerfulEditText mEt_register_input_phone;
    private EditText mEt_register_input_code;
    private TextView mTv_et_register_code_countdown, mTv_register, mTv_register_tologin;
    private PasswordEditText mPsd_register_et, mPsd_register_ok_et;
    private TextView mTv_title_right;
    Long mTime;

    @Override
    public void onClick(View v) {
        if (v == mIv_title_back) {
            onBackPressed();
        } else if (v == mTv_register) {
            if (CommonUtils.getStrFromView(mPsd_register_et).equals(CommonUtils.getStrFromView(mPsd_register_ok_et))) {
                getPresenter().pwdRegister(CommonUtils.getStrFromView(mEt_register_input_phone), CommonUtils.getStrFromView(mEt_register_input_code), CommonUtils.getStrFromView(mPsd_register_ok_et));
            } else {
                ToastUtils.showShort("两次密码不一致");
            }
        } else if (v == mTv_et_register_code_countdown) {
            getPresenter().pwdSendCode(0, CommonUtils.getStrFromView(mEt_register_input_phone));
        } else if (v == mTv_register_tologin) {
            startActivity(new Intent(this, PhoneLoginActivity.class));
        } else if (v == mTv_title_right) {
            if (!ObjectUtils.isEmpty(mTime) && mTime > 0) {
                com.blankj.utilcode.util.ToastUtils.showLong("请于秒" + mTime + "后再次获取");
            } else {
                TwoBtTitleDialog dialog = TwoBtTitleDialog.getInstance("语音验证码", "取消", "现在接听", "若您长时间未能收到短信验证码可通过电话方式获取请注意接听来电");
                dialog.setOnRightClick(new TwoBtTitleDialog.onRightClick() {
                    @Override
                    public void onRight() {
                        getPresenter().pwdSendCode(1, CommonUtils.getStrFromView(mEt_register_input_phone));
                    }
                });
                dialog.show(getSupportFragmentManager(), null);
            }
        }

    }

    @Override
    public int getCreateViewLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initDataBeforeView() {

    }

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        TextView tv_title_content = findViewById(R.id.tv_title_content);
        mIv_title_back = findViewById(R.id.iv_title_back);
        mEt_register_input_phone = findViewById(R.id.et_register_input_phone);
        mEt_register_input_code = findViewById(R.id.et_register_input_code);
        mTv_et_register_code_countdown = findViewById(R.id.tv_et_register_code_countdown);
        mPsd_register_et = findViewById(R.id.psd_register_et);
        mPsd_register_ok_et = findViewById(R.id.psd_register_ok_et);
        mTv_register = findViewById(R.id.tv_register);
        mTv_register_tologin = findViewById(R.id.tv_register_tologin);
        tv_title_content.setText("注册");
        mTv_title_right = findViewById(R.id.tv_title_right);
        mTv_title_right.setVisibility(View.VISIBLE);
        mTv_title_right.setText("语音验证码");
        mTv_title_right.setTextColor(CommonUtils.getColor(R.color.cl_D5A96D));
    }

    @Override
    public void initDataAfterView() {

    }

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder("已有账号？立即登录");
        stringBuilder.setSpan(new ForegroundColorSpan(CommonUtils.getColor(R.color.cl_D2AA77)), 5, stringBuilder.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mTv_register_tologin.setText(stringBuilder);
    }

    @Override
    public void initListener() {
        RxBindingHelper.setOnClickListener(mTv_title_right, this);
        RxBindingHelper.setOnClickListener(mTv_et_register_code_countdown, this);
        RxBindingHelper.setOnClickListener(mIv_title_back, this);
        RxBindingHelper.setOnClickListener(mTv_register, this);
        RxBindingHelper.setOnClickListener(mTv_register_tologin, this);
        mEt_register_input_phone.addTextListener(new PowerfulEditText.TextListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {
                if (s.length() == 11) {
                    //正在倒计时不做处理
                    if (!ObjectUtils.isEmpty(mTime) && mTime > 0) {
                        return;
                    }
                    OtherUtils.setClickableView(mTv_et_register_code_countdown, 3);
                } else {
                    OtherUtils.setNoClickableView(mTv_et_register_code_countdown, 3);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        addDisposable(Observable.combineLatest(RxBindingUtils.textChanges(mEt_register_input_phone), RxBindingUtils.textChanges(mEt_register_input_code), RxBindingUtils.textChanges(mPsd_register_ok_et)
                , new Function3<CharSequence, CharSequence, CharSequence, Boolean>() {
                    @Override
                    public Boolean apply(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) throws Exception {
                        return charSequence.length() == 11 && charSequence2.length() == 6 && charSequence3.length() >= 6 && charSequence3.length() <= 20;
                    }
                }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Boolean aBoolean) throws Exception {
                if (aBoolean) {
                    OtherUtils.setClickableView(mTv_register, 22);
                } else {
                    OtherUtils.setNoClickableView(mTv_register, 22);
                }
            }
        }));
    }

    @Override
    protected RegisterPresenter createPresenter(IBaseView view) {
        return new RegisterPresenter(this);
    }

    @Override
    public void onRegisterSuccess(LoginInfo info) {
        ToastUtils.showShort("注册成功");
        this.finish();
        startActivity(new Intent(this, PhoneLoginActivity.class));
    }

    @Override
    public void onCodeSendSuccess() {
        ToastUtils.showShort("发送成功");
        addDisposable(RxJavaUtils.countDown(60, new BaseSubscriber<Long>() {
            @Override
            public void onError(RxException e) {

            }

            @Override
            protected void onStart() {
                super.onStart();
                OtherUtils.setNoClickableView(mTv_et_register_code_countdown, 3);
            }

            @Override
            public void onComplete() {
                super.onComplete();
                OtherUtils.setClickableView(mTv_et_register_code_countdown, 3);
                mTv_et_register_code_countdown.setText("重新获取");
                mTv_et_register_code_countdown.setTextColor(CommonUtils.getColor(R.color.cl_ffffff));
            }

            @Override
            public void onSuccess(Long aLong) {
                mTime = aLong;
                mTv_et_register_code_countdown.setText(String.format("(%ss)重新获取", aLong));
            }
        }));
    }
}
