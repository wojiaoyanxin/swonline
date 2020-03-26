package com.sunlands.intl.yingshi.ui.my.view;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.sunlands.comm_core.utils.CommonUtils;
import com.sunlands.intl.yingshi.BuildConfig;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.bean.EmptyBean;
import com.sunlands.intl.yingshi.common.CommonActivity;
import com.sunlands.intl.yingshi.constant.RestApi;
import com.sunlands.intl.yingshi.web.WebViewActivity;

public class AboutActivity extends CommonActivity<EmptyBean> {

    TextView mTvVersion;
    TextView tv_protocal;
    TextView tv_privacy;

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        super.findView(inflateView, savedInstanceState);
        mTvVersion = FBIA(R.id.tv_version);
        tv_protocal = FBIA(R.id.tv_protocal);
        tv_privacy = FBIA(R.id.tv_privacy);
    }


    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        super.initView(inflateView, savedInstanceState);
        mTvVersion.setText(String.format("v%s",BuildConfig.VERSION_NAME));
        tv_protocal.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        tv_protocal.getPaint().setAntiAlias(true);//抗锯齿
        tv_privacy.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        tv_privacy.getPaint().setAntiAlias(true);//抗锯齿
    }

    @Override
    public String getTitleText() {
        return CommonUtils.getString(R.string.about);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    public void initListener() {
        super.initListener();
        tv_protocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebViewActivity.goActivity(RestApi.PROTOCAL, "");
            }
        });
        tv_privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebViewActivity.goActivity(RestApi.PRIVACY, "");
            }
        });
    }
}
