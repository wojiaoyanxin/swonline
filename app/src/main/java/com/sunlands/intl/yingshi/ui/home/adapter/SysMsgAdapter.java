package com.sunlands.intl.yingshi.ui.home.adapter;

import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sunlands.comm_core.base.BaseViewImpl;
import com.sunlands.comm_core.helper.ApplicationsHelper;
import com.sunlands.comm_core.helper.RxBindingHelper;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.bean.SysMsgBean;
import com.sunlands.intl.yingshi.helper.SysMsgHelper;
import com.sunlands.intl.yingshi.helper.UpdataRequestHelper;
import com.sunlands.intl.yingshi.util.GlideUtils;

/**
 * @author yxin
 * @date 2019-10-22 - 16:23
 * @des
 */
public class SysMsgAdapter extends BaseQuickAdapter<SysMsgBean.ListBean, BaseViewHolder> {


    public SysMsgAdapter() {
        super(R.layout.item_sys_msg_layout);
    }

    @Override
    protected void convert(BaseViewHolder holder, SysMsgBean.ListBean mListBean) {


        holder.setText(R.id.tv_sys_time, mListBean.getCreated_at());
        holder.setText(R.id.tv_sys_msg_title, mListBean.getMessage().getTitle());
        holder.setText(R.id.tv_sys_msg_content, mListBean.getMessage().getContent());
        if (!TextUtils.isEmpty(mListBean.getMessage().getImg())) {
            holder.getView(R.id.iv_sys_msg).setVisibility(View.VISIBLE);
            GlideUtils.loadImage(ApplicationsHelper.context(), mListBean.getMessage().getImg(), holder.getView(R.id.iv_sys_msg));
        } else {
            holder.getView(R.id.iv_sys_msg).setVisibility(View.GONE);
        }
        if (TextUtils.isEmpty(mListBean.getMessage().getApp_url())) {
            holder.getView(R.id.tv_sys_item_details).setVisibility(View.GONE);
            holder.getView(R.id.iv_sys_item_arrows).setVisibility(View.GONE);
        } else {
            holder.getView(R.id.tv_sys_item_details).setVisibility(View.VISIBLE);
            holder.getView(R.id.iv_sys_item_arrows).setVisibility(View.VISIBLE);
        }
        holder.setVisible(R.id.message_dot, mListBean.is_read.equals("0"));
        RxBindingHelper.setOnClickListener(holder.getView(R.id.view_sys), new BaseViewImpl.OnClickListener() {
            @Override
            public void onClick(View v) {
                SysMsgHelper.jump(mListBean, mContext);
                new UpdataRequestHelper().actionRead(mListBean.user_message_id);
            }
        });
    }
}
