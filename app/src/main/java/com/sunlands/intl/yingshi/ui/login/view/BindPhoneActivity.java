package com.sunlands.intl.yingshi.ui.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
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
import com.sunlands.intl.yingshi.helper.LoginInOutHelper;
import com.sunlands.intl.yingshi.ui.activity.MainActivity;
import com.sunlands.intl.yingshi.ui.login.ILoginContract;
import com.sunlands.intl.yingshi.ui.login.presenter.BindPhonePresenter;
import com.sunlands.intl.yingshi.util.OtherUtils;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

public class BindPhoneActivity extends MvpActivity<BindPhonePresenter> implements ILoginContract.IBindPhoneView {
    private ImageView mIv_title_back;
    private PowerfulEditText mEt_bind_phone_input_phone;
    private EditText mEt_bind_phone_input_code;
    private TextView mTv_bind_phone_countdown, mTv_bind_phone, mTv_title_right;
    public static final String KEY_UNIONID = "unionId";
    public static final String KEY_SEX = "sex";
    public static final String KEY_HEADURL = "headUrl";
    private String mUnionId, mSex, mHeadUrl;
    Long mTime;

    @Override
    public void onClick(View v) {
        if (v == mIv_title_back) {
            onBackPressed();
        } else if (v == mTv_bind_phone_countdown) {
            getPresenter().sendCode(0, CommonUtils.getStrFromView(mEt_bind_phone_input_phone));
        } else if (v == mTv_bind_phone) {
            getPresenter().bindPhone(CommonUtils.getStrFromView(mEt_bind_phone_input_phone), CommonUtils.getStrFromView(mEt_bind_phone_input_code), mUnionId, mSex, mHeadUrl);
        } else if (v == mTv_title_right) {
            if (!ObjectUtils.isEmpty(mTime) && mTime > 0) {
                com.blankj.utilcode.util.ToastUtils.showLong("请于秒" + mTime + "后再次获取");
            } else {
                TwoBtTitleDialog dialog = TwoBtTitleDialog.getInstance("语音验证码", "取消", "现在接听", "若您长时间未能收到短信验证码可通过电话方式获取请注意接听来电");
                dialog.setOnRightClick(new TwoBtTitleDialog.onRightClick() {
                    @Override
                    public void onRight() {
                        getPresenter().sendCode(1, CommonUtils.getStrFromView(mEt_bind_phone_input_phone));
                    }
                });
                dialog.show(getSupportFragmentManager(), null);
            }
        }
    }

    @Override
    public int getCreateViewLayoutId() {
        return R.layout.activity_bindphone;
    }

    @Override
    public void initDataBeforeView() {
        mUnionId = getIntent().getStringExtra(KEY_UNIONID);
        mSex = getIntent().getStringExtra(KEY_SEX);
        mHeadUrl = getIntent().getStringExtra(KEY_HEADURL);
    }

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        TextView tv_title_content = findViewById(R.id.tv_title_content);
        mIv_title_back = findViewById(R.id.iv_title_back);
        tv_title_content.setText("绑定手机");
        mEt_bind_phone_input_phone = findViewById(R.id.et_bind_phone_input_phone);
        mEt_bind_phone_input_code = findViewById(R.id.et_bind_phone_input_code);
        mTv_bind_phone_countdown = findViewById(R.id.tv_bind_phone_countdown);
        mTv_bind_phone = findViewById(R.id.tv_bind_phone);

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

    }

    @Override
    public void initListener() {
        RxBindingHelper.setOnClickListener(mTv_title_right, this);
        RxBindingHelper.setOnClickListener(mIv_title_back, this);
        RxBindingHelper.setOnClickListener(mTv_bind_phone_countdown, this);
        RxBindingHelper.setOnClickListener(mTv_bind_phone, this);

        addDisposable(Observable.combineLatest(RxBindingUtils.textChanges(mEt_bind_phone_input_phone), RxBindingUtils.textChanges(mEt_bind_phone_input_code), new BiFunction<CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean apply(CharSequence charSequence, CharSequence charSequence2) throws Exception {
                return charSequence.length() == 11 && charSequence2.length() == 6;
            }
        }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean) {
                    OtherUtils.setClickableView(mTv_bind_phone, 22);
                } else {
                    OtherUtils.setNoClickableView(mTv_bind_phone, 22);
                }
            }
        }));

        mEt_bind_phone_input_phone.addTextListener(new PowerfulEditText.TextListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {
                if (s.length() == 11) {
                    //正在倒计时不做处理
                    if (!ObjectUtils.isEmpty(mTime) && mTime > 0) {
                        return;
                    }
                    OtherUtils.setClickableView(mTv_bind_phone_countdown, 3);
                } else {
                    OtherUtils.setNoClickableView(mTv_bind_phone_countdown, 3);
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
    protected BindPhonePresenter createPresenter(IBaseView view) {
        return new BindPhonePresenter(this);
    }

    @Override
    public void onBindPhoneSuccess(LoginInfo info) {
        LoginInOutHelper.loginIn(info);
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onCodeSendSuccess() {
        addDisposable(RxJavaUtils.countDown(60, new BaseSubscriber<Long>() {
            @Override
            protected void onStart() {
                super.onStart();
                OtherUtils.setNoClickableView(mTv_bind_phone_countdown, 3);
            }

            @Override
            public void onComplete() {
                super.onComplete();
                OtherUtils.setClickableView(mTv_bind_phone_countdown, 3);
                mTv_bind_phone_countdown.setText("重新获取");
                mTv_bind_phone_countdown.setTextColor(CommonUtils.getColor(R.color.cl_ffffff));
            }

            @Override
            public void onError(RxException e) {

            }

            @Override
            public void onSuccess(Long aLong) {
                mTime = aLong;
                mTv_bind_phone_countdown.setText(String.format("(%ss)重新获取", aLong));
            }
        }));
    }
}
