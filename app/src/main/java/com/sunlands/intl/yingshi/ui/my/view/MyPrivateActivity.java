package com.sunlands.intl.yingshi.ui.my.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.sunlands.comm_core.helper.RxBindingHelper;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.common.CommonActivity;
import com.sunlands.intl.yingshi.ui.my.bean.PrivateBean;

public class MyPrivateActivity<T> extends CommonActivity<T> {

    ImageView toggle1;
    ImageView toggle2;
    ImageView toggle3;
    ImageView toggle4;


    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        super.findView(inflateView, savedInstanceState);
        toggle1 = FBIA(R.id.toggle1);
        toggle2 = FBIA(R.id.toggle2);
        toggle3 = FBIA(R.id.toggle3);
        toggle4 = FBIA(R.id.toggle4);
    }


    @Override
    public String getTitleText() {
        return "隐私";
    }

    @Override
    public void initListener() {
        super.initListener();
        RxBindingHelper.setOnClickListener(toggle1, this);
        RxBindingHelper.setOnClickListener(toggle2, this);
        RxBindingHelper.setOnClickListener(toggle3, this);
        RxBindingHelper.setOnClickListener(toggle4, this);
        RxBindingHelper.setOnClickListener(tvTitle, this);
    }

    @Override
    public void onSuccess(T obean) {


        if (obean instanceof PrivateBean) {

            PrivateBean bean = (PrivateBean) obean;

            int industrys = bean.getIndustry();
            int companys = bean.getCompany();
            int positions = bean.getPosition();
            int residences = bean.getResidence();
            if (industrys == 0) {
                industry = false;
            } else {
                industry = true;
            }
            if (companys == 0) {
                company = false;
            } else {
                company = true;
            }
            if (positions == 0) {
                position = false;
            } else {
                position = true;
            }
            if (residences == 0) {
                residence = false;
            } else {
                residence = true;
            }

            setIndustry(industry);
            setCompany(company);
            setPosition(position);
            setResidence(residence);
        }


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_private;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        int id = v.getId();
        if (id == R.id.toggle1) {
            setIndustry(industry);
            getDataNet(true, s);
        } else if (id == R.id.toggle2) {
            setCompany(company);
            getDataNet(true, s);
        } else if (id == R.id.toggle3) {
            setPosition(position);
            getDataNet(true, s);
        } else if (id == R.id.toggle4) {
            setResidence(residence);
            getDataNet(true, s);
        }
    }

    boolean position;
    boolean industry;
    boolean company;
    boolean residence;

    String s = null;


    public void setIndustry(boolean is) {

        if (is == true) {
            toggle1.setImageResource(R.drawable.button_open);
            industry = false;
            s = "{ \"industry\":1 }";
        } else {
            toggle1.setImageResource(R.drawable.button_close);
            industry = true;
            s = "{ \"industry\":0 }";
        }
    }

    public void setCompany(boolean is) {

        if (is == true) {
            toggle2.setImageResource(R.drawable.button_open);
            company = false;
            s = "{ \"company\":1 }";
        } else {
            toggle2.setImageResource(R.drawable.button_close);
            company = true;
            s = "{ \"company\":0 }";
        }
    }

    public void setPosition(boolean is) {

        if (is == true) {
            toggle3.setImageResource(R.drawable.button_open);
            position = false;
            s = "{ \"position\":1 }";
        } else {
            toggle3.setImageResource(R.drawable.button_close);
            position = true;
            s = "{ \"position\":0 }";
        }
    }

    public void setResidence(boolean is) {
        if (is == true) {
            toggle4.setImageResource(R.drawable.button_open);
            residence = false;
            s = "{ \"residence\":1 }";
        } else {
            toggle4.setImageResource(R.drawable.button_close);
            residence = true;
            s = "{ \"residence\":0 }";
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        getDataNet(true, "");
    }
}
