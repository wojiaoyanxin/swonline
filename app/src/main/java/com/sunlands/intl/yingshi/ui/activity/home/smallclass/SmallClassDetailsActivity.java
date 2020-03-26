package com.sunlands.intl.yingshi.ui.activity.home.smallclass;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sunlands.comm_core.utils.rx.RxBindingUtils;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.bean.event.EventMessage;
import com.sunlands.intl.yingshi.common.MyActivity;
import com.sunlands.intl.yingshi.constant.Constants;
import com.sunlands.intl.yingshi.dialog.DialogUtils;
import com.sunlands.intl.yingshi.dialog.SmallClassUnitsDialog;
import com.sunlands.intl.yingshi.ui.home.bean.SmallClassDetailsBean;
import com.sunlands.intl.yingshi.ui.home.bean.SmallClassOrderBean;
import com.sunlands.intl.yingshi.ui.home.bean.SmallClassUnitsBean;
import com.sunlands.intl.yingshi.util.GlideUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class SmallClassDetailsActivity extends MyActivity<Object> {


    private ImageView iv_bg;
    private ImageView iv_back;
    private TextView tv_class_title;
    private TextView tv_intro;
    private TextView tv_teacher;
    private TextView tv_show_price;
    private TextView tv_price;
    private TextView tv_jiesuo_all_class;
    private View rl_class_list;
    private int canView;
    private List<SmallClassUnitsBean.ListBean> list;
    private SmallClassDetailsBean smallClassDetailsBean;

    public static void show(Context context, String courseId) {
        Intent intent = new Intent(context, SmallClassDetailsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Constants.Key.KEY_COURSE_ID, courseId);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_small_class_details;
    }

    @Override
    public String getTitleText() {
        return "";
    }


    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        super.findView(inflateView, savedInstanceState);
        iv_back = FBIA(R.id.iv_back);
        rl_class_list = FBIA(R.id.rl_class_list);
        tv_jiesuo_all_class = FBIA(R.id.tv_jiesuo_all_class);
    }

    @Override
    public void initListener() {
        super.initListener();
        RxBindingUtils.setOnClickListener(iv_back, this);
        RxBindingUtils.setOnClickListener(rl_class_list, this);
        RxBindingUtils.setOnClickListener(tv_jiesuo_all_class, this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v == iv_back) {
            onBackPressed();
        } else if (v == rl_class_list) {
            if (list == null) {
                getDataOnNet(getIntent().getStringExtra(Constants.Key.KEY_COURSE_ID), "", "");
            } else {
                SmallClassUnitsDialog.getInstance(list,smallClassDetailsBean.getPrice()).show(getSupportFragmentManager(), "");
            }

        } else if (v == tv_jiesuo_all_class) {
            if (canView == 0) {
                getDataOnNet(getIntent().getStringExtra(Constants.Key.KEY_COURSE_ID), "");
            } else {
                //已经支付,跳转课程列表
            }
        }
    }


    @Override
    public void onSuccess(Object bean) {
        super.onSuccess(bean);
        if (bean instanceof SmallClassDetailsBean) {
            smallClassDetailsBean = (SmallClassDetailsBean) bean;
            canView = smallClassDetailsBean.getCanView();
            GlideUtils.loadImageFix(this, smallClassDetailsBean.getPoster(), iv_bg);
            tv_class_title.setText(smallClassDetailsBean.getTitle());
            tv_intro.setText(smallClassDetailsBean.getSubtitle());
            tv_price.setText("限时特价：¥" + smallClassDetailsBean.getPrice());
            tv_show_price.setText("原价:  ¥" + smallClassDetailsBean.getShowPrice() + "");
            tv_teacher.setText(smallClassDetailsBean.getTeacher());
            tv_jiesuo_all_class.setText(smallClassDetailsBean.getPrice() + "元立即解锁全部课程");
            setData(smallClassDetailsBean.getIntroImg(), false);
        } else if (bean instanceof SmallClassOrderBean) {
            if (smallClassDetailsBean == null) {
                return;
            }
            SmallClassOrderBean smallClassOrderBean = (SmallClassOrderBean) bean;
            String orderNumber = smallClassOrderBean.getOrderNumber();
            String payUrl = smallClassOrderBean.getPayUrl();
            if (smallClassOrderBean.getOrderStatus() == 10) {
                DialogUtils.go(this, "恭喜你，报名成功",new DialogUtils.onClick() {
                    @Override
                    public void sure() {
                        SmallClasListActivity.show(SmallClassDetailsActivity.this,
                                smallClassDetailsBean.getCourseId() + "",
                                smallClassDetailsBean.getTitle());
                        finish();
                    }
                });
                return;
            }
            Intent mIntent = new Intent(this, PayWebViewActivity.class);
            mIntent.putExtra(PayWebViewActivity.URL, payUrl);
            mIntent.putExtra(PayWebViewActivity.TITLE, smallClassDetailsBean.getTitle());
            mIntent.putExtra(PayWebViewActivity.COUESEID, smallClassDetailsBean.getCourseId() + "");
            ActivityUtils.startActivity(mIntent);
        } else if (bean instanceof SmallClassUnitsBean) {
            SmallClassUnitsBean smallClassUnitsBean = (SmallClassUnitsBean) bean;
            list = smallClassUnitsBean.getList();
            SmallClassUnitsDialog.getInstance(list,smallClassDetailsBean.getPrice()).show(getSupportFragmentManager(), "");
        }
    }

    @Override
    public void initDataAfterView() {
        super.initDataAfterView();
        getDataOnNet(getIntent().getStringExtra(Constants.Key.KEY_COURSE_ID));
    }

    @Override
    public void load() {
        super.load();
        getDataOnNet(getIntent().getStringExtra(Constants.Key.KEY_COURSE_ID));
    }

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        super.initView(inflateView, savedInstanceState);
        View view = getLayoutInflater().inflate(R.layout.header_small_class,
                (ViewGroup) mRecyclerView.getParent(), false);
        iv_bg = view.findViewById(R.id.iv_bg);
        tv_class_title = view.findViewById(R.id.tv_class_title);
        tv_intro = view.findViewById(R.id.tv_intro);
        tv_teacher = view.findViewById(R.id.tv_teacher);
        tv_show_price = view.findViewById(R.id.tv_show_price);
        tv_price = view.findViewById(R.id.tv_price);
        baseQuickAdapter.addHeaderView(view);
    }

    @Override
    public BaseQuickAdapter getAdapter() {
        return new SmallClassDetailsAdapter();
    }

    @Override
    public void onResume() {
        super.onResume();
        mImmersionBar.fitsSystemWindows(false).transparentStatusBar().autoDarkModeEnable(false).navigationBarEnable(false).init();
    }

    @Override
    public void inflateStateView(String tv, int imageId) {

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(EventMessage eventMessage) {
        if (eventMessage.getEventType() == EventMessage.EVENT_SMALL_PAY) {
            tv_jiesuo_all_class.performClick();
        }
    }
}
