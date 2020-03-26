package com.sunlands.intl.yingshi.ui.home.view;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sunlands.comm_core.helper.RxBindingHelper;
import com.sunlands.comm_core.utils.CommonUtils;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.bean.SysMsgBean;
import com.sunlands.intl.yingshi.common.MyActivity;
import com.sunlands.intl.yingshi.helper.UpdataRequestHelper;
import com.sunlands.intl.yingshi.ui.home.adapter.SysMsgAdapter;

/**
 * 系统消息
 */
public class SysMsgActivity extends MyActivity<Object> {

    private TextView mTv_title_right;
    private SysMsgBean sysMsgBean;


    @Override
    public BaseQuickAdapter getAdapter() {
        return new SysMsgAdapter();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        if (v == mTv_title_right) {

            if (sysMsgBean.unread_num == 0) {
                ToastUtils.showShort("暂无未读消息");
                return;
            }
            new UpdataRequestHelper().actionRead("");

            for (Object listBean : baseQuickAdapter.getData()) {
                ((SysMsgBean.ListBean) listBean).is_read = "1";
            }
            baseQuickAdapter.notifyDataSetChanged();

        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_sys_msg;
    }

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        super.findView(inflateView, savedInstanceState);
        mTv_title_right = findViewById(R.id.tv_header_right);
        mTv_title_right.setText("全部已读");
        mTv_title_right.setVisibility(View.GONE);
        mTv_title_right.setTextColor(CommonUtils.getColor(R.color.cl_666666));
    }

    @Override
    public void onResume() {
        super.onResume();
        getDataOnNet(mNextPage);
    }

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        super.initView(inflateView, savedInstanceState);
        inflateStateView(CommonUtils.getString(R.string.no_message), R.drawable.iv_no_data_bg);
    }

    @Override
    public String getTitleText() {
        return "系统消息";
    }

    @Override
    public void initListener() {
        super.initListener();
        RxBindingHelper.setOnClickListener(mTv_title_right, this);
    }

    @Override
    public void onSuccess(Object bean) {
        super.onSuccess(bean);
        if (bean instanceof SysMsgBean) {
            sysMsgBean = (SysMsgBean) bean;
            setData(sysMsgBean.list, sysMsgBean.hasMore == 1);
            if (baseQuickAdapter.getData().size() > 0) {
                mTv_title_right.setVisibility(View.VISIBLE);
            } else {
                mTv_title_right.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void load() {
        super.load();
        getDataOnNet(mNextPage);
    }
}
