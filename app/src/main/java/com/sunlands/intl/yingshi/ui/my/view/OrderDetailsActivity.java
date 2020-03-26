package com.sunlands.intl.yingshi.ui.my.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.bean.MyOrderBean;
import com.sunlands.intl.yingshi.common.MyActivity;
import com.sunlands.intl.yingshi.ui.home.bean.SmallClassOrderBean;
import com.sunlands.intl.yingshi.ui.home.bean.SmallClassOrderDetailsBean;
import com.sunlands.intl.yingshi.ui.activity.home.smallclass.PayWebViewActivity;
import com.sunlands.intl.yingshi.ui.widget.countdown.CountdownTime;
import com.sunlands.intl.yingshi.ui.widget.countdown.CountdownView;
import com.sunlands.intl.yingshi.util.DensityUtil;
import com.sunlands.intl.yingshi.util.GlideUtils;

public class OrderDetailsActivity extends MyActivity<Object> {


    private MyOrderBean.ListBean orderDetails;
    public static String ORDER_DETAILS = "order_details";
    private CountdownView tvCountDown;
    private ImageView iv_img;
    private TextView tv_name;
    private TextView tv_price1;
    private TextView tv_price2;
    private TextView tv_price3;
    private TextView tv_orderno;
    private TextView tv_order_time;

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_details;
    }

    @Override
    public void initDataBeforeView() {
        super.initDataBeforeView();
        orderDetails = (MyOrderBean.ListBean) getIntent().getSerializableExtra(ORDER_DETAILS);
    }

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        super.findView(inflateView, savedInstanceState);
        tvCountDown = FBIA(R.id.tv_daojishi);
        iv_img = FBIA(R.id.iv_img);
        tv_name = FBIA(R.id.tv_name);
        tv_price1 = FBIA(R.id.tv_price1);
        tv_price2 = FBIA(R.id.tv_price2);
        tv_price3 = FBIA(R.id.tv_price3);
        tv_orderno = FBIA(R.id.tv_orderno);
        tv_order_time = FBIA(R.id.tv_order_time);
    }


    @Override
    public void onSuccess(Object bean) {
        super.onSuccess(bean);

        if (bean instanceof SmallClassOrderBean) {
            SmallClassOrderBean smallClassOrderBean = (SmallClassOrderBean) bean;
            String orderNumber = smallClassOrderBean.getOrderNumber();
            String payUrl = smallClassOrderBean.getPayUrl();
            Intent mIntent = new Intent(this, PayWebViewActivity.class);
            mIntent.putExtra("url", payUrl);
            mIntent.putExtra("title", "收银台");
            ActivityUtils.startActivity(mIntent);
        } else if (bean instanceof SmallClassOrderDetailsBean) {
            SmallClassOrderDetailsBean orderDetails = (SmallClassOrderDetailsBean) bean;
            tvCountDown.setVisibility(View.VISIBLE);
            int leftTime = orderDetails.getLeftTime();
            tvCountDown.setCountdownTime(leftTime, orderDetails.getOrderNo());
            tvCountDown.setTextColor();
            iv_img = FBIA(R.id.iv_img);
            GlideUtils.loadImage(this, orderDetails.getThumb(), iv_img, new RoundedCorners(DensityUtil.dip2px(this, 5)));
            tv_name = FBIA(R.id.tv_name);
            tv_name.setText(orderDetails.getName());
            tv_price1 = FBIA(R.id.tv_price1);
            tv_price1.setText("¥" + orderDetails.getAmount());
            tv_price2 = FBIA(R.id.tv_price2);
            tv_price2.setText("¥" + orderDetails.getAmount());
            tv_price3 = FBIA(R.id.tv_price3);
            tv_price3.setText("¥" + orderDetails.getAmount());
            tv_orderno = FBIA(R.id.tv_orderno);
            tv_orderno.setText("订单编号   " + orderDetails.getOrderNo());
            tv_order_time = FBIA(R.id.tv_order_time);
            tv_order_time.setText("下单时间   " + orderDetails.getCreatedAt());

            FBIA(R.id.ll_xieyi).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            FBIA(R.id.tv_pay).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getDataOnNet(orderDetails.getCourseId());
                }
            });
        }


    }

    @Override
    public String getTitleText() {
        return "订单详情";
    }

    @Override
    public void onResume() {
        super.onResume();
        mImmersionBar.fitsSystemWindows(false).transparentStatusBar().autoDarkModeEnable(false).navigationBarEnable(false).init();
    }


    @Override
    public void initDataAfterView() {
        super.initDataAfterView();
        getDataOnNet(orderDetails.getOrderNo(), "");
    }

    @Override
    public void initListener() {
        super.initListener();
        CountdownTime.onTimeCountdownOverListener = new CountdownTime.OnTimeCountdownOverListener() {
            @Override
            public void onTimeCountdownOver(String id) {
                //倒计时结束
            }
        };
    }
}
