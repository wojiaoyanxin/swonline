package com.sunlands.intl.yingshi.ui.home.view;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.common.CommonActivity;
import com.sunlands.intl.yingshi.ui.home.bean.AskAnswerDetailsBean;

public class AskAnswerDetailsActivity extends CommonActivity<AskAnswerDetailsBean> {


    public static String ANSWERID = "answerId";
    private TextView tv_title;
    private TextView tv_content;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ask_answer_details;
    }

    @Override
    public String getTitleText() {
        return "考研常识";
    }

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        super.findView(inflateView, savedInstanceState);
        tv_title = FBIA(R.id.tv_title_ask);
        tv_content = FBIA(R.id.et_content);
    }

    @Override
    public void initDataAfterView() {
        super.initDataAfterView();
        getDataNet(true, getIntent().getStringExtra(ANSWERID));
    }

    @Override
    public void onSuccess(AskAnswerDetailsBean bean) {
        super.onSuccess(bean);
        tv_content.setText(bean.getContent());
        tv_title.setText(bean.getTitle());
    }
}
